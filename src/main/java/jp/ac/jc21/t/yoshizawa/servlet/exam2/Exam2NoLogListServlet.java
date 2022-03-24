package jp.ac.jc21.t.yoshizawa.servlet.exam2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/exam2/Nolog/list" })
public class Exam2NoLogListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Map<Long, Exam> examMap = Exam.loadAll();

		List<String[]> datas = new ArrayList<String[]>();
		for (Long k : examMap.keySet()) {
			if (k > 300000)
				break;
			Exam e = examMap.get(k);
			String[] s = new String[2];

			int openDiff = 1;
			try {
				openDiff = new Date().compareTo(e.getOpenDate());
			} catch (NullPointerException ex) {
			}
			int closeDiff = -1;
			try {
				closeDiff = new Date().compareTo(e.getCloseDate());
			} catch (NullPointerException ex) {
			}

			if (openDiff == -1) {
				s[0] = e.getName() + "(" + dateFormat(e.getOpenDate()) + "ÇÊÇËâÒìöâ¬î\)";
			} else if (closeDiff == 1) {
				s[0] = e.getName() + "(" + dateFormat(e.getCloseDate()) + "Ç≈âÒìöèIóπ)";

			} else {
				s[0] = "<a href='/toi/list?parentId=" + e.getId() + "'>" + e.getName() + "</a>";
				if (e.getCloseDate() != null) {
					s[0] += "(" + dateFormat(e.getCloseDate()) + "Ç‹Ç≈âÒìöâ¬î\)";
				} else {
					s[0] += "(ä˙å¿Ç»Çµ)";
				}

			}

			s[1] = e.getToiListSize() + "";
			datas.add(s);
		}

		request.setAttribute("datas", datas);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/examList.jsp");
		rd.forward(request, response);

	}

	private final String dateFormat(Date d){	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");	
	    TimeZone timeZoneJP = TimeZone.getTimeZone("Asia/Tokyo");
	    sdf.setTimeZone(timeZoneJP);
		return sdf.format(d);	
	}	

}