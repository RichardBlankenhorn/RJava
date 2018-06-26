package com.rserve.controllers;

import org.rosuda.REngine.REngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rserve.entities.DataFrame;
import com.rserve.services.RserveService;

@RestController
@CrossOrigin({ "*", "http://localhost:4200" })
@RequestMapping(path = "/api")
public class RserveController {
	
	@Autowired
	private RserveService service;
	
	@RequestMapping(path = "/dataframe", method = RequestMethod.GET)
	public DataFrame index() {
		try {
			return service.getDataFrame("df");
		} catch (REngineException e) {
			e.printStackTrace();
			return null;
		}
	}

}