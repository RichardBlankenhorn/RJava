package com.rserve.connection;

import org.rosuda.REngine.Rserve.RConnection;

public interface Connection {
	
	public RConnection initiateConnection(String filename);
	
	public boolean killConnection(RConnection connection);

}
