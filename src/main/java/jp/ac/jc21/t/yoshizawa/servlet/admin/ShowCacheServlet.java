package jp.ac.jc21.t.yoshizawa.servlet.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.servlet.GetGsonInterface;

/**
 * Servlet implementation class ShowCacheServlet
 */
@WebServlet("/admin/showCache")
public class ShowCacheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Optional<String> optUrl = Optional.ofNullable(request.getParameter("URL"));
		String url		 = "http://localhost:8080/endpoint/v0/exam/id/list";
		if(optUrl.isPresent()) {
			url = optUrl.get();
		}
		request.setAttribute("url", url);

		boolean before = GetGsonInterface.isCached(url);
		request.setAttribute("before", before);
		List<String> result = GetGsonInterface.getStringList(url);
		request.setAttribute("result", result);
		boolean after = GetGsonInterface.isCached(url);
		request.setAttribute("after", after);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp5/showCache.jsp");
		rd.forward(request, response);
		
	}

}
