package game.Blackjack.actors;

import game.Blackjack.Color;
import game.Blackjack.Validate;
import game.Blackjack.cards.DeckInterface;
import game.Blackjack.cards.PlayingCards;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor {
    // Each actor has a hand and each table has a deck
    public final String name;
    public final String color;
    protected int wallet;
    protected int activeHandCounter;
    protected List<PlayingCards> hand = new ArrayList<>();
    protected List<PlayingCards> splitHand = new ArrayList<>();
    protected int handValue;
    protected int splitValue;
    protected int betHand;
    protected int betSplit;
    protected boolean isPlaying;


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

    public abstract void getSelection(DeckInterface deck);

    abstract void hit(PlayingCards card);

    abstract void stand();

    abstract void doubleDown(PlayingCards card);

    abstract void split();

    abstract void surrender();


    //TODO hit method,


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

    public int getBet(int betNum) {
        if (betNum == 1) {
            return betHand;
        } else {
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

    public boolean hasSplit() {
        if (splitHand.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<PlayingCards> getActiveHand() {
        if (activeHandCounter == 2) {
            return splitHand;
        }
        return hand;
    }

    public void result(Boolean didWin, int betNum) {
        if (didWin) {
            gain(getBet(betNum) * 2);
        } else {
            lose(getBet(betNum));
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

    public int getWallet() {
        return wallet;
    }

    public List<PlayingCards> getHand() {
        return hand;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setHandValue() {
        int aceCount = 0;
        handValue = 0;
        for (PlayingCards card : hand) {
            handValue += card.value;
            if (card.rank.equals("A")) {
                aceCount++;
            }
        }
        if (handValue > 21) {
            for (int i = 0; i < aceCount; i++) {
                handValue -= 10;
            }
        }
    }

    public void setSplitValue() {
        int aceCount = 0;
        splitValue = 0;
        for (PlayingCards card : splitHand) {
            splitValue += card.value;
            if (card.rank.equals("A")) {
                aceCount++;
            }
        }
        if (splitValue > 21) {
            for (int i = 0; i < aceCount; i++) {
                splitValue -= 10;
            }
        }
    }

    public int getValue() {
        if (getActiveHand() == hand) {
            setHandValue();
            if (handValue > 21) {
                stand();
            }
            return handValue;
        } else {
            setSplitValue();
            if (splitValue > 21) {
                stand();
            }
            return splitValue;
        }
    }

    public int getValue(int handNum) {
        if (handNum == 1) {
            setHandValue();
            return handValue;
        } else {
            setSplitValue();
            return splitValue;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%sActor: %s,\tBets: {Main: %s\tSplit: %s}%s\nHands: {Main: %s\nSplit: %s}%s",
                Color.getColor(this), name, betHand, betSplit, Color.RESET, hand, splitHand, Color.RESET
        );
    }
}
