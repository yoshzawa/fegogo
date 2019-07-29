/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class Exam extends ExamFactory implements Serializable{

	@Id
	Long id;
	@Index
	private Long YYYYMM;
	private String name;
	private Date created;
	private List<Ref<Toi>> toiRefList;
	private List<Long> toiKeyList;


	public Long getId() { 		return id;	}

	public void setId(Long id) {		this.id = id;	}

	public Long getYYYYMM() {		return YYYYMM;	}

	public void setYYYYMM(Long yYYYMM) {		YYYYMM = yYYYMM;	}

	public String getName() {		return name;	}

	public void setName(String name) {		this.name = name;	}

	public Date getCreated() {		return created;	}

	public void setCreated(Date created) {		this.created = created;	}

	public void setToiRefList(List<Ref<Toi>> tois) {		this.toiRefList = tois;	}

	public void newToiRefList() {		setToiRefList(new ArrayList<Ref<Toi>>());	}

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


	
	/**
	 * @param toiKeyList the toiKeyList to set
	 */
	public void setToiKeyList(List<Long> toiKeyList) {
		this.toiKeyList = toiKeyList;
	}
	
	public void newToiKeyList() {
		setToiKeyList(new ArrayList<Long>());
	}
	
	/**
	 * @return the toiKeyList
	 */
	public List<Long> getToiKeyList() {
		if(toiKeyList == null) {
			newToiKeyList();
		}
		return toiKeyList;
	}



	public void setToiKeyList(Long toiKey) {
		List<Long> list = getToiKeyList();
		list.add(toiKey);
		setToiKeyList(list);
	}
	
	public void convertForCache(){
		newToiKeyList();
		for(Ref<Toi> refToi:getToiRefList()) {
			Long toiKey = refToi.get().getId();
			setToiKeyList(toiKey);
		}
		newToiRefList();
	}
	
	public void convertFromCache(){
		newToiRefList();
		for(Long toiKey:getToiKeyList()) {
			Ref<Toi> toiRef = Ref.create(Toi.getById(toiKey));
			addToiRefList(toiRef);
		}
		newToiKeyList();
	}
	
	public Exam save() {
		Key<Exam> key = ofy().save().entity(this).now();
		Exam.clearCache();
		return getById(key.getId());
	}



}
