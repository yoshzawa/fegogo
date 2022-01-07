package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Cache

public class CloneQuestion extends CommonEntity {
	@Id
	Long id;
	@Index
	Long toiId;
	@Index
	Long questionId;
	@Index
	Long originalQuestionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getOriginalQuestionId() {
		return originalQuestionId;
	}

	public void setOriginalQuestionId(Long originalQuestionId) {
		this.originalQuestionId = originalQuestionId;
	}

	public CloneQuestion() {
	}

	public CloneQuestion(Long toiId, Long questionId,Long originalQuestionId) {
		setToiId(toiId);
		setQuestionId(questionId);
		setOriginalQuestionId(originalQuestionId);
	}

	public CloneQuestion save() {
		Key<CloneQuestion> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}
	public void delete() {
		ofy().delete().entity(this).now();
	}
	

	public static final CloneQuestion getById(long id) {
		CloneQuestion q = null;
		q = ofy().load().type(CloneQuestion.class).id(id).now();
		return q;
	}
	@SuppressWarnings("unchecked")
	public static final List<CloneQuestion> getByToiId(long toiId) {
		List<CloneQuestion> q = null;
		q = (List<CloneQuestion>) loadByIndex(CloneQuestion.class, "toiId", toiId);
		return q;
	}
	
	@SuppressWarnings("unchecked")
	public static final List<CloneQuestion> getByQuestionId(long questionId) {
		List<CloneQuestion> q = null;
		q = (List<CloneQuestion>) loadByIndex(CloneQuestion.class, "questionId", questionId);
		return q;
	}

}
