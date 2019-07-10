package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

@Entity
@Cache
public class Question  extends CommonEntity{
	@Id
	Long id;
	@Index
	private Long no;
	private String name;
	private Date created;
	private boolean isMulti;
	private long noOfOption;
//	private long answer;
	private Ref<Toi> parent;
	private int[] correct;

	 static {
//		ObjectifyService.register(Question.class);
	}

	public Question() {
		// TODO Auto-generated constructor stub
	}

	public static Question createQuestion(Toi parent, long no, String name, long noOfOption, long answer) {
		Question q = createQuestion(parent, no, name, noOfOption);
//		q.setMultiQuestion(null);
		q.setMulti(false);
//		q.setAnswer(answer);
		q.setCorrect(null);
		return q;
	}

	public static Question createMultiQuestion(Toi parent, long no, String name, long noOfOption, int[] correct) {
		Question q = createQuestion(parent, no, name, noOfOption);
//		q.setMultiQuestion(null);
		q.setMulti(true);
//		q.setAnswer(-1);
		q.setCorrect(correct);
		return q;
	}

	private static Question createQuestion(Toi parent, long no, String name, long noOfOption) {
		Question q = new Question();
		q.setCreated(new Date());
		q.setNo(no);
		q.setName(name);
		q.setNoOfOption(noOfOption);
		q.setParent(parent);
		q.setMulti(false);
//		q.setAnswer(-1);
		q.setCorrect(null);
		return q;
	}
	
/*	public static List<Question> load(Long parentId){
		Toi t = Toi.getById(parentId);
		return t.getQuestionList();
	}
*/
	
	public Question save() {
		Key<Question> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}
	
	public static Question getById(long id) {
		return ofy().load().type(Question.class).id(id).now();
	}
	
	/**
	 * @return the correct
	 */
	public int[] getCorrect() {
		return correct;
	}

	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(int[] correct) {
		this.correct = correct;
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
		return isMulti;
	}

	/**
	 * @param isMulti the isMulti to set
	 */
	public void setMulti(boolean isMulti) {
		this.isMulti = isMulti;
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
	 * @return the answer
	 */
//	public long getAnswer() {
//		return answer;
//	}

	/**
	 * @param answer the answer to set
	 */
//	public void setAnswer(long answer) {
//		this.answer = answer;
//	}

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

}
