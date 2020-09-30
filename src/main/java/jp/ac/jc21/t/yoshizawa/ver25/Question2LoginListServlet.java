package jp.ac.jc21.t.yoshizawa.ver25;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.admin.QuestionListAdminServlet;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/question2/Login/list" })
public class Question2LoginListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(QuestionListAdminServlet.class.getName());

		String parentIdString = request.getParameter("parentId");

		long parentId = Long.parseLong(parentIdString);
		log.info("[" + request.getServletPath() + "]parentId:" + parentId);

		Toi parent = Toi.getById(parentId);
		
		if(parent.getImageSet() == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/question2/Login/list/noImage");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/question2/Login/list/withImage");
			rd.forward(request, response);
		}



		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/login/questionListLogin.jsp");
		rd.forward(request, response);

	}
}