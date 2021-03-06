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
	private List<Ref<AnswerSum>> refAnswerSumList;
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
		return getRefAnswerSumList().size();
	}

	public List<Ref<AnswerSum>> getRefAnswerSumList() {
		if (refAnswerSumList == null) {
			newRefAnswerSumList();
		}
		return refAnswerSumList;
	}

	public List<AnswerSum> getAnswerSumList() {
		List<AnswerSum> list = new ArrayList<>();
		for (Ref<AnswerSum> as : getRefAnswerSumList()) {
			list.add(as.get());
		}
		return list;
	}

	public List<AnswerSum> getAnswerSumListSorted() {
		List<AnswerSum> list = getAnswerSumList();

		list = sort(list);

		return list;
	}

	public void newRefAnswerSumList() {
		setRefAnswerSumList(new ArrayList<>());
	}

	public void setRefAnswerSumList(List<Ref<AnswerSum>> refAnswerSumMap) {
		this.refAnswerSumList = refAnswerSumMap;
	}

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

	public void addRefAnswerSumList(AnswerSum ansSummary) {
		List<Ref<AnswerSum>> l = getRefAnswerSumList();
		boolean b = containsRefAnswerSum(ansSummary, l);
		if (b == false) {
			l.add(Ref.create(ansSummary));
		}
		setRefAnswerSumList(l);
	}

	public boolean containsRefAnswerSum(AnswerSum ansSummary) {
		List<Ref<AnswerSum>> l = getRefAnswerSumList();
		return containsRefAnswerSum(Ref.create(ansSummary), l);
	}

	public boolean containsRefAnswerSum(Ref<AnswerSum> ansRefSummary) {
		List<Ref<AnswerSum>> l = getRefAnswerSumList();
		return containsRefAnswerSum(ansRefSummary, l);
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

	public boolean containsRefAnswerSum(Ref<AnswerSum> RefAnsSummary, List<Ref<AnswerSum>> l) {
		boolean b = false;
		b = l.contains(RefAnsSummary);
		return b;
	}

	/**
	 * parentIdに一致するExamに関連するAnswerSumの一覧を返す
	 * 
	 * @param parentId 取得したいExamのId
	 * @return parentIdに一致するExamに関連するAnswerSumの一覧
	 */
	public List<AnswerSum> getAnswerSumListByExamId(long parentId) {
		final Logger log = Logger.getLogger(Member.class.getName());

		List<AnswerSum> list = new ArrayList<>();
		if (getRefAnswerSumList() == null) {
			return list;
		}
		for (Ref<AnswerSum> as : getRefAnswerSumList()) {

			AnswerSum answerSum = as.get();
			if (answerSum == null) {
				log.warning("answerSum == null:MemberId=" + geteMail());
				continue;
			}

			Toi toi = answerSum.getRefToi().get();
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

	public void removeRefAnswerSumList(AnswerSum answerSum) {
		List<Ref<AnswerSum>> list = getRefAnswerSumList();
		List<Ref<AnswerSum>> listNew = new ArrayList<>();
		for (Ref<AnswerSum> refAnswerSum : list) {
			if (refAnswerSum.get().getId() != answerSum.getId()) {
				listNew.add(refAnswerSum);
			}
		}
		setRefAnswerSumList(listNew);
	}

	public List<AnswerSum> getAnswerSumListByToi(Long id) {
		List<AnswerSum> list = new ArrayList<AnswerSum>();
		for (Ref<AnswerSum> ras : getRefAnswerSumList()) {
			AnswerSum as = ras.get();
			if ((as.getRefToi() != null) && (as.getRefToi().get().getId() == id)) {
				list.add(as);
			}
		}
		return list;

	}

	public String getExportData() {

		return geteMail() + "," + getDateString(getCreated()) + "," + getDateString(getModified());
	}

}
