package com.gmail.yoshzawa.openid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/openidSignIn" })


public class H28jk3aopenidtestServlet extends HttpServlet implements AzureConstant{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("<h1>Sign In</H1>");
		
		String url = "https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize";
		url+="?client_id=" + AzureAppId;
		url+="&response_type=id_token";
//		url+="&redirec_uri=http%3A%2F%2Fthree.fivepro.xyz%2Fmsredirect%2F" ;
		url+="&redirec_uri=http%3A%2F%2Ffegogo.fivepro.xyz%2Fmsredirect" ;
		url+="&response_mode=form_post";
		url+="&scope=openid%20profile";
		url+="&state=12345";
		url+="&nonce=678910";
		
		resp.getWriter().println("<a href='"+url+"'>sign-in with microsoft account</a>");
		
	}
}
