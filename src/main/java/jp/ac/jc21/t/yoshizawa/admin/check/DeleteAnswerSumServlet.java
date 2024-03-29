package jp.ac.jc21.t.yoshizawa.admin.check;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;
import jp.ac.jc21.t.yoshizawa.objectify.backup.BackupAnswer;
import jp.ac.jc21.t.yoshizawa.objectify.backup.BackupAnswerSum;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/delete/answerSum")
public class DeleteAnswerSumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAnswerSumServlet() {
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

		String answerSumKeyString = request.getParameter("answerSumId");
		Optional<AnswerSum> optAnswerSum = AnswerSum.getOptById(Long.parseLong(answerSumKeyString));
		if (optAnswerSum.isPresent()) {
			AnswerSum answerSum = optAnswerSum.get();
			BackupAnswerSum bupAnswerSum = BackupAnswerSum.createAnswerSum(answerSum);
			bupAnswerSum.save();

			// answer���폜

			List<Answer> answerMap = answerSum.getAnswerList();
			for (Answer a : answerMap) {
				Optional<Answer> answer = Optional.ofNullable(a);
				if (answer.isPresent()) {
					System.out.println("answer:" + answer.get().getId());
					BackupAnswer ba = BackupAnswer.createBackupAnswer(answer.get());
					ba.save();
					answer.get().delete();
				}
			}

			// member����폜

			Optional<Member> optmember = answerSum.getMember();
			if (optmember.isPresent()) {
				/*
				Member member = optmember.get();
				System.out.println("member:" + member.geteMail());
				List<Ref<AnswerSum>> refAnswerSumList = member.getRefAnswerSumList();
				System.out.println("memberLen:" + refAnswerSumList.size());
				refAnswerSumList.remove(Ref.create(answerSum));
				System.out.println("memberLen:" + refAnswerSumList.size());
				member.setRefAnswerSumList(refAnswerSumList);
				member.save();
				*/
			}

			Optional<Toi> optToi = answerSum.getOptToi();
			if (optToi.isPresent()) {
				Toi toi = optToi.get();
				System.out.println("toi:" + toi.getId());
				List<AnswerSum> answerSumRefList = toi.getAnswerSumList();
				System.out.println("toi:" + answerSumRefList.size());
//				answerSumRefList.remove(Ref.create(answerSum));
//				System.out.println("toi:" + answerSumRefList.size());
//				toi.setAnswerSumList(answerSumRefList);
				toi.save();
			}

			answerSum.delete();
		}
		response.sendRedirect("/admin/check/answerSum?answerSumId=" + answerSumKeyString);

	}

}
