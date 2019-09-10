package jp.ac.jc21.t.yoshizawa.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;

@SuppressWarnings("serial")

@WebServlet(urlPatterns = { "/admin/answerSum/dumpAnswerSummary.csv" })

public class AnswerSumDumpAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Logger log = Logger.getLogger(AnswerSumDumpAdminServlet.class.getName());

		response.setContentType("text/csv; charset=Windows-31J");
		PrintWriter out = response.getWriter();

		List<AnswerSum> answerSumList = AnswerSum.loadAll();


		out.println( "ID,‰ğ“šÒ,‰ğ“š“ú,Œ±,–â,•ª–ì‡,•ª–ì,–âÚ×,³‰ğ”,–â‘è”,³‰ğ—¦" );
		
		Date dateStart = new Date();
		log.info(getServletName() + "[" + dateStart.toString() + "]START");
		
        Cache cache=null;
        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
        } catch (CacheException e) {
        	e.printStackTrace(System.err);
        }

		for (AnswerSum as : answerSumList) {
			if(as.getRefMember() != null) {

				String ansSumDump = as.makeAnswerDumpCSV(cache);
				float point=(100.0f * as.getNoOfSeikai() / as.getNoOfAnswer());

				out.print( ansSumDump );
				out.print( as.getNoOfSeikai()  );
				out.print( "," );
				out.print( as.getNoOfAnswer()  );
				out.print( "," );
				out.print( String.format("%1$.1f", point)  );

				out.println(  );
				out.flush();
			}
		}
		Date dateEnd = new Date();
		log.info(getServletName() + "[" + dateEnd.toString() + "]END");
		log.info(getServletName() + "[" +(dateEnd.getTime() - dateStart.getTime())+ "ms]END");
	}
}