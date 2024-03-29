<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Question"%>
<%@page import="java.util.List"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
		String parentId = (String) request.getAttribute("parentId");
		Toi toi = (Toi) request.getAttribute("parent");
		List<String[]> datas = (List<String[]>) request.getAttribute("datas");

	%>

<html>
<head>
<meta charset="UTF-8">
<title><%= toi.getExam().getYYYYMM()%> 問<%=toi.getNo()%> <%=toi.getName()%></title>
</head>
<body>
	<div class="container">


		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">ホーム</a></li>
				<li class="breadcrumb-item"><a href="/exam/">試験 一覧</a></li>
				<li class="breadcrumb-item"><a
					href="/toi/list?parentId=<%=toi.getExamId()%>"><%=toi.getExamName()%>
						試験</a></li>
				<li class="breadcrumb-item active" aria-current="page">問<%=toi.getNo()%>
					<%= toi.getGenreName() %> (<%=toi.getName()%>)
				</li>
			</ol>
		</nav>

		<%@ include file="../common/headerLogin.jsp"%>

		<H1>登録されている設問の一覧</H1>
		<main class="mb-5">

			<%
		if ((datas == null) || (datas.size() == 0)) {
	%>
			設問が登録されていません
			<%
		} else {
	%>
			<script>
$(function(){
    $(document).on('submit', function(event) {
      $('form').find(':submit').prop('disabled', true);
    });
});
</script>

			<form method="post" action="/answer">
				<input type="hidden" name="userId" value="<%= email %>" /> <input
					type="hidden" name="toiId" value="<%= toi.getId() %>" />

				<TABLE border=1
					class="table table-striped table-hover table-responsive">
					<thead class="thead-dark">
						<TR>
							<th>設問</th>
							<th>解答欄</th>
						</TR>
					</thead>

					<%
				for(String[] s : datas){
			%>
					<tr>
						<td><%=s[0]%></td>
						<td><%=s[1]%></td>

					</tr>
					<%
				}
			%>
				</table>

				<input type="submit" value="送信する" />
			</form>

			<%
		}
	%>

			<p></p>
		</main>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>