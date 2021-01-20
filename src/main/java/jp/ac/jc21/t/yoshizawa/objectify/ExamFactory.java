/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.googlecode.objectify.Key;

/**
 * @author t.yoshizawa
 *
 */
public abstract class ExamFactory extends CommonEntity {

	/**
	 * @param YYYYMM
	 * @param name
	 * @return
	 */
	public static final Exam createExam(Long YYYYMM, String name) {
		Exam exam = new Exam();
		exam.setCreated(new Date());
		exam.setName(name);
//		exam.newToiRefList();
		exam.setYYYYMM(YYYYMM);
		return exam;
	}

	/**
	 * @return
	 */
	public static final Map<Long, Exam> loadAll() {

		List<Exam> examList = ofy().load().type(Exam.class).list();

		TreeMap<Long, Exam> examMap = new TreeMap<>();
		for (Exam e : examList) {
			examMap.put(e.getYYYYMM(), e);
		}
		return examMap;
	}

	/**
	 * @param id
	 * @return
	 */
	public static final Exam getById(long id) {
		return ofy().load().type(Exam.class).id(id).now();
	}

	/**
	 * @param k
	 * @return
	 */
	public static final Exam getById(Key<Exam> k) {
		return ofy().load().type(Exam.class).filterKey(k).first().now();
	}
	/**
	 * @param toi
	 * @return
	 */
	public static Optional<Exam> getByToi(Toi toi) {
		Key<Exam> key = Key.create(Exam.class,toi.getExam().getId());
		return Optional.ofNullable(getById(key));
	}
}
