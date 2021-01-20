package jp.ac.jc21.t.yoshizawa.ver40;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.AccessLog;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class ReportLoginServlet
 */
@WebServlet("/report")
public class ReportLoginServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String[]> datas = new ArrayList<String[]>();

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		request.setAttribute("email", email);

		Member member = Member.get(email);

		List<AnswerSum> answerSumList = member.getAnswerSumListSorted();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -90);

		for (AnswerSum as : answerSumList) {

			Calendar answered = Calendar.getInstance();
			answered.setTime(as.getAnswered());

			Optional<Ref<Toi>> optTtoi = as.getOptRefToi();
			if (optTtoi.isPresent()) 
			{
				Toi toi=optTtoi.get().get();
				String[] s = new String[6];
				
			
				if (answered.after(cal)) {
				s[0]=toi.getExamName();
				s[1] = toi.getNo().toString();
//				s[2] = "<a href='/genreDetail/list?id="+toi.getRefGenre().get().getId()+"'>" + toi.getRefGenre().get().getName() + "</a>";
				s[2] = "<a href='/genreDetail/list?id="+toi.getGenre().getId()+"'>" + toi.getGenre().getName() + "</a>";
				s[3] = toi.getName();
				s[4] = dateFormat(as.getAnswered());
				s[5] = changePoint(as)+"%";
				}else {
					s[0]="<s><small>"+toi.getExamName()+"</small></s>";
					s[1] ="<s><small>"+ toi.getNo().toString()+"</small></s>";
//					s[2] ="<s><small>"+ "<a href='/genreDetail/list?id="+toi.getRefGenre().get().getId()+"'>" + toi.getRefGenre().get().getName() + "</a>"+"</small></s>";
					s[2] ="<s><small>"+ "<a href='/genreDetail/list?id="+toi.getGenre().getId()+"'>" + toi.getGenre().getName() + "</a>"+"</small></s>";
					s[3] ="<s><small>"+ toi.getName()+"</small></s>";
					s[4] ="<s><small>"+ dateFormat(as.getAnswered())+"</small></s>";
					s[5] ="<s><small>"+ changePoint(as)+"%"+"</small></s>";
				}
				datas.add(s);
		} else 
			{
//				log.warning("toi==null email=" + email);
				AccessLog.create(email, "[WARNING]/report:toi==null");
			}

		}
		request.setAttribute("datas", datas);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/login/reportLogin.jsp");
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