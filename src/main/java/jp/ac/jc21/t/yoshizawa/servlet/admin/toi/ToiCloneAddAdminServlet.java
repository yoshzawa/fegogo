package jp.ac.jc21.t.yoshizawa.servlet.admin.toi;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.CloneToi;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class ToiCloneAddAdminServlet
 */
@WebServlet("/admin/toi/clone/add")
public class ToiCloneAddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToiCloneAddAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cloneToiId=request.getParameter("fromToi");
		String orgToiId=request.getParameter("toiId");
		
		Optional<Toi> cloneToi= Optional.ofNullable(Toi.getById(cloneToiId));
		Optional<Toi> orgToi= Optional.ofNullable(Toi.getById(orgToiId));
		
		if((cloneToi.isPresent())&&(orgToi.isPresent())) {
			new CloneToi(cloneToi.get().getId(),orgToi.get().getId()).save();
		}
		response.sendRedirect("/admin/question/list3?parentId="+cloneToi.get().getId());

		
		
	}

}
