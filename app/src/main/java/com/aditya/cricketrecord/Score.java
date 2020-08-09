package com.aditya.cricketrecord;

public class Score {
    private int runs;
    private int balls;
    private int wicket;

    int getRuns() {
        return runs;
    }

    int getBalls() {
        return balls;
    }

    int getWicket() {
        return wicket;
    }

    public Score(){
        runs = 0;
        balls = 0;
        wicket = 0;
    }

    void reset(){
        runs = 0;
        balls = 0;
        wicket = 0;
    }

    void single(){
        runs += 1;
    }
    void four(){
        runs += 4;
    }
    void six(){
        runs += 6;
    }
    void wicket(){
        wicket += 1;
    }
    void balls(){
        balls += 1;
    }
}
