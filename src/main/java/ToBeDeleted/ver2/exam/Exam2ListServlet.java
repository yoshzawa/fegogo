package ToBeDeleted.ver2.exam;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
//@WebServlet(urlPatterns = { "/exam/list","/exam2/list" })
@WebServlet(urlPatterns = { "/exam2/list" })
public class Exam2ListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		if (email == null) {

			RequestDispatcher rd = request.getRequestDispatcher("/exam2/Nolog/list");
			rd.forward(request, response);
			
		} else {

			RequestDispatcher rd = request.getRequestDispatcher("/exam2/Login/list");
			rd.forward(request, response);

		}

	}

}