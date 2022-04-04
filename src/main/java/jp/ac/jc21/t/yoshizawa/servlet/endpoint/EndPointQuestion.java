package jp.ac.jc21.t.yoshizawa.servlet.endpoint;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

public class EndPointQuestion extends GetGson {
	public static final List<Long> getQuestionListBy(String examListUr) {
		return getLongList(examListUr,examListUr);
	}
}
