/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

/**
 * @author t.yoshizawa
 *
 */
public class QuestionFactory extends CommonEntity {
	
	public static final Question createQuestion(Toi parent, long no, String name, long noOfOption, int answer) {
		Question q = createQuestion(parent, no, name, noOfOption);
		q.addAnswerSet(answer);
		return q;
	}

	public static final Question createMultiQuestion(Toi parent, long no, String name, long noOfOption, Integer[] answers) {
		Question q = createQuestion(parent, no, name, noOfOption);
		q.addAnswerSet(answers);
		return q;
	}

	private static final Question createQuestion(Toi parent, long no, String name, long noOfOption) {
		Question q = new Question();
		q.setCreated(new Date());
		q.setNo(no);
		q.setName(name);
		q.setNoOfOption(noOfOption);
		q.setParent(parent);
		return q;
	}
	
	public static final Question getById(long id) {
		return ofy().load().type(Question.class).id(id).now();
	}

	public static final String getKana(int i) {
		return Character.toString( "アイウエオカキクケコサシスセソタチツテト".charAt(i));
	}
	public static final List<Question>  loadAll() {

		List<Question> qList = ofy().load().type(Question.class).list();

		return qList;
	}
	
}
