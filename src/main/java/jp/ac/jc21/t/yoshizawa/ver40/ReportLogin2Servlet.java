package jp.ac.jc21.t.yoshizawa.ver40;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.AccessLog;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class ReportLoginServlet
 */
@WebServlet("/report2")
public class ReportLogin2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportLogin2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		request.setAttribute("email", email);

		Member member = Member.get(email);

		List<AnswerSum> answerSumList = member.getAnswerSumListSorted();
		
		// ジャンル別の結果の入れ物を用意
		Map<Long, List<AnswerSum>> resultMap = new TreeMap<Long, List<AnswerSum>>();

		for (AnswerSum as : answerSumList) {
			// 問題のGenreごとに、MapにListを格納
			Optional<Toi> toi = Toi.getByAnswerSum(as);
			if(toi.isPresent()) {
				Ref<Genre> refGenre = toi.get().getRefGenre();
				Long genreId = refGenre.get().getId();
				List<AnswerSum> genreList = resultMap.get(genreId);

				if(genreList == null) {
					genreList=new ArrayList<AnswerSum>();
				}
				genreList.add(as);
				resultMap.put(genreId,genreList);
			}
			
		}
		request.setAttribute("resultMap", resultMap);
		
		// ジャンルの一覧
		
		List<Genre> genreList = Genre.loadAll();
		request.setAttribute("genreList", genreList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/login/reportLogin2.jsp");
		rd.forward(request, response);

	}
	private String changePoint(AnswerSum as) {
		return changePoint(as.getNoOfSeikai(), as.getNoOfAnswer());
	}

	private final String changePoint(int seikai, int answer) {
		float point = (100.0f * seikai / answer);
		return String.format("%1$.1f", point);
	}

	private final String dateFormat(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(d);
}
}