package com.app.quiz;

public class Quiz {

    private String question;
    private String answer;
    private long id;
    private static long idGenerator = 0;

    public Quiz() {
        this("","");
    }

    public Quiz(String q, String a) {
        this.question = q;
        this.answer = a;
        this.id = idGenerator++;
    }

    public long getId() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

}
