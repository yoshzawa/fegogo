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
	private List<Ref<AnswerSum>> refAnswerSumMap;
	private Date created;
	private Date modified;

	public Member() {
	}

	public Member save() {
		Key<Member> key = ofy().save().entity(this).now();
		return getByeMail(this.geteMail());
	}

//	public static Member getById(long id) {
//		return ofy().load().type(Member.class).id(id).now();
//	}

	public static Member getByeMail(String eMail) {
//		return ofy().load().type(Member.class).filter("eMail", eMail).first().now();
		return ofy().load().type(Member.class).filterKey(eMail).first().now();

	}

	public static Member createMember(String eMail) {
		Member m = new Member();
		m.seteMail(eMail);
		m.setCreated(new Date());
		m.setModified(new Date());
		m.newRefAnswerSumMap();
		return m;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public List<Ref<AnswerSum>> getRefAnswerSumMap() {
		if(refAnswerSumMap == null) {
			newRefAnswerSumMap();
		}
		return refAnswerSumMap;
	}
	public void newRefAnswerSumMap() {
		setRefAnswerSumMap(new ArrayList<>());
	}

	public void setRefAnswerSumMap(List<Ref<AnswerSum>> refAnswerSumMap) {
		this.refAnswerSumMap = refAnswerSumMap;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public static Member get(String email) {
		Member m = getByeMail(email);
		if(m==null) {
			m=createMember(email);
		}
		return m;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}


}
