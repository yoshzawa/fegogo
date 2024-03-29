/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import jp.ac.jc21.t.yoshizawa.CommonFunction;

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
//	private List<Ref<Toi>> toiRefList;

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

	public String getExportData() {

		return getId() + "," + getName() + "," + CommonFunction.dateFormat(getCreated()) + "," + getNo();

	}

	public final Map<Long, Toi> getToiMap() {
		return getToiMap(-1);
	}

	public final Map<Long, Toi> getToiMap(int duration) {
		List<Toi> toiList = getToiList();
		Map<Long, Toi> toiMap = new TreeMap<Long, Toi>(new Comparator<Long>() {
			public int compare(Long m, Long n) {
				return ((Long) m).compareTo(n) * duration;
			}
		});
		for (Toi t : toiList) {
			Long yyyymm = t.getExam().getYYYYMM();
			toiMap.put(yyyymm, t);
		}
		return toiMap;
	}

	public final List<Toi> getToiList() {
		Long genreId = getId();
		List<Toi> toiList = Toi.getToiListByGenreId(genreId);
		return toiList;
	}
}
