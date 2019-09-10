package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jp.ac.jc21.t.yoshizawa.AnswerServlet;
import jp.ac.jc21.t.yoshizawa.objectify.*;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answer/dumpAnswer.csv" })
public class AnswerDumpAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(AnswerDumpAdminServlet.class.getName());
		int count = 0;

		response.setContentType("text/csv; charset=Windows-31J");
		PrintWriter out = response.getWriter();

		List<AnswerSum> answerSumList = AnswerSum.loadAll();

		out.println("問id,解答者,解答日,試験,問,分野順,分野,問詳細," + "設問id,出題順,設問,正解,解答,正誤");

		Date dateStart = new Date();
        Cache cache=null;
        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
        } catch (CacheException e) {
        	e.printStackTrace(System.err);
        }
		log.info(getServletName() + "[" + dateStart.toString() + "]START");
		for (AnswerSum as : answerSumList) {

			String ansSumDump = as.makeAnswerDumpCSV(cache);


			Map<Integer, Answer> answerMap = as.getMapAnswer();
			for (Integer key : answerMap.keySet()) {
				Answer answer = answerMap.get(key);

				out.print(ansSumDump);

				String ansDump = answer.makeAnswerDumpCSV(cache);

				out.print(ansDump);

				out.println();
			}
			out.flush();
			if(count++ % 5 == 0) {
				log.info(getServletName() + "[" + new Date().toString() + "]" + count);
			}
		}
		Date dateEnd = new Date();
		log.info(getServletName() + "[" + dateEnd.toString() + "]END");
		log.info(getServletName() + "[" + ((dateEnd.getTime() - dateStart.getTime())/1000) + "s]END");
		log.info(getServletName() + "[" + ((dateEnd.getTime() - dateStart.getTime())/count) + "ms/AnswerSum]END");

	}


}