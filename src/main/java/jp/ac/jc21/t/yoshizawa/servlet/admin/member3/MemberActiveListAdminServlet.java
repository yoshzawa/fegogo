package jp.ac.jc21.t.yoshizawa.servlet.admin.member3;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.*;
import jp.ac.jc21.t.yoshizawa.servlet.GetGsonInterface;

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
		memberEmailList = GetGsonInterface.getStringList(memberListUrl);
		
		String memberGetUrl = "https://fegogo.appspot.com/endpoint/v0/member/get?email=";
		Date now = new Date();
		List<Member> memberList;
		{
			Stream<List<Member>> memberStream = memberEmailList.stream().map((String e)->GetGsonInterface.getMemberList(memberGetUrl+e));
			Stream<Member> memberStream2 = memberStream.flatMap((List<Member> l)->l.stream());
			Stream<Member> memberStream3 = memberStream2.filter((Member m)->(now.getTime() - m.getModified().getTime())<24*10* 60 * 60*1000);
			memberList = memberStream3.collect(Collectors.toList());
			request.setAttribute("memberList", memberList);
		}
		
		{
			Map<String,Integer> answerSumIdMap=new HashMap<String, Integer>();
			String AnswerSumIdListUrl = "https://fegogo.appspot.com/endpoint/v0/member/get/AnswerSumId/List?email=";
			for(Member m : memberList ) {
				String email = m.geteMail();
				List<Long> aSumIdList = GetGsonInterface.getLongList(AnswerSumIdListUrl+email);
				answerSumIdMap.put(email, aSumIdList.size());
			}
			request.setAttribute("answerSumIdMap", answerSumIdMap);
		}
		
		

		RequestDispatcher rd = request.getRequestDispatcher(jspUrl);
		rd.forward(request, response);

	}
}