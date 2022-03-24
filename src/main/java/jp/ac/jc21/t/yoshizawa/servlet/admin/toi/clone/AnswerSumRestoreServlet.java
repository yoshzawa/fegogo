package jp.ac.jc21.t.yoshizawa.servlet.admin.toi.clone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.CloneToi;

/**
 * Servlet implementation class ToiRestoreServlet
 */
@WebServlet("/admin/answerSumRestore")
public class AnswerSumRestoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String answerSumId = request.getParameter("answerSumId");

		AnswerSum aSum = AnswerSum.getById(Long.parseLong(answerSumId));

		CloneToi.reStoreAnswerSum(aSum);

	}

}
