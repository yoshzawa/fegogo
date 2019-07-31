/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

import jp.ac.jc21.t.yoshizawa.admin.QuestionListAdminServlet;

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

	public AnswerSum() {
	}

	public AnswerSum save() {
		Key<AnswerSum> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

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
	}

	/**
	 * @return the noOfAnswer
	 */
	public int getNoOfAnswer() {
		return getMapRefAnswer().size();
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

		Set<String> mraKey = mra.keySet();
		for (String k : mraKey) {
			mapAnswer.put(Integer.parseInt(k), mra.get(k).get());
		}
		return mapAnswer;

	}

	public Ref<Member> getRefMember() {
		return refMember;
	}

	public void setRefMember(Ref<Member> refMember) {
		this.refMember = refMember;
	}

	public void setMember(Member member) {
		setRefMember(Ref.create(member));
	}
	public void delete() {
//		ofy().delete().entity(this).now();
		
		final Logger log = Logger.getLogger(AnswerSum.class.getName());

		Member member = getRefMember().get();
		Toi toi = getRefToi().get();
		member.removeRefAnswerSumList(this);
		member.save();
		setRefMember(null);
//		toi.get
		save();
		
	}

}
