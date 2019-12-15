package jp.ac.jc21.t.yoshizawa.admin.export.exporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/export/genre.csv")
public class ExportGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportGenreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/csv; charset=Windows-31J");
		PrintWriter out = response.getWriter();
		List<Genre> list = Genre.loadAll();
		out.println(
				"id,Name,Created,No"
				);
		
		for(Genre g:list) {
			out.println(g.getExportData());
		}
		out.close();
	}
}
