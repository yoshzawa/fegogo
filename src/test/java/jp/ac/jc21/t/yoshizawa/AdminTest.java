package jp.ac.jc21.t.yoshizawa;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import jp.ac.jc21.t.yoshizawa.servlet.admin.AdminServlet;

public class AdminTest {

  @Test
  public void test() throws IOException {
    MockHttpServletResponse response = new MockHttpServletResponse();
    try {
		new AdminServlet().doGet(null, response);
	} catch (Exception e) {
		Assert.fail("Exception : "+e.getMessage());
	}
//    Assert.assertEquals("text/plain", response.getContentType());
//    Assert.assertEquals("UTF-8", response.getCharacterEncoding());
  }
}
