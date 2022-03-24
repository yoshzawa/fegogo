/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import com.googlecode.objectify.annotation.*;

import jp.ac.jc21.t.yoshizawa.CommonFunction;

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
//	private List<Ref<AnswerSum>> AnswerSumRefList;
	private float sum;

	@Index
	Long genreId;
	@Index
	Long examId;

	String genreName;
	String examName;

	private List<ImageSet> ImageSet;

	/**
	 * @return the imageSet
	 */
	public List<ImageSet> getImageSet() {
		return ImageSet;
	}

	/**
	 * @param imageSet the imageSet to set
	 */
	public void setImageSet(List<ImageSet> imageSet) {
		ImageSet = imageSet;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getGenreName() {
		if (genreName == null) {
			genreName = getGenre().getName();
			save();
		}
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	/**
	 * @param examId the examId to set
	 */
	public void setExamId(Long examId) {
		this.examId = examId;
	}

	/**
	 * 
	 * @return the average
	 */
	public float getAnswerSumSum() {
		if (sum < 0) {
			calcAverage();
		}
		return sum;
	}

	/**
	 * @param average the average to set
	 */
	private final void setAnswerSumSum(float sum) {
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

	public List<Question> getQuestionList() {
		return Question.getListByToiId(getId());
	}

	public int getQuestionListSize() {
		List<Question> questionRefList = getQuestionList();
		if (questionRefList == null) {
			return 0;
		} else {
			return questionRefList.size();
		}
	}

	public void setGenre(Genre genre) {
		setGenreId(genre.getId());
	}



	public List<AnswerSum> getAnswerSumList() {
		return AnswerSum.getListByToiId(getId());
	}

	public int getAnswerSumListSize() {
		return getAnswerSumList().size();
	}

	public boolean containsAnswerSum(AnswerSum as) {
		Long asId = as.getId();
		for (AnswerSum answerSum : getAnswerSumList()) {
			if ((answerSum != null) && (answerSum.getId() == asId)) {
				return true;
			}
		}
		return false;
	}

	public AnswerSum getAnswerSumByMemberId(String email) {
		for (AnswerSum answerSum : getAnswerSumList()) {
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
		float sum = 0;
		for (AnswerSum answerSum : getAnswerSumList()) {
			if (answerSum != null) {
				float temp = 100.0f * answerSum.getNoOfSeikai() / answerSum.getNoOfAnswer();
				sum += temp;
			}
		}
		setAnswerSumSum(sum);
		save();
	}

	public final int getAnswerSumCount() {
		return getAnswerSumList().size();
	}

	public boolean containAnswer(Long toiId) {

		Ref<AnswerSum> refASum = Ref.create(Key.create(AnswerSum.class, toiId));
		return getAnswerSumList().contains(refASum);

	}

	public boolean containQuestion(Question q) {
		return getQuestionList().contains(q);
	}

	public Long getGenreId() {
		return genreId;
	}

	public Long getExamId() {
		return examId;
	}

	public Exam getExam() {
		return getOptExam().get();
	}

	public Genre getGenre() {
		return getOptGenre().get();
	}

	public Optional<Exam> getOptExam() {

		Optional<Exam> optExam = Optional.ofNullable(Exam.getById(getExamId()));
		return optExam;
	}

	public Optional<Genre> getOptGenre() {

		Optional<Genre> optGenre = Optional.ofNullable(Genre.getById(getGenreId()));
		return optGenre;
	}

	public String getExamName() {
		if (examName == null) {
			examName = getExam().getName();
		}
		return examName;
	}

	public String getExportData() {

		return getId() + "," + getNo() + "," + getName() + "," + CommonFunction.dateFormat(getCreated()) + "," + getExamId() + ","
				+ getGenreId() + "," + getAnswerSumSum();
	}
	
	public Toi save() {
		Key<Toi> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public void delete() {
		ofy().delete().entity(this).now();
	}
}