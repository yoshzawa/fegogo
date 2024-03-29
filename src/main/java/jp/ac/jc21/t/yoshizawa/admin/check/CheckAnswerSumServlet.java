package jp.ac.jc21.t.yoshizawa.admin.check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.CommonFunction;
import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/check/answerSum")
public class CheckAnswerSumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckAnswerSumServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text/csv; charset=Windows-31J");
//		PrintWriter out = response.getWriter();

		String keyString = request.getParameter("answerSumId");
//		Exam e =Exam.getById(Long.parseLong(keyString));
		Optional<AnswerSum> optAnswerSum = Optional.ofNullable(AnswerSum.getById(Long.parseLong(keyString)));

		if (optAnswerSum.isPresent()) {
			AnswerSum answerSum = optAnswerSum.get();

			Optional<Member> member = answerSum.getMember();
			request.setAttribute("member", member);
			Optional<Toi> toi = answerSum.getOptToi();
			request.setAttribute("toi", toi);

			List<Answer> answerList = answerSum.getAnswerList();

			List<String[]> list = new ArrayList<>();
			for (Answer answer : answerList) {
				if (answer == null) {
					String[] s = new String[3];
					s[0] = null;
					s[1] = answer.getId() + "";
					list.add(s);
				} else {
					String[] s = new String[6];
					s[0] = answer.getId().toString();
					s[1] = CommonFunction.dateFormat(answer.getAnswered());
					s[2] = answer.getNo().toString();
					s[3] = answer.getName();
					s[4] = answer.getAnswers();

					s[5] = null;
					Optional<AnswerSum> aSum = answer.getAnswerSum();
					if (aSum.isPresent()) {
						s[5] = aSum.get().getId().toString();
					}

					list.add(s);

				}

			}
			request.setAttribute("list", list);
			request.setAttribute("answerSum", answerSum);

		}else {
			request.setAttribute("list", new ArrayList<>());
			request.setAttribute("answerSum", null);
			request.setAttribute("member", Optional.empty());
			request.setAttribute("toi", Optional.empty());

		}

		request.getRequestDispatcher("/jsp/checkAnswerSum.jsp").forward(request, response);
	}

}
