package jp.ac.jc21.t.yoshizawa.ver25.admin;

import java.io.IOException;
import java.util.logging.Logger;

import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.ImageSet;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/question/imageHeight" })
public class QuestionImageHeightAdminServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(QuestionImageHeightAdminServlet.class.getName());

		// ñ‚ÇÃIDÇéÊÇËèoÇ∑
		String parentIdString = request.getParameter("parentId");
		
		String noString = request.getParameter("no");
		int no = Integer.parseInt(noString)-1;
		
		String heightString = request.getParameter("height");
		int height = Integer.parseInt(heightString);

		Toi parent = Toi.getById(Long.parseLong(parentIdString));

		List<ImageSet> imageSet = parent.getImageSet();
		if (imageSet== null) {
			response.sendRedirect("./image?parentId="+parentIdString);
		}else {
			imageSet.get(no).setHeight(height);
			
			parent.setImageSet(imageSet);
			parent.save();
		}
		
		response.sendRedirect("./image"+"?parentId="+parentIdString+"#"+noString);


	}

}