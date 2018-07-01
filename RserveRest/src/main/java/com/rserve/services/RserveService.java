package com.rserve.services;

import java.util.List;

import org.rosuda.REngine.REngineException;

import com.rserve.entities.DataFrame;

public interface RserveService {
	
	DataFrame getDataFrame(String df) throws REngineException;
	
	DataFrame getSumStats(String df) throws REngineException;
	
	DataFrame getLinModel(String df, String respVar) throws REngineException;
	
	List<String> getVarNames(String df);

}
