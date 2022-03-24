package endpoint.v1;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gson.Gson;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

public interface ExamFunction {
	public static List<Exam> getExamByExamId(Optional<String> optExamId) {
		List<Exam> examList = new ArrayList<Exam>();

		if (optExamId.isPresent()) {
			try {
				long examId = Long.parseLong(optExamId.get());
				examList = ofy().load().type(Exam.class).filter("YYYYMM", examId).list();

			} catch (NumberFormatException e) {
			}
		}
		return examList;
	}

	public static String getExamByExamIdWithCache(Optional<String> optExamId) {
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

		String s="[]";

		Optional<String> optExamList = Optional.ofNullable((String) syncCache.get("Exam" + optExamId.get()));
		
		if (optExamList.isPresent()) {
			s = optExamList.get();
		} else {
			List<Exam> examList;
			Gson gson = new Gson();
			examList = ExamFunction.getExamByExamId(optExamId);
			 s = gson.toJson(examList);
			syncCache.put("Exam" + optExamId.get(), s);
		}
		return s;
	}
}
