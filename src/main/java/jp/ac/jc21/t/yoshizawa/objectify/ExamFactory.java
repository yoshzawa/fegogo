/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.appengine.api.memcache.MemcacheService;

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

	public static final Map<Long, Exam> loadAll() {
		// https://cloud.google.com/appengine/docs/standard/java/memcache/examples?hl=ja

		List<Exam> exams = null;
		MemcacheService syncCache = getCache();

		if (isCached(syncCache, cacheKeyName) == false) {
			exams = loadAllFromOfy();
			saveExamstoCache(exams, syncCache);
		}
		return loadExamsFromCache(syncCache);
	}

	private static final boolean isCached(MemcacheService syncCache, String key) {
		return syncCache.get(key) != null;
	}

	@SuppressWarnings("unchecked")
	private static final Map<Long, Exam> loadExamsFromCache(MemcacheService syncCache) {

		Map<Long, Exam> examMap = (Map<Long, Exam>) syncCache.get(cacheKeyName);
		for (Long key : examMap.keySet()) {
			Exam e = examMap.get(key);
			e.convertFromCache();
			examMap.put(key, e);
		}

		return (examMap);
	}

	@SuppressWarnings("unchecked")
	private static final Map<Long, Exam> loadExamsKeyFromCache(MemcacheService syncCache) {

		Map<Long, Exam> examMap = (Map<Long, Exam>) syncCache.get(cacheKeyName + "key");
		for (Long key : examMap.keySet()) {
			Exam e = examMap.get(key);
			e.convertFromCache();
			examMap.put(key, e);
		}

		return (examMap);
	}

	private static final void saveExamstoCache(List<Exam> exams, MemcacheService syncCache) {

		TreeMap<Long, Exam> examMap = new TreeMap<>();
		TreeMap<Long, Exam> examMapKey = new TreeMap<>();
		for (Exam e : exams) {
			e.convertForCache();
			examMapKey.put(e.getId(), e);
			examMap.put(e.getYYYYMM(), e);
		}

		syncCache.put(cacheKeyName, examMap);
		syncCache.put(cacheKeyName + "key", examMapKey);
//		return examMap;
	}

	private static final List<Exam> loadAllFromOfy() {
		List<Exam> list = ofy().load().type(Exam.class).list();
		return list;
	}

	public static final Exam getById(long id) {
		List<Exam> exams = null;
		MemcacheService syncCache = getCache();

		if (isCached(syncCache, cacheKeyName) == false) {
			exams = loadAllFromOfy();
			saveExamstoCache(exams, syncCache);
		}
		Map<Long, Exam> examMapKey = (Map<Long, Exam>) syncCache.get(cacheKeyName + "key");

		Exam exam = examMapKey.get(id);
		exam.convertFromCache();
		return exam;
	}

	protected static void clearCache() {
		MemcacheService syncCache = getCache();
		syncCache.delete(cacheKeyName);
		syncCache.delete(cacheKeyName + "key");

	}
}
