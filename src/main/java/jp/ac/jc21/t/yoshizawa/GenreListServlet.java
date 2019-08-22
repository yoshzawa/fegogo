package jp.ac.jc21.t.yoshizawa;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/genre/list" })
public class GenreListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Genre> genreList = Genre.loadAll();
		request.setAttribute("genreList", genreList);

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		if (email == null) {
			List<String[]> datas = new ArrayList<String[]>();

			for (Genre g : genreList) {
				String genreName = g.getName();

				List<Ref<Toi>> list = g.getToiRefList();
				for (Ref<Toi> rt : list) {
					String[] s = new String[2];
					s[0] = genreName;
					genreName = "";
					Toi t = rt.get();
					s[1] = t.getParent().getName() + " –â" + t.getNo() + " (" + t.getName() + ")";
					datas.add(s);
				}
			}
			request.setAttribute("datas", datas);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/nolog/genreList.jsp");
			rd.forward(request, response);
		} else {
			List<String[]> datas = new ArrayList<String[]>();

			for (Genre g : genreList) {
				String genreName = g.getName();
				List<Ref<Toi>> list = g.getToiRefList();
				for (Ref<Toi> rt : list) {
					Toi t = rt.get();

					String[] s = new String[3];
					s[0] = genreName;
					genreName = "";
					s[1] = t.getParent().getName() + " –â" + t.getNo() + " (" + t.getName() + ")";
					AnswerSum as = t.getAnswerSumByMemberId(email);
					if (as != null) {
						s[2] = "[" + dateFormat(as.getAnswered()) + " ("
								+ changePoint(as.getNoOfSeikai(), as.getNoOfAnswer()) + ")]";
					} else {
						s[2] = "<a href='/question/list?parentId=" + t.getId() + "'>“š‚¦‚é</a>";
					}
					datas.add(s);
				}
			}
			
			request.setAttribute("datas", datas);
			request.setAttribute("email", email);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login/genreListLogin.jsp");
			rd.forward(request, response);
		}

	}

	private final String changePoint(int seikai, int answer) {
		float point = (100.0f * seikai / answer);
		return String.format("%1$.1f", point);
	}

	private final String dateFormat(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(d);
	}
}