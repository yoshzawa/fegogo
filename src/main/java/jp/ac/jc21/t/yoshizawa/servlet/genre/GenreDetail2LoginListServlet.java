package jp.ac.jc21.t.yoshizawa.servlet.genre;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/genreDetail2/login/list" })
public class GenreDetail2LoginListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		Genre genre = Genre.getById(Long.parseLong(request.getParameter("id")));
		String genreName = genre.getName();
		request.setAttribute("genreName", genreName);
		{
			List<String[]> datas = new ArrayList<String[]>();

			Map<Long, Toi> toiMap = genre.getToiMap();

			for (Long key : toiMap.keySet()) {
				Toi toi = toiMap.get(key);

				String toiName = toi.getExam().getName() + " –â" + toi.getNo() + " (" + toi.getName() + ")";

				Member member = Member.get(email);
				List<AnswerSum> las = member.getAnswerSumListByToi(toi.getId());
				String toiSize = toi.getAnswerSumRefListSize() + "";
				String toiSum = String.format("%1$.1f", toi.getAnswerSumSum()/toi.getAnswerSumRefListSize() );
				if ((las == null) || (las.size() == 0)) {
					String[] s = new String[5];
					s[0] = toiName;
					toiName = "";
					s[1] = toiSize;
					s[4] = toiSum+"%";
					s[2] = "–¢‰ñ“š";
					s[3] = "<a href='/question/list?parentId=" + toi.getId() + "'>“š‚¦‚é</a>";
					datas.add(s);

				} else {
					for (AnswerSum as : las) {
						String[] s = new String[5];
						s[0] = toiName;
						toiName = "";
						s[1] = toiSize;
						toiSize = "";
						s[4] = toiSum+"%";
						s[2] = dateFormat(as.getAnswered());
						s[3] = changePoint(as.getNoOfSeikai(), as.getNoOfAnswer()) + "%";

						datas.add(s);
					}

				}
			}

			request.setAttribute("datas", datas);
			request.setAttribute("email", email);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/login/genreDetailListLogin.jsp");
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