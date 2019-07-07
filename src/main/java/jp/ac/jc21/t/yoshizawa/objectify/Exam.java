/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.*;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.Key;

/**
 * @author t.yoshizawa
 *
 */

@Entity
@Cache
public class Exam {
	
	@Id
	Long id;
	@Index
	private Long YYYYMM;
	private String name;
	private Date created;
	private List<Toi> toiList;

	static {
//		ObjectifyService.register(Exam.class);
	}

	public static Exam createExam(Long YYYYMM, String name) {
		Exam exam = new Exam();
		exam.setCreated(new Date());
		exam.setName(name);
		exam.newToiList();
		exam.setYYYYMM(YYYYMM);
		return exam;
	}

	public static List<Exam> loadAll() {
		return ofy().load().type(Exam.class).order("YYYYMM").list();
	}

	public Exam save() {
		Key<Exam> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public static Exam getById(long id) {
		return ofy().load().type(Exam.class).id(id).now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Toi> getToiList() {
		if(toiList == null) {
			newToiList();
		}
		return toiList;
	}
	
	public int getToiListSize() {
		List<Toi> ts = getToiList();
		if (ts == null) {
			return 0;
		}else {
			return ts.size();
		}
	}

	public void setToiList(List<Toi> tois) {
		this.toiList = tois;
	}

	public void newToiList() {
		setToiList(new ArrayList<Toi>());
	}

	public void addToi(Toi t) {
		List<Toi> ts = getToiList();
		ts.add(t);
		setToiList(ts);
	}

	public Long getYYYYMM() {
		return YYYYMM;
	}

	public void setYYYYMM(Long yYYYMM) {
		YYYYMM = yYYYMM;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
