package jp.ac.jc21.t.yoshizawa.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
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

@WebServlet(urlPatterns = { "/admin/member2/list" })
public class MemberActiveList2AdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Member member = Member.get(request.getParameter("email"));
		
		Date now = new Date();
		Map<Long, List<AnswerSum>> map = new TreeMap<Long, List<AnswerSum>>();
		List<AnswerSum> answerSumList = member.getAnswerSumList();
		Predicate<AnswerSum> predicate = (
				AnswerSum aSum) -> (now.getTime() - aSum.getAnswered().getTime()) < 24 * 60 * 60 * 1000;
		answerSumList = answerSumList.stream().filter(predicate)
				.collect(Collectors.toList());


		for (AnswerSum aSum : answerSumList) {
			Long toiId = aSum.getToiId();
			List<AnswerSum> list = map.get(toiId);
			if (list == null) {
				list = new ArrayList<AnswerSum>();
			}
			list.add(aSum);
			map.put(toiId, list);
		}

		request.setAttribute("map", map);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/memberActiveList2Admin.jsp");
		rd.forward(request, response);

	}
}