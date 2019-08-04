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
	<TABLE border=1 class="table table-striped table-hover ">
		<thead class="thead-dark">		<TR>
			<TH>問番号</TH>
			<TH>分野</TH>
			<TH>内容</TH>
			<TH>設問数</TH>
			<TH>過去の解答</TH>
			</TR>
		</thead>
		<%
			Set<Long> toiKeySet = toiMap.keySet();
				for (Long l : toiKeySet) {
					Toi t = toiMap.get(l);
		%>
		<tr>
			<td><%=t.getNo()%></td>
			<td><%=t.getGenre().get().getName()%></td>
			<td><a href="/question/list?parentId=<%=t.getId()%>"><%=t.getName()%></a></td>
						<td><%=t.getQuestionRefListSize()%></td>

			<td>
				<%
					int i = 0;
							for (AnswerSum as : answerSumList) {
								if (as.getRefToi().get().getId() == t.getId()) {
									%>
			<%=dateFormat(as.getAnswered())%>
			(<%= changePoint(as.getNoOfSeikai(),as.getNoOfAnswer()) %>%)
									<% 
								i++;}
							}
							if(i==0){				%>
			<a href="/question/list?parentId=<%=t.getId()%>">答える</a>
				
				<%} %>
			</td>
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
	<TABLE border=1 class="table table-striped table-hover ">
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
			for (AnswerSum as : answerSumList) {
					Toi toi = as.getRefToi().get();
		%>
		<tr>
			<td><%=toi.getParent().getName()%></td>
			<td><%=toi.getNo()%></td>
			<td><%=toi.getGenre().get().getName()%></td>
			<td><%=toi.getName()%></td>
			<td><%=sdf.format(as.getAnswered())%></td>
			<td align="right"><%= changePoint(as.getNoOfSeikai(),as.getNoOfAnswer()) %>%</td>
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