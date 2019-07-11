/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

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

@Entity
@Cache
public class Exam extends CommonEntity {

	@Id
	Long id;
	@Index
	private Long YYYYMM;
	private String name;
	private Date created;
	private List<Ref<Toi>> toiRefList;



	public static Exam createExam(Long YYYYMM, String name) {
		Exam exam = new Exam();
		exam.setCreated(new Date());
		exam.setName(name);
//		exam.newToiList();
		exam.newToiRefList();
		exam.setYYYYMM(YYYYMM);
		return exam;
	}

	public static List<Exam> loadAll() {
		return loadAll(Exam.class, "YYYYMM");
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

	public void setToiRefList(List<Ref<Toi>> tois) {
		this.toiRefList = tois;
	}

	public void newToiRefList() {
		setToiRefList(new ArrayList<Ref<Toi>>());
	}

	public void addToiRefList(Ref<Toi> t) {
		List<Ref<Toi>> ts = getToiRefList();
		ts.add(t);
		setToiRefList(ts);
	}

	public void addToiRefList(Toi t) {
		addToiRefList(Ref.create(t));
	}

}
