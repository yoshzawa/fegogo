package jp.ac.jc21.t.yoshizawa.servlet.admin.toi;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class ToiDeleteAdminServlet
 */
@WebServlet("/admin/toi/delete")
public class ToiDeleteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long toiId = Long.parseLong(request.getParameter("toiId"));
		Toi toi = Toi.getById(toiId);
		
		int ansSumCount = AnswerSum.getListByToiId(toi.getId()).size();
		List<Question> listQ = Question.getListByToiId(toiId);
		
		if(ansSumCount>0) {
			response.getWriter().append("you have ansSum:").append(listQ.toString());
		}else {
			for(Question q :listQ) {
				q.delete();
			}
			toi.delete();
		}
		

	}

}
