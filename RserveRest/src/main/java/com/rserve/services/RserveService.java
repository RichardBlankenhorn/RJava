package com.rserve.services;

import org.rosuda.REngine.REngineException;

import com.rserve.entities.DataFrame;

public interface RserveService {
	
	DataFrame getDataFrame(String df) throws REngineException;
	
	DataFrame getSumStats(String df) throws REngineException;

}
