package jp.ac.jc21.t.yoshizawa.ver2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Genre;
import jp.ac.jc21.t.yoshizawa.objectify.Member;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/genre/list","/genre2/list" })
public class Genre2ListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Genre> genreList = Genre.loadAll();
//		request.setAttribute("genreList", genreList);

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

//		if (email == null) 
		if(true)
		{
			List<String[]> datas = new ArrayList<String[]>(genreList.size());


			RequestDispatcher rd = request.getRequestDispatcher("/genre2/Nolog/list");
			rd.forward(request, response);
		} else {


			RequestDispatcher rd = request.getRequestDispatcher("/genre2/login/list");
			rd.forward(request, response);
		}

	}

	private final String changePoint(int seikai, int answer) {
		float point = (100.0f * seikai / answer);
		return String.format("%1$.1f", point);
	}

	private final String dateFormat(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(d);
	}
}