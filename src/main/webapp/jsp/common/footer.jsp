<%@page import="java.util.TimeZone"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.CommonEntity"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!	
 	private final String changePoint(int seikai , int answer){	
	float point = (100.0f * seikai / answer);	
	return String.format("%1$.1f", point);	
	}		
	private final String dateFormat(Date d){	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");	
	    TimeZone timeZoneJP = TimeZone.getTimeZone("Asia/Tokyo");
	    sdf.setTimeZone(timeZoneJP);
		return sdf.format(d);	
	}	
 %>		
<footer class="page-footer font-small blue pt-4">

  <div class="footer-copyright text-center py-3"><p>
			fegogo.appspot.com Version
			<%=CommonEntity.ofyVersion%></p>
  </div>
  
</footer>
<!-- Custom styles for this template -->
<link href="/css/sticky-footer.css" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
