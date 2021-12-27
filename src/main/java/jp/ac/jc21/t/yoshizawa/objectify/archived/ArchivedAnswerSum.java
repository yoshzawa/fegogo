/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify.archived;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;

/**
 * @author t.yoshizawa
 * ï°êîÇÃAnswerSumÇÇ‹Ç∆ÇﬂÇÈÇÃÇ≈ÅAâÒìöì˙ÇÕÇ»Ç≠Ç»ÇËÅAñ‚ëËêîÇ∆ê≥âêîÇÕçáåvÇ≥ÇÍÇÈ
 */

@Entity
@Cache
public final class ArchivedAnswerSum extends ArchivedAnswerSumFactory {

	@Id
	Long id;
	@Index
	private String name;
	@Index
	Long toiId;
//	private Date answered;
	private int noOfSeikai;
	private Date answered;
	private	String version;
	private int noOfAnswer;
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
	public Long getToiId() {
		return toiId;
	}
	public void setToiId(Long toiId) {
		this.toiId = toiId;
	}

	public int getNoOfSeikai() {
		return noOfSeikai;
	}
	public void setNoOfSeikai(int noOfSeikai) {
		this.noOfSeikai = noOfSeikai;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getNoOfAnswer() {
		return noOfAnswer;
	}
	public void setNoOfAnswer(int noOfAnswer) {
		this.noOfAnswer = noOfAnswer;
	}
	
	public Date getAnswered() {
		return answered;
	}
	public void setAnswered(Date answered) {
		this.answered = answered;
	}
	public ArchivedAnswerSum save() {
		Key<ArchivedAnswerSum> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}
	
}
