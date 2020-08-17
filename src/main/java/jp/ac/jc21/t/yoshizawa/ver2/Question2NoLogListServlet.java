package jp.ac.jc21.t.yoshizawa.ver2;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.admin.QuestionListAdminServlet;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/question2/NoLog/list" })
public class Question2NoLogListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(QuestionListAdminServlet.class.getName());

		String parentIdString = request.getParameter("parentId");

		long parentId = Long.parseLong(parentIdString);
		log.info("[" + request.getServletPath() + "]parentId:" + parentId);

		Toi parent = Toi.getById(parentId);

		Exam exam = parent.getExam();

		TreeMap<Long, Question> qMap = Toi.getQuestionMap(parent);

		request.setAttribute("parent", parent);
		request.setAttribute("parentId", parentIdString);
//		request.setAttribute("questionMap", qMap);
//		request.setAttribute("exam", exam);
//		request.setAttribute("examName", parent.getExamName());

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		List<String[]> datas = new ArrayList<String[]>();

		Set<Long> toiKeySet = qMap.keySet();
		for (Long l : toiKeySet) {
			Question q = qMap.get(l);

			String s[] = new String[2];
			s[0] = q.getName();
			if (q.getNoOfOption() <= 0) {
				s[1] = " <input type='radio' name='" + q.getId() + "' value='-1' disabled='disabled' /> "
						+ "�����Ȃ� <input type='radio' name='" + q.getId()
						+ "' value='1' checked='checked' disabled='disabled' />" + "�S������ ";
			} else {
				if (q.isMulti() == true) {
					s[1] = " <input type='checkbox' name='" + q.getId();
				} else {
					s[1] = " <input type='radio' name='" + q.getId();
				}
				s[1] += "' value='-1' checked='checked' disabled='disabled' /> �����Ȃ� ";
				for (int i = 0; i <= (int) q.getNoOfOption(); i++) {
					if (q.isMulti() == true) {
						s[1] += " <input type='checkbox'";
					} else {
						s[1] += " <input type='radio'";
					}
					s[1] += " name='" + q.getId() + "' value='" + i + "' disabled='disabled' /> "
							+ "�A�C�E�G�I�J�L�N�P�R�T�V�X�Z�\�^�`�c�e�g".charAt(i);
				}
			}
			datas.add(s);

		}
		request.setAttribute("datas", datas);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/questionList.jsp");
		rd.forward(request, response);

	}
}