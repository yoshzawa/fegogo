/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.Date;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * @author t.yoshizawa
 *
 */
public class MultiQuestion {
	@Id
	Long id;
	@Index
	private Long no;
	private Date created;
	private int[] correct;
	private Question question;

	public MultiQuestion() {
		// TODO Auto-generated constructor stub
	}

	public static MultiQuestion createMultiQuestion(Long no, Question question, int[] correct) {
		MultiQuestion mq = new MultiQuestion();
		mq.setCreated(new Date());
		mq.setNo(no);
		mq.setQuestion(question);
		mq.setCorrect(correct);
		return mq;
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
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

}
