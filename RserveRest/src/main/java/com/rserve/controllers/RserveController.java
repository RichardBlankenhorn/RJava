package com.rserve.controllers;

import java.util.List;

import org.rosuda.REngine.REngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(path = "/getDF/{df}", method = RequestMethod.GET)
	public DataFrame getSelectedDF(@PathVariable String df) {
		try {
			return service.getDataFrame(df);
		} catch (REngineException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(path = "/getDF/{df}/stats", method = RequestMethod.GET)
	public DataFrame getSumStats(@PathVariable String df) {
		try {
			return service.getSumStats(df);
		} catch (REngineException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path = "/getLinModel/{df}/response/{respVar}", method = RequestMethod.GET)
	public DataFrame getLinModel(@PathVariable String df, @PathVariable String respVar) {
		try {
			return service.getLinModel(df, respVar);
		} catch (REngineException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path = "/getVarNames/{df}", method = RequestMethod.GET)
	public List<String> getVarNames(@PathVariable String df) {
		return service.getVarNames(df);
	}

}
