package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
/**
 * Servlet implementation class MakeCsvTaskServlet
 */
@WebServlet("/admin/makeCsvTask")
public class MakeCsvTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeCsvTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerSumCSV"));
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", "1"));
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", "3"));
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", "5"));
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", "7"));
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", "9"));
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", "0"));
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", "2"));
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", "4"));
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", "6"));
		queue.add(TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", "8"));
		
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("https://console.cloud.google.com");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
