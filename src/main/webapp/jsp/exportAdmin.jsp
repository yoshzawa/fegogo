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

<H2>バージョン３：テーブルの中身をそのままexportするタイプ</H2>
      <a class="active" href="/admin/export/exam.csv">exam.csv</a>
      <a class="active" href="/admin/export/toi.csv">toi.csv</a>
      <a class="active" href="/admin/export/question.csv">question.csv</a>
      <a class="active" href="/admin/export/member.csv">member.csv</a>
      <a class="active" href="/admin/export/genre.csv">genre.csv</a>
      <a class="active" href="/admin/export/answerSum.csv">answerSum.csv</a>
      <a class="active" href="/admin/export/answer.csv">answer.csv</a>
      <a class="active" href="/admin/export/answer2.csv">answer2.csv</a>

<H2>バージョン３：exportを読むタイプ</H2>
      <a class="active" href="/admin/import/genre">genre.csv</a>
      <a class="active" href="/admin/import/exam">exam.csv</a>
      <a class="active" href="/admin/import/toi">toi.csv</a>
      <a class="active" href="/admin/import/question">question.csv</a>






</body>
　<%@ include file="common/footer.jsp"%>
</html>