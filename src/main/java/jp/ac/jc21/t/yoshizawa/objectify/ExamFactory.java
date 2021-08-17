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
		final Logger log = Logger.getLogger(Exam.class.getName());
		log.info("Exam.loadAll: [---]");

		List<Exam> examList = ofy().load().type(Exam.class).list();

		TreeMap<Long, Exam> examMap = new TreeMap<>();
		for (Exam e : examList) {
			examMap.put(e.getYYYYMM(), e);
		}
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
		final Logger log = Logger.getLogger(Exam.class.getName());
		
		if (cachedMapById == null) {
			cachedMapById = new TreeMap<Long, Exam>();
		}
		Exam exam;
		if (cachedMapById.containsKey(id) == false) {
			 exam = ofy().load().type(Exam.class).id(id).now();
			cachedMapById.put(exam.getId(), exam);
			log.info("Exam.getByid(id):" + id + "[Miss]");
		} else {
			exam = cachedMapById.get(id);
			log.info("Exam.getByid(id):" + id + "[Hit]");
		}
		return exam;
	}

	/**
	 * @param k
	 * @return
	 */
	public static final Exam getById(Key<Exam> k) {
		final Logger log = Logger.getLogger(Exam.class.getName());
		log.info("Exam.getByid(Key):" + k.getId() + "[---]");
		
		Exam exam = ofy().load().type(Exam.class).filterKey(k).first().now();
		return exam;
	}
	
	/**
	 * @param toi
	 * @return
	 */
	public static Optional<Exam> getByToi(Toi toi) {
		final Logger log = Logger.getLogger(Exam.class.getName());
		log.info("Exam.getByToi(Toi):" + toi.getExamId() + "[---]");

		Key<Exam> key = Key.create(Exam.class,toi.getExamId());
		Exam exam = getById(key);
		return Optional.ofNullable(exam);
	}
}
