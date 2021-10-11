package com.etiya.recap.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.recap.business.abstracts.ColorService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.ColorDto;
import com.etiya.recap.entities.requests.colorRequests.CreateColorRequest;
import com.etiya.recap.entities.requests.colorRequests.DeleteColorRequest;
import com.etiya.recap.entities.requests.colorRequests.UpdateColorRequest;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

	private ColorService colorService;

	@Autowired
	public ColorController(ColorService colorService) {
		this.colorService = colorService;
	}

	@PostMapping("/addcolor")
	public ResponseEntity<?> addColor(@Valid @RequestBody CreateColorRequest createColorRequest) {
		return ResponseEntity.ok(this.colorService.add(createColorRequest)) ;
	}

	@GetMapping("/getallcolors")
	public DataResult<List<ColorDto>> getAllColors() {
		return this.colorService.getAll();
	}
	
	@GetMapping("/getcolorbyid")
	public DataResult<ColorDto> getColorById(int id) {
		return this.colorService.getById(id);
	}
	
	@DeleteMapping("/removecolor")
	public Result removeColor(DeleteColorRequest deleteColorRequest) {
		return this.colorService.delete(deleteColorRequest);
	}
	
	@PostMapping("/updatecolor")
	public ResponseEntity<?> updateColor(@Valid @RequestBody  UpdateColorRequest updateColorRequest) {
		return ResponseEntity.ok(this.colorService.update(updateColorRequest));
	}
}
