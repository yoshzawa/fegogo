package jp.ac.jc21.t.yoshizawa.servlet.admin.member3;

import java.io.IOException;
import java.util.List;
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
public class MemberListAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<String> memberEmailList = null;
		String memberListUrl = "https://fegogo.appspot.com/endpoint/v0/member/email/list?order=created";

		
		Optional<String> order= Optional.ofNullable(request.getParameter("Order"));
		if(order.isPresent()) {
			switch(order.get()) {
			case "modified":
				 memberListUrl = "https://fegogo.appspot.com/endpoint/v0/member/email/list?order=modified";
				break;
			case "created":
				 memberListUrl = "https://fegogo.appspot.com/endpoint/v0/member/email/list?order=created";
				break;
			default:
				 memberListUrl = "https://fegogo.appspot.com/endpoint/v0/member/email/list?order=created";
				break;
			}
		}
		memberEmailList = GetGsonInterface.getStringList(memberListUrl);
		
		String memberGetUrl = "https://fegogo.appspot.com/endpoint/v0/member/get?email=";
		
		Stream<List<Member>> memberStream = memberEmailList.stream().map((String e)->GetGsonInterface.getMemberList(memberGetUrl+e));
		Stream<Member> memberStream2 = memberStream.flatMap((List<Member> l)->l.stream());
		List<Member> memberList = memberStream2.collect(Collectors.toList());;
		
		request.setAttribute("memberList", memberList);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/memberListAdmin.jsp");
		rd.forward(request, response);

	}
}