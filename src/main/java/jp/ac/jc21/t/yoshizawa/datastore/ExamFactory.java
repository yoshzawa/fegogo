/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.datastore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

/**
 * @author t.yoshizawa
 *
 */
public abstract class ExamFactory extends CommonEntity {

	// The kind for the entity
	static final String kind = "Exam";

	/**
	 * @param YYYYMM
	 * @param name
	 * @return
	 */
	public static final Exam createExam(Long YYYYMM, String name) {
		Exam exam = new Exam();
		exam.setCreated(new Date());
		exam.setName(name);
		exam.setYYYYMM(YYYYMM);
		return exam;
	}

	public static final Exam createExam(Entity result) {
		Exam exam = new Exam();
		exam.setCreated(new Date());
		exam.setName(result.getString("name"));
		exam.setYYYYMM(result.getLong("YYYYMM"));
		return exam;
	}

	/**
	 * @return
	 */
	public static final List<Exam> loadAllList() {
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		
		// The name/ID for the new entity
		Query<Entity> query = Query.newEntityQueryBuilder().setKind(kind).setOrderBy(OrderBy.desc("YYYYMM")).build();
		QueryResults<Entity> results = datastore.run(query);

		List<Exam> exams = new ArrayList<>();
		
		while (results.hasNext()) {
			Entity result = results.next();
			exams.add(Exam.createExam(result));
		}
		return exams;
	}

	/**
	 * @param id
	 * @return
	 */
	public static final Exam getById(long id) {
		return null;
	}

	/**
	 * @param k
	 * @return
	 */
	public static final Exam getById() {
		return null;
	}
	/**
	 * @param toi
	 * @return
	 */
	
//	public static Optional<Exam> getByToi(Toi toi) {
//	}
}
