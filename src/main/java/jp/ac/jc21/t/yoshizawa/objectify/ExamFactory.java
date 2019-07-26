/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;

import javax.cache.*;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 * @author t.yoshizawa
 *
 */
public abstract class ExamFactory extends CommonEntity {

	final static String cacheKeyName = "Exam";

	public static final Exam createExam(Long YYYYMM, String name) {
		Exam exam = new Exam();
		exam.setCreated(new Date());
		exam.setName(name);
		exam.newToiRefList();
		exam.setYYYYMM(YYYYMM);
		return exam;
	}

	public static final List<Exam> loadAll() {
		// https://cloud.google.com/appengine/docs/standard/java/memcache/examples?hl=ja

		List<Exam> exams = null;
		MemcacheService syncCache = getCache();

		if (isCached(syncCache, cacheKeyName) == false) {

			exams = loadAllFromOfy();
			saveExamstoCache(exams, syncCache);
			return exams;
		} else {
			exams = loadExamsFromCache(syncCache);
		}

		return exams;

	}

	private static boolean isCached(MemcacheService syncCache, String key) {
		return syncCache.get(key) != null;
	}

	private static List<Exam> loadExamsFromCache(MemcacheService syncCache) {

		Map<Long, Exam> examMap = (Map<Long, Exam>) syncCache.get(cacheKeyName);
		List<Exam> list = new ArrayList<Exam>();
		Set<Long> keyset = examMap.keySet();
		for (Long k : keyset) {
			list.add(examMap.get(k));
		}

		return (list);
	}

	private static void saveExamstoCache(List<Exam> exams, MemcacheService syncCache) {

		TreeMap<Long, Exam> examMap = new TreeMap<>();
		for (Exam e : exams) {
			examMap.put(e.getYYYYMM(), e);
		}

		syncCache.put(cacheKeyName, examMap);
	}

	private static final List<Exam> loadAllFromOfy() {
		return ofy().load().type(Exam.class).order("YYYYMM").list();
	}

	public static final Exam getById(long id) {
		return ofy().load().type(Exam.class).id(id).now();
	}

}
