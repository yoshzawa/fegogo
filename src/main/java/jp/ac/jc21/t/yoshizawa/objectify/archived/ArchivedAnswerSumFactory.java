package jp.ac.jc21.t.yoshizawa.objectify.archived;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.Map;

import jp.ac.jc21.t.yoshizawa.CommonFunction;
import jp.ac.jc21.t.yoshizawa.objectify.*;

public class ArchivedAnswerSumFactory extends CommonEntity {
	
	public ArchivedAnswerSum makeArchive(String eMail) {
		List<AnswerSum> asList = AnswerSum.getListByEMail(eMail);
		Map<Long, List<AnswerSum>> asMap = 
		AnswerSum.makeMapByToiId(asList);
		for(Long toiId : asMap.keySet()) {
			ArchivedAnswerSum aas = new ArchivedAnswerSum();
			aas.setName(eMail);
			aas.setNoOfAnswer(0);
			aas.setNoOfSeikai(0);
			aas.setToiId(toiId);
			aas.setVersion(CommonFunction.ofyVersion);
			List<AnswerSum> list = asMap.get(toiId);
			for(AnswerSum as : list) {
				aas.setNoOfAnswer(aas.getNoOfAnswer()+as.getNoOfAnswer());
				aas.setNoOfSeikai(aas.getNoOfSeikai()+as.getNoOfSeikai());
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
}
