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

	public final static List loadAll(Class<?> c, String key) {
		return ofy().load().type(c).order(key).list();
	}

	public final static List loadAll(Class<?> c) {
		return ofy().load().type(c).list();
	}

	public static Object getById(Class<?> c, long id) {
		return ofy().load().type(c).id(id).now();
	}

}
