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
		String email = (String)request.getAttribute("email");
		List<AnswerSum> answerSumList = (List<AnswerSum>) request.getAttribute("answerSumList");
	%>
	<h4 align="right">
<%= email %>としてサインイン（<a href="/openidSignOut">Sign out</a>）
</h4>
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
	<TABLE border=1>
		<TR>
			<TH>問番号</TH>
			<TH>テーマ</TH>
			<TH>設問数</TH>
		</TR>
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
	<TABLE border=1>
		<TR>
			<TH>試験名</TH>
			<TH>問</TH>
			<TH>内容</TH>
			<TH>正解率</TH>
		</TR>
		<%
			for (AnswerSum as : answerSumList) {
					Toi toi = as.getRefToi().get();
					float point = (100.0f * as.getNoOfSeikai() / as.getNoOfAnswer());
		%>
		<tr>
			<td><%=toi.getParent().getName()%></td>
			<td><%=toi.getNo()%></td>
			<td><%=toi.getName()%></td>
			<td><%=String.format("%1$.1f", point)%></td>
		</tr>
		<%
			}
		%>
<%
	}
%>


</body>
</html>