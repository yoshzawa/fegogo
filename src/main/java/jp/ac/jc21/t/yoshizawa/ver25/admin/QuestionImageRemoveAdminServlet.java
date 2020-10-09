package jp.ac.jc21.t.yoshizawa.ver25.admin;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.ImageSet;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/question/imageRemove" })
public class QuestionImageRemoveAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ñ‚ÇÃIDÇéÊÇËèoÇ∑
		String parentIdString = request.getParameter("parentId");
		
		String noString = request.getParameter("no");
		int no = Integer.parseInt(noString)-1;
		

		Toi parent = Toi.getById(Long.parseLong(parentIdString));

		List<ImageSet> imageSet = parent.getImageSet();
		if (imageSet== null) {
			response.sendRedirect("./image?parentId="+parentIdString);
		}else {
			List<ImageSet> imageSetNew = new ArrayList<ImageSet>();
			for(int i=0 ; i<imageSet.size() ; i++) {
				if(i != no) {
					imageSetNew.add(imageSet.get(i));
				}
			}
			parent.setImageSet(imageSetNew);
			parent.save();
		}
		
		response.sendRedirect("./image?parentId="+parentIdString);


	}

}