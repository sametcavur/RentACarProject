package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.ColorService;
import com.etiya.recap.business.constants.messages.ColorMessages;
import com.etiya.recap.core.utilities.business.BusinessRules;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.ErrorResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.ColorDao;
import com.etiya.recap.entities.concretes.Color;
import com.etiya.recap.entities.dtos.ColorDto;
import com.etiya.recap.entities.requests.colorRequests.CreateColorRequest;
import com.etiya.recap.entities.requests.colorRequests.DeleteColorRequest;
import com.etiya.recap.entities.requests.colorRequests.UpdateColorRequest;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	private final ModelMapper modelMapper;

	@Autowired
	public ColorManager(ColorDao colorDao,ModelMapper modelMapper) {
		this.colorDao = colorDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<ColorDto>> getAll() {
		List<Color> colors = this.colorDao.findAll();
		List<ColorDto> colorDtos = colors.stream().map(color -> modelMapper.map(color, ColorDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<>(colorDtos, ColorMessages.GetAll);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {

		var result = BusinessRules.run(checkColorNameDuplication(createColorRequest.getColorName()));

		if (result != null) {
			return result;
		}
		Color color=new Color();
		color.setColorName(createColorRequest.getColorName());

		this.colorDao.save(color);
		return new SuccessResult(true, ColorMessages.Add);
	}

	@Override
	public DataResult<ColorDto> getById(int id) {
		Color color = this.colorDao.getById(id);
		ColorDto colorDto = modelMapper.map(color, ColorDto.class);
		return new SuccessDataResult<>(colorDto, ColorMessages.GetById);
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		Color color=new Color();
		color.setColorId(deleteColorRequest.getId());

		this.colorDao.delete(color);
		return new SuccessResult(true,  ColorMessages.Delete);

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		var result = BusinessRules.run(checkColorNameDuplication(updateColorRequest.getColorName()));

		if (result != null) {
			return result;
		}

		Color color=this.colorDao.getById(updateColorRequest.getId());
		color.setColorName(updateColorRequest.getColorName());
		this.colorDao.save(color);
		return new SuccessResult(true,  ColorMessages.Update);
	}

	private Result checkColorNameDuplication(String colorName) {
		if (this.colorDao.existsColorBycolorName(colorName)) {
			return new ErrorResult(ColorMessages.ErrorCheckColorName);
		}

		return new SuccessResult();
	}

}
