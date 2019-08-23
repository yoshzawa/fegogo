/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import com.googlecode.objectify.annotation.*;

/**
 * @author t.yoshizawa
 *
 */

@Entity
@Cache

public final class Toi extends ToiFactory {
	@Id
	Long id;
	@Index
	private Long no;
	private String name;
	private Date created;
	private Ref<Exam> parent;
	private List<Ref<Question>> questionRefList;
	private List<Ref<AnswerSum>> AnswerSumRefList;
//	private Map<String,List<Ref<AnswerSum>>> AnswerSumRefMap;
	private float sum;
	private Ref<Genre> genre;


	/**
	 * @return the average
	 */
	public float getAnswerSumSum() {
		if (sum==0) {
			calcAverage();
		}
		return sum;
	}

	/**
	 * @param average the average to set
	 */
	public void setAnswerSumSum(float sum) {
		this.sum = sum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the parent
	 */
	public Exam getExam() {

		Ref<Exam> re = parent;
		return re.get();

	}

	/**
	 * @param parent the parent to set
	 */
	public void setExam(Ref<Exam> exam) {
		this.parent = exam;
	}

	public void setExam(Exam exam) {
		setExam(Ref.create(exam));
	}

	public List<Ref<Question>> getQuestionRefList() {
		if (questionRefList == null) {
			newQuestionRefList();
		}
		return questionRefList;
	}

	public void addQuestionRefList(Question q) {
		addQuestionRefList(Ref.create(q));
	}

	public void addQuestionRefList(Ref<Question> q) {
		List<Ref<Question>> list = getQuestionRefList();
		list.add(q);
		setQuestionRefList(list);
	}

	public int getQuestionRefListSize() {
		List<Ref<Question>> questionRefList = getQuestionRefList();
		if (questionRefList == null) {
			return 0;
		} else {
			return questionRefList.size();
		}
	}

	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionRefList(List<Ref<Question>> questionRefList) {
		this.questionRefList = questionRefList;
	}

	public void newQuestionRefList() {
		setQuestionRefList(new ArrayList<Ref<Question>>());
	}

	/**
	 * @return the genre
	 */
	public Ref<Genre> getRefGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenreRef(Ref<Genre> genre) {
		this.genre = genre;
	}

	public void setGenre(Genre genre) {
		setGenreRef(Ref.create(genre));
	}

	public Toi save() {
		Key<Toi> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public List<Ref<AnswerSum>> getAnswerSumRefList() {
		if (AnswerSumRefList == null) {
			newAnswerSumRefList();
		}
		return AnswerSumRefList;
	}

	public int getAnswerSumRefListSize() {
		return getAnswerSumRefList().size();
	}

	private void newAnswerSumRefList() {
		setAnswerSumRefList(new ArrayList<Ref<AnswerSum>>());

	}

	public void setAnswerSumRefList(List<Ref<AnswerSum>> answerSumRefList) {
		AnswerSumRefList = answerSumRefList;
		
//		resetAnswerSumRefMap();
	}

	public boolean containsAnswerSum(AnswerSum as) {
		Long asId = as.getId();
		for (Ref<AnswerSum> r : getAnswerSumRefList()) {
			if (r.get().getId() == asId) {
				return true;
			}
		}
		return false;
	}

	public void addAnswerSumRefList(AnswerSum a) {
		List<Ref<AnswerSum>> list = getAnswerSumRefList();
		list.add(Ref.create(a));
		setAnswerSumRefList(list);

	}

	public AnswerSum getAnswerSumByMemberId(String email) {
		for (Ref<AnswerSum> as : getAnswerSumRefList()) {
			AnswerSum answerSum = as.get();
			Ref<Member> refMember = answerSum.getRefMember();
			if (refMember != null) {
				Member member = refMember.get();
				String geteMail = member.geteMail();
				if (geteMail.equals(email)) {
					return answerSum;
				}
			}
		}
		return null;
	}

	final void calcAverage() {
		float sum=0;
		for (Ref<AnswerSum> ras : getAnswerSumRefList()) {
			float temp = 100.0f*ras.get().getNoOfSeikai() / ras.get().getNoOfAnswer();
			sum += temp;
		}
		setAnswerSumSum(sum);
		save();
	}
	public final int getAnswerSumCount() {
		return getAnswerSumRefList().size();
	}

}
