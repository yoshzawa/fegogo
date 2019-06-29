package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/toi/list" })
public class ToiListAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String parentIdString = request.getParameter("parentId");
		List<Toi> toiList = Toi.load(Long.parseLong(parentIdString));
		
		TreeMap<Long,Toi> toiMap = new TreeMap<>();
		
		for(Toi t:toiList) {
			toiMap.put(t.getNo(),t);
		}
		
		
		
		request.setAttribute("toiMap", toiMap);
		request.setAttribute("parentId", parentIdString);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/toiListAdmin.jsp");
		rd.forward(request, response);

	}
}