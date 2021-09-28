package jp.ac.jc21.t.yoshizawa.servlet.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/member/list" })
public class MemberListAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Member> memberList = Member.loadAll();
		
		Optional<String> order= Optional.ofNullable(request.getParameter("Order"));
		if(order.isPresent()) {
			switch(order.get()) {
			case "modified":
				memberList = Member.orderListByModified(memberList);
				break;
			default:
				break;
			}
		}
		request.setAttribute("memberList", memberList);
		

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/memberListAdmin.jsp");
		rd.forward(request, response);

	}
}