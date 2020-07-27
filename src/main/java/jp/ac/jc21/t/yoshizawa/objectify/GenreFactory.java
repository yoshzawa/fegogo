package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

public class GenreFactory extends CommonEntity {

	public static final Genre createGenre(String name) {
		Genre g = new Genre();
		g.setCreated(new Date());
		g.setName(name);
		g.newToiRefList(); 
		g.setNo(0);
		return g;
	}
	
	public static final List<Genre> loadAll() {
		return ofy().load().type(Genre.class).order("no").list();
	}
	
	public static final Genre getById(long id) {
		return ofy().load().type(Genre.class).id(id).now();
	}
}
