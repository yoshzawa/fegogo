package jp.ac.jc21.t.yoshizawa.ver2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/genre/list", "/genre2/list" })
public class Genre2ListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//		if (email == null) 
		if (true) {
			RequestDispatcher rd = request.getRequestDispatcher("/genre2/Nolog/list");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/genre2/login/list");
			rd.forward(request, response);
		}

	}
}