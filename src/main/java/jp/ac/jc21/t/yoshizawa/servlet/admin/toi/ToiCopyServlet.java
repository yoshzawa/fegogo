package jp.ac.jc21.t.yoshizawa.servlet.admin.toi;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
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
     * @see HttpServlet#HttpServlet()
     */
    public ToiCopyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String fromToiId = "5772930285830144";
		//String toExamId = "4693189256544256";
		//String toToiNoString = "1";

		String fromToiId = request.getParameter("fromToi");
		String toExamId = request.getParameter("toExam");
		String toToiNoString = request.getParameter("toToiNo");

		long toToiNo = Long.parseLong(toToiNoString);

		Toi fromToi =Toi.getById(fromToiId);
		Exam toExam = Exam.getById(toExamId);
		Genre genre = fromToi.getGenre();
		Toi newToi = Toi.createToi(toExam, toToiNo, fromToi.getName(),genre);
		newToi.setGenreName(genre.getName());
		newToi = newToi.save();
		
		List<Question> list = fromToi.getQuestionList();
		for(Question q : list) {
			Set<Integer> ansSet = q.getAnswerSet();
			Question newq = Question.createMultiQuestion(newToi, q.getNo(), q.getName(), q.getNoOfOption(),ansSet );
			newq = newq.save();
		}
		
		
		
		newToi.setImageSet(fromToi.getImageSet());
		newToi = newToi.save();
		
		System.out.println("EXAM : " + toExam.getName());
		
		
		
		
	}

}
