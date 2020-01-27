/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify.backup;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.*;

/**
 * @author t.yoshizawa
 *
 */
public class BackupAnswerFactory extends CommonEntity {
	
	public static final BackupAnswer getById(long id) {
		return ofy().load().type(BackupAnswer.class).id(id).now();
	}

	public static final BackupAnswer createBackupAnswer(Answer answer) {
		BackupAnswer a = new BackupAnswer();
		a.setId(answer.getId());
		a.setName(answer.getName());
		a.setAnswered(answer.getAnswered());
		a.setRefAnswerSum(answer.getRefAnswerSum());
		a.setRefQuestion(answer.getRefQuestion());
		a.setAnswerArray(answer.getAnswerArray());
		a.setNo(answer.getNo());
		return a;
	}
	public static List<BackupAnswer> loadAll() {
		return (List<BackupAnswer>) loadAll(BackupAnswer.class);
	}
}
