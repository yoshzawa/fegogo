package jp.ac.jc21.t.yoshizawa.admin.ver5;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.cloud.datastore.*;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

import jp.ac.jc21.t.yoshizawa.datastore.Exam;

/**
 * Servlet implementation class Exam2Servlet
 * https://googleapis.dev/java/google-cloud-datastore/latest/index.html
 */
@WebServlet("/exam2")
public class Exam2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");

		List<Exam> exams = Exam.loadAllList();
		
		for(Exam e : exams) {
			response.getWriter().println(e.getYYYYMM() + ":" + e.getName());
		}


	}

}
