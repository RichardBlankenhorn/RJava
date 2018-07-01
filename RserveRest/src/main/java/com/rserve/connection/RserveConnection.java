package com.rserve.connection;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RserveConnection {

	public String run() throws REngineException {
		RConnection connection = null;
		String result = "";

		try {
			connection = new RConnection();
			
			String pwd = connection.eval("getwd()").asString();
			connection.eval("setwd('/Users/richardblankenhorn/workspace/RserveProject')");
			pwd = connection.eval("getwd()").asString();
			connection.eval("source('rserveTest.R')");
			result = pwd;
			
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return result;

	}

}
