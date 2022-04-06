package jp.ac.jc21.t.yoshizawa.servlet.admin.member3;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.*;
import jp.ac.jc21.t.yoshizawa.servlet.endpoint.EndPointAnswerSum;
import jp.ac.jc21.t.yoshizawa.servlet.endpoint.EndPointMember;
import jp.ac.jc21.t.yoshizawa.servlet.endpoint.GetGson;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/member/list","/admin/member" })
public class MemberActiveListAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<String> memberEmailList ;
		String jspUrl= "/jsp/memberListAdmin.jsp";
		String memberListUrl = "https://fegogo.appspot.com/endpoint/v0/member/email/list?order=id";

		
		Optional<String> order= Optional.ofNullable(request.getParameter("Order"));
		if(order.isPresent()) {
			switch(order.get()) {
			case "modified":
				 memberListUrl = "https://fegogo.appspot.com/endpoint/v0/member/email/list?order=modified";
					jspUrl = "/jsp/memberListAdmin_modified.jsp";
				break;
			case "created":
				 memberListUrl = "https://fegogo.appspot.com/endpoint/v0/member/email/list?order=created";
					jspUrl = "/jsp/memberListAdmin_created.jsp";
				break;
			default:
//				 memberListUrl = "https://fegogo.appspot.com/endpoint/v0/member/email/list?order=id";
//					jspUrl = "/jsp/memberListAdmin.jsp";
				break;
			}
		}
		memberEmailList = GetGson.getStringList(memberListUrl);
		
		String memberGetUrl = "https://fegogo.appspot.com/endpoint/v0/member/get?email=";
		Date now = new Date();
		List<Member> memberList;
		{
			Predicate<Member> predicate = (
					Member m) -> (now.getTime() - m.getModified().getTime()) < 24 * 10 * 60 * 60 * 1000;
			Stream<List<Member>> memberStream = memberEmailList.stream().map((String e)->EndPointMember.getMemberList(e));
			Stream<Member> memberStream2 = memberStream.flatMap((List<Member> l)->l.stream());
			Stream<Member> memberStream3 = memberStream2.filter(predicate);
			memberList = memberStream3.collect(Collectors.toList());
			request.setAttribute("memberList", memberList);
		}
		
		{
			String AnswerSumIdListUrl = "https://fegogo.appspot.com/endpoint/v0/member/get/AnswerSumId/activeList?email=";
			Map<String,Integer> answerSumIdMap=new HashMap<String, Integer>();
			for(Member m : memberList ) {
				String email = m.geteMail();
				List<Long> aSumIdList = GetGson.getLongList(AnswerSumIdListUrl+email);
				answerSumIdMap.put(email, aSumIdList.size());
				aSumIdList.stream().map((Long id)->EndPointAnswerSum.getAnswerSumListByAnswerSumId(id));
			}
			request.setAttribute("answerSumIdMap", answerSumIdMap);
		}
		
		

		RequestDispatcher rd = request.getRequestDispatcher(jspUrl);
		rd.forward(request, response);

	}
}