/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * @author t.yoshizawa
 *
 */

@Entity
@Cache
public class Exam extends ExamFactory {

	@Id
	Long id;
	@Index
	private Long YYYYMM;
	private String name;
	private Date created;

	////////// id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	////////// YYYYMM

	public Long getYYYYMM() {
		return YYYYMM;
	}

	public void setYYYYMM(Long yYYYMM) {
		YYYYMM = yYYYMM;
	}

	////////// name

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	////////// created

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Exam save() {
		Key<Exam> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public final TreeMap<Long, Toi> getToiMap() {

		TreeMap<Long, Toi> toiMap = new TreeMap<>();

		List<Toi> toiList = Toi.getToiListByExamId(getId());

		if (toiList != null) {
			for (Toi t : toiList) {
				toiMap.put(t.getNo(), t);
			}
		}
		return toiMap;
	}

	public int getToiListSize() {
		List<Toi> toiList = Toi.getToiListByExamId(getId());
		return toiList.size();
	}

	public String getExportData() {

		return getId() + "," + getYYYYMM() + "," + getName() + "," + getDateString(getCreated());
	}

	public boolean containAnswer(Long toiId) {
		return Toi.contain(toiId);
	}

}
