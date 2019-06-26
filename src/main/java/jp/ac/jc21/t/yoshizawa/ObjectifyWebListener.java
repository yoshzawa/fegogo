package jp.ac.jc21.t.yoshizawa;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.googlecode.objectify.ObjectifyService;

import jp.ac.jc21.t.yoshizawa.objectify.Exam;

@WebListener
public class ObjectifyWebListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ObjectifyService.init();
    // This is a good place to register your POJO entity classes.
    // ObjectifyService.register(YourEntity.class);
	ObjectifyService.register(Exam.class);

  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
  }
}