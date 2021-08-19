/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 * @author yoshz
 *
 */
public class CommonEntity {

	public final static List<?> loadAll(Class<?> c, String key) {
		return ofy().load().type(c).order(key).list();
	}

	public final static List<?> loadAll(Class<?> c) {
		return ofy().load().type(c).list();
	}

	public static final Object getById(Class<?> c, long id) {
		return ofy().load().type(c).id(id).now();
	}
	public final static List<?> loadByIndex(Class<?> c , String index,String value) {
		return ofy().load().type(c).filter(index, value).list();
	}
	public final static List<?> loadByIndex(Class<?> c , String index,Long value) {
		return ofy().load().type(c).filter(index, value).list();
	}

	protected static final MemcacheService getCache() {
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
		return syncCache;
	}
	protected static final boolean isCached(MemcacheService syncCache, String key) {
		return syncCache.get(key) != null;
	}
	
	public static final String getDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return sdf.format(date);
	}
	final public static String ofyVersion = "4.00.7";
}
