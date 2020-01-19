package jp.ac.jc21.t.yoshizawa.admin.check;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/delete/toiDeadLink")
public class DeleteToiDeadLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteToiDeadLinkServlet() {
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
		
		
		Key<AnswerSum> key = Key.create(AnswerSum.class,Long.parseLong(answerSumKeyString));
		Ref<AnswerSum> refASum = Ref.create(key);
		List<Ref<AnswerSum>> answerSumRefList = t.getAnswerSumRefList();
		boolean alive = (answerSum!=null);
		boolean contain = answerSumRefList.contains(refASum);
		
			System.out.println("toiKeyString="+toiKeyString);
			System.out.println("answerSumKeyString="+answerSumKeyString);
			System.out.println("alive="+alive);
			System.out.println("contain="+contain);

			if(alive == false) {
				System.out.println("before="+
				answerSumRefList.size());
				answerSumRefList.remove(refASum);
				System.out.println("after="+
				answerSumRefList.size());
				t.setAnswerSumRefList(answerSumRefList);
				t.save();
		}

		
		
		response.sendRedirect("/admin/check/toi?toiId="+toiKeyString);

		


		
		
	}

}
