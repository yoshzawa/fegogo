package jp.ac.jc21.t.yoshizawa.objectify.archived;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jp.ac.jc21.t.yoshizawa.CommonFunction;
import jp.ac.jc21.t.yoshizawa.objectify.*;

public class ArchivedAnswerSumFactory extends CommonEntity {

	public ArchivedAnswerSum makeArchive(String eMail) {
		List<AnswerSum> asList = AnswerSum.getListByEMail(eMail);
		Map<Long, List<AnswerSum>> asMap = AnswerSum.makeMapByToiId(asList);
		for (Long toiId : asMap.keySet()) {
			ArchivedAnswerSum aas = new ArchivedAnswerSum();
			aas.setName(eMail);
			aas.setNoOfAnswer(0);
			aas.setNoOfSeikai(0);
			aas.setToiId(toiId);
			aas.setVersion(CommonFunction.ofyVersion);
			List<AnswerSum> list = asMap.get(toiId);
			for (AnswerSum as : list) {
				aas.setNoOfAnswer(aas.getNoOfAnswer() + as.getNoOfAnswer());
				aas.setNoOfSeikai(aas.getNoOfSeikai() + as.getNoOfSeikai());
//				as.delete();
			}
			aas.save();
		}
		return null;

	}

	public static final ArchivedAnswerSum getById(long id) {
		ArchivedAnswerSum aas = null;
		aas = ofy().load().type(ArchivedAnswerSum.class).id(id).now();
		return aas;
	}

	public static final ArchivedAnswerSum generate(List<AnswerSum> list) {
		ArchivedAnswerSum aas = new ArchivedAnswerSum();
		if (list.size() > 0) {
			AnswerSum as = list.get(0);
			aas.setToiId(as.getToiId());
			aas.setName(as.getName());
			aas.setVersion(CommonFunction.ofyVersion);
			Date d = new Date(0);
			int ans = 0;
			int seikai = 0;
			for (AnswerSum aSum : list) {
				if(aSum.getAnswered().compareTo(d)>0) {
					d=aSum.getAnswered();
				}
				ans += aSum.getNoOfAnswer();
				seikai += aSum.getNoOfSeikai();
			}
			aas.setAnswered(d);
			aas.setNoOfAnswer(ans);
			aas.setNoOfSeikai(seikai);
		}
		return aas;

	}
}
