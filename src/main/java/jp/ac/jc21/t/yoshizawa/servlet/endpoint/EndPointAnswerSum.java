package jp.ac.jc21.t.yoshizawa.servlet.endpoint;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;

public class EndPointAnswerSum extends GetGson {
	
	private static final String AnswerSumGetUrl = endPointServerAddress + "/endpoint/v0/answerSum/get?AnswerSumId=";

	public static final List<AnswerSum> getAnswerSumListByAnswerSumId(Long l) {
		String toiListUrl = AnswerSumGetUrl + l;
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

		Optional<List<AnswerSum>> optMemberList = Optional.ofNullable((List<AnswerSum>) syncCache.get(toiListUrl));
		List<AnswerSum> examList = null;
		if ((optMemberList.isPresent()) && (optMemberList.get().size() > 0)) {
			examList = optMemberList.get();
		} else {
			examList = GetGson.AnswerSumListFromGson(toiListUrl);
			syncCache.put(toiListUrl, examList);
		}
		return examList;
	}
	
	private static final String AnswerSumIdListUrl = endPointServerAddress + "/endpoint/v0/toi/get/answerSumId/List?ToiId=";
	
	public static final List<Long> getAnswerSumIdListByToiId(Long toiId) {
		String examListUrl = AnswerSumIdListUrl + toiId;
		return getLongList(examListUrl);
	}

}
