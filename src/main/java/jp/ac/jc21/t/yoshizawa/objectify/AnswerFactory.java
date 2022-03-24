/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.CommonFunction;

/**
 * @author t.yoshizawa
 *
 */
public class AnswerFactory extends CommonEntity {

	public static final Answer getById(long id) {
		Answer answer = null;
		answer = ofy().load().type(Answer.class).id(id).now();

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
		a.setVersion(CommonFunction.ofyVersion);
		return a;
	}

	@SuppressWarnings("unchecked")
	public static List<Answer> loadAll() {
		return (List<Answer>) loadAll(Answer.class);
	}

	public static List<Answer> getByAnswerSumId_old(Long answerSumId, String name) {
		final Logger log = Logger.getLogger(Answer.class.getName());

		// EMAILÇ≈à¯Ç¡í£ÇÈÅiCacheä‹ÇﬂÇƒÅj
		List<Answer> listByEMail = getByEMail(name);

		Map<Long, List<Answer>> cachedMapByAnswerSumId = new TreeMap<Long, List<Answer>>();
		// answerSumIdÇ≈listÇ…ÇµÇƒCacheÇ…ï™ÇØÇÈ
		for (Answer a : listByEMail) {
			List<Answer> list;
			if ((list = cachedMapByAnswerSumId.get(a.getAnswerSumId())) == null) {
				list = new ArrayList<Answer>();
			}
			list.add(a);
			list = sort(list);
			cachedMapByAnswerSumId.put(a.getAnswerSumId(), list);
		}

		// éÊÇËèoÇµÇƒï‘Ç∑
		return cachedMapByAnswerSumId.get(answerSumId);
	}

	@SuppressWarnings("unchecked")
	public static List<Answer> getByAnswerSumId(Long answerSumId, String name) {
		List<Answer> list = null;
		list = (List<Answer>) loadByIndex(Answer.class, "answerSumId", answerSumId);
		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<Answer> getByEMail(String name) {
		List<Answer> list = null;
		list = (List<Answer>) loadByIndex(Answer.class, "name", name);
		return list;
	}

	private static List<Answer> sort(List<Answer> list) {
		list.sort(new Comparator<Answer>() {
			@Override
			public int compare(Answer a1, Answer a2) {
				return Integer.parseInt(a1.getNo()) - Integer.parseInt(a2.getNo());
			}
		});
		return list;
	}

	public final static List<Answer> getListByEMail(String eMail) {

		List<Answer> list = null;
		list = (List<Answer>) loadByIndex(Answer.class, "name", eMail);

		return list;
	}

	public final static Map<Long, List<Answer>> makeMapByQuestionId(List<Answer> answerList) {
		Map<Long, List<Answer>> map2 = answerList.stream().filter((Answer a) -> {
			return Objects.nonNull(a);
		}).collect(Collectors.groupingBy(Answer::getQuestionId));
		return map2;
	}
}
