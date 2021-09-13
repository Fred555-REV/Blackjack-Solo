package game.Blackjack.actors;

import game.Blackjack.cards.Card;

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
    protected List<Card> hand = new ArrayList<>();
    protected List<Card> splitHand = new ArrayList<>();
    protected int betHand;
    protected int betSplit;
    private boolean isPlaying;


    public Actor(String name, String color, int wallet) {
        this.name = name;
        this.color = color;
        this.wallet = wallet;
        isPlaying = false;
    }

    public void getCard(Card card) {
        hand.add(card);
    }

    //TODO hit method,
    // Take another card.
    public void hit(Card card) {
        getActiveHand().add(card);
        changeHand();
    }

    //TODO stand method,
    // Take no more cards; also known as "stand pat", "stick", or "stay".
    public void stand() {
        //TODO Check over progress logically
        // make sure everything makes sense
        switch (activeHandCounter) {
            case 1:
                if (splitHand.size() == 0) {
                    isPlaying = false;
                } else activeHandCounter = 4;
                break;
            case 2:
                activeHandCounter = 3;
                break;
            case 3:
            case 4:
                isPlaying = false;
                break;
        }
    }

    //TODO doubleDown method,
    // Increase the initial bet by 100% and take exactly one more card.
    //Some games permit the player to increase the bet by amounts smaller than 100%.
    //TODO research non-controlling player
    //!!Non-controlling players may or may not double their wager, but they still only take one card.!!
    public void doubleDown() {
    }

    //TODO split method,
    // Create two hands from a starting hand where both cards are the same value.
    //Each new hand gets another card so that the player has two starting hands. This requires an additional bet on the second hand.
    //The two hands are played out independently, and the wager on each hand is won or lost independently.
    // In the case of cards worth 10 points, some casinos only allow splitting when the cards are the same rank.
    //!!Non-controlling players can opt to put up a second bet or not. If they do not, they only get paid or lose on one of the two post-split hands.!!
    public void split() {
        splitHand.add(hand.get(hand.size() - 1));
    }

    //TODO surrender method,
    // Forfeit half the bet and end the hand immediately.
    public void surrender() {
    }

    //TODO where bet is called make a check for enough money
    public void bet(int amount) {
        wallet -= amount;
    }

    //TODO research/implement insurance (optional)
    //If the dealer shows an ace, an "insurance" bet is allowed. Insurance is a side bet that the dealer has a blackjack.
    // The dealer asks for insurance bets before the first player plays.
    // Insurance bets of half the player's current bet are placed on the "insurance bar" above player's cards.
    // If the dealer has a blackjack, insurance pays 2 to 1.
    // In most casinos, the dealer looks at the down card and pays off or takes the insurance bet immediately.
    // In other casinos, the payoff waits until the end of the play.
    //In face-down games, if a player has more than one hand, they are allowed to look at all their hands before deciding.
    // This is the only condition where a player can look at multiple hands.

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getWallet() {
        return wallet;
    }

    //TODO Check over progress logically
    // make sure everything makes sense
    public void changeHand() {
        switch (activeHandCounter) {
            case 0:
                activeHandCounter = 1;
            case 1:
                //If there is a split hand change hand if not then stay the same
                if (splitHand.size() > 0) {
                    activeHandCounter = 2;
                }
                break;
            case 2:
                activeHandCounter = 1;
                break;
            default:
                activeHandCounter = 0;
        }
    }

    public void clearHands() {
        hand.removeAll(hand);
        splitHand.removeAll(splitHand);

    }

    public List<Card> getActiveHand() {
        if (activeHandCounter == 2 || activeHandCounter == 4) {
            return splitHand;
        }
        return hand;
    }

    public int getActiveHandCounter() {
        return activeHandCounter;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
