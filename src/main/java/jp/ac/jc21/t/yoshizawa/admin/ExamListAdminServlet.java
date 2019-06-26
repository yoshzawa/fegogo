package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

@WebServlet(
    urlPatterns = {"/admin/exam/list"}
)
public class ExamListAdminServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
    
    List<Exam> examList = Exam.loadAll();
    request.setAttribute("examList", examList);
    
    RequestDispatcher rd = request.getRequestDispatcher("/jsp/examListAdmin.jsp");
    rd.forward(request, response);
    

  }
}