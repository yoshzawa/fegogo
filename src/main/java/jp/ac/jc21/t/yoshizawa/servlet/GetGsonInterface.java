package jp.ac.jc21.t.yoshizawa.servlet;

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
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

public interface GetGsonInterface {

	static JsonReader getGsonReader(String examListUrl)
			throws  IOException {
		JsonReader reader = null;

		URL url = new URL(examListUrl);

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");

		con.connect();

		int responseCode = con.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {

			InputStream is = con.getInputStream();

			InputStreamReader isr = new InputStreamReader(is, "UTF-8");

			reader = new JsonReader(isr);
		}
		return reader;
	}

	static List<Long> LongListFromGson(String examListUrl) throws IOException {
		Gson gson = new Gson();
		List<Long> list=new ArrayList<>();

		JsonReader reader = getGsonReader(examListUrl);
		if (reader != null) {
			Type collectionType = new TypeToken<Collection<Long>>() {
			}.getType();
			list = gson.fromJson(reader, collectionType);
		}
		return list;
	}

	static List<Exam> ExamListFromGson(String examListUrl) throws IOException {
		Gson gson = new Gson();
		List<Exam> list=new ArrayList<>();

		JsonReader reader = getGsonReader(examListUrl);
		if (reader != null) {
			Type collectionType = new TypeToken<Collection<Exam>>() {
			}.getType();
			list = gson.fromJson(reader, collectionType);
		}
		return list;
	}
	static List<Toi> ToiListFromGson(String examListUrl)  {
		Gson gson = new Gson();
		List<Toi> list=new ArrayList<>();

		JsonReader reader;
		try {
			reader = getGsonReader(examListUrl);
			if (reader != null) {
				Type collectionType = new TypeToken<Collection<Toi>>() {
				}.getType();
				list = gson.fromJson(reader, collectionType);
			}
		} catch (IOException e) {}
		return list;
	}
}
