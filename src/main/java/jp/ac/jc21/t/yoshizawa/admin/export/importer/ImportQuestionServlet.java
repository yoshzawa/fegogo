package jp.ac.jc21.t.yoshizawa.admin.export.importer;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class ImportGenreServlet
 */
@WebServlet("/admin/import/question")
public class ImportQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportQuestionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/importer/question.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Map<String, String[]> paramMap = request.getParameterMap();
		String[] params = paramMap.get("question");
		String param = params[params.length - 1];
		params = param.replaceAll("\r\n", "\n").replaceAll("\r", "\n").split("\n");

		List<Question> list = new ArrayList<Question>();
		boolean discarded = false;

		for (String s : params) {
			String[] ss = s.split(",");

			Long id = Long.parseLong(ss[0]);
			Long no = Long.parseLong(ss[1]);
			String name = ss[2];
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
			format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
			Date created;
			try {
				created = format.parse(ss[3]);
			} catch (ParseException e) {
				created = null;
			}

			long noOfOption = Long.parseLong(ss[4]);
			Toi t = Toi.getById(Long.parseLong(ss[5]));
			if (t == null) {
				System.out.println("ERROR:No Toi " + ss[5]);
				discarded = true;
				break;
			}
			String answers = ss[6];

			Question q = new Question();
			q.setId(id);
			q.setNo(no);
			q.setName(name);
			q.setCreated(created);
			q.setNoOfOption(noOfOption);
			q.setParent(Ref.create(t));
			q.setAnswerSet(answers);

			list.add(q);
			out.println("INSERT Question ID=[" + id + "]");
			out.flush();

		}

		if (discarded != true) {
			for (Question q : list) {
				q.save();
				Toi t = q.getParent();
				t.save();
			}
		}

	}

}
