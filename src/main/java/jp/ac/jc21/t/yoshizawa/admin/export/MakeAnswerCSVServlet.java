package jp.ac.jc21.t.yoshizawa.admin.export;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
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

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;;

/**
 * Servlet implementation class ExportTestServlet
 */
@WebServlet("/admin/makeAnswerCSV")
public class MakeAnswerCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeAnswerCSVServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageString = request.getParameter("page");
		int page=0;
		try {
		page= Integer.parseInt(pageString);
		} catch (NumberFormatException e) {}
		
		String string = getAnswerSumDump(page);
		Storage storage = StorageOptions.getDefaultInstance().getService();
		BlobId blobId = BlobId.of("fegogo.appspot.com", "dumpAnswer" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(page) + ".csv");
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/csv").build();
		Blob blob = storage.create(blobInfo, string.getBytes(UTF_8));
/*		page+=6;
		if(page<30) {
			Queue queue = QueueFactory.getDefaultQueue();
			TaskOptions 		task = TaskOptions.Builder.withUrl("/admin/makeAnswerCSV").param("page", page+"");
			queue.add(task);
		}
*/		response.getWriter().println("finished");
	}

	public String getAnswerSumDump(int page) {
		final Logger log = Logger.getLogger(MakeAnswerCSVServlet.class.getName());

		String result = "";
//		result = "–âid,‰ð“šŽÒ,‰ð“š“ú,ŽŽŒ±,–â,•ª–ì‡,•ª–ì,–âÚ×," + "Ý–âid,o‘è‡,Ý–â,³‰ð,‰ð“š,³Œë";
//		result += "\n";

		List<AnswerSum> answerSumList = AnswerSum.loadAll();

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		Date dateStart = new Date();
		log.info(getServletName() + "[" + dateStart.toString() + "]START");

		int count = 0;
		final boolean forceRewrite = false;
        Cache cache=null;
        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
        } catch (CacheException e) {
        	e.printStackTrace(System.err);
        }
		for(int pointer=page ; pointer < answerSumList.size() ; pointer+=30) {
			AnswerSum as = answerSumList.get(pointer);
			if ((as.getRefMember() != null) 
					) {
				String ansSumDump = as.makeAnswerDumpCSV(cache);

				
				Map<Integer, Answer> answerMap = as.getMapAnswer();
				for (Integer key : answerMap.keySet()) {
					Answer answer = answerMap.get(key);
					String ansDump = answer.makeAnswerDumpCSV(cache);


					result += ansSumDump;
					result += ansDump;
					result += "\n";

				}
			}
			count++;
			if (count % 10 == 0) {
				log.info("[page" +page+":"+ new Date().toString() + "]" + count);
			}
		}
		Date dateEnd = new Date();
		log.info(getServletName() + "[page" +page+":"+ dateEnd.toString() + "]END");
		log.info(getServletName() + "[page" +page+":"+(dateEnd.getTime() - dateStart.getTime()) + "ms]END");

		return result;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
