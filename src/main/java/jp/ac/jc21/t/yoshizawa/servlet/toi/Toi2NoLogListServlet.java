package jp.ac.jc21.t.yoshizawa.servlet.toi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/toi2/NoLog/list" })
public class Toi2NoLogListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ExamとIDを取得
		String parentIdString = request.getParameter("parentId");
		long parentId = Long.parseLong(parentIdString);

		// Examを取得
		Exam e = Exam.getById(parentId);


		// 問の一覧を取得
		TreeMap<Long, Toi> toiMap = e.getToiMap();

		// ユーザー情報取得
		request.setAttribute("ExamName", e.getName());


			// ログインしていない場合
		Set<Long> toiKeySet = toiMap.keySet();
			List<String[]> datas = new ArrayList<String[]>(toiKeySet.size());
			for (Long key : toiKeySet) {
				Toi t = toiMap.get(key);
				String[] s = new String[4];
				s[0] = t.getNo().toString();
//				s[1] = t.getRefGenre().get().getName();
				s[1] = t.getGenreName();
				s[2] = "<a href='/question/list?parentId=" + t.getId() + "'>" + t.getName() + "</a>";
				if(t.getImageSet() != null) {
					s[2] = s[2]+"<B>(CBT)</B>";
				}
				s[3] = t.getQuestionRefListSize() + "";
				datas.add(s);
			}
			request.setAttribute("datas", datas);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/toiList.jsp");
			rd.forward(request, response);
	}

}