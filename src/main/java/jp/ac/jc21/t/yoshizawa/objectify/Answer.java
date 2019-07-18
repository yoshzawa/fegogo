/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.Date;
import java.util.logging.Logger;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.Key;

/**
 * @author t.yoshizawa
 *
 */

@Entity
@Cache
public final class Answer extends CommonEntity {

	@Id
	Long id;
	@Index
	private String name;
	private String no;
	private Date answered;
	private Ref<AnswerSum> refAnswerSum;
	private Ref<Question> refQuestion;
	private int[] answerArray;

	public Answer() {
	}

	public Answer save() {
		Key<Answer> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public static Answer getById(long id) {
		return ofy().load().type(Answer.class).id(id).now();
	}

	public static Answer createAnswer(String name, Ref<AnswerSum> refAnswerSum, Question question, String[] answerArray,
			long no) {
		int answerIntArray[] = null;
		if (answerArray != null) {
			answerIntArray = new int[answerArray.length];
			for (int i = 0; i < answerArray.length; i++) {
				answerIntArray[i] = Integer.parseInt(answerArray[i]);
			}
		}

		String noString = "0" + no;
		if (noString.length() > 2) {
			noString = noString.substring(noString.length() - 2);
		}

		return createAnswer(name, refAnswerSum, Ref.create(question), answerIntArray, noString);
	}

	public static Answer createAnswer(String name, Ref<AnswerSum> refAnswerSum, Ref<Question> refQuestion,
			int[] answerArray, String no) {
		Answer a = new Answer();
		a.setName(name);
		a.setAnswered(new Date());
		a.setRefAnswerSum(refAnswerSum);
		a.setRefQuestion(refQuestion);
		a.setAnswerArray(answerArray);
		a.setNo(no);
		return a;
	}

	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
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
	 * @return the answered
	 */
	public Date getAnswered() {
		return answered;
	}

	/**
	 * @param answered the answered to set
	 */
	public void setAnswered(Date answered) {
		this.answered = answered;
	}

	/**
	 * @return the refAnswerSum
	 */
	public Ref<AnswerSum> getRefAnswerSum() {
		return refAnswerSum;
	}

	/**
	 * @param refAnswerSum the refAnswerSum to set
	 */
	public void setRefAnswerSum(Ref<AnswerSum> refAnswerSum) {
		this.refAnswerSum = refAnswerSum;
	}

	/**
	 * @return the refQuestion
	 */
	public Ref<Question> getRefQuestion() {
		return refQuestion;
	}

	/**
	 * @param refQuestion the refQuestion to set
	 */
	public void setRefQuestion(Ref<Question> refQuestion) {
		this.refQuestion = refQuestion;
	}

	/**
	 * @return the answerArray
	 */
	public int[] getAnswerArray() {
		return answerArray;
	}

	/**
	 * @param answerArray the answerArray to set
	 */
	public void setAnswerArray(int[] answerArray) {
		this.answerArray = answerArray;
	}

	public void setAnswerSum(AnswerSum a) {
		setRefAnswerSum(Ref.create(a));

	}

	public boolean isCorrect() {
//		final Logger log = Logger.getLogger(Answer.class.getName());

		int[] answerArray = getAnswerArray();
		Question question = getRefQuestion().get();

		if ((answerArray == null) || (answerArray.length != question.getAnswerlength())) {
			return false;
		} else {
			for (int i = 0; i < answerArray.length; i++) {
				if (!question.isCorrect(answerArray[i])) {
					return false;
				}
			}
			return true;
		}
	}

	public String getAnswers() {
		String s = "";
		for (int i : getAnswerArray()) {
			if(i == -1) {
				s+= "[解けない]";
				
			} else {
				s += "アイウエオカキクケコサシスセソタチツテト".charAt(i);
			}

		}
		return s;
	}

}
