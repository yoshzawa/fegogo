/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
public final class AnswerSum extends AnswerSumFactory {

	@Id
	Long id;
	@Index
	private String name;
	private Date answered;
	private Ref<Toi> refToi;
	private int noOfSeikai;
	private Map<String, Ref<Answer>> mapRefAnswer;
	private Ref<Member> refMember;
//	private String answerSumDumpCSV;

	String memberId;
	Long toiId;

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
		if (toiId == null) {
			setToiId(getRefToi().get().getId());
			save();
		}
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
	 * @return the mapRefAnswer
	 */
	public Map<String, Ref<Answer>> getMapRefAnswer() {
		return mapRefAnswer;
	}

	/**
	 * @param mapRefAnswer the mapRefAnswer to set
	 */
	public void setMapRefAnswer(Map<String, Ref<Answer>> mapRefAnswer) {
		this.mapRefAnswer = mapRefAnswer;
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
	public Ref<Toi> getRefToi() {
		return refToi;
	}

	/**
	 * @param refToi the refToi to set
	 */
	public void setRefToi(Ref<Toi> refToi) {
		this.refToi = refToi;
		setToiId(refToi.get().getId());
	}

	/**
	 * @return the noOfAnswer
	 */
	public int getNoOfAnswer() {
		Map<String, Ref<Answer>> map = getMapRefAnswer();
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
		Map<String, Ref<Answer>> mra = getMapRefAnswer();
		Map<Integer, Answer> mapAnswer = new HashMap<Integer, Answer>();

		if (mra != null) {
			Set<String> mraKey = mra.keySet();
			for (String k : mraKey) {
				mapAnswer.put(Integer.parseInt(k), mra.get(k).get());
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
//		ofy().delete().entity(this).now();

//		final Logger log = Logger.getLogger(AnswerSum.class.getName());

		Member member = getRefMember().get();
		Toi toi = getRefToi().get();
		member.removeRefAnswerSumList(this);
		member.save();
		setRefMember(null);
		save();

	}
	/**
	 * @return the answerSumDumpCSV
	 */
//	public String getAnswerSumDumpCSV() {
//		return answerSumDumpCSV;
//	}

	/**
	 * @param answerSumDumpCSV the answerSumDumpCSV to set
	 */
//	public void setAnswerSumDumpCSV(String answerSumDumpCSV) {
//		this.answerSumDumpCSV = answerSumDumpCSV;
//	}

	public AnswerSum() {
	}

	public AnswerSum save() {
//		setRefId();
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

	public void setRefId() {
		if (getMemberId() == null) {
			setMemberId(getRefMember().get().geteMail());
		}
		if (toiId == null) {
			setToiId(getRefToi().get().getId());
		}
	}

	public String makeAnswerDumpCSV(javax.cache.Cache cache) {

		String key = "AnswerSum:" + getId();
		if (cache.containsKey(key) == true) {
			String value = (String) cache.get(key);
			return value;
		} else {
			Toi toi = getRefToi().get();
			Exam exam = toi.getExam();
			String s = getId() + "," + getName() + "," + getDateString(getAnswered()) + "," + exam.getName() + ","
					+ toi.getNo() + "," + toi.getRefGenre().get().getNo() + "," + toi.getRefGenre().get().getName()
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
//		Optional<Member> optMem =Optional.ofNullable(null); 
//		if(optRefMem.isPresent()) {
//			optMem=Optional.ofNullable(optRefMem.get().get());
//		}else {
//			optMem=Optional.ofNullable(null);
//		}

		Optional<Member> optMem = optRefMem.map(refMem -> refMem.get());
		return optMem; 
	}
	public Optional<Toi> getToi(){
		Optional<Ref<Toi>> optRefToi = Optional.ofNullable(getRefToi());
		Optional<Toi> optToi =null;
		if(optRefToi.isPresent()) {
			optToi = Optional.ofNullable(optRefToi.get().get());
		}else {
//			optToi = Optional.ofNullable(null);
			optToi = Optional.empty();
		}
		return optToi;
		
	}
	

}
