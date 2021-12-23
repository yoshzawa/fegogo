package jp.ac.jc21.t.yoshizawa.servlet.admin.archived;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Member;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/archivedAnswerSum/generate" })
public class ArchivedAnswerSumAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Member member = Member.get(request.getParameter("email"));

		Date date1 = new Date();
		Map<Long, List<AnswerSum>> map = new TreeMap<Long, List<AnswerSum>>();
		List<AnswerSum> answerSumList = member.getAnswerSumList();
		for (AnswerSum aSum : answerSumList) {
			Long toiId = aSum.getToiId();
			List<AnswerSum> list = map.get(toiId);
			if (list == null) {
				list = new ArrayList<AnswerSum>();
			}
			list.add(aSum);
			map.put(toiId, list);
		}
		Date date2 = new Date();
		Map<Long, List<AnswerSum>> map2 = answerSumList.stream().filter((AnswerSum aSum) -> {
			return Objects.nonNull(aSum);
		}).collect(Collectors.groupingBy(AnswerSum::getToiId));
		Date date3 = new Date();
		
		System.out.println(date2.getTime() - date1.getTime());
		System.out.println(date3.getTime() - date2.getTime());
		System.out.println(map.size());
		System.out.println(map2.size());
		
		
		
		//request.setAttribute("map", map);

		// RequestDispatcher rd =
		// request.getRequestDispatcher("/jsp/memberList2Admin.jsp");
		// rd.forward(request, response);

	}
}