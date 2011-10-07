package writer;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.TreeMap;

import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.constraint.Unique;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import basic.MoodBean;

public class FileRead {
	
	public static TreeMap<Date, Object[]> readNewLine() throws IOException {
		
		ICsvBeanReader stream = new CsvBeanReader(new FileReader("moody-out.csv"), CsvPreference.EXCEL_PREFERENCE);
		TreeMap<Date, Object[]> mappy = new TreeMap<Date, Object[]>();
		
		try {
			
			//final String[] header = new String[] {"date", "happiness", "energy", "anger", "trust", "balance", "description"};
			final String[] header = stream.getCSVHeader(true);
			MoodBean moodz = new MoodBean();
			
			while((moodz = stream.read(MoodBean.class, header, userProcessors)) != null) {
				
				mappy.put(moodz.getDate(), new Object[] {
					moodz.getHappiness(),
					moodz.getEnergy(),
					moodz.getAnger(),
					moodz.getTrust(),
					moodz.getBalance(),
					moodz.getDescription()
				});
				
			}
			
		} finally {
			
			stream.close();
			
		}
		
		// return HashMap
		System.out.println(mappy);
		return mappy;
		
	}
	
	static final CellProcessor[] userProcessors = new CellProcessor[] {
        new Unique(new ParseDate("yyyy-MM-dd HH:mm")),
        new ParseInt(),
        new ParseInt(),
        new ParseInt(),
        new ParseInt(),
        new ParseInt(),
        new StrMinMax(0,120)
    };

}
