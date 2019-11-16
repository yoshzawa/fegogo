package jp.ac.jc21.t.yoshizawa.admin.export;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

import static java.nio.charset.StandardCharsets.UTF_8;;

/**
 * Servlet implementation class ExportTestServlet
 */
@WebServlet("/admin/makeAnswerSumCSV")
public class MakeAnswerSumCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeAnswerSumCSVServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String string = getAnswerSumDump();
		Storage storage = StorageOptions.getDefaultInstance().getService();
		BlobId blobId = BlobId.of("fegogo.appspot.com", "dumpAnswerSummary.csv");
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/csv").build();
		Blob blob = storage.create(blobInfo, string.getBytes(UTF_8));
		response.getWriter().println("finished");
	}

	public String getAnswerSumDump() {
		final Logger log = Logger.getLogger(MakeAnswerSumCSVServlet.class.getName());

		String s = "";
		s= "ID,âìöé“,âìöì˙,ééå±,ñ‚,ï™ñÏèá,ï™ñÏ,ñ‚è⁄ç◊,ê≥âêî,ñ‚ëËêî,ê≥âó¶" ;
		s+="\n";
		
		List<AnswerSum> answerSumList = AnswerSum.loadAll();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		Date dateStart = new Date();
		log.info(getServletName() + "[" + dateStart.toString() + "]START");
		
		int count=0;
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
				
				s+= ansSumDump ;
				s+= as.getNoOfSeikai() + ","  ;
				s+=  as.getNoOfAnswer()   +",";
				s+=  String.format("%1$.1f", point)  ;
				s+="\n";
				
				}
			count++;
			if(count%10 == 0) {
			log.info( "[" + new Date().toString() + "]" + count);

			}
		}
		Date dateEnd = new Date();
		log.info(getServletName() + "[" + dateEnd.toString() + "]END");
		log.info(getServletName() + "[" +(dateEnd.getTime() - dateStart.getTime())+ "ms]END");
		
		return s;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
