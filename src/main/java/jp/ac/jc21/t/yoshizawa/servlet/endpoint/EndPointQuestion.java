package jp.ac.jc21.t.yoshizawa.servlet.endpoint;

import java.util.List;

public class EndPointQuestion extends GetGson {
	private final static String questionListUrl = "https://fegogo.appspot.com/endpoint/v0/toi/get/questionId/List?ToiId=";

	public static final List<Long> getQuestionListByToiId(Long toiId) {
		String examListUrl = questionListUrl + toiId;
		return getLongList(examListUrl,examListUrl);
	}
}
