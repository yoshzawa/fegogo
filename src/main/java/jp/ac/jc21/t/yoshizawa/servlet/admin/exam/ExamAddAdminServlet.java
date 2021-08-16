package jp.ac.jc21.t.yoshizawa.servlet.admin.exam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/exam/add" })
public class ExamAddAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		long YYYYMM = Long.parseLong(request.getParameter("YYYYMM"));
		String ExamName = request.getParameter("ExamName");

		Exam e = Exam.createExam(YYYYMM, ExamName);
		e.save();

		response.sendRedirect("/admin/exam/list");

	}
}