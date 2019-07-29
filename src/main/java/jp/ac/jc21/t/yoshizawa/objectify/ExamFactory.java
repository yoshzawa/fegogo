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
import com.googlecode.objectify.Ref;

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

		List<Exam> examList = loadAllFromOfy();
		TreeMap<Long, Exam> examMap = new TreeMap<>();
		for (Exam e : examList) {
			examMap.put(e.getYYYYMM(), e);
		}
		return examMap;
	}

	private static final List<Exam> loadAllFromOfy() {
		List<Exam> list = ofy().load().type(Exam.class).list();
		return list;
	}

	public static final Exam getById(long id) {
		return ofy().load().type(Exam.class).id(id).now();
	}
	


}
