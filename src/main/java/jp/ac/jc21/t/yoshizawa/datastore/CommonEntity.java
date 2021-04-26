/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.datastore;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.cloud.Timestamp;


/**
 * @author yoshz
 *
 */
public class CommonEntity {
	
	public static final String getDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return sdf.format(date);
	}
	final public static String ofyVersion = "4.00.1";
	
	final static Timestamp DateToTimestamp(Date d) {
		Timestamp t = Timestamp.of(d);
		return t;
	}
	final static Date TimestampToDate( Timestamp t) {
		Date d = t.toDate();
		return d;
	}

}
