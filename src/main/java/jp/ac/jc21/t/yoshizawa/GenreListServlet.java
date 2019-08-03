package jp.ac.jc21.t.yoshizawa;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Member;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/genre/list"  })
public class GenreListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		
		List<Genre> genreList = Genre.loadAll();
		request.setAttribute("genreList", genreList);
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		if(email == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/genreList.jsp");
			rd.forward(request, response);			
		} else {
			request.setAttribute("email", email);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/genreListLogin.jsp");
			rd.forward(request, response);
		}



	}
}