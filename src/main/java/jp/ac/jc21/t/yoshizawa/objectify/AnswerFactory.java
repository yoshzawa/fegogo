/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;

import com.googlecode.objectify.Ref;

/**
 * @author t.yoshizawa
 *
 */
public class AnswerFactory extends CommonEntity {
	public static Answer getById(long id) {
		return ofy().load().type(Answer.class).id(id).now();
	}

	public static Answer createAnswer(String name, Ref<AnswerSum> refAnswerSum, Question question, String[] answerArray,
			long no) {
		int answerIntArray[] = null;
		if (answerArray != null) {
			answerIntArray = new int[answerArray.length];
			for (int i = 0; i < answerArray.length; i++) {
				answerIntArray[i] = Integer.parseInt(answerArray[i]);
			}
		}

		String noString = "0" + no;
		if (noString.length() > 2) {
			noString = noString.substring(noString.length() - 2);
		}

		return createAnswer(name, refAnswerSum, Ref.create(question), answerIntArray, noString);
	}

	public static Answer createAnswer(String name, Ref<AnswerSum> refAnswerSum, Ref<Question> refQuestion,
			int[] answerArray, String no) {
		Answer a = new Answer();
		a.setName(name);
		a.setAnswered(new Date());
		a.setRefAnswerSum(refAnswerSum);
		a.setRefQuestion(refQuestion);
		a.setAnswerArray(answerArray);
		a.setNo(no);
		return a;
	}
}
