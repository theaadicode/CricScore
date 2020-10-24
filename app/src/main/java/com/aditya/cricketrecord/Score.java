package com.aditya.cricketrecord;

public class Score {
    private int runs;
    private int balls;
    private int wicket;
    private int extras;
    private int four;
    private int six;

    int getRuns() {
        return runs;
    }

    int getBalls() {
        return balls;
    }

    int getWicket() {
        return wicket;
    }

    int getExtras() {
        return extras;
    }

    int getFour() {
        return four;
    }

    int getSix() {
        return six;
    }
    public Score(){
        runs = 0;
        balls = 0;
        wicket = 0;
        extras = 0;
        four = 0;
        six = 0;
    }

    void reset(){
        runs = 0;
        balls = 0;
        wicket = 0;
        extras = 0;
        four = 0;
        six = 0;
    }

    void single(){
        runs += 1;
    }
    void four(){
        runs += 4;
        four += 1;
    }
    void six(){
        runs += 6;
        six += 1;
    }
    void wicket(){
        wicket += 1;
    }
    void balls(){
        balls += 1;
    }
    void extras(){
        extras += 1;
    }
}
