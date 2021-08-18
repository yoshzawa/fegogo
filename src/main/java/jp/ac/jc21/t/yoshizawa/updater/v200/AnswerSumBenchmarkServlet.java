package jp.ac.jc21.t.yoshizawa.updater.v200;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSumFactory;

/**
 * Servlet implementation class AnswerSumBenchmarkServlet
 */
@WebServlet("/AnswerSumBenchmarkServlet")
public class AnswerSumBenchmarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerSumBenchmarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Date started = new Date();
		out.println("Program Start");
		out.println(started+"("+(started.getTime()-started.getTime())+")");
		AnswerSum.loadAll();
		Date lap1 = new Date();
		out.println("Lap1 : loadAll");
		out.println(lap1+"("+(lap1.getTime()-started.getTime())+")");
		AnswerSum.loadAll2();
		Date lap2 = new Date();
		out.println("Lap2 : loadAll2");
		out.println(lap2+"("+(lap2.getTime()-lap1.getTime())+")");
		AnswerSum.loadAll2();
		Date lap3 = new Date();
		out.println("Lap3 : loadAll2");
		out.println(lap3+"("+(lap3.getTime()-lap2.getTime())+")");
	}

}
