/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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
public final class Member extends MemberFactory {

	@Id
//	Long id;
	@Index
	private String eMail;
//	private List<Ref<AnswerSum>> refAnswerSumList;
	private Date created;
	private Date modified;

	public Member() {
	}

	public Member save() {
		ofy().save().entity(this).now();
		return getByeMail(this.geteMail());
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public int getRefAnswerSumListCount() {
		return getAnswerSumList().size();
	}

	/*
	public List<Ref<AnswerSum>> getRefAnswerSumList() {
		if (refAnswerSumList == null) {
			newRefAnswerSumList();
		}
		return refAnswerSumList;
	}*/

	public List<AnswerSum> getAnswerSumList() {
		return AnswerSum.getListByEMail(geteMail());
	}

	public List<AnswerSum> getAnswerSumListSorted() {
		List<AnswerSum> list = getAnswerSumList();

		list = sort(list);

		return list;
	}

	/*
	public void newRefAnswerSumList() {
		setRefAnswerSumList(new ArrayList<>());
	}

	public void setRefAnswerSumList(List<Ref<AnswerSum>> refAnswerSumMap) {
		this.refAnswerSumList = refAnswerSumMap;
	}
		public void addRefAnswerSumList(AnswerSum ansSummary) {
		List<AnswerSum> l = getAnswerSumList();
		boolean b = containsRefAnswerSum(ansSummary, l);
		if (b == false) {
			l.add(Ref.create(ansSummary));
		}
		setRefAnswerSumList(l);
	}
	public void removeRefAnswerSumList(AnswerSum answerSum) {
		List<AnswerSum> list = getAnswerSumList();
		List<Ref<AnswerSum>> listNew = new ArrayList<>();
		for (Ref<AnswerSum> refAnswerSum : list) {
			if (refAnswerSum.get().getId() != answerSum.getId()) {
				listNew.add(refAnswerSum);
			}
		}
		setRefAnswerSumList(listNew);
	}
	*/

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}


	public boolean containsRefAnswerSum(AnswerSum ansSummary) {
		List<AnswerSum> l = getAnswerSumList();
		return containsAnswerSum(ansSummary, l);
	}

	public boolean containsAnswerSum(AnswerSum ansRefSummary) {
		List<AnswerSum> l = getAnswerSumList();
		return containsAnswerSum(ansRefSummary, l);
	}

	public boolean containsRefAnswerSum(AnswerSum ansSummary, List<Ref<AnswerSum>> l) {
		boolean b = false;
		for (Ref<AnswerSum> ras : l) {
			if (ras.get().getId() == ansSummary.getId()) {
				b = true;
				break;
			}
		}
		return b;
	}

	public boolean containsAnswerSum(AnswerSum AnsSummary, List<AnswerSum> l) {
		boolean b = false;
		b = l.contains(AnsSummary);
		return b;
	}

	/**
	 * parentId‚Éˆê’v‚·‚éExam‚ÉŠÖ˜A‚·‚éAnswerSum‚Ìˆê——‚ð•Ô‚·
	 * 
	 * @param parentId Žæ“¾‚µ‚½‚¢Exam‚ÌId
	 * @return parentId‚Éˆê’v‚·‚éExam‚ÉŠÖ˜A‚·‚éAnswerSum‚Ìˆê——
	 */
	public List<AnswerSum> getAnswerSumListByExamId(long parentId) {
		final Logger log = Logger.getLogger(Member.class.getName());

		List<AnswerSum> list = new ArrayList<>();
		if (getAnswerSumList() == null) {
			return list;
		}
		for (AnswerSum answerSum : getAnswerSumList()) {

			if (answerSum == null) {
				log.warning("answerSum == null:MemberId=" + geteMail());
				continue;
			}

			Toi toi = answerSum.getOptToi().get();
			if (toi == null) {
				log.warning("toi == null:MemberId=" + geteMail());
				continue;
			}

			Exam exam = toi.getExam();
			if (exam == null) {
				log.warning("exam == null:MemberId=" + geteMail());
				continue;
			}
			if (exam.getId() != parentId) {
				continue;
			}
			list.add(answerSum);
		}
		return list;
	}



	public List<AnswerSum> getAnswerSumListByToi(Long id) {
		List<AnswerSum> list = new ArrayList<AnswerSum>();
		for (AnswerSum as : getAnswerSumList()) {
			if ((as.getOptToi() != null) && (as.getOptToi().get().getId() == id)) {
				list.add(as);
			}
		}
		return list;

	}

	public String getExportData() {

		return geteMail() + "," + getDateString(getCreated()) + "," + getDateString(getModified());
	}

}
