/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify.archived;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * @author t.yoshizawa 複数のAnswerをまとめるので、回答日はなくなり、問題数と正解数は合計される
 */

@Entity
@Cache
public final class ArchivedAnswer extends ArchivedAnswerFactory {

	@Id
	Long id;
	@Index
	private String name;
	private String no;
//	private Date answered;
	private int[] answerArray;
	@Index
	private Long archivedAnswerSumId;
	@Index
	private Long toiId;
	@Index
	private Long questionId;
	private String version;
	private int count;

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

	public int[] getAnswerArray() {
		return answerArray;
	}

	public void setAnswerArray(int[] answerArray) {
		this.answerArray = answerArray;
	}

	public Long getToiId() {
		return toiId;
	}

	public void setToiId(Long toiId) {
		this.toiId = toiId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Long getArchivedAnswerSumId() {
		return archivedAnswerSumId;
	}

	public void setArchivedAnswerSumId(Long archivedAnswerSumId) {
		this.archivedAnswerSumId = archivedAnswerSumId;
	}

	public ArchivedAnswer save() {
		Key<ArchivedAnswer> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

}