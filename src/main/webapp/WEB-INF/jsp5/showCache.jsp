<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
		boolean before = 
		(boolean)request.getAttribute("before");
		List<String> result = 
		(List<String>)request.getAttribute("result");
		boolean after = 
		(boolean)request.getAttribute("after");
		String url = 
		(String)request.getAttribute("url");
		%>
<body>

<H1>URL</H1>
<p><%= url %>
<H2>before</H2>
<p><%= before %>
<H2>data</H2>
<% for(String s : result){
%>
<P><%= s %></P>
<%}
%>
<H2>after</H2>
<p><%= after %>

</body>
</html>