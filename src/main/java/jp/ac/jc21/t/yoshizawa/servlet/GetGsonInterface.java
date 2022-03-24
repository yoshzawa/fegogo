package jp.ac.jc21.t.yoshizawa.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

public interface GetGsonInterface {

	static JsonReader getGsonReader(String examListUrl) throws IOException {
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

	static List<String> getStringList(String examListUrl) throws IOException {
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

		Optional<List<String>> optStringList = Optional.ofNullable((List<String>) syncCache.get(examListUrl));
		List<String> stringList = null;
		if ((optStringList.isPresent()) && (optStringList.get().size() > 0)) {
			stringList = optStringList.get();
		} else {
			stringList = StringListFromGson(examListUrl);
			syncCache.put(examListUrl, stringList);
		}
		return stringList;

	}

	static List<String> StringListFromGson(String examListUrl) throws IOException {
		Gson gson = new Gson();
		List<String> list = new ArrayList<>();

		JsonReader reader = getGsonReader(examListUrl);
		if (reader != null) {
			Type collectionType = new TypeToken<Collection<String>>() {
			}.getType();
			list = gson.fromJson(reader, collectionType);
		}
		return list;
	}

	static boolean isCached(String examListUrl) throws IOException {
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		return syncCache.contains(examListUrl);
	}

	static List<Long> getLongList(String examListUr) throws IOException {
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

		Optional<List<Long>> optLongList = Optional.ofNullable((List<Long>) syncCache.get(examListUr));
		List<Long> longList = null;
		if ((optLongList.isPresent()) && (optLongList.get().size() > 0)) {
			longList = optLongList.get();
		} else {
			longList = LongListFromGson(examListUr);
			syncCache.put(examListUr, longList);
		}
		return longList;

	}

	static List<Long> LongListFromGson(String examListUrl) throws IOException {
		Gson gson = new Gson();
		List<Long> list = new ArrayList<>();

		JsonReader reader = getGsonReader(examListUrl);
		if (reader != null) {
			Type collectionType = new TypeToken<Collection<Long>>() {
			}.getType();
			list = gson.fromJson(reader, collectionType);
		}
		return list;
	}

	static List<Exam> getExamList(String examListUrl) {
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

		Optional<List<Exam>> optExamList = Optional.ofNullable((List<Exam>) syncCache.get(examListUrl));
		List<Exam> examList = null;
		if ((optExamList.isPresent()) && (optExamList.get().size() > 0)) {
			examList = optExamList.get();
		} else {
			examList = ExamListFromGson(examListUrl);
			syncCache.put(examListUrl, examList);
		}
		return examList;
	}

	static List<Exam> ExamListFromGson(String examListUrl) {
		Gson gson = new Gson();
		List<Exam> list = new ArrayList<>();
		try {
			JsonReader reader = getGsonReader(examListUrl);
			if (reader != null) {
				Type collectionType = new TypeToken<Collection<Exam>>() {
				}.getType();
				list = gson.fromJson(reader, collectionType);
			}
		} catch (IOException e) {
		}
		return list;
	}
	static List<Toi> getToiList(String toiListUrl) {
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

		Optional<List<Toi>> optToiList = Optional.ofNullable((List<Toi>) syncCache.get(toiListUrl));
		List<Toi> examList = null;
		if ((optToiList.isPresent()) && (optToiList.get().size() > 0)) {
			examList = optToiList.get();
		} else {
			examList = ToiListFromGson(toiListUrl);
			syncCache.put(toiListUrl, examList);
		}
		return examList;
	}
	static List<Toi> ToiListFromGson(String examListUrl) {
		Gson gson = new Gson();
		List<Toi> list = new ArrayList<>();

		JsonReader reader;
		try {
			reader = getGsonReader(examListUrl);
			if (reader != null) {
				Type collectionType = new TypeToken<Collection<Toi>>() {
				}.getType();
				list = gson.fromJson(reader, collectionType);
			}
		} catch (IOException e) {
		}
		return list;
	}
}
