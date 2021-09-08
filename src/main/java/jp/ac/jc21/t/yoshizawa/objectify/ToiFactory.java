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
		t.setExamId(parent.getId());
		t.setGenre(genre);
//		t.setAnswerSumRefList(null);
		return t;
	}

	public static final Toi createToi(Exam parent, Long no, String name) {
		Toi t = new Toi();
		t.setNo(no);
		t.setName(name);
		t.setCreated(new Date());
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

	public static final List<Toi> loadAll() {

		List<Toi> tList = ofy().load().type(Toi.class).list();

		return tList;
	}

	public static Optional<Toi> getByAnswerSum(AnswerSum aSum) {
		Long toiId = aSum.getToiId();
		Toi toi = getById(toiId);
		return Optional.ofNullable(toi);
	}

	public static boolean contain(Long toiId) {
		Toi t = getById(toiId);
		return (t != null);
	}

	public static final Toi getById(long id) {
		Toi toi = null;
		toi = ofy().load().type(Toi.class).id(id).now();
		return toi;
	}
	public static final Toi getById(String id) {
		return getById(Long.parseLong(id));
	}

	
	@SuppressWarnings("unchecked")
	public final static List<Toi> getToiListByExamId(Long examId) {
		Logger.getLogger(Toi.class.getName());
		List<Toi> list;
		list = (List<Toi>) loadByIndex(Toi.class, "examId", examId);
		return list;
	}

	@SuppressWarnings("unchecked")
	public final static List<Toi> getToiListByGenreId(Long genreId) {
		List<Toi> list;
		list = (List<Toi>) loadByIndex(Toi.class, "genreId", genreId);
		return list;
	}

}
