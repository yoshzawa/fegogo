package jp.ac.jc21.t.yoshizawa.admin.export.exporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/export/answer2.csv")
public class ExportAnswer2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportAnswer2Servlet() {
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
		List<Answer> list = Answer.loadAll();
		out.println(
				"id,No,Name,Answered,AnswerSumId,QuestionId,Answers"
				);
        Date date = new Date();
        
        long now = date.getTime();

        
		for(Answer a:list) {
			if(now - a.getAnswered().getTime() <= 1000*60*60*24) {
				out.println(a.getExportData());
			}
		}
		out.close();
	}

}
