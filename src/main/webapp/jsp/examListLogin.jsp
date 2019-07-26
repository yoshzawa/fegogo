<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
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
	<%
		Map<Long, Exam> examMap = (Map<Long, Exam>) request.getAttribute("examMap");
		List<AnswerSum> answerSumList = (List<AnswerSum>) request.getAttribute("answerSumList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	%>

	<%@ include file="common/headerLogin.jsp"%>

	<H1>登録されている試験の一覧</H1>

	<%
	if (examMap == null || examMap.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<TABLE border="1"
		class="table table-striped table-hover table-responsive">
		<thead class="thead-dark">
			<tr>
				<TH>試験名</TH>
				<TH>問題登録</TH>
			</TR>
		</thead>
		<%
		for (Long k : examMap.keySet()) {
			Exam e = examMap.get(k);		%>
		<tr>
			<td><a href="/toi/list?parentId=<%=e.getId()%>"><%=e.getName()%></a></td>
			<td><%=e.getToiRefListSize()%></td>
		</tr>
		<%
			}
		%>
	</TABLE>
	<%
		}
	%>

	<H1>解答済み試験の一覧</H1>

	<%
		if ((answerSumList == null) || (answerSumList == null)) {
	%>
	試験が解答されていません
	<%
		} else {
	%>
	<TABLE border="1"
		class="table table-striped table-hover table-responsive">
		<thead class="thead-dark">
			<TR>
				<TH>試験名</TH>
				<TH>問</TH>
				<TH>内容</TH>
				<TH>解答日</TH>
				<TH>正解率</TH>
			</TR>
		</thead>

		<%
			for (AnswerSum as : answerSumList) {
					float point = (100.0f * as.getNoOfSeikai() / as.getNoOfAnswer());
		%>
		<tr>
			<td><%=as.getRefToi().get().getParent().getName()%></td>
			<td><%=as.getRefToi().get().getNo()%></td>
			<td><%=as.getRefToi().get().getName()%></td>
			<td><%=sdf.format(as.getAnswered())%></td>
			<td align="right"><%=String.format("%1$.1f", point)%></td>
		</tr>
		<%
			}
		%>
	</TABLE>
	<%
		}
	%>

</body>
<%@ include file="common/footer.jsp"%>

</html>