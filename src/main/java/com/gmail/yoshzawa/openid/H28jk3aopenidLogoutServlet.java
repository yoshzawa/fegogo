package com.gmail.yoshzawa.openid;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/openidSignOut" })


public final class H28jk3aopenidLogoutServlet extends HttpServlet implements AzureConstant{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		session.setAttribute("email", null);
		
		String url = "https://login.microsoftonline.com/organizations/oauth2/v2.0/logout";
//		url+="&post_logout=http%3A%2F%2Fthree.fivepro.xyz%2F%2F" ;
		url+="?post_logout=http%3A%2F%2Ffegogo.appspot.com%2Fexam%2Flist" ;

		resp.sendRedirect(url);
		
		
	}
}
