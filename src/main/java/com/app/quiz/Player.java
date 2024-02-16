package com.app.quiz;

public class Player {

    private String playerName;
    private int correctAnswers;

    public Player(String name) {
        this.correctAnswers = 0;
        this.playerName = name;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getCorrectAnswers() {
        return this.correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void incrementCorrectAnswers() {
        this.correctAnswers++;
    }

}
