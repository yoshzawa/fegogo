<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-146071751-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-146071751-1');
</script>
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
      <a class="  disabled" href="/genre" tabindex="-1" aria-disabled="true">ジャンルから選択</a>
      <a class="  disabled" href="/#" tabindex="-1" aria-disabled="true">個人結果分析</a>
