package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.Date;
import java.util.List;

public class AccessLogFactory extends CommonEntity {
	
	public static final AccessLog getById(long id) {
		return (AccessLog) getById(AccessLog.class, id);
//		return ofy().load().type(AccessLog.class).id(id).now();
	}
	
	public static final AccessLog create(String name , String operation){
		AccessLog aLog = new AccessLog();
		aLog.setName(name);
		aLog.setOperation(operation);
		aLog.setAccessed(new Date());
		return aLog;
	}
	
	public static List<AccessLog> loadAll() {
		return (List<AccessLog>) loadAll(AccessLog.class);
	}

}
