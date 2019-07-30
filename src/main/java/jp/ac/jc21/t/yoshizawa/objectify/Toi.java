/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
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
	private Ref<Genre> genre;

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
	private void resetParent() {
		parent=null;
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

	private void resetQuestionRefList() {
		questionRefList=null;
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
	 * @return the genre
	 */
	public Ref<Genre> getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(Ref<Genre> genre) {
		this.genre = genre;
	}

		public Toi save() {
			Key<Toi> key = ofy().save().entity(this).now();
//			Toi.clearCache();
			return getById(key.getId());
		}
	
}
