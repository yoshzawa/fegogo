/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;
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
public class Exam extends ExamFactory {

	@Id
	Long id;
	@Index
	private Long YYYYMM;
	private String name;
	private Date created;
	private Date openDate;
	private Date closeDate;
	

	////////// id

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

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

		return getId() + "," + getYYYYMM() + "," + getName() + "," + CommonFunction.dateFormat(getCreated());
	}

	public boolean containAnswer(Long toiId) {
		return Toi.contain(toiId);
	}

	public boolean isOpened() {
		int openDiff = 1;
		try {
			openDiff = new Date().compareTo(getOpenDate());
		} catch (NullPointerException ex) {
		}
		int closeDiff = -1;
		try {
			closeDiff = new Date().compareTo(getCloseDate());
		} catch (NullPointerException ex) {
		}		
		
		return((openDiff==1)&&(closeDiff==-1));
	}

}
