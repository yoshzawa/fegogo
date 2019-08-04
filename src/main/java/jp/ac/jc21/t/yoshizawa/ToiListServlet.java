package jp.ac.jc21.t.yoshizawa;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/toi/list" })
public class ToiListServlet extends HttpServlet {

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
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		if (email == null) {
			// ログインしていない場合
			List<String[]> datas = new ArrayList<String[]>();
			Set<Long> toiKeySet = toiMap.keySet();
			for (Long l : toiKeySet) {
				Toi t = toiMap.get(l);
				String[] s = new String[4];
				s[0] = t.getNo().toString();
				s[1] = t.getGenre().get().getName();
				s[2] = "<a href='/question/list?parentId=<%=t.getId()%>'>" + t.getName() + "</a>";
				s[3] = t.getQuestionRefListSize() + "";
				datas.add(s);
			}
			request.setAttribute("datas", datas);

			request.setAttribute("ExamName", e.getName());

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/nolog/toiList.jsp");
			rd.forward(request, response);
		} else {
			// ログインしている場合
			request.setAttribute("email", email);

			// 回答した情報を取得
			Member member = Member.get(email);
			List<AnswerSum> asl = member.getAnswerSumListByExamId(parentId);
			request.setAttribute("answerSumList", asl);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login/toiListLogin.jsp");
			rd.forward(request, response);
		}
	}
}