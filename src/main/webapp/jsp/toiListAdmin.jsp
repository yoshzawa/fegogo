<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Genre"%>
<%@page import="com.google.appengine.api.users.UserService"%>
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
	Exam parentExam = (Exam) request.getAttribute("parent");
		TreeMap<Long, Toi> toiMap = (TreeMap<Long, Toi>) request.getAttribute("toiMap");
		String parentId = (String) request.getAttribute("parentId");
		List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");

		String openDate = "";
		try {
			openDate =  CommonFunction.toWebDateString(parentExam.getOpenDate());
		} catch (NullPointerException e) {
		}

		String closeDate = "";
		try {
			closeDate = CommonFunction.toWebDateString(parentExam.getCloseDate());
		} catch (NullPointerException e) {
		}
	%>

	<%@ include file="common/headerAdmin.jsp"%><br>

	<H1>登録されている問の一覧</H1>

	<p>
		選択された試験：[<%=parentExam.getYYYYMM()%>]
		<%=parentExam.getName()%><a href="/admin/exam/list">（EXAM一覧に戻る)</a>
	</p>
	<hr />
	<form method="post"
		action="../exam/edit?examId=<%=parentExam.getId()%>">
		<P>examの変更：</P>
		<P>
			YYYYMM<input type="text" name="YYYYMM"
				value="<%=parentExam.getYYYYMM()%>" /> name<input type="text"
				name="name" value="<%=parentExam.getName()%>" /> open <input
				type="checkbox" name="openNull"
				<%=(openDate.length()>0 ?  "":"checked")%> /> NULL
				<input
				type="datetime-local" name="open"
				value="<%=openDate%>" /> close <input
				type="checkbox" name="closeNull"
				<%=(closeDate.length()>0 ?  "":"checked")%> NULL
				/> <input
				type="datetime-local" name="close" value="<%= closeDate %>" /> <input
				type="submit" value="変更" />
	</form>
	<hr />

	<%
	if (toiMap == null || toiMap.size() == 0) {
	%>
	試験が登録されていません
	<a href="/admin/exam/remove?examId=<%= parentExam.getId()%>">remove</a>
	<%
	} else {
	%>
	<TABLE border=1>
		<TR>
			<TD>ToiId</TD>
			<TD>No</TD>
			<TD>Genre</TD>
			<TD>Name</TD>
			<TD>問題数</TD>
			<TD>回答者</TD>
			<TD>出題</TD>
		</TR>
		<%
		Set<Long> toiKeySet = toiMap.keySet();
				for (Long l : toiKeySet) {
					Toi t = toiMap.get(l);
		%>
		<tr>
			<td><%=t.getId()%></td>
			<td><%=t.getNo()%></td>
			<td>
				<%
				if (t.getGenre() == null) {
				%> no Genre <%
				} else {
				%> <%=t.getGenre().getName()%> <%
 }
 %>
			</td>
			<td><a href="/admin/question/list?parentId=<%=t.getId()%>"><%=t.getName()%></a></td>
			<td><%=t.getQuestionListSize()%></td>
			<td><a
				href="/admin/answerSum/listByToi?toiId=<%=t.getId()%>&parentId=<%=parentId%>"><%=t.getAnswerSumCount()%></a></td>
			<td><a href="/admin/question/image?parentId=<%=t.getId()%>">
					<%
					if (t.getImageSet() != null) {
									out.print("(CBT)");
								}
					%> 画像調整
			</a></td>
		</tr>
		<%
		}
		%>
	</TABLE>
	<%
	}
	%>

	<hr />

	<form method='post' action='/admin/toi/add'>
		<label>No</label> <input type="text" name="No" /> <label>ToiName</label>
		<input type="text" name="toiName" /> <select name="genreId">
			<%
			for (Genre g : genreList) {
			%>
			<option value="<%=g.getId()%>"><%=g.getName()%></option>

			<%
			}
			%>
		</select> <input type="hidden" name="parentId" value='<%=parentId%>' /> <input
			type="submit" name="追加" />
	</form>
</body>
<%@ include file="common/footer.jsp"%>

</html>