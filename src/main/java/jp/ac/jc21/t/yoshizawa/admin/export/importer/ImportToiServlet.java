package jp.ac.jc21.t.yoshizawa.admin.export.importer;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class ImportGenreServlet
 */
@WebServlet("/admin/import/toi")
public class ImportToiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportToiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/importer/toi.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> paramMap = request.getParameterMap();
		String[] params = paramMap.get("toi");
		String param = params[params.length - 1];
		params = param.replaceAll("\r\n", "\n").replaceAll("\r", "\n").split("\n");
		PrintWriter out = response.getWriter();

		for (String s : params) {
			String[] ss = s.split(",");
			
			
			Long id = Long.parseLong(ss[0]);
			Long no=Long.parseLong(ss[1]);
			String name=ss[2];
		      SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		      format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		      Date created;
			try {
				created = format.parse(ss[3]);
			} catch (ParseException e) {
				created=null;
			}
			Exam exam=Exam.getById(Long.parseLong(ss[4]));
			if(exam==null) {
				System.out.println("ERROR:No Exam "+ ss[4]);
				break;
			}
			Ref<Exam> refExam = Ref.create(exam);
			Genre genre=Genre.getById(Long.parseLong(ss[5]));
			if(genre==null) {
				System.out.println("ERROR:No Genre "+ ss[5]);
				break;
			}
			Ref<Genre> refGenre = Ref.create(genre);
			Toi t=new Toi();
			t.setId(id);
			t.setNo(no);
			t.setName(name);
			t.setCreated(created);
			t.setRefExam(refExam);
			t.setRefGenre(refGenre);
			t.save();
			
			Ref<Toi> toiRef = Ref.create(t);
			genre.addToiRefList(toiRef);
			genre.save();
//			exam.addToiRefList(toiRef);
			exam.save();
			
			out.println("INSERT Toi ID=["+id+"]");
		}
	}
}
