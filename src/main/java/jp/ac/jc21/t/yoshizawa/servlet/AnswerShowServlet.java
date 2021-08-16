package jp.ac.jc21.t.yoshizawa.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Question;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/answer/show","/answer2/show" })
public class AnswerShowServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		Optional<String> optAnswerSumId = Optional.ofNullable(request.getParameter("answerSumId"));

		if(optAnswerSumId.isPresent()) {
			AnswerSum ansSummary = AnswerSum.getById(Long.parseLong(optAnswerSumId.get()));
			List<String[]> datas = new ArrayList<String[]>();
			Map<Integer, Answer> answer = ansSummary.getMapAnswer();
			Set<Integer> keyset = answer.keySet();

			for (Integer i : keyset) {
				Answer a = answer.get(i);
				String[] s = new String[3];
				Question question = a.getQuestion();

				s[0] = question.getName();
				s[1] = question.getAnswers();
				s[2] = a.getAnswers();
				datas.add(s);

			}
			request.setAttribute("datas", datas);
			
			request.setAttribute("ansSummary",ansSummary);


			

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/login/answer.jsp");
			rd.forward(request, response);
		}else {
			response.getWriter().println("�����G���[���������܂����B�𓚂�o�^�ł��܂���B");
		}
		
	}

}