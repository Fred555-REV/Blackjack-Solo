package game.Blackjack;

import game.Blackjack.actors.Actor;

import java.util.List;

public class Turn {
    private int turnCounter;
    private final int MAX_TURNS;

    public Turn(int max_turns) {
        MAX_TURNS = max_turns;
        turnCounter = 0;
    }

    //TODO Check over passing logically
    protected void pass(List<Actor> actors) {

//        while (!actors.get(turnCounter).isPlaying()) {
//            turnCounter++;
//            if (turnCounter >= actors.size()) {
//                turnCounter = 0;
//            }
//        }
        turnCounter++;
        if (turnCounter >= actors.size()) {
            turnCounter = 0;
        }
    }

    public int getCounter() {
        return turnCounter;
    }
}
