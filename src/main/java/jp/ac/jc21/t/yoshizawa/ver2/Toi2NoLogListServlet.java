package jp.ac.jc21.t.yoshizawa.ver2;

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
import javax.servlet.http.HttpSession;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/toi2/NoLog/list" })
public class Toi2NoLogListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Exam��ID���擾
		String parentIdString = request.getParameter("parentId");
		long parentId = Long.parseLong(parentIdString);

		// Exam���擾
		Exam e = Exam.getById(parentId);


		// ��̈ꗗ���擾
		TreeMap<Long, Toi> toiMap = e.getToiMap();

		// ���[�U�[���擾
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		request.setAttribute("ExamName", e.getName());


		if (email == null) {
			// ���O�C�����Ă��Ȃ��ꍇ
			List<String[]> datas = new ArrayList<String[]>();
			Set<Long> toiKeySet = toiMap.keySet();
			for (Long key : toiKeySet) {
				Toi t = toiMap.get(key);
				String[] s = new String[4];
				s[0] = t.getNo().toString();
				s[1] = t.getRefGenre().get().getName();
				s[2] = "<a href='/question2/list?parentId=" + t.getId() + "'>" + t.getName() + "</a>";
				s[3] = t.getQuestionRefListSize() + "";
				datas.add(s);
			}
			request.setAttribute("datas", datas);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/toiList.jsp");
			rd.forward(request, response);
		} 		
	}

}