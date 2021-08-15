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
import com.google.cloud.datastore.*;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

import jp.ac.jc21.t.yoshizawa.objectify.Toi;

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
		return createExam(null, YYYYMM, name);
	}

	public static final Exam createExam(Long id, Long YYYYMM, String name) {
		Exam exam = new Exam();
		exam.setCreated(new Date());
		exam.setName(name);
		exam.setYYYYMM(YYYYMM);
		exam.setId(id);
		return exam;
	}

	public static final Exam createExam(Entity result) {
		Exam exam = new Exam();
		exam.setCreated(TimestampToDate(result.getTimestamp("created")));
		exam.setName(result.getString("name"));
		exam.setYYYYMM(result.getLong("YYYYMM"));
		exam.setId(result.getKey().getId());
		return exam;
	}

	static List<Exam> chachedData = null;
	
	/**
	 * @return
	 */
	public static final List<Exam> loadAllList() {
		
		List<Exam> exams = new ArrayList<>();

		if(chachedData == null) {
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

			// The name/ID for the new entity
			Query<Entity> query = Query.newEntityQueryBuilder().setKind(kind).setOrderBy(OrderBy.desc("YYYYMM")).build();
			QueryResults<Entity> results = datastore.run(query);


			while (results.hasNext()) {
				Entity result = results.next();
				exams.add(Exam.createExam(result));
			}
			chachedData = exams;
			
		} else {
			exams = chachedData;
		}
		
		return exams;
	}

	public static final Map<Long, Exam> loadAllMap() {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

		// The name/ID for the new entity
		Query<Entity> query = Query.newEntityQueryBuilder().setKind(kind).setOrderBy(OrderBy.desc("YYYYMM")).build();
		QueryResults<Entity> results = datastore.run(query);

		Map<Long, Exam> maps = new TreeMap<Long, Exam>();

		while (results.hasNext()) {
			Entity result = results.next();
			Exam exam = Exam.createExam(result);
			maps.put(exam.getYYYYMM(), exam);
		}
		return maps;
	}

	/**
	 * @param k
	 * @return
	 */
	public static final Optional<Exam> getOptById(Long id) {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

		// The name/ID for the new entity
		Query<Entity> query = Query.newEntityQueryBuilder().setKind(kind).setFilter(PropertyFilter.eq("id", id))
				.build();
		QueryResults<Entity> results = datastore.run(query);
		Optional<Exam> optExam = null;
		if (results.hasNext() == true) {
			Entity result = results.next();
			optExam = Optional.ofNullable(Exam.createExam(result));
		}
		return optExam;
	}

	/**
	 * @param toi
	 * @return
	 */
	public static final Optional<Exam> getByToi(Toi toi) {
		Long examId = toi.getExamId();
		return getOptById(examId);
	}
}
