package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.googlecode.objectify.Ref;

public class ToiFactory extends CommonEntity{

	
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
	
	public static final Toi getById(long id) {
		return ofy().load().type(Toi.class).id(id).now();
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
	public static final List<Toi> loadAll() {
		return loadAll(Toi.class, "no");
	}
}