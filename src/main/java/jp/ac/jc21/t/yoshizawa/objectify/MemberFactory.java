/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;

/**
 * @author t.yoshizawa
 *
 */
public class MemberFactory extends CommonEntity{
	public static List<Member> loadAll() {
		return loadAll(Member.class);
	}

	protected static Member getByeMail(String eMail) {
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
}
