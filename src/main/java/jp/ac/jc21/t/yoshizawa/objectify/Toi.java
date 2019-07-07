/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;

import com.googlecode.objectify.annotation.*;

/**
 * @author t.yoshizawa
 *
 */

@Entity
//@Cache

public class Toi {
	@Id
	Long id;
	@Index
	private Long no;
	private String name;
	private Date created;
	private Ref<Exam> parent;
	private List<Question> questionList;
	private List<Ref<Question>> questionRefList;

	static {
//		ObjectifyService.register(Toi.class);
	}



	public static Toi createToi(Exam parent, Long no, String name) {
		Toi t = new Toi();
		t.setNo(no);
		t.setName(name);
		t.setCreated(new Date());
		t.setParent(parent);
		t.newQuestionList();
		return t;
	}

	public static List<Toi> loadAll() {
		return ofy().load().type(Toi.class).order("no").list();
	}

	public static List<Toi> load(long parentId) {
		Exam e = Exam.getById(parentId);
		return e.getToiList();
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

	
	/**
	 * @return the questionList
	 */
	public List<Question> getQuestionList() {
		if (questionList == null) {
			newQuestionList();
		}
		return questionList;
	}

	public List<Ref<Question>> getQuestionRefList() {
		if (questionRefList == null) {
			newQuestionRefList();
		}
		return questionRefList;
	}
	
	public void addQuestionList(Question q) {
		List<Question> list = getQuestionList();
		list.add(q);
		setQuestionList(list);
	}
	public void addQuestionRefList(Ref<Question> q) {
		List<Ref<Question>> list = getQuestionRefList();
		list.add(q);
		setQuestionRefList(list);
	}
	
	public int getQuestionListSize() {
		List<Question> qs = getQuestionList();
		if (qs == null) {
			return 0;
		}else {
			return qs.size();
		}
	}

	public int getQuestionRefListSize() {
		List<Ref<Question>> qs = getQuestionRefList();
		if (qs == null) {
			return 0;
		}else {
			return qs.size();
		}
	}


	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionRefList(List<Ref<Question>> questionRefList) {
		this.questionRefList = questionRefList;
	}

	public void newQuestionList() {
		setQuestionList(new ArrayList<Question>());
	}
	public void newQuestionRefList() {
		setQuestionRefList(new ArrayList<Ref<Question>>());
	}

}
