/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.Key;

/**
 * @author t.yoshizawa
 *
 */

@Entity
@Cache
public class Genre extends GenreFactory {

	@Id
	Long id;
	private String name;
	private Date created;
	@Index
	private Integer no;
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
	public Map<Long, Toi> getToiMap() {
		toiRefList = getToiRefList();
		Map<Long,Toi> toiMap = new TreeMap<Long,Toi>(
				 new Comparator<Long>() {
				        public int compare(Long m, Long n){
				            return ((Long)m).compareTo(n) * -1;
				        }
				    }
				);
		for(Ref<Toi> rt : toiRefList) {
			Toi toi = rt.get();
			Long yyyymm=toi.getExam().getYYYYMM();
			toiMap.put(yyyymm, toi);
		}
		return toiMap;
	}
	
	

	/**
	 * @param toiRefList the toiRefList to set
	 */
	public void setToiRefList(List<Ref<Toi>> toiRefList) {
		this.toiRefList = toiRefList;
	}

	public void addToiRefList(Ref<Toi> toiRef) {
		List<Ref<Toi>> toiRefList = getToiRefList();
		toiRefList.add(toiRef);
		setToiRefList(toiRefList);
	}

	public void addToiRefList(Toi toi) {
		addToiRefList(Ref.create(toi));
	}

	public void removeToiRefList(Long id) {
		List<Ref<Toi>> toiRefList = getToiRefList();
		List<Ref<Toi>> newRefList = new ArrayList<Ref<Toi>>();
		for(Ref<Toi> rt : toiRefList) {
			Toi toi = rt.get();
			Long tId = toi.getId();
			if(!tId.equals(id)) {
				newRefList.add(rt);
			}
		}
		setToiRefList(newRefList);
	}

	
	public Genre save() {
		Key<Genre> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
