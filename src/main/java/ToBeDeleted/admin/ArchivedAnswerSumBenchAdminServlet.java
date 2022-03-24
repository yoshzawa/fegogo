package ToBeDeleted.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Member;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/archivedAnswerSum/bench" })
public class ArchivedAnswerSumBenchAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Member member = Member.get(request.getParameter("email"));

		Date date1 = new Date();
		List<AnswerSum> answerSumList = member.getAnswerSumList();
		Map<Long, List<AnswerSum>> map = AnswerSum.makeMapByToiId_old(answerSumList);
		Date date2 = new Date();
		Map<Long, List<AnswerSum>> map2 = AnswerSum.makeMapByToiId(answerSumList);
		Date date3 = new Date();
		
		response.getWriter().println(date2.getTime() - date1.getTime());
		response.getWriter().println(date3.getTime() - date2.getTime());
		response.getWriter().println(map.size());
		response.getWriter().println(map2.size());
		
		
		
		//request.setAttribute("map", map);
		//request.setAttribute("map2", map2);

		// RequestDispatcher rd =
		// request.getRequestDispatcher("/jsp/memberList2Admin.jsp");
		// rd.forward(request, response);

	}


}