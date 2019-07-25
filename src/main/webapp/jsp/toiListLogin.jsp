<%@page import="java.text.SimpleDateFormat"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.AnswerSum"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="java.util.*"%>
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


	<%
		Exam parent = (Exam) request.getAttribute("parent");
		TreeMap<Long, Toi> toiMap = (TreeMap<Long, Toi>) request.getAttribute("toiMap");
		String parentId = (String) request.getAttribute("parentId");
		List<AnswerSum> answerSumList = (List<AnswerSum>) request.getAttribute("answerSumList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	%>
<%@ include file="common/headerLogin.jsp"%>

	<H1>登録されている問の一覧</H1>
	
	<p>選択された試験：<%= parent.getName() %> <a href="/exam/list">(選択解除する)</a>
	</p>

	<%
		if (toiMap == null || toiMap.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<TABLE border=1 class="table table-striped table-hover table-responsive">
		<thead class="thead-dark">		<TR>
			<TH>問番号</TH>
			<TH>テーマ</TH>
			<TH>設問数</TH>
			</TR>
		</thead>
		<%
			Set<Long> toiKeySet = toiMap.keySet();
				for (Long l : toiKeySet) {
					Toi t = toiMap.get(l);
		%>
		<tr>
			<td><%=t.getNo()%></td>
			<td><a href="/question/list?parentId=<%=t.getId()%>"><%=t.getName()%></a></td>
						<td><%=t.getQuestionRefListSize()%></td>
			
		</tr>
		<%
			}
		%>
	</TABLE>
	<%
		}
	%>

<H1>解答済み試験の一覧</H1>

<% if ((answerSumList == null) || (answerSumList.size() == 0)){ %>
	試験が解答されていません
<% } else {%>
	<TABLE border=1 class="table table-striped table-hover table-responsive">
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
					Toi toi = as.getRefToi().get();
					float point = (100.0f * as.getNoOfSeikai() / as.getNoOfAnswer());
		%>
		<tr>
			<td><%=toi.getParent().getName()%></td>
			<td><%=toi.getNo()%></td>
			<td><%=toi.getName()%></td>
			<td><%=sdf.format(as.getAnswered())%></td>
			<td align="right"><%=String.format("%1$.1f", point)%></td>
		</tr>
		<%
			}
		%>
		<%
	}
%>
	
</body>
　<%@ include file="common/footer.jsp"%>

</html>