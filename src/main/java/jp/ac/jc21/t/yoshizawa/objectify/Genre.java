/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.Key;

/**
 * @author t.yoshizawa
 *
 */

@SuppressWarnings("serial")
@Entity
@Cache
public class Genre extends GenreFactory {

	@Id
	Long id;
	private String name;
	private Date created;
	private List<Ref<Toi>> toiRefList;



	void newToiRefList() {
		setToiRefList(new ArrayList<Ref<Toi>>());
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the toiRefList
	 */
	public List<Ref<Toi>> getToiRefList() {
		if(toiRefList == null) {
			newToiRefList();
		}
		return toiRefList;
	}

	/**
	 * @param toiRefList the toiRefList to set
	 */
	public void setToiRefList(List<Ref<Toi>> toiRefList) {
		this.toiRefList = toiRefList;
	}

	public Genre save() {
		Key<Genre> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

}
