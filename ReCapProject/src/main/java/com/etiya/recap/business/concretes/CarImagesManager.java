package com.etiya.recap.business.concretes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.recap.business.abstracts.CarImagesService;
import com.etiya.recap.business.constants.messages.CarImagesMessages;
import com.etiya.recap.core.utilities.business.BusinessRules;
import com.etiya.recap.core.utilities.helpers.FileHelperService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.ErrorDataResult;
import com.etiya.recap.core.utilities.results.ErrorResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.CarDao;
import com.etiya.recap.dataAccess.abstracts.CarImagesDao;
import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.concretes.CarImages;
import com.etiya.recap.entities.dtos.CarImageDto;
import com.etiya.recap.entities.requests.carImageRequests.CreateCarImagesRequest;
import com.etiya.recap.entities.requests.carImageRequests.DeleteCarImagesRequest;

@Service
public class CarImagesManager implements CarImagesService {

	private CarImagesDao carImagesDao;
	private FileHelperService fileHelperService;
	private CarDao carDao;
	private final ModelMapper modelMapper;

	@Autowired
	public CarImagesManager(CarImagesDao carImagesDao,FileHelperService fileHelperService,ModelMapper modelMapper,CarDao carDao) {
		this.carImagesDao = carImagesDao;
		this.fileHelperService=fileHelperService;
		this.modelMapper = modelMapper;
		this.carDao = carDao;
	}

	@Override
	public DataResult<List<CarImageDto>> getAll() {
		List<CarImages> carImages = this.carImagesDao.findAll();
		List<CarImageDto> carImageDtos = carImages.stream().map(carimage -> modelMapper.map(carimage, CarImageDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<>(carImageDtos, CarImagesMessages.GetAll);
	}

	@Override
	public Result add(CreateCarImagesRequest createCarImagesRequest) throws IOException {

		var result = BusinessRules.run(checkIfCarHasMoreThanFiveImages(createCarImagesRequest.getCarId(), 5),
				checkImageIsNullOrCheckImageTypeIsWrong(createCarImagesRequest.getFile()));
		if (result != null) {
			return result;
		}

		CarImages carImages = new CarImages();
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());

		String imageNameRandom = UUID.randomUUID().toString();
		fileHelperService.createCarImagePathName(createCarImagesRequest, imageNameRandom);


		Car car = this.carDao.getById(createCarImagesRequest.getCarId());

		carImages.setDate(dateNow);
		carImages.setCar(car);
		carImages.setImagePath(imageNameRandom);

		this.carImagesDao.save(carImages);
		return new SuccessResult(true, CarImagesMessages.Add);
	}

	@Override
	public DataResult <List<CarImageDto>> getByCarId(int carId) {
		List<CarImages> carImages = this.returnCarImageWithDefaultImageIfCarImageIsNull(carId).getData();
		List<CarImageDto> carImageDtos = carImages.stream().map(carimage -> modelMapper.map(carimage, CarImageDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<>(carImageDtos, CarImagesMessages.GetById);
	}

	@Override
	public Result delete(DeleteCarImagesRequest deleteCarImagesRequest) {

		CarImages carImages = new CarImages();
		carImages.setId(deleteCarImagesRequest.getId());

		this.carImagesDao.delete(carImages);
		return new SuccessResult(true, CarImagesMessages.Delete);
	}


	//Arabanın 5'ten fazla resmi var mı kontrolü
	private Result checkIfCarHasMoreThanFiveImages(int carId, int limit) {
		if (this.carImagesDao.countByCar_id(carId) >= limit) {
			return new ErrorResult(CarImagesMessages.ErrorIfCarHasMoreImages);
		}
		return new SuccessResult();
	}

	//Arabanın resmi boş mu yada format yanlış mı kontrolü
	private Result checkImageIsNullOrCheckImageTypeIsWrong(MultipartFile file) throws IOException {
		if (file == null) {
			return new ErrorResult(CarImagesMessages.ErrorCarImageNull);
		}
		if(!file.getOriginalFilename().endsWith("jpg") &&(!file.getOriginalFilename().endsWith("png")  && !file.getOriginalFilename().endsWith("jpeg")))
		{
			return new ErrorResult(CarImagesMessages.ErrorCarImageType);
		}
          return new SuccessResult();
	}

	//Eğer arabanın resmi yoksa default resim atama
	private DataResult<List<CarImages>> returnCarImageWithDefaultImageIfCarImageIsNull(int carId) {

		if (this.carImagesDao.existsByCar_id(carId)) {
			return new ErrorDataResult<>(this.carImagesDao.getByCar_id(carId));
		}

		List<CarImages> carImages = new ArrayList<>();
		CarImages carImage = new CarImages();
		carImage.setImagePath("C:\\Users\\samet.cavur\\sts4workspace\\ReCapProject\\images\\default.jpg");

		carImages.add(carImage);

		return new SuccessDataResult<>(carImages, CarImagesMessages.GetAll);
	}
}
