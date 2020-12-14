package jp.ac.jc21.t.yoshizawa.admin.property;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.TargetDataLine;

import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;

/**
 * Servlet implementation class ReadAdminServlet
 */
@WebServlet("/admin/property/read")
public class ReadAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Instantiates a client
	    Datastore datastore = getDataStore();

	    // The kind for the new entity
	    String kind = "Property";
	    // The name/ID for the new entity
	    String name = "AzureAppId";

	    String id="Buy milk";
		putId( kind,  name,  id) ;

	    
	    //Retrieve entity
	    String id2 = getId(datastore, kind,name);

	    System.out.printf("Retrieved : %s%n",  id2);

	}
	
	static Datastore datastore;
	
	static {
		datastore = initDataStore();
	}

	private final static Datastore initDataStore() {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		return datastore;
	}
	
	Datastore getDataStore() {
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
	    Entity data = Entity.newBuilder(key)
	        .set("id", id)
	        .build();

	    // Saves the entity
	    datastore.put(data);
	}

	private final void putId(String kind, String name, String id) {
		Datastore dataStore = getDataStore();
		Key key = getKey(dataStore,kind,name);
		putId(dataStore, key, id);
	}

	
	private final String getId(Datastore datastore, String kind, String name) {
		Key key = getKey(kind,name);
		Entity retrieved = datastore.get(key);

	    String id = retrieved.getString("id");
		return id;
	}
	private final String getId(String kind, String name) {
		return getId(getDataStore(),kind,name);
	}

}
