package jp.ac.jc21.t.yoshizawa.ver25.admin;

import java.io.IOException;
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

@WebServlet(urlPatterns = { "/admin/question/imageReset" })
public class QuestionImageResetAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// 問のIDを取り出す
		String parentIdString = request.getParameter("parentId");
		request.setAttribute("parentId", parentIdString);


		// 問を取り出す
		long parentId = Long.parseLong(parentIdString);
		Toi parent = Toi.getById(parentId);
		request.setAttribute("parent", parent);
		
		// 試験を取り出す
		Exam exam = parent.getExam();
		request.setAttribute("exam", exam);

		// 設問を取り出す
		TreeMap<Long, Question> qMap = Toi.getQuestionMap(parent);
		request.setAttribute("questionMap", qMap);
		
		List<Genre> genreList = Genre.loadAll();
		request.setAttribute("genreList", genreList);

		List<ImageSet> imageSet = parent.getImageSet();
			imageSet = new ArrayList<ImageSet>();
			for(Long key : qMap.keySet()) {
				imageSet.add(new ImageSet(qMap.get(key).getNo()));
			}
			parent.setImageSet(imageSet);
			parent.save();
		request.setAttribute("imageSet", imageSet);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionImageAdmin.jsp");
		rd.forward(request, response);

	}

}