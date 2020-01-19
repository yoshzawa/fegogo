package jp.ac.jc21.t.yoshizawa;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

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
@WebServlet(urlPatterns = { "/exam/list" })
public class ExamListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		final Logger log = Logger.getLogger(ExamListServlet.class.getName());

		Map<Long, Exam> examMap = Exam.loadAll();
		//request.setAttribute("examMap", examMap);

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		List<String[]> datas = new ArrayList<String[]>();
		for (Long k : examMap.keySet()) {
			Exam e = examMap.get(k);
			String[] s = new String[2];

			s[0] = "<a href='/toi/list?parentId=" + e.getId() + "'>" + e.getName() + "</a>";
			s[1] = e.getToiRefListSize() + "";
			datas.add(s);
		}
		request.setAttribute("datas", datas);
		
		if (email == null) {

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/nolog/examList.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("email", email);

			Member member = Member.get(email);
			
			List<AnswerSum> answerSumList = member.getAnswerSumList();
			//request.setAttribute("answerSumList", answerSumList);

			List<String[]> datas2 = new ArrayList<String[]>();

			for (AnswerSum as : answerSumList) {

				Toi toi = as.getRefToi().get();
				if(toi == null) {
					log.warning("toi==null email="+email);
				}else {
					String[] s = new String[6];
					Optional<Exam> optExam = toi.getOptExam();
					String examName ;
					if(optExam.isPresent()) {
						examName = optExam.get().getName();
					}else {
						examName = null;
					}
					s[0] = examName;
					s[1] = toi.getNo().toString();
					s[2] = toi.getRefGenre().get().getName();
					s[3] = toi.getName();
					s[4] = dateFormat(as.getAnswered());
					s[5] = changePoint(as);
					datas2.add(s);
				}


			}
			request.setAttribute("datas2", datas2);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login/examListLogin.jsp");
			rd.forward(request, response);
		}

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