/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.googlecode.objectify.Ref;

/**
 * @author t.yoshizawa
 *
 */
public class AnswerSumFactory extends CommonEntity {
	
	public static final AnswerSum createAnswerSum(String name,Ref<Toi> refToi,int noOfSeikai,Map<String,Ref<Answer>> mapRefAnswer) {
		AnswerSum as = new AnswerSum();
		as.setName(name);
		as.setRefToi(refToi);
		as.setAnswered(new Date());
		as.setMapRefAnswer(mapRefAnswer);
		as.setNoOfSeikai(noOfSeikai);
		as.setVersion(ofyVersion);
		return as;
	}
	
	public static final AnswerSum createAnswerSum(String name,Toi toi,int noOfSeikai,Map<String,Ref<Answer>> mapRefAnswer) {
		return createAnswerSum(name,Ref.create(toi),noOfSeikai,mapRefAnswer);
	}
	
	public static final AnswerSum getById(long id) {
		return (AnswerSum) getById(AnswerSum.class,id);
	}
	
	public static final Optional<AnswerSum> getOptById(long id) {
		return Optional.ofNullable(getById(id));
	}

	@SuppressWarnings("unchecked")
	public final static List<AnswerSum> loadByEMail(String eMail) {
		return (List<AnswerSum>)loadByIndex(AnswerSum.class,"name",eMail);
	}
	
	@SuppressWarnings("unchecked")
	public static final List<AnswerSum> loadAll() {
		return (List<AnswerSum>) loadAll(AnswerSum.class);
	}
	
	private static Map<Long,List<AnswerSum>> cachedMapByToiId = null;

	protected void flush() {
		cachedMapByToiId = null;
	}

	@SuppressWarnings("unchecked")
	public static final List<AnswerSum> getListByToiId(Long toiId) {
		final Logger log = Logger.getLogger(AnswerSum.class.getName());

		if(cachedMapByToiId==null) {
			cachedMapByToiId=new TreeMap<Long,List<AnswerSum>>();
		}
		List<AnswerSum> list;
		if(cachedMapByToiId.containsKey(toiId) == false) {
			list = ofy().load().type(AnswerSum.class).filter("toiId", toiId).list();
			cachedMapByToiId.put(toiId, list);
			log.info( "AnswerSum.getListByToiId:"+toiId+"[Miss]");

		} else {
			list = cachedMapByToiId.get(toiId);
			log.info( "AnswerSum.getListByToiId:"+toiId+"[Hit]");
		}
		return list;
	}
}
