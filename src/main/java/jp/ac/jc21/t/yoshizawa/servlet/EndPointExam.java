package jp.ac.jc21.t.yoshizawa.servlet;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

public class EndPointExam extends GetGson {
	
//	String examGetUrl = "http://localhost:8080/endpoint/v0/exam/get?ExamId=" + OptToiIdString.orElse("");
	private static final String examGetUrlById = "https://fegogo.appspot.com/endpoint/v0/exam/get?ExamId=";

	public static final List<Exam> getExamById(String examId) {
		String examListUrl = examGetUrlById + examId;
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService("jp.ac.jc21.t.yoshizawa-Exam");
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

		Optional<List<Exam>> optExamList = Optional.ofNullable((List<Exam>) syncCache.get(examListUrl));
		List<Exam> examList = null;
		if ((optExamList.isPresent()) && (optExamList.get().size() > 0)) {
			examList = optExamList.get();
		} else {
			examList = ExamListFromGson(examListUrl);
			syncCache.put(examListUrl, examList);
		}
		return examList;
	}
	private final static String examGetUrlByYYYYMM = "https://fegogo.appspot.com/endpoint/v0/exam/get?YYYYMM=";

	public static final List<Exam> getExamByYYYYMM(Long yyyymm) {
		String examListUrl = examGetUrlByYYYYMM + yyyymm;
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService("jp.ac.jc21.t.yoshizawa-Exam");
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

		Optional<List<Exam>> optExamList = Optional.ofNullable((List<Exam>) syncCache.get(examListUrl));
		List<Exam> examList = null;
		if ((optExamList.isPresent()) && (optExamList.get().size() > 0)) {
			examList = optExamList.get();
		} else {
			examList = ExamListFromGson(examListUrl);
			syncCache.put(examListUrl, examList);
		}
		return examList;
	}
}
