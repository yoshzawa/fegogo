/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.googlecode.objectify.Ref;

/**
 * @author t.yoshizawa
 *
 */
public class AnswerFactory extends CommonEntity {

	private static Map<Long,Answer> cachedById = null;

	protected void flush() {
		cachedById = null;
	}
	
	private static boolean checkCachedById(Long id) {
		if (cachedById == null) {
			cachedById = new TreeMap<Long, Answer>();
			return false;
		}
		return cachedById.containsKey(id);
	}
	
	public static final Answer getById(long id) {
		Answer answer=null;
		if(checkCachedById(id) == false) {
			answer = ofy().load().type(Answer.class).id(id).now();
			cachedById.put(answer.getId(),answer);
			
		}else {
			answer=cachedById.get(id);
		}
		return answer;
	}

	public static final Answer createAnswer(String name, Ref<AnswerSum> refAnswerSum, Question question,
			String[] answerArray) {
		return createAnswer(name, refAnswerSum, question, answerArray, question.getNo());
	}

	public static final Answer createAnswer(String name, Ref<AnswerSum> refAnswerSum, Question question,
			String[] answerArray, long no) {
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

	public static final Answer createAnswer(String name, Ref<AnswerSum> refAnswerSum, Ref<Question> refQuestion,
			int[] answerArray, String no) {
		Answer a = new Answer();
		a.setName(name);
		a.setAnswered(new Date());
		a.setAnswerSumId(refAnswerSum.get().getId());
		a.setQuestionId(refQuestion.get().getId());
		a.setAnswerArray(answerArray);
		a.setNo(no);
		a.setVersion(ofyVersion);
		return a;
	}

	@SuppressWarnings("unchecked")
	public static List<Answer> loadAll() {
		return (List<Answer>) loadAll(Answer.class);
	}
	public static List<Answer> getByAnswerSumId(Long answerSumId, String name) {
		
	}
}
