package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.googlecode.objectify.Ref;

public class ToiFactory extends CommonEntity {

	final static String cacheKeyName = "Toi";

	public static final Toi createToi(Exam parent, Long no, String name,Genre genre) {
		Toi t = new Toi();
		t.setNo(no);
		t.setName(name);
		t.setCreated(new Date());
		t.setExam(parent);
		t.newQuestionRefList();
		t.setGenre(genre);
		t.setAnswerSumRefList(null);
		return t;
	}
	public static final Toi createToi(Exam parent, Long no, String name) {
		Toi t = new Toi();
		t.setNo(no);
		t.setName(name);
		t.setCreated(new Date());
		t.setExam(parent);
		t.newQuestionRefList();
		t.setRefGenre(null);
		return t;
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

	public static final Toi getById(long id) {
		return ofy().load().type(Toi.class).id(id).now();

	}
	
	public static final List<Toi>  loadAll() {

		List<Toi> tList = ofy().load().type(Toi.class).list();

		return tList;
	}

}
