package jp.ac.jc21.t.yoshizawa.admin.export;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Blob.BlobSourceOption;
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
@WebServlet("/admin/dumpAnswer2.csv")
public class MakeAnswerCSVServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeAnswerCSVServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/csv; charset=Windows-31J");
		PrintWriter out = response.getWriter();
		out.println("ID,‰ğ“šÒ,‰ğ“š“ú,Œ±,–â,•ª–ì‡,•ª–ì,–âÚ×,İ–âid,o‘è‡,³‰ğ”,–â‘è”,³‰ğ—¦") ;
		for(int i=0 ; i<30 ; i++) {
			String name = "dumpAnswer" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(i) + ".csv";
			outFromFile(out, name);
		}

	}

	public void outFromFile(PrintWriter out, String name) throws UnsupportedEncodingException {
		Storage storage = StorageOptions.getDefaultInstance().getService();
		BlobId blobId = BlobId.of("fegogo.appspot.com", name);
		Blob blob = storage.get(blobId);
		byte[] content = blob.getContent(BlobSourceOption.generationMatch());
		String credential[] = new String(content, "UTF-8").split("\n", 0);
		for(String s : credential) {
			out.println(s);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
