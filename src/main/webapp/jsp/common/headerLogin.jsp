<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
		String email = (String) request.getAttribute("email");
%>

<div align="right">

    <%= email %>としてサインイン
  <a  href="/openidSignOut">
    <button type="button" class="btn btn-danger">Sign out</button>
  
  </a><br>
</div>


      <a class="  active" href="/">ホーム <span class="sr-only">(current)</span></a>
      <a class=" " href="/exam/list">問題から選択</a>
      <a class="  disabled" href="/#" tabindex="-1" aria-disabled="true">ジャンルから選択</a>
      <a class="  disabled" href="/#" tabindex="-1" aria-disabled="true">個人結果分析</a>
