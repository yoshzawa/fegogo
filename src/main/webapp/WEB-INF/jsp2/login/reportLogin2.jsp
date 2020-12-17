<%@page import="jp.ac.jc21.t.yoshizawa.objectify.*"%>
<%@page import="java.util.Map"%>
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
	<div class="container">

		<%
			Map<Long, List<AnswerSum>> resultMap = (Map<Long, List<AnswerSum>>) request.getAttribute("resultMap");

		List<Genre> genreList = (List) request.getAttribute("genreList");
		int[] seikai = new int[13];
	int[] answer = new int[13];

		%>

		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">ホーム</a></li>
				<li class="breadcrumb-item active" aria-current="page">試験 一覧</li>
			</ol>
		</nav>

		<%@ include file="../common/headerLogin.jsp"%>




		<H1>解答済み試験の一覧</H1>
		<main class="mb-5">



			<%
				if ((resultMap == null) || (resultMap.size() < 1)) {
			%>
			試験が解答されていません
			<%
				} else {
			%>
			<TABLE border="1" class="table table-striped table-hover ">
				<thead class="thead-dark">
					<TR>
						<TH>試験名</TH>
						<TH>問</TH>
						<TH>分野</TH>
						<TH>内容</TH>
						<TH>解答日</TH>
						<TH>正解率</TH>
					</TR>
				</thead>

				<%
				for (Genre g : genreList) {
				%>
				<tr>
					<td><%=g.getId()%></td>
					<td><%=g.getNo()%></td>
					<td><%=g.getName()%></td>
					<td>
						<%
							List<AnswerSum> asList = resultMap.get(g.getId());
						if (asList == null) {
							System.out.println("NULL!:" + g.getId());
							continue;
						}
						int a = 0, s = 0;
						for (AnswerSum as : asList) {
						%>
						<p><%=as.getToi().get().getExamName()%>(<%=dateFormat(as.getAnswered())%>)
							:
							<%=as.getNoOfSeikai()%>/<%=as.getNoOfAnswer()%></p> 
							<%
							 	s += as.getNoOfSeikai();
 								a += as.getNoOfAnswer();
 							}
 						%>
					</td>
					<td><%= Math.round(s * 1000.0 / a) / 10.0 %>%</td>
				</tr>
				<%
					seikai[g.getNo() - 1] += s;
					answer[g.getNo() - 1] += a;
				}
				%>
			</TABLE>
			<%
				}
			double toi1=Math.round(seikai[1-1] * 1000.0 / answer[1-1]) / 10.0;
			double toi8=Math.round(seikai[8-1] * 1000.0 / answer[8-1]) / 10.0;
			double gengo = Math.round((seikai[12-1]+seikai[13-1]) * 1000.0 / (answer[12-1]+answer[13-1])) / 10.0;
			%>
<h2>必須</h2>
<table border="1">
<tr><th></th><th>セキュリティ</th><th>アルゴリズム</th><th>言語</th><th>合計点</th></tr>
<tr><th>正解率</th>
<td><%=  toi1  %>%</td>
<td><%=  toi8  %>%</td>
<td><%=  gengo  %>%</td>
<td></td>
</tr>
<tr><th>得点</th>
<td><%=  Math.floor(20*toi1/10.0)/10.0  %>点</td>
<td><%=  Math.floor(25*toi8/10.0)/10.0  %>点</td>
<td><%=  Math.floor(25*gengo/10.0)/10.0  %>点</td>
<td><%=  Math.floor(20*toi1/10.0 +
  25*toi8/10.0  +
  25*gengo/10.0 )/10.0 %>点</td>
</tr>
</table>

			<p></p>
		</main>
		<%@ include file="../common/footer.jsp"%>
</body>

</html>