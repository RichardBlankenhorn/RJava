package com.rserve.connection;

import java.io.IOException;

import org.rosuda.REngine.Rserve.RConnection;

public class StartRserve {
	
	public static void init() {
		try {
			invoke();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean isRunning = false;
		while (isRunning != true) {
			isRunning = isRserveRunning();
		}
		System.out.println(isRserveRunning());
	}

	public static void invoke() throws InterruptedException {
		String s;

		try {

			// run the Unix ""R CMD RServe --vanilla"" command
			// using the Runtime exec method:
			// Process p = Runtime.getRuntime().exec("
			// /Library/Frameworks/R.framework/Resources/bin/R CMD
			// /Library/Frameworks/R.framework/Versions/3.5/Resources/library/Rserve/libs//Rserve
			// --vanilla --RS-port 6311");
			Process p = Runtime.getRuntime().exec(
					" /Library/Frameworks/R.framework/Resources/bin/R CMD /Library/Frameworks/R.framework/Versions/3.3/Resources/library/Rserve/libs//Rserve --vanilla");

			// BufferedReader stdInput = new BufferedReader(new
			// InputStreamReader(p.getInputStream()));
			//
			// BufferedReader stdError = new BufferedReader(new
			// InputStreamReader(p.getErrorStream()));
			//
			// // read the output from the command
			// System.out.println("Here is the standard output of the command:\n");
			// while ((s = stdInput.readLine()) != null) {
			// System.out.println(s);
			// }
			//
			// // read any errors from the attempted command
			// System.out.println("Here is the standard error of the command (if any):\n");
			// while ((s = stdError.readLine()) != null) {
			// System.out.println(s);
			// }
			//
			// System.exit(0);
			////
			////// p.waitFor();

		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static void kill() {
		try {
			Process p = Runtime.getRuntime().exec("killall Rserve");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isRserveRunning() {
		try {
			RConnection c = new RConnection();
			System.out.println("Rserve is running.");
			c.close();
			return true;
		} catch (Exception e) {
			System.out.println("First connect try failed with: " + e.getMessage());
		}
		return false;
	}

}
