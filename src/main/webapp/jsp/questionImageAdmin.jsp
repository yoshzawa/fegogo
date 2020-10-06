<%@page import="jp.ac.jc21.t.yoshizawa.objectify.ImageSet"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Genre"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Question"%>
<%@page import="java.util.List"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
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
	<%@ include file="common/headerAdmin.jsp"%><br>
	<%
		String parentId = (String) request.getAttribute("parentId");
		Toi parent = (Toi) request.getAttribute("parent");
		Map<Long, Question> qMap = (Map<Long, Question>) request.getAttribute("questionMap");
		Exam exam = (Exam) request.getAttribute("exam");
		List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");
		Long genreId = 0L;
		if (parent.getRefGenre() != null) {
			genreId = parent.getRefGenre().get().getId();
		}
		List<ImageSet> imageSet = (List<ImageSet>) request.getAttribute("imageSet");
	%>

		<div class="row">
			<div class="container-sm">

				<H1>登録されている設問の一覧</H1>
				<p>選択された試験：</p>
				<P>
					<%=exam.getName()%>
					問<%=parent.getNo()%>(<%=parent.getName()%>)
					<a href="./imageReset?parentId=<%=parentId%>">登録をリセットする</a>
					
				</p>
			</div>
		</div>
		<div class="row">
			<div style="overflow: auto;">
				<table border=1>
					<% 
						for (ImageSet is : imageSet){
						if(is.isImage()==true){
					%>

					<tr>
						<td>
							<p
								style="position:relative;
									background-image: url(<%=is.getUrl()%>);
									height: <%=is.getHeight()%>px;
									width:700px;
									background-position: 0  <%=is.getTop()%>px;
									background-size: 700px auto ;
									overflow: hidden;">
							</p>
						</td>
					</tr>
					<%
						} else {
					%>
					<tr>
						<td>Question <%=is.getQuestionIds() + ""%></td>
					</tr>
					<%
						}
					%>
					<%
						}
					%>
				</table>
			</div>
			<div style="float: right; overflow: auto;">

				<%
					int no = 1;
				%>
				<table border=1>
					<%
						for (ImageSet is : imageSet) {
							if (is.isImage() == true) {
					%>
					<tr>
						<td><%=no%></td>
						<td><%=is.getUrl()%> <a
							href="./imageRemove?parentId=<%=parentId%>&no=<%=no%>">消す</a>
							<br>
							<form method="post" action="./imageHeight">
								<input type="hidden" name="parentId" value="<%=parentId%>" />
								<input type="hidden" value="<%=no%>" name="no"> height=<input
									type="number" value="<%=is.getHeight()%>" name="height">
								<input type="submit" value="変更">
							</form>
							<form method="post" action="./imageTop">
								<input type="hidden" name="parentId" value="<%=parentId%>" />
								<input type="hidden" value="<%=no%>" name="no"> top=<input
									type="number" value="<%=is.getTop()%>" name="top"> <input
									type="submit" value="変更">
							</form></td>
					</tr>
					<%
						} else {
					%>
					<tr>
						<td><%=no%></td>
						<td>
							<%
								for (Long id : is.getQuestionIds()) {
							%> Question <%=id%><br> <%
 	}
 %>
						</td>
					</tr>
					<%
						}
							no++;
					%>
					<%
						}
					%>
					<tr>
						<td></td>
						<td>
							<form method="post" action="./imageAdd">
								<input type="hidden" name="parentId" value="<%=parentId%>" />
								画像を追加する<br> 位置：<input type="number" value="1" name="no">の前<br>
								URL：http://～～image/<input type="text" value="" name="url"><br>
								<input type="submit" value="追加する">
							</form>
						</td>
					</tr>

				</table>
			</div>
		</div>

		<P></P>
		<P></P>
	</div>

</body>
<%@ include file="common/footer.jsp"%>
</html>