package jp.ac.jc21.t.yoshizawa.updater.v200;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;

/**
 * Servlet implementation class Ver4003QuestionUpdater
 */
@WebServlet("/admin/Ver4003QuestionUpdater")
public class Ver4003QuestionUpdater extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ver4003QuestionUpdater() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
		List<Question> QuestionList = Question.loadAll();
		for(Question q : QuestionList) {
			q.save();
			response.getWriter().println(q.getId());
			response.getWriter().flush();
			System.out.println(q.getId());
		}
*/		
int i = 0;
List<AnswerSum> aSumList = AnswerSum.loadAll();
for (AnswerSum q : aSumList) {
	q.save();
	i++;
	if (i % 200 == 0) {
		System.out.println("(" + i + "/" + aSumList.size());
	}
}
		
	}

}
