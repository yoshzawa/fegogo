package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.*;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

/**
 * @author t.yoshizawa
 *
 */
@Entity
@Cache
public final class Question extends QuestionFactory {
	@Id
	Long id;
	@Index
	private Long no;
	private String name;
	private Date created;
	private long noOfOption;
	private Ref<Toi> parent;
	private Set<Integer> answerSet;

	public Question() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the no
	 */
	public Long getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(Long no) {
		this.no = no;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the isMulti
	 */
	public boolean isMulti() {
		return getAnswerlength() != 1;
	}

	/**
	 * @return the noOfOption
	 */
	public long getNoOfOption() {
		return noOfOption;
	}

	/**
	 * @param noOfOption the noOfOption to set
	 */
	public void setNoOfOption(long noOfOption) {
		this.noOfOption = noOfOption;
	}

	/**
	 * @return the parent
	 */
	public Toi getParent() {
		return parent.get();
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Toi parent) {
		this.parent = Ref.create(parent);
	}

	public void setParent(Ref<Toi> parent) {
		this.parent = parent;
	}

	/**
	 * @return the answerSet
	 */
	public Set<Integer> getAnswerSet() {
		if (answerSet == null) {
			newAnswerSet();
		}
		return answerSet;
	}

	public void newAnswerSet() {
		setAnswerSet(new HashSet<Integer>());
	}

	/**
	 * @param answerSet the answerSet to set
	 */
	public void setAnswerSet(Set<Integer> answerSet) {
		this.answerSet = answerSet;
	}

	public void addAnswerSet(Integer answer) {
		Set<Integer> as = getAnswerSet();
		as.add(answer);
		setAnswerSet(as);
	}

	public void addAnswerSet(Integer[] answer) {
		for (Integer a : answer) {
			addAnswerSet(a);
		}
	}

	public int getAnswerlength() {
		return getAnswerSet().size();

	}

	/**
	 * 正解に含まれているか調べる
	 * 
	 * @param i ユーザーの解答
	 * @return 含まれていればtrue
	 */
	public boolean isCorrect(int i) {
		Set<Integer> as = getAnswerSet();
		return as.contains(i);
	}

	public void setAnswerSet(String[] correct) {
		for (String a : correct) {
			addAnswerSet(Integer.parseInt(a));
		}
	}

	public String getAnswers() {
		String s = "";
		for (int i : getAnswerSet()) {
			s += "アイウエオカキクケコサシスセソタチツテト".charAt(i);
		}
		return s;
	}

	public Question save() {
		Key<Question> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

}
