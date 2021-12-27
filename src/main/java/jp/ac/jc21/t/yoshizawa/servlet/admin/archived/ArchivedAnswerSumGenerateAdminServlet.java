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
import jp.ac.jc21.t.yoshizawa.objectify.archived.ArchivedAnswerSum;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/archivedAnswerSum/generate" })
public class ArchivedAnswerSumGenerateAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Member member = Member.get(request.getParameter("email"));

		List<AnswerSum> answerSumList = member.getAnswerSumList();
		Map<Long, List<AnswerSum>> map = AnswerSum.makeMapByToiId(answerSumList);
		for(Long toiId : map.keySet()) {
			response.getWriter().println(toiId);
			List<AnswerSum> list = map.get(toiId);
			ArchivedAnswerSum.generate(list).save();
		}
	}
}