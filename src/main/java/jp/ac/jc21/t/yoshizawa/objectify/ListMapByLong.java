package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import com.google.appengine.api.utils.SystemProperty;

public class ListMapByLong<Long, List> {
	static String className = null;
	Map<Long, List> m = null;

	public ListMapByLong(String cName) {
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			// Production
			className = cName;
			try {
				CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
				Cache cache = cacheFactory.createCache(Collections.emptyMap());
			} catch (CacheException e) {
				e.printStackTrace(System.err);
			}
		} else {
			// Local development server
			m = new TreeMap<Long, List>();
		}
	}

	public void put(Long key, List list) {
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			// Production
			try {
				CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
				Cache cache = cacheFactory.createCache(Collections.emptyMap());
				@SuppressWarnings("unchecked")
				Map<Long, List> map = (Map<Long, List>) cache.get(className);
				if (map == null) {
					map = new TreeMap<Long, List>();
				}
				map.put(key, list);
				cache.put(className, map);
			} catch (CacheException e) {
				e.printStackTrace(System.err);
			}
		} else {
			// Local development server
			m.put(key, list);
		}
	}

	public Optional<List> get(Long key) {
		List list = null;
		;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			// Production
			try {
				CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
				Cache cache = cacheFactory.createCache(Collections.emptyMap());
				@SuppressWarnings("unchecked")
				Map<Long, List> map = (Map<Long, List>) cache.get(className);
				if (map == null) {
					map = new TreeMap<Long, List>();
				}
				List l = map.get(key);
				list = l;
			} catch (CacheException e) {
				e.printStackTrace(System.err);
			}
		} else {
			// Local development server
			list = m.get(key);
		}
		return Optional.ofNullable(list);
	}

	public void flush() {
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			// Production
			try {
				CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
				Cache cache = cacheFactory.createCache(Collections.emptyMap());
				Map<Long, List> map = (Map<Long, List>) cache.get(className);
				map.remove(className);

			} catch (CacheException e) {
				e.printStackTrace(System.err);
			}
		} else {
			// Local development server
			m = new TreeMap<Long, List>();
		}
	}

}
