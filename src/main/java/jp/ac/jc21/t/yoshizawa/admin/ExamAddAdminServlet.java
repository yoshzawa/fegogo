package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/exam/add" })
public class ExamAddAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		UserService userService = UserServiceFactory.getUserService();
		if(userService.isUserAdmin()!=true) {
			response.sendRedirect("/");
		}

		long YYYYMM = Long.parseLong(request.getParameter("YYYYMM"));
		String ExamName = request.getParameter("ExamName");

		Exam e = Exam.createExam(YYYYMM, ExamName);
		e.save();

//    RequestDispatcher rd = request.getRequestDispatcher("/jsp/examListAdmin.jsp");
//    rd.forward(request, response);
		response.sendRedirect("/admin/exam/list");

	}
}