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
		if(aSum != null) {
			BackupAnswerSum bas = new BackupAnswerSum();
			bas.setId(aSum.getId());
			bas.setName(aSum.getName());
			bas.setRefToi(aSum.getRefToi());
			bas.setAnswered(aSum.getAnswered());
			bas.setMapRefAnswer(aSum.getMapRefAnswer());
			bas.setNoOfSeikai(aSum.getNoOfSeikai());
			return bas;
		}
		return null;
	}
	

	
	public static final BackupAnswerSum getById(long id) {
		return (BackupAnswerSum) getById(BackupAnswerSum.class,id);
	}
	
	@SuppressWarnings("unchecked")
	public static final List<BackupAnswerSum> loadAll() {
		return (List<BackupAnswerSum>) loadAll(BackupAnswerSum.class);
	}
}