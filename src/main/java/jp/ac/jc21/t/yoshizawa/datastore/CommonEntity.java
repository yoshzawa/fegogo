/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.datastore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;



/**
 * @author yoshz
 *
 */
public class CommonEntity {
	
	public static final String getDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return sdf.format(date);
	}
	final public static String ofyVersion = "1.90";
}
