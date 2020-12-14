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
@WebServlet("/admin/property/set")
public class PropertySetAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp5/property/set.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataStoreControl dsc = new DataStoreControl();
		
		String AzureAppId=request.getParameter("AzureAppId");
		String AzureAppIdLocal=request.getParameter("AzureAppIdLocal");
		

	    dsc.putId( "Property",  "AzureAppId",  AzureAppId) ;

	    dsc.putId( "Property",  "AzureAppIdLocal",  AzureAppIdLocal) ;
	    
	    //Retrieve entity
	    String id2 = dsc.getId("Property","AzureAppId");
	    String id3 = dsc.getId("Property","AzureAppIdLocal");

	    // System.out.printf("Retrieved : %s : %s%n",  id2,id3);

	}
	


}
