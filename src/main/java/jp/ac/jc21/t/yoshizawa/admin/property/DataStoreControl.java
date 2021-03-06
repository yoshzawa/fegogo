/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.admin.property;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

/**
 * @author t.yoshizawa
 *
 */
public class DataStoreControl {

	// Instantiates a client
	static Datastore datastore;
	
	static {
		datastore = initDataStore();
	}

	private final static Datastore initDataStore() {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		return datastore;
	}
	
	private final Datastore getDataStore() {
		if(datastore == null) {
			datastore = initDataStore();
		}
		return datastore;
	}

	// The Cloud Datastore key for the new entity
	private final Key getKey(Datastore datastore, String kind, String name) {
		Key key = datastore.newKeyFactory().setKind(kind).newKey(name);
		return key;
	}
	
	private final Key getKey(String kind, String name) {
		Datastore datastore=getDataStore();
		return getKey( datastore,  kind,  name);
	}

	

	private final void putId(Datastore datastore, Key key, String id) {
		// Prepares the new entity
		
		LocalDateTime d = LocalDateTime.now();
		d.plusMinutes(2);
		Date date = toDate(d);
	    Entity data = Entity.newBuilder(key)
		    .set("id", id)
	        .set("limitDate", Timestamp.of(date))
	        .set("created", Timestamp.now())
	        .build();

	    // Saves the entity
	    datastore.put(data);
	}

	/**
	 * idを保存する。期限を2ヶ月後に設定する（つもりで2分後に設定している）
	 * @param kind データの種類
	 * @param name データの名前
	 * @param id 保存するid
	 */
	final void putId(String kind, String name, String id) {
		Datastore dataStore = getDataStore();
		Key key = getKey(dataStore,kind,name);
		putId(dataStore, key, id);
	}


	private final String getLimitDate(Datastore datastore, String kind, String name) {
		Key key = getKey(kind,name);
		Entity retrieved = datastore.get(key);

	    String limitDate = retrieved.getString("limitDate");
		return limitDate;
	}

	private final String getId(Datastore datastore, String kind, String name) {
		Key key = getKey(kind,name);
		Entity retrieved = datastore.get(key);

	    String id = retrieved.getString("id");
		return id;
	}
	
	/**
	 * idを取得する。
	 * limitDateを超えたら、idを初期化したい。
	 * @param kind データの種類
	 * @param name データの名前
	 * @return 取得したid
	 */
	public final String getId(String kind, String name) {
//		getLimitDate(getDataStore(),kind,name);
		return getId(getDataStore(),kind,name);
	}

	// https://qiita.com/riekure/items/d83d4ea5d8a19a267453
    public final static Date toDate(LocalDateTime localDateTime) {
    	return Date.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant());
    }
}
