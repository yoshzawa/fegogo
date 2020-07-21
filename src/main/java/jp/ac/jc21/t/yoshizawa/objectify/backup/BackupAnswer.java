/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify.backup;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import jp.ac.jc21.t.yoshizawa.objectify.*;

/**
 * @author t.yoshizawa
 *
 */

@Entity
@Cache
public final class BackupAnswer extends BackupAnswerFactory {

	@Id
	Long id;
	@Index
	private String name;
	private String no;
	private Date answered;
	private Ref<AnswerSum> refAnswerSum;
	private Ref<Question> refQuestion;
	private int[] answerArray;
//	private String dumpCSV;
	Long answerSumId;
	Long questionId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Date getAnswered() {
		return answered;
	}
	public void setAnswered(Date answered) {
		this.answered = answered;
	}
	public Ref<AnswerSum> getRefAnswerSum() {
		return refAnswerSum;
	}
	public void setRefAnswerSum(Ref<AnswerSum> refAnswerSum) {
		this.refAnswerSum = refAnswerSum;
	}
	public Ref<Question> getRefQuestion() {
		return refQuestion;
	}
	public void setRefQuestion(Ref<Question> refQuestion) {
		this.refQuestion = refQuestion;
	}
	public int[] getAnswerArray() {
		return answerArray;
	}
	public void setAnswerArray(int[] answerArray) {
		this.answerArray = answerArray;
	}
	public Long getAnswerSumId() {
		return answerSumId;
	}
	public void setAnswerSumId(Long answerSumId) {
		this.answerSumId = answerSumId;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public BackupAnswer save() {
		Key<BackupAnswer> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

}
