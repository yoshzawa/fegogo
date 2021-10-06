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

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.CommonFunction;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/check/toiMember")
public class CheckToiMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckToiMemberServlet() {
		super();
		// TODO Auto-generated constructor stub
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

		String keyString = request.getParameter("toiId");
		Toi t = Toi.getById(Long.parseLong(keyString));

		List<AnswerSum> answerSumRefList = t.getAnswerSumList();

		ArrayList<String[]> list = new ArrayList<>();

		for (AnswerSum answerSum : answerSumRefList) {

			if (answerSum == null) {
				String[] s = new String[1];
				s[0] = "null";
				list.add(s);
			} else {
				String[] s = new String[7];
				s[0] = answerSum.getId().toString();
				s[1] = answerSum.getOptToi().toString();
				s[2] = answerSum.getOptToi().get().getId().toString();
				s[3] = answerSum.getName();

				Optional<Ref<Member>> refMem = Optional.ofNullable(answerSum.getRefMember());
				Optional<Member> mem = refMem.map(rm -> rm.get());

				if(!mem.isPresent()) {
					s[4] = "null";
					s[5] = "null";
				} else {
					Member member = mem.get();
					s[4] = member.toString();

			
					s[5]=member.containsRefAnswerSum(answerSum)+"";

					
				}
				s[6] = CommonFunction.dateFormat(answerSum.getAnswered());
				list.add(s);

			}

		}

		request.setAttribute("list", list);
		request.setAttribute("t", t);

		request.getRequestDispatcher("/jsp/checkToiMember.jsp").forward(request, response);
	}

}
