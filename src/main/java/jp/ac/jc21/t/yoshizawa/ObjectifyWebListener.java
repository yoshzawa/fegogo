package jp.ac.jc21.t.yoshizawa;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.googlecode.objectify.ObjectifyService;


import jp.ac.jc21.t.yoshizawa.objectify.*;
import jp.ac.jc21.t.yoshizawa.objectify.backup.*;


@WebListener
public class ObjectifyWebListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ObjectifyService.init();
		// This is a good place to register your POJO entity classes.
		// ObjectifyService.register(YourEntity.class);
		
		ObjectifyService.register(Exam.class);
		ObjectifyService.register(Toi.class);
		ObjectifyService.register(Question.class);
		ObjectifyService.register(Answer.class);
		ObjectifyService.register(AnswerSum.class);
		ObjectifyService.register(Member.class);
		ObjectifyService.register(Genre.class);

	
		ObjectifyService.register(BackupAnswer.class);
		ObjectifyService.register(BackupAnswerSum.class);
}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}