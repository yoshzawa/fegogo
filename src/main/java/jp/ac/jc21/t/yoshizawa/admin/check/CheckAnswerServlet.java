package jp.ac.jc21.t.yoshizawa.admin.check;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.jc21.t.yoshizawa.objectify.Answer;
import jp.ac.jc21.t.yoshizawa.objectify.AnswerSum;
import jp.ac.jc21.t.yoshizawa.objectify.Exam;
import jp.ac.jc21.t.yoshizawa.objectify.Question;
import jp.ac.jc21.t.yoshizawa.objectify.Toi;

/**
 * Servlet implementation class AnswerExportServlet
 */
@WebServlet("/admin/check/Answer")
public class CheckAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text/csv; charset=Windows-31J");
//		PrintWriter out = response.getWriter();

		// answer
		Optional<Answer> optAnswer;
		{
			String keyString = request.getParameter("answerId");
			optAnswer =Optional.ofNullable(Answer.getById(Long.parseLong(keyString)));
			request.setAttribute("optAnswer", optAnswer);
		}
		
		// answer -> answerSum(Link)
		Optional<AnswerSum> optAnswerSum;
		{
			optAnswerSum = optAnswer.flatMap(a -> a.getAnswerSum());
			request.setAttribute("optAnswerSum", optAnswerSum);
		}
		
		
		// answerSum(Real)
		{
			Optional<Long> answerSumId = optAnswer.map(a -> a.getAnswerSumId());
			Optional<AnswerSum> optAnswerSumReal;
			if(answerSumId.isPresent()){
				optAnswerSumReal = Optional.ofNullable(AnswerSum.getById(answerSumId.get()));
			} else {
				optAnswerSumReal =Optional.empty();
			}
			request.setAttribute("optAnswerSumReal", optAnswerSumReal);
			
		}
		
		// answer -> answerSum -> toi
		Optional<Toi> optToi;
		Optional<Toi> optToiReal;
		if(optAnswerSum.isPresent()) {
			optToi = optAnswerSum.get().getToi();  
			optToiReal=Toi.getByAnswerSum(optAnswerSum.get());
		} else {
			optToi=Optional.empty();
			optToiReal=Optional.empty();
		}
		request.setAttribute("optToi", optToi);
		request.setAttribute("optToiReal", optToiReal);
		
		// answer -> answerSum -> toi -> exam
		Optional<Exam> optExam;
		Optional<Exam> optExamReal;
		if(optToi.isPresent()) {
			optExam = optToi.get().getOptExam();
			optExamReal = Exam.getByToi(optToi.get());
		}else {
			optExam = Optional.empty();
			optExamReal = Optional.empty();
		}
		request.setAttribute("optExam", optExam);
		request.setAttribute("optExamReal", optExamReal);
		
		// answer -> Question(Link)
		Optional<Question> optQuestion = optAnswer.flatMap(a -> a.getOptQuestion());
		request.setAttribute("optQuestion", optQuestion);
		
		// Question(Real)
		{
			Optional<Long> questionId = optAnswer.map(q -> q.getQuestionId());
			Optional<Question> optQuestionReal;
			if(questionId.isPresent()){
				optQuestionReal = Optional.ofNullable(Question.getById(questionId.get()));
			} else {
				optQuestionReal =Optional.empty();
			}
			request.setAttribute("optQuestionReal", optQuestionReal);
			
		}
		
		// answer -> Question -> Toi(Link)
		Optional<Toi> optToiQ = optQuestion.flatMap(q -> q.getOptToi());
		request.setAttribute("optToiQ", optToiQ);
		Optional<Toi> optToiQReal ;
		
		if(optQuestion.isPresent()) {
			optToiQReal=Optional.ofNullable(Toi.getById(optQuestion.get().getToiId()));
		}else {
			optToiQReal=Optional.empty();
		}
		request.setAttribute("optToiQReal", optToiQReal);

		
		request.getRequestDispatcher("/jsp/checkAnswer.jsp").forward(request, response);
	}

}
