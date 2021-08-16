package ToBeDeleted.admin.export;

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

		TaskOptions task = TaskOptions.Builder.withUrl("/admin/makeAnswerSumCSV");
		queue.add(task);
		
//		for(int i=0 ; i<6 ; i++) {
		for(int i=0 ; i<30 ; i++) {
			task = TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", i+"");
			queue.add(task);
		}
		
		response.sendRedirect("https://console.cloud.google.com/storage/browser/fegogo.appspot.com?project=fegogo");
	}
}
