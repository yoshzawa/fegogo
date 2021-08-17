package jp.ac.jc21.t.yoshizawa.servlet.exam;

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

import jp.ac.jc21.t.yoshizawa.objectify.AccessLog;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.datastore.Exam;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/exam2/Login/list" })
public class Exam2LoginListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Map<Long, Exam> examMap = Exam.loadAllMap();
		// ÉÜÅ[ÉUÅ[èÓïÒéÊìæ
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		request.setAttribute("email", email);

		List<String[]> datas = new ArrayList<String[]>();
		for (Long k : examMap.keySet()) {
			if(k>300000) break;
			Exam e = examMap.get(k);

			String[] s = new String[2];

			s[0] = "<a href='/toi/list?parentId=" + e.getId() + "'>" + e.getName() + "</a>";
			s[1] = e.getToiListSize() + "";
			datas.add(s);
		}
		
		request.setAttribute("datas", datas);
		
	
		AccessLog.create(email, "/exam2/Login/list "+"exam="+datas.size()).save();

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/login/examListLogin.jsp");
		rd.forward(request, response);
	}

	private String changePoint(AnswerSum as) {
		return changePoint(as.getNoOfSeikai(), as.getNoOfAnswer());
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