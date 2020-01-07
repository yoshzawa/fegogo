/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.googlecode.objectify.Key;

/**
 * @author t.yoshizawa
 *
 */
public abstract class ExamFactory extends CommonEntity {

	public static final Exam createExam(Long YYYYMM, String name) {
		Exam exam = new Exam();
		exam.setCreated(new Date());
		exam.setName(name);
		exam.newToiRefList();
		exam.setYYYYMM(YYYYMM);
		return exam;
	}

	public static final Map<Long, Exam> loadAll() {

		List<Exam> examList = ofy().load().type(Exam.class).list();

		TreeMap<Long, Exam> examMap = new TreeMap<>();
		for (Exam e : examList) {
			examMap.put(e.getYYYYMM(), e);
		}
		return examMap;
	}

	public static final Exam getById(long id) {
		return ofy().load().type(Exam.class).id(id).now();
	}

	public static final Exam getById(Key<Exam> k) {
		return ofy().load().type(Exam.class).filterKey(k).first().now();
	}
}
