package jp.ac.jc21.t.yoshizawa.servlet.exam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/exam2/Nolog/list" })
public class Exam2NoLogListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Map<Long, Exam> examMap = Exam.loadAll();

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

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/examList.jsp");
		rd.forward(request, response);

	}

}