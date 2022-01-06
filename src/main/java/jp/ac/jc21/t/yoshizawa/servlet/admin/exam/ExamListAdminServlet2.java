package jp.ac.jc21.t.yoshizawa.servlet.admin.exam;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.*;
@SuppressWarnings("serial")


@WebServlet(urlPatterns = { "/admin/exam/list2" })
public class ExamListAdminServlet2 extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Map<Long,Exam> examMap = Exam.loadAll2();
		request.setAttribute("examMap", examMap);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/examListAdmin2.jsp");
		rd.forward(request, response);

	}
}