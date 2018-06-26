package com.rserve;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import com.rserve.connection.ConnectToRserve;
import com.rserve.connection.RserveConnection;
import com.rserve.connection.StartRserve;
import com.rserve.entities.DataFrame;
import com.rserve.services.RserveServiceImpl;

class ConnectionTest {

	@BeforeEach
	void setUp() throws Exception {
		StartRserve.init();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		StartRserve.kill();
	}

	@Test
	void test() throws REngineException {
		RserveConnection conn = new RserveConnection();
		String result = conn.run();
		assertEquals("/Users/richardblankenhorn/Desktop", result);
	}
	
	@Test
	void test_connection_by_providing_filename() throws RserveException, REXPMismatchException {
		ConnectToRserve connection = new ConnectToRserve();
		RConnection rcon = connection.initiateConnection("rserveTest.R");
		int sum = rcon.eval("myAdd("+5+","+10+")").asInteger();
		assertEquals(15, sum);
	}
	
	@Test
	void test_dataframe_connection_and_evaluation() {
		RserveServiceImpl impl = new RserveServiceImpl();
		try {
			DataFrame frame = impl.getDataFrame("auto");
			System.out.println(frame.getNames());
			
		} catch (REngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
