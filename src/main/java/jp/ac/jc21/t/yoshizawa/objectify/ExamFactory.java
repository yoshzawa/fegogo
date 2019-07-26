/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

/**
 * @author t.yoshizawa
 *
 */
public abstract class ExamFactory extends CommonEntity {
	
	public static final Exam createExam(Long YYYYMM, String name) {
		Exam exam = new Exam();
		exam.setCreated(new Date());
		exam.setName(name);
//		exam.newToiList();
		exam.newToiRefList();
		exam.setYYYYMM(YYYYMM);
		return exam;
	}
	
	public static final List<Exam> loadAll() {
			return ofy().load().type(Exam.class).order("YYYYMM").list();
	}
	
	public static final Exam getById(long id) {
		return ofy().load().type(Exam.class).id(id).now();
	}
	
	

}


