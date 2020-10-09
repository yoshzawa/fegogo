package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.Date;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class AccessLog {
	@Id
	Long id;
	@Index
	private String name;
	private Date accessed;
	private String operation;
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
	 * @return the accessed
	 */
	public Date getAccessed() {
		return accessed;
	}
	/**
	 * @param accessed the accessed to set
	 */
	public void setAccessed(Date accessed) {
		this.accessed = accessed;
	}
	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	

}
