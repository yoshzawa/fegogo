package jp.ac.jc21.t.yoshizawa.v200;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class Ver200UpgradeServlet
 */
@WebServlet("/upgrade")
public class Ver200UpgradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("exam getName()");
		Long examId = Long.parseLong("5751404605997056");
		Exam e = Exam.getById(examId);
		out.println(e.getName());

		out.println();

		out.println("exam -> Toi getName()");

		List<Toi> tois = Toi.getToiListByExamId(examId);
		for(Toi t : tois) {
			out.println(t.getName());
		}
		out.println();

		out.println("genre getName()");
		Long genreId = Long.parseLong("6240281313673216");
		Genre g = Genre.getById(genreId);
		out.println(g.getName());
		out.println();

		ToiReload();
		
		out.println("genre -> Toi getName()");
//		Toi toi = Toi.getById(Long.parseLong("5681150794137600"));
//		toi.save();
		List<Toi> tois2 = Toi.getToiListByGenreId(genreId);
		tois2.size();
		for(Toi t : tois2) {
			out.println(t.getName());
		}

	}

	private void ToiReload() {
		List<Toi> list = Toi.loadAll();
		for(Toi t : list) {
			t.save();
		}
	}

}
