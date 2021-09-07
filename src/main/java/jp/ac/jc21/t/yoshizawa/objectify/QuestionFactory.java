/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

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

	public static final Question createMultiQuestion(Toi parent, long no, String name, long noOfOption,
			Integer[] answers) {
		Question q = createQuestion(parent, no, name, noOfOption);
		q.newAnswerSet();
		q.addAnswerSet(answers);
		return q;
	}
	public static final Question createMultiQuestion(Toi parent, long no, String name, long noOfOption,
			Set<Integer> answers) {
		Question q = createQuestion(parent, no, name, noOfOption);
		q.newAnswerSet();
		q.setAnswerSet(answers);
		return q;
	}

	private static final Question createQuestion(Toi parent, long no, String name, long noOfOption) {
		Question q = new Question();
		q.setCreated(new Date());
		q.setNo(no);
		q.setName(name);
		q.setNoOfOption(noOfOption);
		q.setToiId(parent.getId());
		return q;
	}

	public static final Question getById(long id) {
		return ofy().load().type(Question.class).id(id).now();
	}

	public static final String getKana(int i) {
		return Character.toString("アイウエオカキクケコサシスセソタチツテト".charAt(i));
	}

	public static final List<Question> loadAll() {

		List<Question> qList = ofy().load().type(Question.class).list();
		return qList;
	}

	public static final List<Question> getListByToiId(Long toiId) {

		List<Question> qList;
		qList = ofy().load().type(Question.class).filter("toiId", toiId).list();
		return qList;
	}

}
