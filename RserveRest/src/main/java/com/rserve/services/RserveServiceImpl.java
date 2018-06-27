package com.rserve.services;

import java.util.ArrayList;
import java.util.List;

import org.rosuda.REngine.REXP;
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

	@Override
	public DataFrame getDataFrame(String df) throws REngineException {

		ConnectToRserve connection = new ConnectToRserve();
		RConnection rcon = connection.initiateConnection("rserveTest.R");
		DataFrame dataFrame = new DataFrame();

		List<List<Object>> objValues = new ArrayList<>();

		try {

			String dsn = df;
			String path = "getDataFrame(" + "'" + dsn + "'" + ")";
			RList dataframe = rcon.parseAndEval(path).asList();

			List<String> names = new ArrayList<>();
			for (int i = 0; i < dataframe.names.size(); i++) {
				names.add(dataframe.names.get(i).toString());
				if (dataframe.at(i).isNumeric() && !dataframe.at(i).isFactor()) {
					List<Object> nums = new ArrayList<>();
					double[] doubleValues = dataframe.at(i).asDoubles();
					for (int j = 0; j < doubleValues.length; j++) {
						nums.add(doubleValues[j]);
					}

					objValues.add(nums);
				} else if (dataframe.at(i).isFactor()) {
					List<Object> factors = new ArrayList<>();
					String[] stringValues = dataframe.at(i).asStrings();
					for (int k = 0; k < stringValues.length; k++) {
						factors.add(stringValues[k]);
					}
					objValues.add(factors);
				}
			}

			dataFrame.setNames(names);
			dataFrame.setObjValues(objValues);
			return dataFrame;

		} catch (RserveException | REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connection.killConnection(rcon);
		}
		return null;
	}

	@Override
	public DataFrame getSumStats(String df) throws REngineException {

		ConnectToRserve connection = new ConnectToRserve();
		RConnection rcon = connection.initiateConnection("rserveTest.R");
		DataFrame dataFrame = new DataFrame();

		List<List<Object>> objValues = new ArrayList<>();

		try {

			String dsn = df;
			String path = "getSummaryStatsNumeric(" + "'" + dsn + "'" + ")";
			RList dataframe = rcon.parseAndEval(path).asList();
			
			REXP sumStat = (REXP) dataframe.get(0);
			REXP strs = (REXP) dataframe.get(1);
			
			RList statList = sumStat.asList();
			String[] strings = strs.asStrings();

			List<String> names = new ArrayList<>();
			names.add("Statistic");
			
			List<Object> statStrings = new ArrayList<>();
			for (int l = 0; l < strings.length; l++) {
				statStrings.add(strings[l]);
			}
			
			objValues.add(statStrings);
			
			for (int i = 0; i < statList.names.size(); i++) {
				names.add(statList.names.get(i).toString());
				if (statList.at(i).isNumeric() && !statList.at(i).isFactor()) {
					List<Object> nums = new ArrayList<>();
					double[] doubleValues = statList.at(i).asDoubles();
					for (int j = 0; j < doubleValues.length; j++) {
						nums.add(doubleValues[j]);
					}

					objValues.add(nums);
				} else if (statList.at(i).isFactor()) {
					List<Object> factors = new ArrayList<>();
					String[] stringValues = statList.at(i).asStrings();
					for (int k = 0; k < stringValues.length; k++) {
						factors.add(stringValues[k]);
					}
					objValues.add(factors);
				}
			}

			dataFrame.setNames(names);
			dataFrame.setObjValues(objValues);
			return dataFrame;

		} catch (RserveException | REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connection.killConnection(rcon);
		}

		return null;
	}

}
