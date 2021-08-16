package jp.ac.jc21.t.yoshizawa.servlet.question;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/question2/Login/list" })
public class Question2LoginListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(Question2LoginListServlet.class.getName());

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


	}
}