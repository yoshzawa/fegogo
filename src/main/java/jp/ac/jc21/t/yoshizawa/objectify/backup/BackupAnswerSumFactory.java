/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify.backup;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.CommonEntity;

/**
 * @author t.yoshizawa
 *
 */
public class BackupAnswerSumFactory extends CommonEntity {
	
	public static final BackupAnswerSum createAnswerSum(AnswerSum aSum) {
		BackupAnswerSum as = new BackupAnswerSum();
		as.setId(aSum.getId());
		as.setName(aSum.getName());
		as.setRefToi(aSum.getRefToi());
		as.setAnswered(aSum.getAnswered());
		as.setMapRefAnswer(aSum.getMapRefAnswer());
		as.setNoOfSeikai(aSum.getNoOfSeikai());
		return as;
	}
	

	
	public static final BackupAnswerSum getById(long id) {
		return (BackupAnswerSum) getById(BackupAnswerSum.class,id);
	}
	
	@SuppressWarnings("unchecked")
	public static final List<BackupAnswerSum> loadAll() {
		return (List<BackupAnswerSum>) loadAll(BackupAnswerSum.class);
	}
}
