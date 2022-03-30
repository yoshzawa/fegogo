package com.gmail.yoshzawa.openid;

import java.io.IOException;
import org.apache.geronimo.mail.util.Base64;

import com.gmail.yoshzawa.openid.jwt.JwtHeader;
import com.gmail.yoshzawa.openid.ofy.UserAccount;
import com.gmail.yoshzawa.openid.jwt.JwtPayload;
import com.google.common.collect.Comparators;
import com.google.gson.Gson;

import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.servlet.GetGsonInterface;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/msredirect", "/msredirect/" })

public final class Jc21MSRedirectServlet extends HttpServlet {

	static {
		UserAccount.ofyInit();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		final Logger log = Logger.getLogger(Jc21MSRedirectServlet.class.getName());
		resp.setContentType("text/html");

		String id_token = req.getParameter("id_token");
		String state = req.getParameter("state");
		String error = req.getParameter("error");
		String error_description = req.getParameter("error_description");

		log.info("id_token = " + id_token);
		log.info("state = " + state);
		log.info("error = " + error);
		log.info("error_description = " + error_description);

		String[] tokens = id_token.split("\\.");

		String head = new String(Base64.decode(tokens[0]));
		log.info("head = " + head);
		Gson gson = new Gson();
		JwtHeader header = gson.fromJson(head, JwtHeader.class);
		log.info("typ = " + header.getTyp());
		log.info("alg = " + header.getAlg());
		log.info("kid = " + header.getKid());

		String s = tokens[1];
		switch (s.length() % 4) {
		case 0:
			break;
		case 1:
			s += "===";
			break;
		case 2:
			s += "==";
			break;
		case 3:
			s += "=";
			break;
		}

		String payload = new String(Base64.decode(s));
		log.info("payload = " + payload);
		JwtPayload body = gson.fromJson(payload + "", JwtPayload.class);
		log.info("aud = " + body.getAud());
		log.info("iss = " + body.getIss());
		log.info("iat = " + body.getIat());
		log.info("nbf = " + body.getNbf());
		log.info("name = " + body.getName());
		log.info("exp = " + body.getExp());
		log.info("nonce = " + body.getNonce());
		log.info("oid = " + body.getOid());
		String email = body.getPreferredUsername();
		log.info("preferred_username = " + email);
		log.info("sub = " + body.getSub());
		log.info("tid = " + body.getTid());
		log.info("ver = " + body.getVer());

		UserAccount user = new UserAccount(email,body.getAud());
		user.setRemoteHost(req.getRemoteHost());
		user.save();

		HttpSession session = req.getSession();
		session.setAttribute("email", email);

		String memberGetUrl = "https://fegogo.appspot.com/endpoint/v0/member/get?email=";

		List<Member> memberList = GetGsonInterface.getMemberList(memberGetUrl + email);
		Member m;
			m = memberList.stream()
					.sorted(Comparator.comparing(Member::getModified))
					.findFirst()
					.orElse(Member.createMember(email));
		
		m.setModified(new Date());

		String addMemberUrl="https://fegogo.appspot.com/endpoint/v0/member/add";
		GetGsonInterface.addMember(addMemberUrl,m);


		resp.getWriter().println("<H1>Welcome," + email + "</h1>");
		resp.getWriter().println("<a href='/index'>Continue</a>");

	}
}
