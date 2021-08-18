/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

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

/**
 * @author t.yoshizawa
 *
 */

@Entity
@Cache
public final class AnswerSum extends AnswerSumFactory {

	@Id
	Long id;
	@Index
	private String name;
	@Index
	Long toiId;
	private Date answered;
	private int noOfSeikai;
//	private Map<String, Ref<Answer>> mapRefAnswer;
	private Ref<Member> refMember;

	String memberId;
	private	String version;
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	////////// id

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		if (memberId == null) {
			Ref<Member> refMem = getRefMember();
			if (refMem != null) {
				setMemberId(refMem.get().geteMail());
				save();
			}
		}
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the toiId
	 */
	public Long getToiId() {

		return toiId;
	}

	/**
	 * @param toiId the toiId to set
	 */
	public void setToiId(Long toiId) {
		this.toiId = toiId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	////////// mapRefAnswer

	/**
	public Map<String, Ref<Answer>> getMapRefAnswer() {
		Optional<Map<String, Ref<Answer>>> oMRA = getOptMapRefAnswer();
		if(oMRA.isPresent()) {
			return oMRA.get();
		}
		else {
			return new HashMap<String, Ref<Answer>> ();
		}
	}
	public Optional<Map<String, Ref<Answer>>> getOptMapRefAnswer() {
		return Optional.ofNullable(mapRefAnswer);
	}
	
	public void setMapRefAnswer(Map<String, Ref<Answer>> mapRefAnswer) {
		this.mapRefAnswer = mapRefAnswer;
	}
	*/
	public List<Answer> getAnswerList() {
		//TODO íÜêgÇÇøÇ·ÇÒÇ∆çÏÇÁÇ»Ç¢Ç∆ëÂïœÇ»Ç±Ç∆Ç…Ç»ÇÈ
		List<Answer> result = Answer.getByAnswerSumId(getId(),getName());
		return result;
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

	////////// refToi

	/**
	 * @return the refToi
	 */
	public Toi getToi() {
		return Toi.getById(getToiId());
	}
	public Optional<Toi> getOptToi() {
		return Optional.ofNullable(getToi());
	}

	/**
	 * @return the noOfAnswer
	 */
	public int getNoOfAnswer() {
		List<Answer> map = getAnswerList();
		if (map == null) {
			return 0;
		} else {
			return map.size();
		}
	}

	/**
	 * @return the noOfSeikai
	 */
	public int getNoOfSeikai() {
		return noOfSeikai;
	}

	/**
	 * @param noOfSeikai the noOfSeikai to set
	 */
	public void setNoOfSeikai(int noOfSeikai) {
		this.noOfSeikai = noOfSeikai;
	}

	public Map<Integer, Answer> getMapAnswer() {
		List<Answer> mra = getAnswerList();
		Map<Integer, Answer> mapAnswer = new HashMap<Integer, Answer>();

		if (mra != null) {
			for (Answer k : mra) {
				mapAnswer.put(Integer.parseInt(k.getNo()), k);
			}
		}
		return mapAnswer;

	}

	public Ref<Member> getRefMember() {
		return refMember;
	}

	public void setRefMember(Ref<Member> refMember) {
		this.refMember = refMember;
		setMemberId(refMember.get().geteMail());
	}

	public void setMember(Member member) {
		setRefMember(Ref.create(member));
	}

	public void delete() {
		ofy().delete().entity(this).now();
	}

	public AnswerSum() {
	}

	public AnswerSum save() {
		Key<AnswerSum> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public boolean isRefId() {
		if (memberId == null) {
			return false;
		}
		if (toiId == null) {
			return false;
		}
		return true;
	}

	public String makeAnswerDumpCSV(javax.cache.Cache cache) {

		String key = "AnswerSum:" + getId();
		if (cache.containsKey(key) == true) {
			String value = (String) cache.get(key);
			return value;
		} else {
			Toi toi = getOptToi().get();
			Exam exam = toi.getExam();
			String s = getId() + "," + getName() + "," + getDateString(getAnswered()) + "," 
			+ exam.getName() + "," + toi.getNo() + "," + toi.getGenre().getNo() + "," + 
					toi.getGenre().getName()
					+ "," + toi.getName() + ",";
			cache.put(key, s);
			return s;
		}
	}

	public String getExportData() {

		return getId() + "," + 
		getName() + "," + 
		getDateString(getAnswered()) + "," + 
		getToiId() + "," + 
		getMemberId()				+ "," + 
		getNoOfAnswer() + "," + 
		getNoOfSeikai();

	}

	public Optional<Member> getMember(){
		Optional<Ref<Member>> optRefMem = Optional.ofNullable(getRefMember());

		Optional<Member> optMem = optRefMem.map(refMem -> refMem.get());
		return optMem; 
	}

	public boolean containAnswer(Long answerId) {
		Answer ans=Answer.getById(answerId);
		return getAnswerList().contains(ans);
	}

}
