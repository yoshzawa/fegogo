package jp.ac.jc21.t.yoshizawa.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
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

import jp.ac.jc21.t.yoshizawa.objectify.Member;

public class EndPointMember extends GetGson {
	
	private static final String memberGetUrl = "https://fegogo.appspot.com/endpoint/v0/member/get?email=";

	public static final List<Member> getMemberList(String email) {
		
		String toiListUrl = memberGetUrl + email;
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService("jp.ac.jc21.t.yoshizawa-Member");
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

		Optional<List<Member>> optMemberList = Optional.ofNullable((List<Member>) syncCache.get(toiListUrl));
		List<Member> examList = null;
		if ((optMemberList.isPresent()) && (optMemberList.get().size() > 0)) {
			examList = optMemberList.get();
		} else {
			examList = GetGson.MemberListFromGson(toiListUrl);
			syncCache.put(toiListUrl, examList);
		}
		return examList;
	}
	
	public static final List<Member> addMember(String addMemberUrl, Member m) {
		Gson gson = new Gson();
		List<Member> list = new ArrayList<>();

		JsonReader reader;
		String jsonString = gson.toJson(m, Member.class);
		try {
			reader = GetGson.getGsonPostReader(addMemberUrl, jsonString);
			if (reader != null) {
				Type collectionType = new TypeToken<Collection<Member>>() {
				}.getType();
				
				MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService("jp.ac.jc21.t.yoshizawa-Member");
				syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

				list = gson.fromJson(reader, collectionType);
				list.stream().forEach((Member mem)-> syncCache.delete(memberGetUrl+mem.geteMail()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
