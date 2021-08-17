package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.googlecode.objectify.Ref;

public class ToiFactory extends CommonEntity {

	final static String cacheKeyName = "Toi";

	public static final Toi createToi(Exam parent, Long no, String name, Genre genre) {
		Toi t = new Toi();
		t.setNo(no);
		t.setName(name);
		t.setCreated(new Date());
//		t.setExam(parent);	
		t.setExamId(parent.getId());
		t.setGenre(genre);
		t.setAnswerSumRefList(null);
		return t;
	}

	public static final Toi createToi(Exam parent, Long no, String name) {
		Toi t = new Toi();
		t.setNo(no);
		t.setName(name);
		t.setCreated(new Date());
//		t.setExam(parent);	
		t.setExamId(parent.getId());
		t.setGenreId(null);
		return t;
	}

	public static final TreeMap<Long, Question> getQuestionMap(Toi parent) {
		TreeMap<Long, Question> qMap = new TreeMap<>();
		List<Question> list = parent.getQuestionList();

		for (Question q : list) {
			qMap.put(q.getNo(), q);
		}
		return qMap;
	}

	public static final Toi getById(long id) {
		final Logger log = Logger.getLogger(Toi.class.getName());
		log.info("Toi.getById:" + id + "[---]");

		return ofy().load().type(Toi.class).id(id).now();

	}

	public static final List<Toi> loadAll() {
		final Logger log = Logger.getLogger(Toi.class.getName());
		log.info("Toi.loadAll: Å@[---]");

		List<Toi> tList = ofy().load().type(Toi.class).list();

		return tList;
	}

	public static Optional<Toi> getByAnswerSum(AnswerSum aSum) {
		final Logger log = Logger.getLogger(Toi.class.getName());
		log.info("Toi.getByAnswerSum:" + aSum.getId() + "[---]");

		Optional<Long> toiId = Optional.ofNullable(aSum.getToi().getId());
		Optional<Toi> toi;
		if (toiId.isPresent()) {
			toi = Optional.ofNullable(Toi.getById(toiId.get()));
		} else {
			toi = Optional.empty();
		}
		return toi;

	}

	public static boolean contain(Long toiId) {
		Toi t = getById(toiId);
		return (t != null);
	}

	static Map<Long, List<Toi>> cachedMapByExamId = null;
	static Map<Long, List<Toi>> cachedMapByGenreId = null;

	public void flush() {
		cachedMapByExamId = null;
		cachedMapByGenreId = null;

	}

	public final static List<Toi> getToiListByExamId(Long examId) {
		final Logger log = Logger.getLogger(Toi.class.getName());
		if (cachedMapByExamId == null) {
			cachedMapByExamId = new TreeMap<Long, List<Toi>>();
		}
		List<Toi> list;
		if (cachedMapByExamId.containsKey(examId) == false) {
			list = (List<Toi>) loadByIndex(Toi.class, "examId", examId);
			cachedMapByExamId.put(examId, list);
			log.info("Toi.getToiListByExamId:" + examId + "[Miss]");

		} else {
			list = cachedMapByExamId.get(examId);
			log.info("Toi.getToiListByExamId:" + examId + "[Hit]");
		}
		return list;
	}

	public final static List<Toi> getToiListByGenreId(Long genreId) {
		final Logger log = Logger.getLogger(Toi.class.getName());

		if (cachedMapByGenreId == null) {
			cachedMapByGenreId = new TreeMap<Long, List<Toi>>();
		}
		List<Toi> list;
		if (cachedMapByGenreId.containsKey(genreId) == false) {
			list = (List<Toi>) loadByIndex(Toi.class, "genreId", genreId);
			cachedMapByGenreId.put(genreId, list);
			log.info("Toi.getToiListByGenreId:" + genreId + "[Miss]");
		} else {
			list = cachedMapByGenreId.get(genreId);
			log.info("Toi.getToiListByGenreId:" + genreId + "[Hit]");

		}
		return list;
	}

}
