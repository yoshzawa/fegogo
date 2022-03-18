package endpoint.v1;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

public interface ExamFunction {
	public static List<Exam> getExamByExamId(Optional<String> optExamId) {
		List<Exam> examList = new ArrayList<Exam>();

		if (optExamId.isPresent()) {
			try {
				long examId = Long.parseLong(optExamId.get());
				Optional<Exam> exam = Optional.ofNullable(ofy().load().type(Exam.class).id(examId).now());
				exam.ifPresent(examList::add);

			} catch (NumberFormatException e) {
			}
		}
		return examList;
	}
}
