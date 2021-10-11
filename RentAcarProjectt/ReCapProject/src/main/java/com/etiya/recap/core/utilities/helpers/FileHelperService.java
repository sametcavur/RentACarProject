package com.etiya.recap.core.utilities.helpers;

import java.io.IOException;

import com.etiya.recap.entities.requests.carImageRequests.CreateCarImagesRequest;
import com.etiya.recap.entities.requests.carImageRequests.UpdateCarImagesRequest;


public interface FileHelperService {
	
	  void  createCarImagePathName(CreateCarImagesRequest createCarImagesRequest,String imagePathName) throws IOException;
	  
	  void  updateCarImagePathName(UpdateCarImagesRequest updateCarImagesRequest,String imagePathName) throws IOException;


}
