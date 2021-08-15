/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.datastore;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.google.cloud.datastore.*;

import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * @author t.yoshizawa
 *         https://googleapis.dev/java/google-cloud-datastore/latest/index.html
 */
public class Exam extends ExamFactory {

	private Long id;
	private Long YYYYMM;
	private String name;
	private Date created;

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

	public Exam() {
	}

	public Optional<Exam> save() {
//		Key<Exam> key = ofy().save().entity(this).now();

		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		Key examKey;
		  KeyFactory keyFactory;
	    keyFactory = datastore.newKeyFactory().setKind("Exam");

		if(getId() == null) {
			examKey = datastore.allocateId(keyFactory.newKey());
			setId(examKey.getId());
		}else {
			examKey = keyFactory.newKey(getId());
			}

		Entity exam = Entity.newBuilder(examKey).set("YYYYMM", getYYYYMM()).set("name", getName())
				.set("created", DateToTimestamp(created)).build();
		datastore.put(exam);
		Key key = exam.getKey();
		return getOptById(key.getId());
	}
	public int getToiListSize() {
		List<Toi> toiList = Toi.getToiListByExamId(getId());
		return toiList.size();
	}
}
