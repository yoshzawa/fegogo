/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import com.googlecode.objectify.Ref;

/**
 * @author t.yoshizawa
 *
 */
public class AnswerSumFactory extends CommonEntity {
	
	public static final AnswerSum createAnswerSum(String name,Toi Toi,int noOfSeikai,Map<String,Ref<Answer>> mapRefAnswer) {
		AnswerSum as = new AnswerSum();
		as.setName(name);
		as.setToiId(Toi.getId());
		as.setAnswered(new Date());
		as.setNoOfSeikai(noOfSeikai);
		as.setVersion(ofyVersion);
		return as;
	}
	
	public static final AnswerSum getById(long id) {
		return (AnswerSum) getById(AnswerSum.class,id);
	}
	
	public static final Optional<AnswerSum> getOptById(long id) {
		return Optional.ofNullable(getById(id));
	}

	
	@SuppressWarnings("unchecked")
	public static final List<AnswerSum> loadAll() {
		return (List<AnswerSum>) loadAll(AnswerSum.class);
	}
	
	public static final List<AnswerSum> loadAll2() {
		List<Member> list = Member.loadAll();
		List<AnswerSum> allList = new ArrayList<AnswerSum>();
		for(Member m:list) {
			List<AnswerSum> asList = AnswerSum.getListByEMail(m.geteMail());
			allList.addAll(asList);
		}
		return allList;
	}

	public final static List<AnswerSum> getListByEMail(String eMail) {
		List<AnswerSum> list=null;
			 list = (List<AnswerSum>)loadByIndex(AnswerSum.class,"name",eMail);
		return list;
	}

	
	public static final List<AnswerSum> getListByToiId(Long toiId) {
		List<AnswerSum> list;
			list = ofy().load().type(AnswerSum.class).filter("toiId", toiId).list();

		return list;
	}
}
