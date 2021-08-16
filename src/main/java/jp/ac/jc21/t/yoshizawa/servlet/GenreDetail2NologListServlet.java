package jp.ac.jc21.t.yoshizawa.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/genreDetail2/noLog/list" })
public class GenreDetail2NologListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Genre genre = Genre.getById(Long.parseLong(request.getParameter("id")));
		String genreName = genre.getName();
		request.setAttribute("genreName", genreName);

		List<String[]> datas = new ArrayList<String[]>();

		Map<Long, Toi> toiMap = genre.getToiMap();
		{
			for (Long key : toiMap.keySet()) {
				Toi toi = toiMap.get(key);

				String[] s = new String[3];
				s[0] = "";
				s[1] = "<a href='/question/list?parentId=" + toi.getId() + "'>" + toi.getExam().getName() + " –â"
						+ toi.getNo() + " (" + toi.getName() + ")</a>";
				s[2] = toi.getAnswerSumRefListSize() + "";
				datas.add(s);
			}

		}
		request.setAttribute("datas", datas);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/genreList.jsp");
		rd.forward(request, response);

	}

}