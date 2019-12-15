/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
		return as;
	}
	
	public static final AnswerSum createAnswerSum(String name,Toi toi,int noOfSeikai,Map<String,Ref<Answer>> mapRefAnswer) {
		return createAnswerSum(name,Ref.create(toi),noOfSeikai,mapRefAnswer);
	}
	
	public static final AnswerSum getById(long id) {
		return (AnswerSum) getById(AnswerSum.class,id);
	}
	
	@SuppressWarnings("unchecked")
	public static final List<AnswerSum> loadAll() {
		return (List<AnswerSum>) loadAll(AnswerSum.class);
	}
}
