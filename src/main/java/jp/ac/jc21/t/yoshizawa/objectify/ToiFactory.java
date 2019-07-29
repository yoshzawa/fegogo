package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.appengine.api.memcache.MemcacheService;
import com.googlecode.objectify.Ref;

public class ToiFactory extends CommonEntity {

	final static String cacheKeyName = "Toi";

	public static final Toi createToi(Exam parent, Long no, String name) {
		Toi t = new Toi();
		t.setNo(no);
		t.setName(name);
		t.setCreated(new Date());
		t.setParent(parent);
		t.newQuestionRefList();
		return t;
	}

	public static final TreeMap<Long, Toi> getToiMap(long parentId) {
		TreeMap<Long, Toi> toiMap = new TreeMap<>();

		Exam e = Exam.getById(parentId);
		List<Ref<Toi>> toiRefList = e.getToiRefList();

		if (toiRefList != null) {
			for (Ref<Toi> t : toiRefList) {
				Toi tt = t.get();
				toiMap.put(tt.getNo(), tt);
			}
		}
		return toiMap;
	}

	public static final TreeMap<Long, Question> getQuestionMap(Toi parent) {
		TreeMap<Long, Question> qMap = new TreeMap<>();
		List<Ref<Question>> list = parent.getQuestionRefList();

		for (Ref<Question> qq : list) {
			Question q = qq.get();
			qMap.put(q.getNo(), q);
		}
		return qMap;
	}

	@SuppressWarnings("unchecked")
	public static final List<Toi> loadAllFromOfy() {
		return loadAll(Toi.class, "no");
	}

	public static final Map<Long, Toi> loadAll() {
		MemcacheService syncCache = getCache();

		if (isCached(syncCache, cacheKeyName) == false) {
			List<Toi> toiList = loadAllFromOfy();
			saveToisToCache(toiList, syncCache);
		}
		return loadToisFromCache(syncCache);
	}

	private static Map<Long, Toi> loadToisFromCache(MemcacheService syncCache) {

		Map<Long, Toi> toiMap = (Map<Long, Toi>) syncCache.get(cacheKeyName);
		for (Long key : toiMap.keySet()) {
			Toi t = toiMap.get(key);
			t.convertFromCache();
			toiMap.put(key, t);
		}

		return (toiMap);
	}

	private static void saveToisToCache(List<Toi> toiList, MemcacheService syncCache) {
		TreeMap<Long, Toi> toiMap = new TreeMap<>();
		for (Toi t : toiList) {
			t.convertForCache();
			toiMap.put(t.getId(), t);
		}

		syncCache.put(cacheKeyName, toiMap);
	}

	public static final Toi getById(long id) {
//		return ofy().load().type(Toi.class).id(id).now();

		Map<Long, Toi> map = loadAll();
		Toi t = map.get(id);
		return t;

	}

}
