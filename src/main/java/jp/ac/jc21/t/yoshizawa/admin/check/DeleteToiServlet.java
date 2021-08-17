package jp.ac.jc21.t.yoshizawa.admin.check;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/delete/toi")
public class DeleteToiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteToiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text/csv; charset=Windows-31J");
//		PrintWriter out = response.getWriter();
		
		String toiKeyString = request.getParameter("toiId");
		Toi t =Toi.getById(Long.parseLong(toiKeyString));

		String answerSumKeyString = request.getParameter("answerSumId");
		AnswerSum answerSum =AnswerSum.getById(Long.parseLong(answerSumKeyString));
		
		Member member = answerSum.getRefMember().get();
		
		List<AnswerSum> list = t.getAnswerSumList();
		boolean contain = list.contains(Ref.create(answerSum));
		
		System.out.println("contain = "+contain);
		System.out.println("member = "+member);
		
		if(member==null) {
//			list.remove(Ref.create(answerSum));
//			t.setAnswerSumRefList(list);
//			t.save();
		}
		
		response.sendRedirect("/admin/check/toiMember?toiId="+toiKeyString);

		


		
		
	}

}
