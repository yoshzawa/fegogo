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

@WebServlet(urlPatterns = { "/admin/question/imageRename" })
public class QuestionImageRenameAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ñ‚ÇÃIDÇéÊÇËèoÇ∑
		String parentIdString = request.getParameter("parentId");
		
		String noString = request.getParameter("no");
		int no = Integer.parseInt(noString)-1;

		String urlString = request.getParameter("url");


		Toi parent = Toi.getById(Long.parseLong(parentIdString));

		List<ImageSet> imageSet = parent.getImageSet();
		if (imageSet== null) {
			response.sendRedirect("./image?parentId="+parentIdString);
		}else {
			ImageSet is = imageSet.get(no);
			is.setUrl(urlString);
			parent.save();
			response.sendRedirect("./image?parentId="+parentIdString+"#"+noString);
		}
		


	}

}