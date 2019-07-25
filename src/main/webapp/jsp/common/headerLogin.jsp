<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
		String email = (String) request.getAttribute("email");
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
  <span class="navbar-brand mb-0 h1">Navigation</span>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="/">ホーム <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="/exam/list">問題から選択</a>
      <a class="nav-item nav-link disabled" href="#" tabindex="-1" aria-disabled="true">ジャンルから選択</a>
      <a class="nav-item nav-link disabled" href="#" tabindex="-1" aria-disabled="true">個人結果分析</a>
    </div>
  </div>
    <span class="navbar-text"><%= email %>としてサインイン</span>
  <a class="btn btn-danger d-none d-lg-inline-block mb-3 mb-md-0 ml-md-3" href="/openidSignOut">Sign out</a>
</nav>
