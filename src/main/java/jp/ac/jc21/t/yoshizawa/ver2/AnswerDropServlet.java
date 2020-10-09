package jp.ac.jc21.t.yoshizawa.ver2;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/answer/drop","/answer2/drop" })
public class AnswerDropServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		String answerSumId = request.getParameter("answerSumId");
		Optional<String> optMasterAnswerSumId = Optional.ofNullable(request.getParameter("masterAnswerSumId"));

		Optional<AnswerSum> optASum = AnswerSum.getOptById(Long.parseLong(answerSumId));
		if(optASum.isPresent()) {
			AnswerSum aSum = optASum.get();
			Optional<Map<String, Ref<Answer>>> optMap = aSum.getOptMapRefAnswer();
			if(optMap.isPresent()) {
				Map<String, Ref<Answer>> map = optMap.get();
				for(String key:map.keySet()) {
					Answer v = map.get(key).get();
					v.delete();
				}
			}
			//aSum.setMapRefAnswer(null);
			aSum.delete();
		}
		if(optMasterAnswerSumId.isPresent()) {
			RequestDispatcher rd = request.getRequestDispatcher("/answer2/show?answerSumId="+optMasterAnswerSumId.get());
			rd.forward(request, response);
		}else {
			response.getWriter().println("内部エラーが発生しました。解答を登録できません。");
		}
		
	}

}