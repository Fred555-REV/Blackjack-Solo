package game.Blackjack.actors;

import game.Blackjack.Validate;
import game.Blackjack.cards.DeckInterface;
import game.Blackjack.cards.PlayingCards;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    public Player(String name, String color, int currency) {
        super(name, color, currency);
    }

    @Override
    public void getSelection(DeckInterface deck) {
        int selection = Validate.inputInt("", 1, 5);
        switch (selection) {
            case 1:
                hit(deck.deal());
                getValue();
                break;
            case 2:
                stand();
                break;
            case 3:
                doubleDown(deck.deal());
                break;
            case 4:
                split();
                break;
            case 5:
                surrender();
                break;
        }

    }

    @Override
    protected void hit(PlayingCards card) {
        getActiveHand().add(card);
    }

    //    abstract void stand();
    @Override
    protected void stand() {
        //TODO Check over progress logically
        // make sure everything makes sense
        if (splitHand.isEmpty()) {
            isPlaying = false;
        } else if (activeHandCounter == 1) {
            activeHandCounter = 2;
        } else {
            isPlaying = false;
        }

    }

    @Override
    protected void doubleDown(PlayingCards playingCards) {
        if (getActiveHand().size() == 2) {
            if (getActiveHand().equals(hand)) {
                bet(betHand);
            } else {
                bet(betSplit);
            }
            hit(playingCards);
            stand();
        } else {
            System.out.println("Invalid Selection");
        }
    }

    @Override
    protected void split() {
        if (hand.get(0).rank.equals(hand.get(1).rank) && splitHand.isEmpty()) {
            splitHand.add(hand.remove(0));
            betSplit += betHand;
        }
    }

    @Override
    protected void surrender() {
        if (getActiveHand().size() == 2) {
            if (getActiveHand().equals(hand)) {
                betHand /= 2;
            } else {
                betSplit /= 2;
            }
            stand();
        } else {
            System.out.println("Invalid Selection");
        }
    }
}
