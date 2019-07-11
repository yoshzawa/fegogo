package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.*;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

@Entity
@Cache
public class Question extends CommonEntity {
	@Id
	Long id;
	@Index
	private Long no;
	private String name;
	private Date created;
	private long noOfOption;
	private Ref<Toi> parent;
	private Set<Integer> answerSet;

	static {
	}

	public Question() {
	}

	public static Question createQuestion(Toi parent, long no, String name, long noOfOption, int answer) {
		Question q = createQuestion(parent, no, name, noOfOption);
		q.addAnswerSet(answer);
		return q;
	}

	public static Question createMultiQuestion(Toi parent, long no, String name, long noOfOption, Integer[] answers) {
		Question q = createQuestion(parent, no, name, noOfOption);
		q.addAnswerSet(answers);
		return q;
	}

	private static Question createQuestion(Toi parent, long no, String name, long noOfOption) {
		Question q = new Question();
		q.setCreated(new Date());
		q.setNo(no);
		q.setName(name);
		q.setNoOfOption(noOfOption);
		q.setParent(parent);
		return q;
	}

	public Question save() {
		Key<Question> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public static Question getById(long id) {
		return ofy().load().type(Question.class).id(id).now();
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

}
