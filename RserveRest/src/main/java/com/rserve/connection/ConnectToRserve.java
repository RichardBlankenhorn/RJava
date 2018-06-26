package com.rserve.connection;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class ConnectToRserve implements Connection{

	@Override
	public RConnection initiateConnection(String filename) {
		RConnection connection = null;
		
		try {
			connection = new RConnection();
			
			connection.eval("setwd('/Users/richardblankenhorn/workspace/RserveProject')");
			String path = "source(" + "'" + filename + "')";
			connection.eval(path);
			
			
		} catch (RserveException e) {
			e.printStackTrace();
		} 
		return connection;
	}

	@Override
	public boolean killConnection(RConnection connection) {
		try {
			connection.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	

}
