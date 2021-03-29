/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.datastore;

import java.util.Date;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

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

}
