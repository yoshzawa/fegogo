package com.gmail.yoshzawa.openid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.appengine.api.utils.SystemProperty;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/openidSignIn" })


public class H28jk3aopenidtestServlet extends HttpServlet implements AzureConstant{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("<h1>Sign In</H1>");
		String url =null;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			   // Production
			 url = "https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize";
			url+="?client_id=" + AzureAppId;
			url+="&response_type=id_token";
			url+="&redirec_uri=https%3A%2F%2Ffegogo.fivepro.xyz%2Fmsredirect" ;
			url+="&response_mode=form_post";
			url+="&scope=openid%20profile";
			url+="&state=12345";
			url+="&nonce=678910";
		} else {
			  // Local development server
			 url = "https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize";
			url+="?client_id=" + AzureAppIdLocal;
			url+="&response_type=id_token";
			url+="&redirec_uri=http%3A%2F%2Flocalhost%3A8080%2Fmsredirect" ;
			url+="&response_mode=form_post";
			url+="&scope=openid%20profile";
			url+="&state=12345";
			url+="&nonce=678910";

			}
		
		
		resp.getWriter().println("<a href='"+url+"'>sign-in with microsoft account</a>");
		
	}
}
