package jp.ac.jc21.t.yoshizawa.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/question2/NoLog/list" })
public class Question2NoLogListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(Question2NoLogListServlet.class.getName());

		String parentIdString = request.getParameter("parentId");

		long parentId = Long.parseLong(parentIdString);
		log.info("[" + request.getServletPath() + "]parentId:" + parentId);

		Toi parent = Toi.getById(parentId);

		TreeMap<Long, Question> qMap = Toi.getQuestionMap(parent);

		request.setAttribute("parent", parent);
		request.setAttribute("parentId", parentIdString);


		List<String[]> datas = new ArrayList<String[]>();

		Set<Long> toiKeySet = qMap.keySet();
		for (Long l : toiKeySet) {
			Question q = qMap.get(l);

			String s[] = new String[2];
			s[0] = q.getName();
			if (q.getNoOfOption() <= 0) {
				s[1] = " <input type='radio' name='" + q.getId() + "' value='-1' disabled='disabled' /> "
						+ "解けない <input type='radio' name='" + q.getId()
						+ "' value='1' checked='checked' disabled='disabled' />" + "全員正解 ";
			} else {
				if (q.isMulti() == true) {
					s[1] = " <input type='checkbox' name='" + q.getId();
				} else {
					s[1] = " <input type='radio' name='" + q.getId();
				}
				s[1] += "' value='-1' checked='checked' disabled='disabled' /> 解けない ";
				for (int i = 0; i <= (int) q.getNoOfOption(); i++) {
					if (q.isMulti() == true) {
						s[1] += " <input type='checkbox'";
					} else {
						s[1] += " <input type='radio'";
					}
					s[1] += " name='" + q.getId() + "' value='" + i + "' disabled='disabled' /> "
							+ "アイウエオカキクケコサシスセソタチツテト".charAt(i);
				}
			}
			datas.add(s);

		}
		request.setAttribute("datas", datas);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/questionList.jsp");
		rd.forward(request, response);

	}
}