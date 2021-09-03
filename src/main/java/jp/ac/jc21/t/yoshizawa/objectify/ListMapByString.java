package jp.ac.jc21.t.yoshizawa.objectify;

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


public class ListMapByString<E> {
	
	private String className = null;
	Map<String,List<E>> m = null;

	public ListMapByString(String cName) {
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			// Production
			setClassName(cName);
	        try {
	            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
	             Cache cache = cacheFactory.createCache(Collections.emptyMap());
	        } catch (CacheException e) {
	        	e.printStackTrace(System.err);
	        }
		}
		else
		{
			// Local development server
			 m = new TreeMap<String, List<E>>();
		}
	}
		
		public final void put(String key , List<E> list) {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				// Production
		        try {
		            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
		             Cache cache = cacheFactory.createCache(Collections.emptyMap());
		             @SuppressWarnings("unchecked")
					Map<String, List<E>> map =  (Map<String, List<E>>) cache.get(getClassName());
		             if(map==null) {
		            	 map = new TreeMap<String, List<E>>();
		             }
		             map.put(key, list);
		             cache.put(getClassName(), map);
		        } catch (CacheException e) {
		        	e.printStackTrace(System.err);
		        }
			}
			else
			{
				// Local development server
				m.put(key,list);
			}
	}
		public final Optional<List<E>> get(String key ) {
			List<E> list=null;;
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				// Production
		        try {
		            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
		             Cache cache = cacheFactory.createCache(Collections.emptyMap());
		             @SuppressWarnings("unchecked")
					Map<Long, List<E>> map =   (Map<Long, List<E>>) cache.get(getClassName());
		             if(map==null) {
		            	 map = new TreeMap<Long, List<E>>();
		             }
		             List<E> l = map.get(key);
		             list= l;
		        } catch (CacheException e) {
		        	e.printStackTrace(System.err);
		        }
			}
			else
			{
				// Local development server
				list= m.get(key);
			}
			return Optional.ofNullable(list);
	}
		public void flush() {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				// Production
				try {
					CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
					Cache cache = cacheFactory.createCache(Collections.emptyMap());
					Map<Long, List<E>> map = (Map<Long, List<E>>) cache.get(getClassName());
					map.remove(getClassName());

				} catch (CacheException e) {
					e.printStackTrace(System.err);
				}
			} else {
				// Local development server
				m = new TreeMap<String, List<E>>();
			}
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}
}
