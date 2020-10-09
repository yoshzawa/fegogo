package jp.ac.jc21.t.yoshizawa.admin.export.importer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

/**
 * Servlet implementation class ImportGenreServlet
 */
@WebServlet("/admin/import/exam")
public class ImportExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportExamServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/importer/exam.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> paramMap = request.getParameterMap();
		String[] params = paramMap.get("exam");
		String param = params[params.length - 1];
		params = param.replaceAll("\r\n", "\n").replaceAll("\r", "\n").split("\n");

		for (String s : params) {
			String[] ss = s.split(",");
			
			
			Long id = Long.parseLong(ss[0]);
			Long YYYYMM=Long.parseLong(ss[1]);
			String name=ss[2];
		      SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		      format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		      Date created;
			try {
				created = format.parse(ss[3]);
			} catch (ParseException e) {
				created=null;
			}
			
			Exam e = new Exam();
			e.setId(id);
			e.setYYYYMM(YYYYMM);
			e.setName(name);
			e.setCreated(created);
			e.save();
			

		}

	}

}
