package com.gmail.yoshzawa.openid;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/openidSignOut" })

public final class Jc21MSOpenidSignoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();
		session.setAttribute("email", null);

		String url = "https://login.microsoftonline.com/common/oauth2/v2.0/logout";
		url += "?post_logout_redirect_uri=http%3A%2F%2Ffegogo.fivepro.xyz%2F%2F";

		resp.sendRedirect(url);

	}
}
