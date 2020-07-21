/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
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
	private List<Ref<Toi>> toiRefList;

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


	////////// toiRefList

	public void setToiRefList(List<Ref<Toi>> tois) {
		this.toiRefList = tois;
	}

	public void newToiRefList() {
		setToiRefList(new ArrayList<Ref<Toi>>());
	}

	public List<Ref<Toi>> getToiRefList() {
		if (toiRefList == null) {

			newToiRefList();

		}

		return toiRefList;
	}

	public int getToiRefListSize() {
		List<Ref<Toi>> ts = getToiRefList();
		return ts.size();
	}

	public void addToiRefList(Ref<Toi> t) {
		List<Ref<Toi>> ts = getToiRefList();
		ts.add(t);
		setToiRefList(ts);
	}

	public void addToiRefList(Toi t) {
		addToiRefList(Ref.create(t));
	}

	////////// 
	public Exam save() {
		Key<Exam> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}
	
	public final TreeMap<Long, Toi> getToiMap() {

		TreeMap<Long, Toi> toiMap = new TreeMap<>();

		List<Ref<Toi>> toiRefList = getToiRefList();

		if (toiRefList != null) {
			for (Ref<Toi> t : toiRefList) {
				Toi tt = t.get();
				toiMap.put(tt.getNo(), tt);
			}
		}
		return toiMap;
	}

	public String getExportData() {

		return getId()+","+
				getYYYYMM()+","+
				getName()+","+
				getDateString(getCreated());
	}
	public boolean containAnswer(Long toiId ) {
		Ref<Toi> rToi = Ref.create(Key.create(Toi.class,toiId));
		return getToiRefList().contains(rToi);
		
	}
}
