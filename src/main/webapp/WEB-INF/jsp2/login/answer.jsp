<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Answer"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.AnswerSum"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
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
		AnswerSum ansSummary = (AnswerSum) request.getAttribute("ansSummary");
		Toi toi = ansSummary.getRefToi().get();
		Exam exam = toi.getExam();
		List<String[]> datas = (List<String[]>) request.getAttribute("datas");
	%>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/">ホーム</a></li>
			<li class="breadcrumb-item"><a href="/exam/">試験 一覧</a></li>
			<li class="breadcrumb-item"><a
				href="/toi/list?parentId=<%=exam.getId()%>"><%=exam.getName()%>
					試験</a></li>
			<li class="breadcrumb-item active" aria-current="page">問<%=toi.getNo()%>
				<%=toi.getRefGenre().get().getName()%> (<%=toi.getName()%>) 解答登録
			</li>
		</ol>
	</nav>
	<%@ include file="../common/headerLogin.jsp"%>

	<H1>解答を登録しました</H1>
		    <main class="mb-5">
		    <P>最後の回答から10日間は、新規の回答は受け付けません。</P>
	
	<h3>
		<%=ansSummary.getNoOfAnswer()%>問中<%=ansSummary.getNoOfSeikai()%>問正解
	</h3>

	<TABLE border="1"
		class="table table-striped table-hover table-responsive">
		<thead class="thead-dark">
			<tr>
				<th>設問</th>
				<th>正解</th>
				<th>解答</th>
			</tr>
		</thead>

		<%
			for (String[] s : datas) {
		%>
		<tr>
			<th><%=s[0]%></th>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
		</tr>
		<%
			}
		%>
	</table>

	<a
		href="/toi2/list?parentId=<%=exam.getId()%>">戻る</a>

<p></p>
</main>
<%@ include file="../common/footer.jsp"%>
</div>
</body>

</html>