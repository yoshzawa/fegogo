package jp.ac.jc21.t.yoshizawa.objectify;


import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
	public static Optional<Toi> getByAnswerSum(AnswerSum aSum){
		Optional<Long> toiId = Optional.ofNullable(aSum.getRefToi().getKey().getId());
		Optional<Toi> toi;
		if(toiId.isPresent()) {
			 toi = Optional.ofNullable(Toi.getById(toiId.get()));
		}else {
			toi = Optional.empty();
		}
		return toi;

	}
	public static List<Toi> getToiListByExamId(Long examId){
		return  ofy().load().type(Toi.class).filter("examId", examId).list();
	}
	public static List<Toi> getToiListByGenreId(Long genreId){
		return  ofy().load().type(Toi.class).filter("genreId", genreId).list();
	}
	
	public static boolean contain(Long toiId) {
		Toi t = getById(toiId);
		return(t != null);
	}

}
