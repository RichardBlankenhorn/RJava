package com.rserve.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.stereotype.Service;

import com.rserve.connection.ConnectToRserve;
import com.rserve.entities.DataFrame;

@Service
public class RserveServiceImpl implements RserveService {
	
//	@Autowired
//	ConnectToRserve connection;

	@Override
	public DataFrame getDataFrame(String df) throws REngineException {
		
		ConnectToRserve connection = new ConnectToRserve();
		RConnection rcon = connection.initiateConnection("rserveTest.R");
		DataFrame dataFrame = new DataFrame();
		
//		List<Map<String, List<Double>>> numericValues = new ArrayList<>();
//		List<Map<String, List<String>>> factorValues = new ArrayList<>();
		List<List<Object>> objValues = new ArrayList<>();
		
		try {
			
//			RList dataframe = rcon.parseAndEval("myWholeDF()").asList();
			String dsn = df;
			String path = "getDataFrame(" + "'" + dsn + "'" + ")";
			RList dataframe = rcon.parseAndEval(path).asList();
			
			List<String> names = new ArrayList<>();
			for(int i = 0; i < dataframe.names.size(); i++) {
				names.add(dataframe.names.get(i).toString());
				if(dataframe.at(i).isNumeric() && !dataframe.at(i).isFactor()) {
//					Map<String, List<Object>> numMap = new HashMap<>();
					List<Object> nums = new ArrayList<>();
					double[] doubleValues = dataframe.at(i).asDoubles();
					for(int j = 0; j < doubleValues.length; j++) {
						nums.add(doubleValues[j]);
					}
//					numMap.put(dataframe.names.get(i).toString(), nums);
//					numericValues.add(numMap);
					objValues.add(nums);
				}
				else if(dataframe.at(i).isFactor()) {
//					Map<String, List<Object>> factorMap = new HashMap<>();
					List<Object> factors = new ArrayList<>();
					String[] stringValues = dataframe.at(i).asStrings();
					for(int k = 0; k < stringValues.length; k++) {
						factors.add(stringValues[k]);
					}
//					factorMap.put(dataframe.names.get(i).toString(), factors);
//					factorValues.add(factorMap);
					objValues.add(factors);
				}
			}
			
			dataFrame.setNames(names);
			dataFrame.setObjValues(objValues);
//			dataFrame.setNumericValues(numericValues);
//			dataFrame.setFactorValues(factorValues);
			return dataFrame;
			
		} catch (RserveException | REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connection.killConnection(rcon);
		}
		return null;
	}

}
