<%@page import="java.util.Collections"%>
<%@page import="java.util.Optional"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ja">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
	integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
	crossorigin="anonymous">

<title>fegogo v3 index</title>
</head>
<body>
	<%
		Map<Long, Exam> examMap = (Map<Long, Exam>)request.getAttribute("examMap");
/*
		Optional<String> optExamId = (Optional<String>)	request.getAttribute("optExamId");
		Long examId = null;
		if(optExamId.isPresent()){
			examId=Long.parseLong(optExamId.get());
		}else{
			Long maxKey = Collections.max(examMap.keySet());
			examId=examMap.get(maxKey).getId();
		}
*/
		Long examId = (Long)request.getAttribute("examId");

	%>
	<a href="https://fegogo.appspot.com/"
		class="d-block px-3 py-2 text-center small"
		style="background-color: infobackground;"> 安定版(Ver.1.20)に戻る </a>

	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">基本情報技術者試験対策
			午後問題 </a>
		<button class="navbar-toggler position-absolute d-md-none collapsed"
			type="button" data-toggle="collapse" data-target="#sidebarMenu"
			aria-controls="sidebarMenu" aria-expanded="true"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link" href="#">MicrosoftAccountでサインイン
			</a></li>
		</ul>
	</nav>

	<div class="container">
		<div class="row">
			<nav id="sidebarMenu"
				class="nav flex-column nav-pills col-md-3 col-lg-2 d-md-block bg-light sidebar collapse show">
				<a class="nav-link" href="../">index</a> 
				<a class="nav-link text-success disabled" href="../exam/index" tabindex="-1" aria-disabled="true">試験別</a>

<% 
	for(Long key : examMap.keySet()){
		Exam exam = examMap.get(key);
		if(exam.getId().equals( examId)){
		
%>
				<a class="nav-link active" aria-current="page" href="../exam/list?examId=<%= exam.getId()%>"><%= exam.getName()%></a> 
<%
		} else {
%>
				<a class="nav-link" href="../exam/list?examId=<%= exam.getId()%>"><%= exam.getName()%></a> 
<%
		}
	}
%>

				<a class="nav-link"	href="../genre/list">分野別</a>
			</nav>
			<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
			<p>・(2020/08/04) 新しいページを開発中です</p>
			</main>
		</div>
	</div>

</body>
<!-- Optional JavaScript -->
<!-- Popper.js first, then Bootstrap JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
	integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
	crossorigin="anonymous"></script>

</html>