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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

public interface GetGsonInterface {
	 static  List<Exam> ExamListFromGson(String examListUrl)
			throws  IOException {
		List<Exam> list;

		Gson gson = new Gson();

		URL url = new URL(examListUrl);

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");

		con.connect();
		
		int responseCode = con.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) {

			InputStream is = con.getInputStream();

			InputStreamReader isr = new InputStreamReader(is, "UTF-8");

			JsonReader reader = new JsonReader(isr);

			Type collectionType = new TypeToken<Collection<Exam>>() {
			}.getType();
			list = gson.fromJson(reader, collectionType);

		} else {
			list = new ArrayList<>();
		}
		return list;
	}
	 
		 static  List<Long> LongListFromGson(String examListUrl)
				throws  IOException {
			List<Long> list;

			Gson gson = new Gson();

			URL url = new URL(examListUrl);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			con.connect();
			
			int responseCode = con.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK) {

				InputStream is = con.getInputStream();

				InputStreamReader isr = new InputStreamReader(is, "UTF-8");

				JsonReader reader = new JsonReader(isr);

				Type collectionType = new TypeToken<Collection<Long>>() {
				}.getType();
				list = gson.fromJson(reader, collectionType);

			} else {
				list = new ArrayList<>();
			}
			return list;
		}
}
