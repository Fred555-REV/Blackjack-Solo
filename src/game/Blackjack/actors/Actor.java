package game.Blackjack.actors;

import game.Blackjack.Color;
import game.Blackjack.Validate;
import game.Blackjack.cards.DeckInterface;
import game.Blackjack.cards.PlayingCards;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor {
    // Each actor has a hand and each table has a deck
    protected String name;
    protected String color;
    protected int wallet;
    protected int activeHandCounter;
    protected List<PlayingCards> hand = new ArrayList<>();
    protected List<PlayingCards> splitHand = new ArrayList<>();
    protected int betHand;
    protected int betSplit;
    private boolean isPlaying;


    public Actor(String name, String color, int wallet) {
        this.name = name;
        this.color = color;
        this.wallet = wallet;
        isPlaying = true;
        activeHandCounter = 1;
    }

    public void getCard(PlayingCards playingCards) {
        hand.add(playingCards);
    }

    //TODO make a branch to change the change hand method
    // refactor everything so instead of one action per hand
    // to play each hand until a stand or end

    public void getSelection(DeckInterface deck) {
        int selection = Validate.inputInt("", 1, 5);
        switch (selection) {
            case 1:
                hit(deck.deal());
                break;
            case 2:
                stand();
                break;
            case 3:
                if (getActiveHand().size() == 2) {
                    doubleDown(deck.deal());
                } else {
                    System.out.println("Invalid Selection");
                }
                break;
            case 4:
                if (hand.get(0).rank.equals(hand.get(1).rank) && splitHand.isEmpty()) {
                    split();
                }
                break;
            case 5:
                if (getActiveHand().size() == 2) {
                    surrender();
                } else {
                    System.out.println("Invalid Selection");
                }
                break;
        }

    }

    //TODO hit method,
    public void hit(PlayingCards playingCards) {
        getActiveHand().add(playingCards);
    }

//    abstract void stand();

    public void stand() {
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

    public void doubleDown(PlayingCards playingCards) {
        if (getActiveHand().equals(hand)) {
            bet(betHand);
        } else {
            bet(betSplit);
        }
        hit(playingCards);
        stand();
    }

    //TODO split method,
    // Create two hands from a starting hand where both cards are the same value.
    //Each new hand gets another card so that the player has two starting hands. This requires an additional bet on the second hand.
    //The two hands are played out independently, and the wager on each hand is won or lost independently.
    // In the case of cards worth 10 points, some casinos only allow splitting when the cards are the same rank.
    //!!Non-controlling players can opt to put up a second bet or not. If they do not, they only get paid or lose on one of the two post-split hands.!!
    private void split() {
        splitHand.add(hand.remove(0));
        betSplit += betHand;
    }

    //TODO surrender method,
    // Forfeit half the bet and end the hand immediately.
    private void surrender() {
        if (getActiveHand().equals(hand)) {
            betHand /= 2;
        } else {
            betSplit /= 2;
        }
        stand();
    }

    //TODO where bet is called make a check for enough money
    public int bet(int amount) {
        if (getActiveHand().equals(hand)) {
            betHand += amount;
            return betHand;
        } else {
            betSplit += amount;
            return betSplit;
        }
    }

    //TODO Check over progress logically
    // make sure everything makes sense

    public void clear() {
        hand.clear();
        splitHand.clear();
        betHand = 0;
        betSplit = 0;
        activeHandCounter = 1;
        isPlaying = true;

    }

    public List<PlayingCards> getActiveHand() {
        if (activeHandCounter == 2) {
            return splitHand;
        }
        return hand;
    }

    public void result(Boolean didWin) {
        if (didWin) {
            gain(bet(0));
        } else {
            lose(bet(0));
        }

    }

    private void gain(int amount) {
        wallet += amount;
    }

    private void lose(int amount) {
        wallet -= amount;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getWallet() {
        return wallet;
    }

    public List<PlayingCards> getHand() {
        return hand;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public String toString() {
        return String.format(
                "%sActor: %s,\tBets: {Main: %s\tSplit: %s}\nHands: {Main: %s\nSplit: %s}%s",
                Color.getColor(this), name, betHand, betSplit, hand, splitHand, Color.RESET
        );
    }
}
