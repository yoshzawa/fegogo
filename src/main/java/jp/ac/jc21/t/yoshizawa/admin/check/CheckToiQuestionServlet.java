package jp.ac.jc21.t.yoshizawa.admin.check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.CommonFunction;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/check/toiQuestion")
public class CheckToiQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckToiQuestionServlet() {
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
		
		String keyString = request.getParameter("toiId");
		Toi t =Toi.getById(Long.parseLong(keyString));
		
//		List<Ref<AnswerSum>> answerSumRefList = t.getAnswerSumRefList();
		List<Question> refQList = t.getQuestionList();
		
		ArrayList<String[]> list = new ArrayList<>();
		
		for(Question question : refQList) {

			if(question == null) {
				String[] s = new String[4];
				s[0]=null;
//				s[1]=refAnswerSum.getKey().getId()+"";
//				Key<AnswerSum> key = Key.create(AnswerSum.class,Long.parseLong(s[1]));
//				Ref<AnswerSum> refASum = Ref.create(key);
//				s[2]=answerSumRefList.contains(refASum)+"";

				
				list.add(s);
			}else {
				String[] s = new String[7];
				s[0]=question.getId().toString();
				s[1]=question.getOptToi().toString();
				s[2]=question.getOptToi().get().getId().toString();
				s[3]=question.getOptToi().get().containQuestion(question)+"";
					

				
				s[6]=CommonFunction.dateFormat(question.getCreated());
				list.add(s);
				
			}
					
		}
		
		request.setAttribute("list", list);
		request.setAttribute("t", t);
		
		request.getRequestDispatcher("/jsp/checkToiQuestion.jsp").forward(request, response);
	}

}
