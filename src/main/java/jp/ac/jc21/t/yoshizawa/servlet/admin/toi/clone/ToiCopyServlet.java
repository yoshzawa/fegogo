package jp.ac.jc21.t.yoshizawa.servlet.admin.toi.clone;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.CloneQuestion;
import jp.ac.jc21.t.yoshizawa.objectify.CloneToi;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class ToiCopyServlet
 */
@WebServlet("/admin/ToiCopyServlet")
public class ToiCopyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fromToiId = request.getParameter("fromToi");
		String toExamId = request.getParameter("toExam");
		String toToiNoString = request.getParameter("toToiNo");

		long toToiNo = Long.parseLong(toToiNoString);

		// toiÇï°êª

		Toi fromToi = Toi.getById(fromToiId);
		Exam toExam = Exam.getById(toExamId);
		Genre genre = fromToi.getGenre();
		Toi newToi = Toi.createToi(toExam, toToiNo, fromToi.getName(), genre);
		newToi.setGenreName(genre.getName());
		newToi = newToi.save();

		// Cloneä÷òA

		CloneToi ct = new CloneToi(newToi.getId(), fromToi.getId());
		ct.save();

		// questionÇï°êª

		List<Question> list = fromToi.getQuestionList();
		for (Question q : list) {
			Set<Integer> ansSet = q.getAnswerSet();
			Question newq = Question.createMultiQuestion(newToi, q.getNo(), q.getName(), q.getNoOfOption(), ansSet);
			newq = newq.save();

			// Cloneä÷òA

			CloneQuestion cq = new CloneQuestion(newToi.getId(), newq.getId(), q.getId());
			cq.save();

		}

		// ImageSetÇï°êª

		newToi.setImageSet(fromToi.getImageSet());
		newToi = newToi.save();

		System.out.println("EXAM : " + toExam.getName());

	}

}
