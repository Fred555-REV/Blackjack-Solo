package game.Blackjack.actors;

import game.Blackjack.Color;
import game.Blackjack.cards.PlayingCards;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor {
    // Each actor has a hand and each table has a deck
    protected String name;
    protected String color;
    protected int wallet;
    protected int activeHandCounter;// 0 is done playing       1 and 3 is hand 1       2 and 4 is hand 2
    // 1 is hand if there
    //if splitHand is not empty activeHand changes from 1 and 2
    //if you stand while splitHand is active activeHand stays at 3
    //if you stand while hand is active and splitHand is nonEmpty activeHand stays at 0
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

    //TODO hit method,
    // Take another card.
    public void hit(PlayingCards playingCards) {
        getActiveHand().add(playingCards);
    }

    //TODO stand method,
    // Take no more cards; also known as "stand pat", "stick", or "stay".
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

    //TODO doubleDown method,
    // Increase the initial bet by 100% and take exactly one more card.
    //Some games permit the player to increase the bet by amounts smaller than 100%.
    //TODO research non-controlling player
    //!!Non-controlling players may or may not double their wager, but they still only take one card.!!
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
    public void split() {
        if (hand.get(0) == hand.get(1)) {
            splitHand.add(hand.remove(0));
        }
    }

    //TODO surrender method,
    // Forfeit half the bet and end the hand immediately.
    public void surrender() {
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
        hand.removeAll(hand);
        splitHand.removeAll(splitHand);
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

    public void gain(int amount) {
        wallet += amount;
    }

    public void lose(int amount) {
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


    public int getActiveHandCounter() {
        return activeHandCounter;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public String toString() {
        return String.format(
                "%sActor: %s,\tBets: {Main: %s\tSplit: %s}\nHands: {Main: %s\nSplit: %s}%s",
                Color.getColor(this),name,betHand,betSplit,hand,splitHand,Color.RESET
                );
    }
}
