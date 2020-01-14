package jp.ac.jc21.t.yoshizawa.admin.check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/check/toi")
public class CheckToiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckToiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/csv; charset=Windows-31J");
//		PrintWriter out = response.getWriter();
		
		String keyString = request.getParameter("toiId");
		Toi t =Toi.getById(Long.parseLong(keyString));
		

		
		List<Ref<AnswerSum>> answerSumRefList = t.getAnswerSumRefList();
		
		
		
		ArrayList<String[]> list = new ArrayList<>();
		
		for(Ref<AnswerSum> refAnswerSum : answerSumRefList) {
			AnswerSum answerSum = refAnswerSum.get();

			if(answerSum == null) {
				String[] s = new String[1];
				s[0]="null";
				list.add(s);
			}else {
				String[] s = new String[7];
				s[0]=answerSum.getId().toString();
				s[1]=answerSum.getRefToi().toString();
				s[2]=answerSum.getRefToi().get().getId().toString();
				s[3]=answerSum.getMemberId();
				Member member = answerSum.getRefMember().get();
				if(member==null) {
					s[5]="null";
					s[4]="null";
				}else {
					s[4]=member.toString();
					List<AnswerSum> answerSumList = member.getAnswerSumList();
					s[5]=answerSumList.contains(answerSum)+"";
				}
				s[6]=answerSum.getDateString(answerSum.getAnswered());
				list.add(s);
				
			}
					
		}
		
		request.setAttribute("list", list);
		request.setAttribute("t", t);
		
		request.getRequestDispatcher("/jsp/checkToi.jsp").forward(request, response);
	}

}
