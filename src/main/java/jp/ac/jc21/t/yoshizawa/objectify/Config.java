/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import java.util.Date;
import java.util.logging.Logger;

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
public class Config extends CommonEntity {

	@Id
	Long id;
	@Index
	private String key;
	private String value;
	private Date saved;

	public Config() {
	}

	public Config save() {
		Key<Config> key = ofy().save().entity(this).now();
		return getById(key.getId());
	}

	public static Config getById(long id) {
		return ofy().load().type(Config.class).id(id).now();
	}

	public static Config createAnswer(String key,String value) {
		Config c = new Config();
		c.setKey(key);
		c.setValue(value);
		c.setSaved(new Date());
		return c;
	}

	public static Config getByKey(long id) {
		return ofy().load().type(Config.class).id(id).now();
	}

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
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the saved
	 */
	public Date getSaved() {
		return saved;
	}

	/**
	 * @param saved the saved to set
	 */
	public void setSaved(Date saved) {
		this.saved = saved;
	}

}
