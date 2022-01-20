package jp.ac.jc21.t.yoshizawa.objectify.archived;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jp.ac.jc21.t.yoshizawa.CommonFunction;
import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.CommonEntity;

public class ArchivedAnswerFactory extends CommonEntity {
	public static List<ArchivedAnswer> generate(List<Answer> list, Long toiId) {
		if (list.size() == 0)
			return null;
		Long questionId = list.get(0).getQuestionId();
		String name = list.get(0).getName();
		String no = list.get(0).getNo();

		List<ArchivedAnswer> listAA = new ArrayList<ArchivedAnswer>();
		Map<int[], List<Answer>> answerMap = list.stream().collect(Collectors.groupingBy(Answer::getAnswerArray));

		for (int[] key : answerMap.keySet()) {

			List<Answer> answerList = answerMap.get(key);
			ArchivedAnswer aa = new ArchivedAnswer();
			aa.setQuestionId(questionId);
			aa.setAnswerArray(key);
			aa.setCount(answerList.size());
			aa.setName(name);
			aa.setNo(no);
			aa.setToiId(toiId);
			aa.setVersion(CommonFunction.ofyVersion);
			aa.save();
//			listAA.add(aa);
		}
		return listAA;
	}
	public static final ArchivedAnswer getById(long id) {
		ArchivedAnswer answer = null;
		answer = ofy().load().type(ArchivedAnswer.class).id(id).now();

		return answer;
	}
	public static void save(List<ArchivedAnswer> listArcAnswer) {
		for(ArchivedAnswer aa : listArcAnswer) {
			aa.save();
		}
		
	}

}
