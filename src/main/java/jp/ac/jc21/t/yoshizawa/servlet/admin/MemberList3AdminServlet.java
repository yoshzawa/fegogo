package jp.ac.jc21.t.yoshizawa.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Member;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/member3/list" })
public class MemberList3AdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Member member = Member.get(request.getParameter("email"));

		List<AnswerSum> answerSumList = member.getAnswerSumList();
		Map<Long, List<AnswerSum>> map2 = answerSumList.stream().filter((AnswerSum aSum) -> {
			return Objects.nonNull(aSum);
		}).collect(Collectors.groupingBy(AnswerSum::getToiId, TreeMap::new, Collectors.toList()));

		request.setAttribute("map", map2);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/memberList2Admin.jsp");
		rd.forward(request, response);

	}
}