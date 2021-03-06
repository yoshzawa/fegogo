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

import jp.ac.jc21.t.yoshizawa.objectify.Genre;

/**
 * Servlet implementation class ImportGenreServlet
 */
@WebServlet("/admin/import/genre")
public class ImportGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportGenreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/importer/genre.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> paramMap = request.getParameterMap();
		String[] params = paramMap.get("genre");
		String param = params[params.length - 1];
		params = param.replaceAll("\r\n", "\n").replaceAll("\r", "\n").split("\n");

		for (String s : params) {
			String[] ss = s.split(",");
			Long id = Long.parseLong(ss[0]);
			String name = ss[1];
		      SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		      format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		      Date created;
			try {
				created = format.parse(ss[2]);
			} catch (ParseException e) {
				created=null;
			}
			int no = Integer.parseInt(ss[3]);
			
			Genre g = new Genre();
			g.setId(id);
			g.setName(name);
			g.setCreated(created);
			g.setNo(no);
			g.save();
		}

	}

}
