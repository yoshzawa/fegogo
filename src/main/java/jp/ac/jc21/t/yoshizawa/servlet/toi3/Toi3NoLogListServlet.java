package jp.ac.jc21.t.yoshizawa.servlet.toi3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;
import jp.ac.jc21.t.yoshizawa.servlet.endpoint.EndPointAnswerSum;
import jp.ac.jc21.t.yoshizawa.servlet.endpoint.EndPointExam;
import jp.ac.jc21.t.yoshizawa.servlet.endpoint.EndPointQuestion;
import jp.ac.jc21.t.yoshizawa.servlet.endpoint.EndPointToi;
import jp.ac.jc21.t.yoshizawa.servlet.endpoint.GetGson;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/toi3/NoLog/list" })
public class Toi3NoLogListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Exam��ID���擾
		Optional<String> OptExamIdString = Optional.ofNullable(request.getParameter("parentId"));

		// Exam���擾

		List<Exam> examList = EndPointExam.getExamById(OptExamIdString.orElse(""));
		Optional<Exam> streamExam = examList.stream().sorted(Comparator.comparing(Exam::getYYYYMM)).findFirst();
		if (streamExam.isPresent()) {
			Exam e = streamExam.get();

			request.setAttribute("isOpened", e.isOpened());

			// ��̈ꗗ���擾
//			TreeMap<Long, Toi> toiMap = e.getToiMap();
			Map<Long, Toi> toiMap = new TreeMap<Long, Toi>();
			List<Long> toiIdList = EndPointToi.getToiIdListByExamId(OptExamIdString.orElse(""));
			
			// Toi�̒��g���擾
			for(Long toiId : toiIdList)			{
				List<Toi> toiList = EndPointToi.getToiListByToiId(toiId);
				Toi t = toiList.stream().findAny().get();
				toiMap.put(t.getNo(), t);
			}
			

			// ���[�U�[���擾
			request.setAttribute("ExamName", e.getName());

			// ���O�C�����Ă��Ȃ��ꍇ
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
				
				List<Long> questionIdList = EndPointQuestion.getQuestionListByToiId(t.getId());
				s[3] = questionIdList.size()+"";

				
				// TODO WEB�o�R�ɂ���
				List<Long> answerSumIdList = EndPointAnswerSum.getAnswerSumIdListByToiId(t.getId());
				
				s[4] = t.getAnswerSumCount() + "";
				s[4]=answerSumIdList.size()+"";
				datas.add(s);
			}
			request.setAttribute("datas", datas);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/toiList.jsp");
			rd.forward(request, response);

		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

}