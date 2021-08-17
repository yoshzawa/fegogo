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

	protected static Member getByeMail(String eMail) {
		return ofy().load().type(Member.class).filterKey(Key.create(Member.class, eMail)).first().now();

	}

	public static Member createMember(String eMail) {
		Member m = new Member();
		m.seteMail(eMail);
		m.setCreated(new Date());
		m.setModified(new Date());
//		m.newRefAnswerSumList();
		return m;
	}

	public static Member get(String email) {
		Member m = getByeMail(email);
		if (m == null) {
			m = createMember(email);
		}
		return m;
	}
	public static List<AnswerSum> sort(List<AnswerSum> list) {
		Collections.sort(list, new Comparator<AnswerSum>() {

			public int compare(AnswerSum a, AnswerSum b) {

//				return Integer.compare(personFirst.getId(), personSecond.getId());
				Long aYYYYMM = a.getOptToi().get().getExam().getYYYYMM();
				Long bYYYYMM = b.getOptToi().get().getExam().getYYYYMM();
				if (aYYYYMM != bYYYYMM) {
					return (int) (aYYYYMM - bYYYYMM);
				}
				Long ano = a.getOptToi().get().getNo();
				Long bno = b.getOptToi().get().getNo();
				if (ano != bno) {
					return (int) (ano - bno);
				}
				return a.getAnswered().compareTo(b.getAnswered());
			}

		});
		return list;
	}
}
