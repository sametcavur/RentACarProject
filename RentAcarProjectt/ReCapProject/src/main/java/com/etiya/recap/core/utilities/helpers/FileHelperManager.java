package com.etiya.recap.core.utilities.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.etiya.recap.entities.requests.carImageRequests.CreateCarImagesRequest;
import com.etiya.recap.entities.requests.carImageRequests.UpdateCarImagesRequest;

@Service
public class FileHelperManager implements FileHelperService{

	@Override
	public  void createCarImagePathName(CreateCarImagesRequest createCarImagesRequest,String imagePathName) throws IOException {
		File myFile = new File("C:\\Users\\samet.cavur\\sts4workspace\\ReCapProject\\images\\" + imagePathName + "." + createCarImagesRequest.getFile().getContentType().toString().substring(6));
		myFile.createNewFile();
		FileOutputStream fileOutputStream = new FileOutputStream(myFile);
		fileOutputStream.write(createCarImagesRequest.getFile().getBytes());
		fileOutputStream.close();
	}
	@Override
	public void updateCarImagePathName(UpdateCarImagesRequest updateCarImagesRequest, String imagePathName)
			throws IOException {
	
		File myFile = new File("C:\\Users\\samet.cavur\\sts4workspace\\ReCapProject\\images\\" + imagePathName + "." + updateCarImagesRequest.getFile().getContentType().toString().substring(6));
		myFile.createNewFile();
		FileOutputStream fileOutputStream = new FileOutputStream(myFile);
		fileOutputStream.write(updateCarImagesRequest.getFile().getBytes());
		fileOutputStream.close();
		
	}
	
	

}
