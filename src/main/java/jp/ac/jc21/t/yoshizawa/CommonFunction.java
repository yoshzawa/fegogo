package jp.ac.jc21.t.yoshizawa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

public class CommonFunction {
	public static  final String dateFormat(Date d){	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");	
	    TimeZone timeZoneJP = TimeZone.getTimeZone("Asia/Tokyo");
	    sdf.setTimeZone(timeZoneJP);
	    try {
			return sdf.format(d);		    	
	    } catch (NullPointerException e) {
	    	return "null";
	    }
	}
	
	public static  final String toWebDateString(Date d){	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    TimeZone timeZoneJP = TimeZone.getTimeZone("Asia/Tokyo");
    sdf.setTimeZone(timeZoneJP);
    try {
		return sdf.format(d);		    	
    } catch (NullPointerException e) {
    	return "null";
    }
	}
    
	public static  final Optional<Date> toDate(String s){	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	    TimeZone timeZoneJP = TimeZone.getTimeZone("Asia/Tokyo");
	    sdf.setTimeZone(timeZoneJP);
	    try {
	    	return Optional.ofNullable(sdf.parse(s));	
	    } catch(ParseException e) {
	    	return Optional.empty();
	    }
	}
	
	public static final String changePoint(int seikai , int answer){	
	float point = (100.0f * seikai / answer);	
	return String.format("%1$.1f", point);	
	}		
	final public static String ofyVersion = "4.03.01";

}
