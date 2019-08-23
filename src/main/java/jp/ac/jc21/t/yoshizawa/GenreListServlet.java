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
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/genre/list" })
public class GenreListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Genre> genreList = Genre.loadAll();
//		request.setAttribute("genreList", genreList);

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		if (email == null) {
			List<String[]> datas = new ArrayList<String[]>();

			for (Genre g : genreList) {
				List<Ref<Toi>> toiRefList = g.getToiRefList();
				{

					String genreName = g.getName();
					int count = 0;
					float sum = 0;
					for (Ref<Toi> rt : toiRefList) {
						Toi toi = rt.get();
						sum += toi.getAnswerSumSum();
						count += toi.getAnswerSumCount();
					}
					String[] s = new String[3];
					s[0] = genreName;
					s[1] = "<P align='CENTER'>‘S‘Ì•½‹Ï" + String.format("%1$.1f", sum / count) + "%</P>";
					s[2] = count + "";

					datas.add(s);
				}

				List<Ref<Toi>> list = toiRefList;
				for (Ref<Toi> rt : list) {
					String[] s = new String[3];
					Toi toi = rt.get();
					s[0] = "";
					s[1] = "<a href='/question/list?parentId=" + toi.getId() + "'>" + toi.getExam().getName() + " –â"
							+ toi.getNo() + " (" + toi.getName() + ")</a>";
					s[2] = toi.getAnswerSumRefListSize() + "";
					datas.add(s);
				}
			}
			request.setAttribute("datas", datas);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/nolog/genreList.jsp");
			rd.forward(request, response);
		} else {
			List<String[]> datas = new ArrayList<String[]>();

			for (Genre g : genreList) {
				List<Ref<Toi>> toiRefList = g.getToiRefList();
				{
					String genreName = g.getName();
					int count = 0;
					float sum = 0;
					for (Ref<Toi> rt : toiRefList) {
						Toi toi = rt.get();
						sum += toi.getAnswerSumSum();
						count += toi.getAnswerSumCount();
					}
					String[] s = new String[5];
					s[0] = genreName;
					s[1] = "<P align='CENTER'>‘S‘Ì•½‹Ï" + String.format("%1$.1f", sum / count) + "%</P>";
					s[2] = count + "";
					s[3] = "";
					s[4] = "";

					datas.add(s);
				}
				List<Ref<Toi>> list = toiRefList;

				for (Ref<Toi> rt : list) {
					Toi toi = rt.get();

					String toiName = toi.getExam().getName() + " –â" + toi.getNo() + " (" + toi.getName() + ")";

					Member member = Member.get(email);
					List<AnswerSum> las = member.getAnswerSumListByToi(toi.getId());
					String toiSize = toi.getAnswerSumRefListSize() + "";
					if ((las == null) || (las.size() == 0)) {
						String[] s = new String[5];
						s[0] = "";
						s[1] = toiName;
						toiName = "";
						s[2] = toiSize;
						s[3] = "–¢‰ñ“š";
						s[4] = "<a href='/question/list?parentId=" + toi.getId() + "'>“š‚¦‚é</a>";
						datas.add(s);

					} else {
						for (AnswerSum as : las) {
							String[] s = new String[5];
							s[0] = "";
							s[1] = toiName;
							toiName = "";
							s[2] = toiSize;
							toiSize = "";
							s[3] = dateFormat(as.getAnswered());
							s[4] = changePoint(as.getNoOfSeikai(), as.getNoOfAnswer()) + "%";
							datas.add(s);
						}

					}
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