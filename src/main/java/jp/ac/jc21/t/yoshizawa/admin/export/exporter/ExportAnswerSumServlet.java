package jp.ac.jc21.t.yoshizawa.admin.export.exporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/export/answerSum.csv")
public class ExportAnswerSumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportAnswerSumServlet() {
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
		out.println(
				"id,Name,Answered,ToiId,MemberId,NoOfAnswer,NoOfSeikai"
				);
		AnswerSum.loadAll()
		.stream().forEach((AnswerSum aSum)->out.println(aSum.getExportData()));
		
		out.close();
	}

}
