package jp.ac.jc21.t.yoshizawa.servlet.admin.question;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.CloneQuestion;
import jp.ac.jc21.t.yoshizawa.objectify.CloneToi;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/question/list3" })
public class QuestionList3AdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ���ID�����o��
		String parentIdString = request.getParameter("parentId");
		request.setAttribute("parentId", parentIdString);


		// ������o��
		long parentId = Long.parseLong(parentIdString);
		Toi parent = Toi.getById(parentId);
		request.setAttribute("parent", parent);

		// ���������o��
		Exam exam = parent.getExam();
		request.setAttribute("exam", exam);

		// �ݖ�����o��
		TreeMap<Long, Question> qMap = Toi.getQuestionMap(parent);
		request.setAttribute("questionMap", qMap);
		
		List<Genre> genreList = Genre.loadAll();
		request.setAttribute("genreList", genreList);

		
		if(CloneToi.getByToiId(parentId).size()>0) {
			//clone���ꂽ
			
			CloneToi cloneToi = CloneToi.getByToiId(parent.getId()).get(0);
			Toi orgToi = Toi.getById(cloneToi.getOriginalToiId());
			List<CloneQuestion> cloneQuestionList = CloneQuestion.getByToiId(parent.getId());
			Map<Long,CloneQuestion> cloneQuestionMap = cloneQuestionList.stream().collect(
					Collectors.toMap(CloneQuestion::getQuestionId , (CloneQuestion q)->q));
			
			request.setAttribute("cloneToi", cloneToi);
			request.setAttribute("orgToi", orgToi);
			request.setAttribute("cloneQuestionMap", cloneQuestionMap);
			
			TreeMap<Long, Question> qMap2 = Toi.getQuestionMap(orgToi);
			request.setAttribute("questionMap2", qMap2);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionListAdmin3cloned.jsp");
			rd.forward(request, response);
		}else {
			//clone���ꂽ���̂ł͂Ȃ�
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/questionListAdmin3.jsp");
			rd.forward(request, response);
		}

	}

}