package com.rserve.connection;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RserveConnection {
	
//	public static void main(String[] args) {
//		RserveConnection serve = new RserveConnection();
//		try {
//			serve.run();
//		} catch (REngineException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		serve.runScript();
//
//	}

	public String run() throws REngineException {
		RConnection connection = null;
		String result = "";

		try {
			connection = new RConnection();

//			String vector = "c(1,2,3,4)";
//			connection.eval("meanVal=mean(" + vector + ")");
//			double mean = connection.eval("meanVal").asDouble();
//			System.out.println("The mean of given vector is=" + mean);
			
			String pwd = connection.eval("getwd()").asString();
//			System.out.println(pwd);
			connection.eval("setwd('/Users/richardblankenhorn/workspace/RserveProject')");
			pwd = connection.eval("getwd()").asString();
//			System.out.println(pwd);
			connection.eval("source('rserveTest.R')");
			result = pwd;
			
//			int sum = connection.eval("myAdd("+5+","+10+")").asInteger();
//			System.out.println(sum);
			
//			REXP result = connection.parseAndEval("myAdd("+5+","+10+")");
//			System.out.println(result.length());
//			double[] tst = result.asDoubles();
//			
//			for(int i = 0; i < result.length(); i++) {
//				System.out.println(tst[i]);
//			}
//			
//			REXP data = connection.parseAndEval("myDF()");
//			System.out.println(data.length());
//			double[] db = data.asDoubles();
//			
//			for(int j = 0; j < db.length; j++) {
//				System.out.println(db[j]);
//			}
//			
//			RList dataframe = connection.parseAndEval("myWholeDF()").asList();
//			System.out.println(dataframe.at(0));
//			System.out.println(dataframe.names);
//			double[] first = dataframe.at(0).asDoubles();
//			
//			for (double d : first) {
//				System.out.println(d);
//			}
//			
//			RList myList = connection.parseAndEval("myList()").asList();
//			System.out.println(myList.names);
			
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
