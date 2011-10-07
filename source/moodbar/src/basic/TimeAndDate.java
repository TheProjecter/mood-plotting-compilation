package basic;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class TimeAndDate {
	
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm";

	  public String getStamp() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());

	  }


}
