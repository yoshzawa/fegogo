package jp.ac.jc21.t.yoshizawa.admin.ver3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Genre;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = { "/admin/ver3/maintenance/genre" })
public class MaintenanceGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaintenanceGenreServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		for(Genre g : Genre.loadAll()) {
			System.out.println("Genre :" + g.getName());
			g.resetToiRefList();
			g.save();
		}
		
//		RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionEditAdmin.jsp");
//		rd.forward(request, response);
	}



}
