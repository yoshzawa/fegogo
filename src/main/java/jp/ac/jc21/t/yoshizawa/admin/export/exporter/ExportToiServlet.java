package jp.ac.jc21.t.yoshizawa.admin.export.exporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/export/toi.csv")
public class ExportToiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportToiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/csv; charset=Windows-31J");
		PrintWriter out = response.getWriter();
		List<Toi> list = Toi.loadAll();
		out.println(
				"id,No,Name,Created,ExamId,GenreId,Sum"
				);
		
		for(Toi t:list) {
			out.println(t.getExportData());
		}
		out.close();
	}
}
