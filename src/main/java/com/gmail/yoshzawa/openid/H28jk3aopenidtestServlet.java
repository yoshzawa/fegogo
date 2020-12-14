package com.gmail.yoshzawa.openid;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.appengine.api.utils.SystemProperty;

import jp.ac.jc21.t.yoshizawa.admin.property.DataStoreControl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/openidSignIn" })

public final class H28jk3aopenidtestServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		DataStoreControl dsc = new DataStoreControl();
		String url = null;

		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			// Production
			url = getProductionLoginUrl(dsc);
		} else {
			// Local development server
			url = getDevelopLoginUrl(dsc);

		}

//		resp.getWriter().println("<a href='"+url+"'>sign-in with microsoft account</a>");
		if(url != null) {
			resp.sendRedirect(url);
		} else {
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp5/property/propertyError.jsp");
				rd.forward(req, resp);
		}

	}

	private String getDevelopLoginUrl(DataStoreControl dsc) {
		String url = null;
		String AzureAppIdLocal = dsc.getId("Property", "AzureAppIdLocal");
		if (AzureAppIdLocal != null) {
			url = "https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize";
			url += "?client_id=" + AzureAppIdLocal;
			url += "&response_type=id_token";
			url += "&redirec_uri=http%3A%2F%2Flocalhost%3A8080%2Fmsredirect";
			url += "&response_mode=form_post";
			url += "&scope=openid%20profile";
			url += "&state=12345";
			url += "&nonce=678910";
		}
		return url;
	}

	private String getProductionLoginUrl(DataStoreControl dsc) {
		String url = null;
		String AzureAppId = dsc.getId("Property", "AzureAppId");
		if (AzureAppId != null) {
			url = "https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize";
			url += "?client_id=" + AzureAppId;
			url += "&response_type=id_token";
			url += "&redirec_uri=https%3A%2F%2Ffegogo.fivepro.xyz%2Fmsredirect";
			url += "&response_mode=form_post";
			url += "&scope=openid%20profile";
			url += "&state=12345";
			url += "&nonce=678910";
		}
		return url;
	}
}
