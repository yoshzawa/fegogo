package jp.ac.jc21.t.yoshizawa.ver2;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

@WebServlet(urlPatterns = { "/toi2/login/list" })
public class Toi2LoginListServlet extends HttpServlet {

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
		request.setAttribute("ExamName", e.getName());


 {
			// ログインしている場合
			request.setAttribute("email", email);

			// 回答した情報を取得
			Member member = Member.get(email);
			List<AnswerSum> answerSumList = member.getAnswerSumListByExamId(parentId);
			request.setAttribute("answerSumList", answerSumList);

			List<String[]> datas = new ArrayList<String[]>();

			/////

			Set<Long> toiKeySet = toiMap.keySet();
			for (Long key : toiKeySet) {
				Toi t = toiMap.get(key);
				String[] s = new String[5];

				s[0] = t.getNo().toString();
				s[1] = t.getRefGenre().get().getName();
				s[2] = "<a href='/question2/list?parentId=" + t.getId() + "'>" + t.getName() + "</a>";
				s[3] = t.getQuestionRefListSize() + "";
				s[4] = "";

				int i = 0;
				for (AnswerSum as : answerSumList) {
					if (as.getRefToi().get().getId() == t.getId()) {
						s[4] += dateFormat(as.getAnswered()) + "(" + changePoint(as.getNoOfSeikai(), as.getNoOfAnswer())
								+ "%)<br/>";
						i++;
					}
				}
				if (i == 0) {
					s[4] = "<a href='/question2/list?parentId=" + t.getId() + "'>答える</a>";

				}
				datas.add(s);

			}
			/////
			request.setAttribute("datas", datas);
			
			List<String[]> datas2 = new ArrayList<String[]>();

			for (AnswerSum as : answerSumList) {
				String[] s = new String[6];
					Toi toi = as.getRefToi().get();
			s[0]=toi.getExam().getName();
			s[1]=toi.getNo().toString();
			s[2]=toi.getRefGenre().get().getName();
			s[3]=toi.getName();
			s[4]=dateFormat(as.getAnswered());
			s[5]= changePoint(as.getNoOfSeikai(),as.getNoOfAnswer()) +"%";
			datas2.add(s);
			
			}
			request.setAttribute("datas2", datas2);
			

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/login/toiListLogin.jsp");
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