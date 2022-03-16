/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;

/**
 * @author t.yoshizawa
 *
 */
public class MemberFactory extends CommonEntity{
	@SuppressWarnings("unchecked")
	public static List<Member> loadAll() {
		return (List<Member>) loadAll(Member.class);
	}

	public static Member getByeMail(String eMail) {
		return ofy().load().type(Member.class).filterKey(Key.create(Member.class, eMail)).first().now();

	}

	public static Member createMember(String eMail) {
		Member m = new Member();
		m.seteMail(eMail);
		m.setCreated(new Date());
		m.setModified(new Date());
		return m;
	}

	public static Member get(String email) {
		Member m = getByeMail(email);
		if (m == null) {
			m = createMember(email);
		}
		return m;
	}

	
	public static List<Member> orderListByModified(List<Member> list) {
		Collections.sort(list, new Comparator<Member>() {

			public int compare(Member a, Member b) {

				return a.getModified().compareTo(b.getModified());
			}

		});
		return list;
	}
	public static List<Member> orderListByEMail(List<Member> list) {
		Collections.sort(list, new Comparator<Member>() {

			public int compare(Member a, Member b) {

				return a.geteMail().compareTo(b.geteMail());
			}

		});
		return list;
	}
}
