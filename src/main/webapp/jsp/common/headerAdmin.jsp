<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	UserService userService = UserServiceFactory.getUserService();
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-warning">
  <span class="navbar-brand mb-0 h1">Navigation</span>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="/admin">ホーム </a>
      <a class="nav-item nav-link active" href="/admin/exam">exam</a>
      <a class="nav-item nav-link active" href="/admin/member">member</a>
      <a class="nav-item nav-link active" href="/admin/answerSum">answerSum</a>
      <a class="nav-item nav-link active" href="/admin/answer">answer</a>
      <a class="nav-item nav-link disabled" href="#" tabindex="-1" aria-disabled="true">個人結果分析</a>
    </div>
  </div>
    <span class="navbar-text">
    <%= userService.getCurrentUser().getNickname() %>(Admin)としてサインイン
    </span>
  <a class="btn btn-danger d-none d-lg-inline-block mb-3 mb-md-0 ml-md-3" href="<%= userService.createLogoutURL("/")%>">Sign out</a>
</nav>



