package jp.ac.jc21.t.yoshizawa.servlet.admin.answerSum;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answerSum/listByToi" })
public class AnswerSumListAdminByToiServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		long toiId = Long.parseLong(request.getParameter("toiId"));
		String parentId = request.getParameter("parentId");
		request.setAttribute("toiId", toiId);
		
		List<AnswerSum> answerSumList = AnswerSum.getListByToiId(toiId);
		request.setAttribute("answerSumList", answerSumList);
		
		request.setAttribute("redirectTo", "/admin/toi/list?parentId="+ parentId );

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/answerSumListAllAdmin.jsp");
		rd.forward(request, response);

	}
}