package jp.ac.jc21.t.yoshizawa.servlet.admin.exam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

/**
 * Servlet implementation class ExamEditAdminServlet
 */
@WebServlet("/admin/exam/edit")
public class ExamEditAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamEditAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		String examId=request.getParameter("examId");
		String YYYYMM=request.getParameter("YYYYMM");
		String name=request.getParameter("name");
		String openNull=request.getParameter("openNull");
		String open=request.getParameter("open");
		String closeNull=request.getParameter("closeNull");
		String close=request.getParameter("close");

		System.out.println(examId);
		System.out.println(YYYYMM);
		System.out.println(name);
		System.out.println(openNull);
		System.out.println(open);
		System.out.println(closeNull);
		System.out.println(close);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		Exam e = Exam.getById(Long.parseLong(examId));
		e.setYYYYMM(Long.parseLong(YYYYMM));
		e.setName(name);
		try {
		if(openNull == null) {
				e.setOpenDate(sdf.parse(open));
		} else {
			e.setOpenDate(null);
		}
		} catch (ParseException e1) {
			e1.printStackTrace();
			e.setOpenDate(null);			
		}
		try {
		if(closeNull == null) {
				e.setCloseDate(sdf.parse(close));
		} else {
			e.setCloseDate(null);
		}
		} catch (ParseException e1) {
			e1.printStackTrace();
			e.setCloseDate(null);			
		}
		e.save();
	}

}
