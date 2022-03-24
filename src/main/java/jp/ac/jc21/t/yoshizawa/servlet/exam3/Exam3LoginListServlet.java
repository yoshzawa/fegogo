package jp.ac.jc21.t.yoshizawa.servlet.exam3;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.servlet.GetGsonInterface;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/exam3/Login/list" })
public class Exam3LoginListServlet extends HttpServlet {

	private final String examListUrl = "https://fegogo.appspot.com/endpoint/v0/exam/id/list";
	private final String examGetUrl = "https://fegogo.appspot.com/endpoint/v0/exam/get?ExamId=";
	private final String cacheKeyTop = "Exam3ListServlet:";
	private final String toiListUrl = "https://fegogo.appspot.com/endpoint/v0/exam/get/toiId/List";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ユーザー情報取得
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		request.setAttribute("email", email);

		List<String[]> datas = new ArrayList<>();
		Stream<Long> examIdStream = GetGsonInterface.getLongList(examListUrl).stream();
		Stream<Long> examIdStream2 = examIdStream.filter((Long yyyymm) -> yyyymm < 300000);
		Stream<Long> examIdStream3 = examIdStream2.sorted();
		examIdStream3.forEach((Long l) -> {
			String[] data = getCachedArrayOrEndPoint(l);
			datas.add(data);
		});

		request.setAttribute("datas", datas);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/login/examListLogin.jsp");
		rd.forward(request, response);
	}

	private final String[] getCachedArrayOrEndPoint(Long examKey) {
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
		String[] data = new String[2];

		String cacheKey = cacheKeyTop + examKey;
		Optional<String[]> optExamArray = Optional.ofNullable((String[]) syncCache.get(cacheKey));
		if (optExamArray.isPresent()) {
			data = optExamArray.get();
		} else {
			List<Exam> examList = GetGsonInterface.ExamListFromGson(examGetUrl + examKey);
			optExamArray = examList.stream().map((Exam e) -> makeDisplayData(e)).findAny();
			if (optExamArray.isPresent()) {
				data = optExamArray.get();
				syncCache.put(cacheKey, optExamArray.get());
			}
		}
		return data;
	}

	private final String[] makeDisplayData(Exam e) {
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
			s[0] = e.getName() + "(" + dateFormat(e.getOpenDate()) + "より回答可能)";
		} else if (closeDiff == 1) {
			s[0] = e.getName() + "(" + dateFormat(e.getCloseDate()) + "で回答終了)";

		} else {
			s[0] = "<a href='/toi/list?parentId=" + e.getId() + "'>" + e.getName() + "</a>";
			if (e.getCloseDate() != null) {
				s[0] += "(" + dateFormat(e.getCloseDate()) + "まで回答可能)";
			} else {
				s[0] += "(期限なし)";
			}
		}

		try {
			List<Long> examList = GetGsonInterface.getLongList(toiListUrl + "?ExamId=" + e.getId());
			s[1] = examList.size() + "";
		} catch (IOException ex) {
			s[1] = "**exception!**";
		}
		return s;
	}

	private final String dateFormat(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		TimeZone timeZoneJP = TimeZone.getTimeZone("Asia/Tokyo");
		sdf.setTimeZone(timeZoneJP);
		return sdf.format(d);
	}
}