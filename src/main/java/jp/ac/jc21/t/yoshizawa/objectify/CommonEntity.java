/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

/**
 * @author yoshz
 *
 */
public class CommonEntity {


	public static List loadAll(	Class<?> c,String key) {

		return ofy().load().type(c).order(key).list();
	}
}
