<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%@ include file="common/headerAdmin.jsp"%><br>
	
<H1>EXPORT一覧</H1>
<H2>バージョン１：平成と同じ形式でEXPORT</H2>
      <a class="disabled" href="/admin/answerSum/dumpAnswerSummary.csv">解答情報をCSVでダウンロード</a>
	  <a class="disabled" href="/admin/answer/dumpAnswer.csv">解答詳細をCSVでダウンロード（TIMEOUT）</a>

<H2>バージョン２：平成と同じ形式で、TASKでGoogleCloudConsoleに一度出力するタイプ</H2>

      <a class="active" href="/admin/makeCsvTask">EXPORT生成</a>
      <a class="active" href="/admin/dumpAnswerSummary2.csv">解答情報CSV</a>
      <a class="active" href="/admin/dumpAnswer2.csv">解答詳細CSV</a>

<H2>バージョン３：テーブルの中身をそのままexportするタイプ</H2>
      <a class="active" href="/admin/export/exam.csv">exam.csv</a>
      <a class="active" href="/admin/export/toi.csv">toi.csv</a>
      <a class="active" href="/admin/export/question.csv">question.csv</a>
      <a class="active" href="/admin/export/member.csv">member.csv</a>
      <a class="active" href="/admin/export/genre.csv">genre.csv</a>
      <a class="active" href="/admin/export/answerSum.csv">answerSum.csv</a>
      <a class="active" href="/admin/export/answer.csv">answer.csv</a>






</body>
　<%@ include file="common/footer.jsp"%>
</html>