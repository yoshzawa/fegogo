package jp.ac.jc21.t.yoshizawa.servlet.exam3;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/exam3/Login/list" })
public class Exam3LoginListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ユーザー情報取得
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		request.setAttribute("email", email);

		String examListUrl = "https://fegogo.appspot.com/endpoint/v1/exam/list";
		List<Exam> examList = GetGsonInterface.ExamListFromGson(examListUrl);

		Stream<Exam> stream1 = examList.stream();
		Stream<Exam> stream2 = stream1.sorted(Comparator.comparing(Exam::getYYYYMM));
		Stream<Exam> stream3 = stream2.filter((Exam e) -> e.getYYYYMM() < 300000);
		Stream<String[]> d4 = stream3.map((Exam e) -> makeDisplayData(e));
		List<String[]> datas = d4.collect(Collectors.toList());

		request.setAttribute("datas", datas);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/examList.jsp");
		rd.forward(request, response);
	}

			private final String[] makeDisplayData(Exam e) {
				String[] s = new String[2];

				int openDiff = 1;
				try {
					openDiff = new Date().compareTo(e.getOpenDate());
				} catch (NullPointerException ex) {
				}
				int closeDiff = -1;
				try {
					closeDiff = new Date().compareTo(e.getCloseDate());
				} catch (NullPointerException ex) {
				}

				if (openDiff == -1) {
					s[0] = e.getName() + "(" + dateFormat(e.getOpenDate()) + "より回答可能)";
				} else if (closeDiff == 1) {
					s[0] = e.getName() + "(" + dateFormat(e.getCloseDate()) + "で回答終了)";

				} else {
					s[0] = "<a href='/toi/list?parentId=" + e.getId() + "'>" + e.getName() + "</a>";
					if (e.getCloseDate() != null) {
						s[0] += "(" + dateFormat(e.getCloseDate()) + "まで回答可能)";
					} else {
						s[0] += "(期限なし)";
					}
				}

				String examListUrl = "https://fegogo.appspot.com/endpoint/v1/exam/get/toiId/List";
				try {
					List<Long> examList = GetGsonInterface.LongListFromGson(examListUrl + "?ExamId=" + e.getId());

					s[1] = examList.size() + "";
				} catch (IOException ex) {
					s[1] = "**exception!**";
				}
				return s;
			}
			
	private String changePoint(AnswerSum as) {
		return changePoint(as.getNoOfSeikai(), as.getNoOfAnswer());
	}

	private final String changePoint(int seikai, int answer) {
		float point = (100.0f * seikai / answer);
		return String.format("%1$.1f", point);
	}

	private final String dateFormat(Date d){	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");	
	    TimeZone timeZoneJP = TimeZone.getTimeZone("Asia/Tokyo");
	    sdf.setTimeZone(timeZoneJP);
		return sdf.format(d);	
	}	
}