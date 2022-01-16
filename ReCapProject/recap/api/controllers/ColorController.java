package com.etiya.recap.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.recap.business.abstracts.ColorService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.concretes.Color;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

	private ColorService colorService;

	@Autowired
	public ColorController(ColorService colorService) {
		this.colorService = colorService;
	}

	@PostMapping("/addcolor")
	public Result addColor(Color color) {
		return this.colorService.add(color);
	}

	@GetMapping("/getallcolors")
	public DataResult<List<Color>> getAllColors() {
		return this.colorService.getAll();
	}
	
	@GetMapping("/getcolorbyid")
	public DataResult<Color> getColorById(int id) {
		return this.colorService.getById(id);
	}
	
	@DeleteMapping("/removecolor")
	public Result removeColor(Color color) {
		return this.colorService.delete(color);
	}
	
	@PostMapping("/updatecolor")
	public Result updateColor(Color color) {
		return this.colorService.update(color);
	}

}
