package writer;

import java.io.*;

public class FileWrite {
	
	public void doWrite(String text) {
		
		String FILE = "moody-out.csv";
		
		try{
			// check existence
			File f = new File(FILE);
			boolean writeHeader = false;
			if(!f.exists()) {
				writeHeader = true;
			}
		    // Create file 
		    FileWriter fstream = new FileWriter(FILE, true);
		    BufferedWriter out = new BufferedWriter(fstream);
		    if(writeHeader) {
		    	out.write("Date,Happiness,Energy,Anger,Trust,Balance,Description\n");
		    }
		    out.write(text);
		    //Close the output stream
		    out.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
	}

}
