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
			
			connection.eval("setwd('/Users/richardblankenhorn/Desktop')");
			String pwd = connection.eval("getwd()").asString();
			System.out.println(pwd);
			String path = "source(" + "'" + filename + "')";
			System.out.println(path);
			connection.eval(path);
			//connection.eval("source('rserveTest.R')");
			
			
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
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
