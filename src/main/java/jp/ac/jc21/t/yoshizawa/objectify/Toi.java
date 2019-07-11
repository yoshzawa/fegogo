/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import com.googlecode.objectify.annotation.*;

/**
 * @author t.yoshizawa
 *
 */

@Entity
@Cache

public class Toi extends CommonEntity {
	@Id
	Long id;
	@Index
	private Long no;
	private String name;
	private Date created;
	private Ref<Exam> parent;
//	private List<Question> questionList;
	private List<Ref<Question>> questionRefList;

	public static Toi createToi(Exam parent, Long no, String name) {
		Toi t = new Toi();
		t.setNo(no);
		t.setName(name);
		t.setCreated(new Date());
		t.setParent(parent);
		t.newQuestionRefList();
		return t;
	}

	@SuppressWarnings("unchecked")
	public static List<Toi> loadAll() {
		return loadAll(Toi.class, "no");
	}

	public static TreeMap<Long, Toi> getToiMap(long parentId) {
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

	public Toi save() {
		Key<Toi> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public static Toi getById(long id) {
		return ofy().load().type(Toi.class).id(id).now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the parent
	 */
	public Exam getParent() {
		Ref<Exam> re = parent;
		return re.get();
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Ref<Exam> parent) {
		this.parent = parent;
	}

	private void setParent(Exam parent) {
		setParent(Ref.create(parent));
	}

	public List<Ref<Question>> getQuestionRefList() {
		if (questionRefList == null) {
			newQuestionRefList();
		}
		return questionRefList;
	}

	public void addQuestionRefList(Question q) {
		addQuestionRefList(Ref.create(q));
	}

	public void addQuestionRefList(Ref<Question> q) {
		List<Ref<Question>> list = getQuestionRefList();
		list.add(q);
		setQuestionRefList(list);
	}

	public int getQuestionRefListSize() {
		List<Ref<Question>> qs = getQuestionRefList();
		if (qs == null) {
			return 0;
		} else {
			return qs.size();
		}
	}

	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionRefList(List<Ref<Question>> questionRefList) {
		this.questionRefList = questionRefList;
	}

	public void newQuestionRefList() {
		setQuestionRefList(new ArrayList<Ref<Question>>());
	}

	public static TreeMap<Long, Question> getQuestionMap(Toi parent) {
		TreeMap<Long, Question> qMap = new TreeMap<>();
		List<Ref<Question>> list = parent.getQuestionRefList();

		for (Ref<Question> qq : list) {
			Question q = qq.get();
			qMap.put(q.getNo(), q);
		}
		return qMap;
	}
}
