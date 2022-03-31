package jp.ac.jc21.t.yoshizawa.servlet;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import jp.ac.jc21.t.yoshizawa.objectify.Toi;

public class EndPointToi extends GetGson {
	
	private static final String toiGetUrl = "https://fegogo.appspot.com/endpoint/v0/toi/get?ToiId=";

	public static final List<Toi> getToiList(Long toiId) {

		String toiListUrl = toiGetUrl + toiId;
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService("jp.ac.jc21.t.yoshizawa-Toi");
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

		Optional<List<Toi>> optToiList = Optional.ofNullable((List<Toi>) syncCache.get(toiListUrl));
		List<Toi> examList = null;
		if ((optToiList.isPresent()) && (optToiList.get().size() > 0)) {
			examList = optToiList.get();
		} else {
			examList = GetGson.ToiListFromGson(toiListUrl);
			syncCache.put(toiListUrl, examList);
		}
		return examList;
	}

}
