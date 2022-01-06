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
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
	
	public static final Map<Long, Exam> loadAll2() {


		Map<Long, Exam> examMap = new TreeMap<>();

		examMap = ofy().load().type(Exam.class).list().stream().collect(Collectors.toMap(Exam::getYYYYMM,(Exam d)->d,(Exam d1,Exam d2)->d2,TreeMap::new));
		return examMap;
	}
	

	static Map<Long, Exam> cachedMapById = null;
	void flush() {
		cachedMapById=null;
	}

	/**
	 * @param id
	 * @return
	 */
	public static final Exam getById(long id) {

		Exam exam;
			 exam = ofy().load().type(Exam.class).id(id).now();

		return exam;
	}
	public static final Exam getById(String id) {
		return getById(Long.parseLong(id));
	}

	/**
	 * @param k
	 * @return
	 */
	public static final Exam getById(Key<Exam> k) {
		
		Exam exam = ofy().load().type(Exam.class).filterKey(k).first().now();
		return exam;
	}
	
	/**
	 * @param toi
	 * @return
	 */
	public static Optional<Exam> getByToi(Toi toi) {

		Key<Exam> key = Key.create(Exam.class,toi.getExamId());
		Exam exam = getById(key);
		return Optional.ofNullable(exam);
	}
}
