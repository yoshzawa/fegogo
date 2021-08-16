package jp.ac.jc21.t.yoshizawa.servlet.admin.question;

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

@WebServlet(urlPatterns = { "/admin/question/imageAdd" })
public class QuestionImageAddAdminServlet extends HttpServlet {

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
			List<ImageSet> imageSetNew = new ArrayList<ImageSet>();
			for(int i=0 ; i<imageSet.size() ; i++) {
				if(i == no) {
					imageSetNew.add(new ImageSet(urlString,1000,0));
				}
				imageSetNew.add(imageSet.get(i));
			}
			if(no<0) {
				imageSetNew.add(new ImageSet(urlString,1000,0));
			}
			
			parent.setImageSet(imageSetNew);
			parent.save();
		}
		
		response.sendRedirect("./image?parentId="+parentIdString);


	}

}