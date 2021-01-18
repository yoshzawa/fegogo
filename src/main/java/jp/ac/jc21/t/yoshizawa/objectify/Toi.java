/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	private List<Ref<Question>> questionRefList;
	private List<Ref<AnswerSum>> AnswerSumRefList;
	private float sum;

//	private Ref<Exam> parent;
//	private Ref<Genre> genre;
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
		if(genreName == null) {
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



	public void setGenre(Genre genre) {
//		setRefGenre(Ref.create(genre));
		setGenreId(genre.getId());
	}

	public Toi save() {
//		setRefId();
		Key<Toi> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public boolean isRefId() {
		if (genreId == null) {
			return false;
		}
		if (examId == null) {
			return false;
		}
		return true;
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

	}

	public boolean containsAnswerSum(AnswerSum as) {
		Long asId = as.getId();
		for (Ref<AnswerSum> r : getAnswerSumRefList()) {
			AnswerSum answerSum = r.get();
			if ((answerSum != null) && (answerSum.getId() == asId)) {
				return true;
			}
		}
		return false;
	}

	public void addAnswerSumRefList(AnswerSum a) {
		List<Ref<AnswerSum>> list = getAnswerSumRefList();
		list.add(Ref.create(a));
		setAnswerSumRefList(list);

		calcAverage();
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
		float sum = 0;
		for (Ref<AnswerSum> ras : getAnswerSumRefList()) {
			AnswerSum answerSum = ras.get();
			if (answerSum != null) {
				float temp = 100.0f * answerSum.getNoOfSeikai() / answerSum.getNoOfAnswer();
				sum += temp;
			}
		}
		setAnswerSumSum(sum);
		save();
	}

	public final int getAnswerSumCount() {
		return getAnswerSumRefList().size();
	}



	public boolean containAnswer(Long toiId) {

		Ref<AnswerSum> refASum = Ref.create(Key.create(AnswerSum.class, toiId));
		return getAnswerSumRefList().contains(refASum);

	}

	public boolean containQuestion(Question q) {
		return getQuestionRefList().contains(Ref.create(q));

	}
	
	

	public Long getGenreId() {
		return genreId;
	}

	public Long getExamId() {
		return examId;
	}

	/**


	public Ref<Genre> getRefGenre() {
		return genre;
	}

	public void setRefGenre(Ref<Genre> genre) {
		this.genre = genre;
		setGenreName(genre.get().getName());
	}
	public void setRefId() {
		if (genreId == null) {
			genreId = genre.get().getId();
		}
		if (examId == null) {
			examId = parent.get().getId();
		}
	}

	public void setExam(Exam exam) {
		setRefExam(Ref.create(exam));
	}
	
	public Ref<Exam> getRefExam() {
		Ref<Exam> re = parent;
		return re;
	}

	public Long getExamId() {
		if (examId == null) {
			setExamId(parent.get().getId());
			save();
		}
		return examId;
	}
	
	
	public void setRefExam(Ref<Exam> exam) {
		this.parent = exam;
	}
	
	public Exam getExam() {
		Ref<Exam> re = parent;
		return re.get();
	}
	
	public Long getGenreId() {
		if (genreId == null) {
			setGenreId(getRefGenre().get().getId());
			save();
		}
		return genreId;
	}
	 */

	public Exam getExam() {
		return getOptExam().get();
	}
	public Genre getGenre() {
		return getOptGenre().get();
	}
	

	public Optional<Exam> getOptExam() {

//		Optional<Ref<Exam>> optReExam = Optional.ofNullable(parent);
//		Optional<Exam> optExam = optReExam.map(reExam -> reExam.get());
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

		return getId() + "," + getNo() + "," + getName() + "," + getDateString(getCreated()) +
				"," + getExamId() + "," + getGenreId() + "," + getAnswerSumSum();
	}





}