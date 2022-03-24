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

public class CloneToi extends CommonEntity {
	@Id
	Long id;
	@Index
	Long toiId;
	@Index
	Long originalToiId;

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

	public Long getOriginalToiId() {
		return originalToiId;
	}

	public void setOriginalToiId(Long originalToiId) {
		this.originalToiId = originalToiId;
	}

	public CloneToi() {
	}

	public CloneToi(Long toiId, Long originalToiId) {
		setToiId(toiId);
		setOriginalToiId(originalToiId);
	}

	public CloneToi save() {
		Key<CloneToi> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public static final CloneToi getById(long id) {
		CloneToi toi = null;
		toi = ofy().load().type(CloneToi.class).id(id).now();
		return toi;
	}

	public static final List<CloneToi> getByToiId(long id) {
		List<CloneToi> toi = null;
		toi = (List<CloneToi>) loadByIndex(CloneToi.class, "toiId", id);
		return toi;
	}
	
	public static 
	 void reStoreAnswerSum(AnswerSum aSum) {
		CloneToi cloneToi = CloneToi.getByToiId(aSum.getToiId()).get(0);
		
		Long copiedToiId = cloneToi.getToiId();
		Long orgToiId = cloneToi.getOriginalToiId();

		Toi copiedToi = Toi.getById(copiedToiId);
		Toi orgToi = Toi.getById(orgToiId);
		
		System.out.println("AnswerSum.toiId:" + aSum.getToiId() + "->" + orgToi.getId());
		
		aSum.setToiId(orgToi.getId());
		aSum.save();
		
		List<Answer> ListAns = aSum.getAnswerList();
		for(Answer ans : ListAns) {
			Long qId = ans.getQuestionId();
			List<CloneQuestion> listCQ = CloneQuestion.getByQuestionId(qId);
			System.out.println("Answer.QuestionId:" + ans.getQuestionId() + "->" + listCQ.get(0).getOriginalQuestionId());
			ans.setQuestionId(listCQ.get(0).getOriginalQuestionId());
			ans.save();
		}
	}

}
