package jp.ac.jc21.t.yoshizawa.servlet.exam3;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/exam3/Nolog/list" })
public class Exam3NoLogListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String examListUrl = "http://fegogo.appspot.com/endpoint/v1/exam/list";
		List<Exam> examList = ExamListFromGson(examListUrl);
		
		Stream<Exam> stream1 = examList.stream();
		Stream<Exam> stream2 = stream1.sorted(Comparator.comparing(Exam::getYYYYMM));
		Stream<Exam> stream3 = stream2.filter((Exam e)-> e.getYYYYMM()<300000);
		Stream<String[]> d4 = stream3.map((Exam e)->makeDisplayData(e));
		List<String[]> datas = d4.collect(Collectors.toList());

		request.setAttribute("datas", datas);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp2/nolog/examList.jsp");
		rd.forward(request, response);

	}


	private final String[] makeDisplayData(Exam e) {
		String[] s = new String[2];

		int openDiff = 1;
		try {
			openDiff = new Date().compareTo(e.getOpenDate());
		} catch (NullPointerException ex) {
		}
		int closeDiff = -1;
		try {
			closeDiff = new Date().compareTo(e.getCloseDate());
		} catch (NullPointerException ex) {
		}

		if (openDiff == -1) {
			s[0] = e.getName() + "(" + dateFormat(e.getOpenDate()) + "より回答可能)";
		} else if (closeDiff == 1) {
			s[0] = e.getName() + "(" + dateFormat(e.getCloseDate()) + "で回答終了)";

		} else {
			s[0] = "<a href='/toi/list?parentId=" + e.getId() + "'>" + e.getName() + "</a>";
			if (e.getCloseDate() != null) {
				s[0] += "(" + dateFormat(e.getCloseDate()) + "まで回答可能)";
			} else {
				s[0] += "(期限なし)";
			}

		}

		s[1] = e.getToiListSize() + "";
		return s;
	}


	private final  List<Exam> ExamListFromGson(String examListUrl)
			throws MalformedURLException, IOException, ProtocolException, UnsupportedEncodingException {
		List<Exam> list;

		Gson gson = new Gson();

		// urlの文字列からURLインスタンスを作成
		URL url = new URL(examListUrl);

		// openConnectionで接続
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// GETによるリクエスト
		con.setRequestMethod("GET");

		// 通信開始
		con.connect();
		// レスポンスコードを戻る
		int responseCode = con.getResponseCode();
		// レスポンスコードを判断する、OKであれば、進める
		if (responseCode == HttpURLConnection.HTTP_OK) {
			// 通信に成功した
			// テキストを取得する
			// データ取得
			InputStream is = con.getInputStream();

			// スプーンからコップで効率化
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");

			// インプットストリームリーダーインスタンスからJsonReadrインスタンスを作成できる。
			JsonReader reader = new JsonReader(isr);

			Type collectionType = new TypeToken<Collection<Exam>>() {
			}.getType();
			list = gson.fromJson(reader, collectionType);

		} else {
			list = new ArrayList<>();
		}
		return list;
	}

	private final String dateFormat(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		TimeZone timeZoneJP = TimeZone.getTimeZone("Asia/Tokyo");
		sdf.setTimeZone(timeZoneJP);
		return sdf.format(d);
	}

}