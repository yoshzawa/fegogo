<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	UserService us = UserServiceFactory.getUserService();
%>

<div align="right">

    <%= us.getCurrentUser().getNickname() %>(Admin)としてサインイン
  <a  href="<%= us.createLogoutURL("/")%>">
    <button type="button" class="btn btn-danger">Sign out</button>
  
  </a><br>
</div>




      <a class="  active" href="/admin">ホーム </a>
      <a class="  active" href="/admin/exam">exam</a>
      <a class="  active" href="/admin/member">member</a>
      <a class="  active" href="/admin/answerSum">answerSum</a>
      <a class="  active" href="/admin/answer">answer</a>
      <a class="  active" href="/admin/genre/">Genre</a>
      <a class="  disabled" href="#" tabindex="-1" aria-disabled="true">個人結果分析</a>




