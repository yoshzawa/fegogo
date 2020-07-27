package jp.ac.jc21.t.yoshizawa.ver2;

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
@WebServlet(urlPatterns = { "/genre2/Nolog/list" })
public class Genre2NoLogListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Genre> genreList = Genre.loadAll();

		{
			List<String[]> datas = new ArrayList<String[]>(genreList.size());

			for (Genre g : genreList) {
				List<Ref<Toi>> toiRefList = g.getToiRefList();
				{

					String genreName = g.getName();

					int count = 0;
					float sum = 0;
					for (Ref<Toi> rt : toiRefList) {
						Toi toi = rt.get();
						if (toi != null) {
							sum += toi.getAnswerSumSum();
							count += toi.getAnswerSumCount();
						}
					}

					String[] s = new String[3];
					s[0] = "<a href='/genreDetail/list?id=" + g.getId() + "'>" + genreName + "</a>";
					s[1] = "<P align='CENTER'>‘S‘Ì•½‹Ï" + String.format("%1$.1f", sum / count) + "%</P>";
					s[2] = count + "";

					datas.add(s);
				}

			}
			request.setAttribute("datas", datas);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/genreList.jsp");
			rd.forward(request, response);
		}

	}

}