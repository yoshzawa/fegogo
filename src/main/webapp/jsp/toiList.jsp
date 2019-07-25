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
	%>
<%@ include file="common/header.jsp"%>

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
		<thead class="thead-dark"><tr>
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


</body>
　<%@ include file="common/footer.jsp"%>

</html>