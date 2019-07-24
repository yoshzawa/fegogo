/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public final class Member extends CommonEntity {

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

	public static List<Member> loadAll() {
		return loadAll(Member.class);
	}

	private static Member getByeMail(String eMail) {
		return ofy().load().type(Member.class).filterKey(Key.create(Member.class, eMail)).first().now();

	}

	public static Member createMember(String eMail) {
		Member m = new Member();
		m.seteMail(eMail);
		m.setCreated(new Date());
		m.setModified(new Date());
		m.newRefAnswerSumList();
		return m;
	}

	public static Member get(String email) {
		Member m = getByeMail(email);
		if (m == null) {
			m = createMember(email);
		}
		return m;
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
		boolean b = containsRef(ansSummary, l);
		if (b == false) {
			l.add(Ref.create(ansSummary));
		}
		setRefAnswerSumList(l);
	}

	public boolean containsRef(AnswerSum ansSummary) {
		List<Ref<AnswerSum>> l = getRefAnswerSumList();
		return containsRef(ansSummary, l);
	}

	public boolean containsRef(AnswerSum ansSummary, List<Ref<AnswerSum>> l) {
		boolean b = false;
		for (Ref<AnswerSum> ras : l) {
			if (ras.get().getId() == ansSummary.getId()) {
				b = true;
				break;
			}
		}
		return b;
	}

	/**
	 * parentIdに一致するExamに関連するAnswerSumの一覧を返す
	 * @param parentId 取得したいExamのId
	 * @return　parentIdに一致するExamに関連するAnswerSumの一覧
	 */
	public List<AnswerSum> getAnswerSumListByExamId(long parentId) {

		List<AnswerSum> list = new ArrayList<>();
		for (Ref<AnswerSum> as : getRefAnswerSumList()) {
			if (as.get().getRefToi().get().getParent().getId() != parentId) {
				continue;
			}
			list.add(as.get());
		}
		return list;
	}

}
