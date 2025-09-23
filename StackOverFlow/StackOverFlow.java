package StackOverFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import StackOverFlow.Content.AnswerComment;
import StackOverFlow.Content.Comment;
import StackOverFlow.Content.QuestionComment;
import StackOverFlow.entities.Answer;
import StackOverFlow.entities.Question;
import StackOverFlow.entities.User;
import StackOverFlow.exception.AnswerException;
import StackOverFlow.exception.QuestionException;
import StackOverFlow.exception.UserException;

public class StackOverFlow {

    private ConcurrentHashMap<String, Question> questionList;
    private ConcurrentHashMap<String, Answer> answerList;
    private ConcurrentHashMap<String,User>users;

    private class StackOverFlowClass {
        public static StackOverFlow stackOverFlow = new StackOverFlow();
    };

    private StackOverFlow() {
        this.questionList = new ConcurrentHashMap<>();
        this.answerList = new ConcurrentHashMap<>();

    }

    public static StackOverFlow getInstance() {
        return StackOverFlowClass.stackOverFlow;
    }

    public synchronized void addQuestion(Question question) throws QuestionException {
        if (questionList.containsKey(question.getQuetsionId())) {
            throw new QuestionException("Question already added");
        }
        questionList.put(question.getQuetsionId(), question);
    }
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        for (Question question : questionList.values()) {
            questions.add(question);
        }
        return questions;
    }
    public synchronized void addAnswer(Answer answer) throws QuestionException, AnswerException,UserException {
        String questionId = answer.getQuestion().getQuetsionId();
        if (!questionList.containsKey(questionId)) {
            throw new QuestionException("Question not found");
        }
        if (answerList.containsKey(answer.getAnswerId())) {
            throw new AnswerException("answer already added");
        }
      
        userPresent(answer.getAuthor());
        Question question = questionList.get(questionId);
        question.addAnswers(answer);

    }
    public List<Answer> getAllAnswersForQuestion(String questionId) throws QuestionException {

        if (!questionList.containsKey(questionId)) {
            throw new QuestionException("Question not found");
        }
        Question question = questionList.get(questionId);

        return question.getAnswers();
    }
    public synchronized void createAndAddComment(String message, String contentId, User user, String commentType) {
        Comment comment;
        userPresent(user);
        if (commentType.equals("answerType")) {
            if (!answerList.containsKey(contentId)) {
                throw new AnswerException("answer not found");
            }
            Answer answer=answerList.get(contentId);
            comment=new AnswerComment(message,user,contentId);
            answer.addComments(comment);

        } else {
            if (!questionList.containsKey(contentId)) {
                throw new QuestionException("question not found");
            }
            Question question=questionList.get(contentId);
            comment=new QuestionComment(message,user,contentId);
            question.addComments(comment);
        }
    }
    public List<Comment> getAnswerComments(String answerId) throws AnswerException{
        if (!answerList.containsKey(answerId)) {
            throw new AnswerException("answer not found");
        }
        Answer answer=answerList.get(answerId);

        return answer.getComments();
    }
    public List<Comment> getQuestionComments(String questionId) throws QuestionException{
        if (!questionList.containsKey(questionId)) {
            throw new QuestionException("question not found");
        }
       Question question=questionList.get(questionId);

        return question.getComments();
    }
    public synchronized void addUser(User user) throws UserException{
        userPresent(user);
        users.put(user.getUserId(), user);
    }
    private void userPresent(User user)throws UserException{
        if(users.containsKey(user.getUserId())){
            throw new UserException("user not present in the stack overflow");
        }
    }
    public synchronized boolean answerAccepted(Answer answer) throws AnswerException{
        if(!answerList.containsKey(answer.getAnswerId())){
            throw new AnswerException("answer not found");
        }

        int x=(int) (Math.random()*2);
        x%=2;
        Question question=answer.getQuestion();
        Answer acceptedAnswer=question.getAcceptedAnswer();
        if(acceptedAnswer!=null){
            throw new AnswerException("answer is already accepted for this question");
        }
        if(x==1){
            question.setAcceptedAnswer(answer);
            User user=answer.getAuthor();
            user.setReputation(10);
            return true;
        }
        return false;
    }



}
