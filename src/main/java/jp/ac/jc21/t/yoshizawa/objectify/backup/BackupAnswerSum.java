/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify.backup;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.Map;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.Member;

/**
 * @author t.yoshizawa
 *
 */

@Entity
@Cache
public final class BackupAnswerSum extends BackupAnswerSumFactory {

	@Id
	Long id;
	@Index
	private String name;
	private Date answered;
//	private Ref<Toi> refToi;
	private int noOfSeikai;
	private Map<String, Ref<Answer>> mapRefAnswer;
	private Ref<Member> refMember;
//	private String answerSumDumpCSV;

	String memberId;
	Long toiId;



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

	public Date getAnswered() {
		return answered;
	}

	public void setAnswered(Date answered) {
		this.answered = answered;
	}

	/*
	public Ref<Toi> getRefToi() {
		return refToi;
	}

	public void setRefToi(Ref<Toi> refToi) {
		this.refToi = refToi;
	}
	*/

	public int getNoOfSeikai() {
		return noOfSeikai;
	}

	public void setNoOfSeikai(int noOfSeikai) {
		this.noOfSeikai = noOfSeikai;
	}

	public Map<String, Ref<Answer>> getMapRefAnswer() {
		return mapRefAnswer;
	}

	public void setMapRefAnswer(Map<String, Ref<Answer>> mapRefAnswer) {
		this.mapRefAnswer = mapRefAnswer;
	}

	public Ref<Member> getRefMember() {
		return refMember;
	}

	public void setRefMember(Ref<Member> refMember) {
		this.refMember = refMember;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getToiId() {
		return toiId;
	}

	public void setToiId(Long toiId) {
		this.toiId = toiId;
	}

	public void delete() {
		ofy().delete().entity(this).now();
	}
	
	public BackupAnswerSum save() {
//		setRefId();
		Key<BackupAnswerSum> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}




}
