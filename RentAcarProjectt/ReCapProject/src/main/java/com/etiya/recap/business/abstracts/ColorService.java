package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.ColorDto;
import com.etiya.recap.entities.requests.colorRequests.CreateColorRequest;
import com.etiya.recap.entities.requests.colorRequests.DeleteColorRequest;
import com.etiya.recap.entities.requests.colorRequests.UpdateColorRequest;

public interface ColorService {
	
	DataResult<List<ColorDto>> getAll();
	
	DataResult<ColorDto> getById(int id);
	
	Result add(CreateColorRequest createColorRequest);
	
	Result delete(DeleteColorRequest deleteColorRequest);
	
	Result update(UpdateColorRequest updateColorRequest);

}
