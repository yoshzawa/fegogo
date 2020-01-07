package jp.ac.jc21.t.yoshizawa.admin.check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/check/exam")
public class CheckExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckExamServlet() {
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
		
		String keyString = request.getParameter("examId");
		Exam e =Exam.getById(Long.parseLong(keyString));
		
		
		
		TreeMap<Long, Toi> map = e.getToiMap();
		
		Set<Long> keyset = map.keySet();
		
		ArrayList<String[]> list = new ArrayList<>();
		
		for(Long key : keyset) {
			Toi toi = map.get(key);
			Long examId = toi.getExamId();
			String[] s = new String[4];
			s[0]=key.toString();
			s[1]=toi.getId().toString();
			s[2]=toi.getName();
			s[3]=examId.toString();
			list.add(s);
		}
		
		request.setAttribute("list", list);
		request.setAttribute("e", e);
		
		request.getRequestDispatcher("/jsp/checkExam.jsp").forward(request, response);
	}

}
