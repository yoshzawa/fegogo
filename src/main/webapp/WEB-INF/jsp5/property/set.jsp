<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>システム設定</title>
</head>
<body>
	<%
		UserService userService = (UserService) request.getAttribute("userService");
	%>
	<%
		if ((userService!=null) && (userService.isUserAdmin() == true) ) {
	%>
	<h4 align="right">login as <%= userService.getCurrentUser().getNickname() %>(Admin)
	(<a href="<%= userService.createLogoutURL("/")%>">logout</a>)</h4>
	<%
		} 
	%>
	<%@ include file="/jsp/common/headerAdmin.jsp"%><br>
<h1>Microsoft Graphに関連する、登録アプリケーションのアプリケーション (クライアント) ID</h1>
<a href="https://docs.microsoft.com/ja-jp/graph/auth-v2-user">https://docs.microsoft.com/ja-jp/graph/auth-v2-user</a>を参照し、
「https://fegogo.fivepro.xyz」と「http://localhost:8080」のアプリケーション (クライアント) IDを作成し、コピペしてください
<form method="post" action="/admin/property/set">
<p>
「https://fegogo.fivepro.xyz」の「アプリケーション (クライアント) ID」
<input type="text" placeholder="12345678-1234-1234-1234-123456789abc" name="AzureAppId" /></p>
「http://localhost:8080」の「アプリケーション (クライアント) ID」
<input type="text" placeholder="12345678-1234-1234-1234-123456789abc" name="AzureAppIdLocal" /></p>
<input type="submit" value="設定" />
</form>
</body>
　<%@ include file="/jsp/common/footer.jsp"%>

</html>