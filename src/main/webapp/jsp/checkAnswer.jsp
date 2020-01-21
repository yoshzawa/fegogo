<%@page import="java.util.Optional"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.*"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="java.util.ArrayList"%>
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
		Optional<Answer> optAnswer = (Optional<Answer>) request.getAttribute("optAnswer");

		// answer -> answerSum
		Optional<AnswerSum> optAnswerSum = (Optional<AnswerSum>) request.getAttribute("optAnswerSum");

		// answer -> answerSum
		Optional<AnswerSum> optAnswerSumReal = (Optional<AnswerSum>) request.getAttribute("optAnswerSumReal");

		// answer -> Question
		Optional<Question> optQuestion = (Optional<Question>) request.getAttribute("optQuestion");

		// answer -> Question
		Optional<Question> optQuestionReal = (Optional<Question>) request.getAttribute("optQuestionReal");

		// answer -> answerSum -> toi
		Optional<Toi> optToi = (Optional<Toi>) request.getAttribute("optToi");
		Optional<Toi> optToiReal = (Optional<Toi>) request.getAttribute("optToiReal");

		Optional<Exam> optExam = (Optional<Exam>) request.getAttribute("optExam");

		Optional<Exam> optExamReal = (Optional<Exam>) request.getAttribute("optExamReal");
	%>

	<%@ include file="common/headerAdmin.jsp"%><br>

	<H1>解答詳細に関するチェック</H1>
	<%
		if (!optAnswer.isPresent()) {
	%>
	解答詳細が登録されていません
	<%
		} else {
	%>
	<table border="1">
		<tr>
			<th>Exam</th>
			<th>Toi</th>
			<th>AnswerSum</th>
			<th>Answer</th>
		</tr>
		<tr>
		<td>

			// Exam
			<table border="1">
				<tr>
					<td>exam <- toi へのリンク</td>
					<td><%=optExam.get().getId()%><br />
					<a
						href="/admin/check/exam?examId=<%=optExam.get().getId()%>">check</a></td>

				</tr>
				<%
					if (optExamReal.isPresent()) {
				%>
				<tr>
					<td>Exam確認</td>
					<td>存在する(id=<%=optExamReal.get().getId()%>)
					</td>
				</tr>
				<tr>
					<td>Exam.toiRefListに含まれる</td>
					<td><%=optExamReal.get().containAnswer(optToi.get().getId())%></td>
				</tr>

				<%
					} else {
				%>
				<tr>
					<td>Exam確認</td>
					<td>存在しない</td>
				</tr>
				<%
					}
				%>
			</table>
		</td>
		<td>


			// Toi

			<table border="1">
				<tr>
					<td>toi <- answerSum へのリンク</td>
					<td><%=optToi.get().getId()%><br />
					<a href="/admin/check/toi?toiId=<%=optToi.get().getId()%>">check</a></td>
				</tr>
				<%
					if (optToiReal.isPresent()) {
				%>
				<tr>
					<td>toi確認</td>
					<td>存在する(id=<%=optToiReal.get().getId()%>)
					</td>
				</tr>
				<tr>
					<td>toi.AnswerSumRefListに含まれる</td>
					<td><%=optToiReal.get().containAnswer(optAnswerSum.get().getId())%></td>
				</tr>

				<%
					} else {
				%>
				<tr>
					<td>toi確認</td>
					<td>存在しない</td>
				</tr>
				<%
					}
				%>
			</table>
		</td>
		<td>
			// AnswerSum

			<table border="1">
				<tr>
					<td>AnswerSum <- answerへのリンク</td>
					<td><%=optAnswerSum.get().getId()%><br />
					<a
						href="/admin/check/answerSum?answerSumId=<%=optAnswerSum.get().getId()%>">check</a></td>
				</tr>
				<%
					if (optAnswerSumReal.isPresent()) {
				%>
				<tr>
					<td>AnswerSum確認</td>
					<td>存在する（ID=<%=optAnswerSumReal.get().getId()%>)
					</td>
				</tr>
				<tr>
					<td>AnswerSum.mapAnswerに含まれる</td>
					<td><%=optAnswerSumReal.get().containAnswer(optAnswer.get().getId())%></td>
				</tr>
				<%
					} else {
				%>
				<tr>
					<td>AnswerSum確認</td>
					<td>存在しない</td>
				</tr>
				<%
					}
				%>
			</table>
		</td>
		<td>

			// Answer
			<table border="1">
				<tr>
					<td>Answer id</td>
					<td><%=optAnswer.get().getId()%></td>
				</tr>
				<tr>
					<td>Answer date</td>
					<td><%=Answer.getDateString(optAnswer.get().getAnswered())%></td>

				</tr>
			</table>


			<table border="1">
				<tr>
					<td>answer->Questionへのリンク</td>
					<td><%=optQuestion.get().getId()%></td>
				</tr>
				<%
					if (optQuestionReal.isPresent()) {
				%>
				<tr>
					<td>Question確認</td>
					<td><%=optQuestionReal.get().getId()%></td>
				</tr>

				<%
					} else {
				%>
				<tr>
					<td>Question確認</td>
					<td>存在しない</td>
				</tr>
				<%
			}
		%>
			</table>
			
			</td>
			</tr>
			</table>





			<%
		} 
	%>


			<hr />
</body>
<%@ include file="common/footer.jsp"%>
</html>