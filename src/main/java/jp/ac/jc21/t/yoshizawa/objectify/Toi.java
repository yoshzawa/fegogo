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

public final class Toi extends ToiFactory {
	@Id
	Long id;
	@Index
	private Long no;
	private String name;
	private Date created;
	private Ref<Exam> parent;
	private List<Ref<Question>> questionRefList;

	private Long parentKey;
	private List<Long> questionKeyList;

	public Toi save() {
		Key<Toi> key = ofy().save().entity(this).now();
		return getById(key.getId());
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

	public void setParent(Exam parent) {
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
		List<Ref<Question>> questionRefList = getQuestionRefList();
		if (questionRefList == null) {
			return 0;
		} else {
			return questionRefList.size();
		}
	}

	/**
	 * @return the parentKey
	 */
	public Long getParentKey() {
		return parentKey;
	}

	/**
	 * @param parentKey the parentKey to set
	 */
	public void setParentKey(Long parentKey) {
		this.parentKey = parentKey;
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

	/**
	 * @return the questionKeyList
	 */
	public List<Long> getQuestionKeyList() {
		if(questionKeyList == null) {
			newQuestionKeyList();
		}
		return questionKeyList;
	}

	/**
	 * @param questionKeyList the questionKeyList to set
	 */
	public void setQuestionKeyList(List<Long> questionKeyList) {
		this.questionKeyList = questionKeyList;
	}
	
	public void newQuestionKeyList() {
		setQuestionKeyList(new ArrayList<Long>());
	}

	public void addQuestionKeyList(Long questionKey) {
		List<Long> list = getQuestionKeyList();
		list.add(questionKey);
		setQuestionKeyList(list);
	}

	public void convertFromCache() {
		setParent(Exam.getById(getParentKey()));

		newQuestionRefList();
		for(Long key : getQuestionKeyList()) {
			addQuestionRefList(Question.getById(key));
		}
		newQuestionKeyList();
		
		
	}

	public void convertForCache() {
		setParentKey(getParent().getId());
		newQuestionKeyList();
		for(Ref<Question> questionRef : getQuestionRefList()) {
			addQuestionKeyList(questionRef.get().getId());
		}
		newQuestionRefList();
		
	}
	
	
}
