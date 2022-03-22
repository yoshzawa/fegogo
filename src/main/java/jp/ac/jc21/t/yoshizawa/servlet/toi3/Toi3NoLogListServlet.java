package jp.ac.jc21.t.yoshizawa.servlet.toi3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;
import jp.ac.jc21.t.yoshizawa.servlet.GetGsonInterface;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/toi3/NoLog/list" })
public class Toi3NoLogListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ExamとIDを取得
		Optional<String> OptToiIdString = Optional.ofNullable(request.getParameter("parentId"));

		// Examを取得
		String examListUrl = "https://fegogo.appspot.com/endpoint/v0/exam/get?ExamId=" + OptToiIdString.orElse("");
		List<Exam> examList = GetGsonInterface.ExamListFromGson(examListUrl);
		Optional<Exam> streamExam = examList.stream().sorted(Comparator.comparing(Exam::getYYYYMM)).findFirst();
		if (streamExam.isPresent()) {
			Exam e = streamExam.get();

			request.setAttribute("isOpened", e.isOpened());

			// 問の一覧を取得
//			TreeMap<Long, Toi> toiMap = e.getToiMap();
			Map<Long, Toi> toiMap = new TreeMap<Long, Toi>();
			String toiListUrl = "https://fegogo.appspot.com/endpoint/v0/exam/get/toiId/List";
			List<Long> toiIdList = GetGsonInterface.LongListFromGson(toiListUrl + "?ExamId=" + OptToiIdString.orElse(""));
			
			String toiGetUrl = "https://fegogo.appspot.com/endpoint/v0/toi/get";
			for(Long toiId : toiIdList)			{
				List<Toi> toiList = GetGsonInterface.ToiListFromGson(toiGetUrl + "?ToiId=" + toiId);
				Toi t = toiList.stream().findAny().get();
				toiMap.put(t.getNo(), t);
			}
			

			// ユーザー情報取得
			request.setAttribute("ExamName", e.getName());

			// ログインしていない場合
			Set<Long> toiKeySet = toiMap.keySet();
			List<String[]> datas = new ArrayList<String[]>(toiKeySet.size());
			for (Long key : toiKeySet) {
				Toi t = toiMap.get(key);
				String[] s = new String[5];
				s[0] = t.getNo().toString();
				s[1] = t.getGenreName();
				s[2] = "<a href='/question/list?parentId=" + t.getId() + "'>" + t.getName() + "</a>";
				if (t.getImageSet() != null) {
					s[2] = s[2] + "<B>(CBT)</B>";
				}
				s[3] = t.getQuestionListSize() + "";
				s[4] = t.getAnswerSumCount() + "";
				datas.add(s);
			}
			request.setAttribute("datas", datas);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/toiList.jsp");
			rd.forward(request, response);

		} else {
			response.sendError(404);
		}

	}

}