package game.Blackjack;

import game.Blackjack.actors.Actor;
import game.Blackjack.cards.Card;
import game.Blackjack.cards.Deck;

import java.util.List;

public class Table {
    //TODO test single large deck vs multiple normal decks
    //ie. 208 cards in one deck shuffled or 52 cards in 4 decks shuffled and changing decks
    private Deck deck;
    private List<Actor> actors;
    private Turn turn;
    private final List<String> CONTROL_MENU = List.of(
            "(1) Hit",
            "(2) Stand",
            "(3) DoubleDown",
            "(4) Split",
            "(5) Surrender"
    );

    public Table() {

    }

    public void setup() {


    }
    //TODO Check over progress logically
    public void round() {
        boolean isRunning = true;
        while (roundIsNotOver()) {

        }
        for (Actor actor : actors) {
            actor.clearHands();
        }
    }

    public void getSelection(Actor actor) {
        CONTROL_MENU.forEach(System.out::println);

    }

    private boolean roundIsNotOver() {
        int counter = 0;
        for (Actor actor : actors) {
            if (actor.isPlaying()) {
                counter++;
            }
        }
        if (counter == 0) {
            return false;
        }
        return true;
    }

    private Actor getActivePlayer() {
        return actors.get(turn.getCounter());
    }

}
//TODO instructions research
/*
At a blackjack table, the dealer faces five to seven playing positions from behind a semicircular table.
Between one and eight standard 52-card decks are shuffled together.
To start each round, players place bets in the "betting box" at each position.
In jurisdictions allowing back betting, up to three players can be at each position.
The player whose bet is at the front of the betting box controls the position,
and the dealer consults the controlling player for playing decisions; the other bettors "play behind".
A player can usually control or bet in as many boxes as desired at a single table,
but an individual cannot play on more than one table at a time or place multiple bets within a single box.
In many U.S. casinos, players are limited to playing one to three positions at a table.
*/
