/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import jp.ac.jc21.t.yoshizawa.CommonFunction;

/**
 * @author t.yoshizawa
 *
 */

@Entity
@Cache
public final class Answer extends AnswerFactory {

	@Id
	Long id;
	@Index
	private String name;
	private String no;
	private Date answered;
	private int[] answerArray;
	@Index
	private Long answerSumId;
	@Index
	private Long questionId;
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getAnswerSumId() {
		return answerSumId;
	}

	/**
	 * @param answerSumId the answerSumId to set
	 */
	public void setAnswerSumId(Long answerSumId) {
		this.answerSumId = answerSumId;
	}

	public Answer() {
	}

	////////// id

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

	////////// no

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

	////////// name

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

	////////// answered

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

	public void setAnswerSum(AnswerSum a) {
		setAnswerSumId(a.getId());
	}

	/**
	 * @param answerArray the answerArray to set
	 */
	public void setAnswerArray(int[] answerArray) {
		this.answerArray = answerArray;
	}

	////////// answerArray

	/**
	 * @return the answerArray
	 */
	public int[] getAnswerArray() {
		return answerArray;
	}

	/**
	 * @param answerDumpCSV the answerDumpCSV to set
	 */

	public boolean isCorrect() {
//		final Logger log = getLogger();

		int[] answerArray = getAnswerArray();
		Question question = getQuestion();

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

	public Logger getLogger() {
		final Logger log = Logger.getLogger(Answer.class.getName());
		return log;
	}

	public String getAnswers() {
		String s = "";
		int[] answers = getAnswerArray();
		if (answers == null) {
			return "";
		}
		for (int i : answers) {
			if (i == -1) {
				s += "[解けない]";

			} else {
				s += "アイウエオカキクケコサシスセソタチツテト".charAt(i);
			}
			if (getQuestion().getNoOfOption() <= 0) {
				s = "全員正解";
			}
		}
		return s;
	}

	public Answer save() {
		Key<Answer> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public Optional<AnswerSum> getAnswerSum() {
		return AnswerSum.getOptById(getAnswerSumId());
	}

	public void delete() {
		ofy().delete().entity(this).now();
	}

/*	@SuppressWarnings("unchecked")
	public String makeAnswerDumpCSV(javax.cache.Cache cache) {
		String cacheId = "Answer:" + getId();
		if (cache.containsKey(cacheId) == true) {
			String value = (String) cache.get(cacheId);
			return value;
		} else {
			Question question = getQuestion();
			String s = getId() + "," + getNo() + "," + question.getName() + "," + question.getAnswers() + ","
					+ getAnswers() + "," + (isCorrect() ? 1 : 0);
			cache.put(cacheId, s);
			return s;
		}
	}
*/
	public String getExportData() {

		return getId() + "," + getNo() + "," + getName() + "," + CommonFunction.dateFormat(getAnswered()) + "," + getAnswerSumId()
				+ "," + getQuestionId() + "," + getAnswers();
	}

	public Question getQuestion() {
		return Question.getById(getQuestionId());
	}


}