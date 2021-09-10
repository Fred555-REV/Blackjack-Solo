package game.Blackjack.actors;

import game.Blackjack.cards.Card;

import java.util.List;

public abstract class Actor {
    // Each actor has a hand and each table has a deck
    protected String name;
    protected String color;
    protected List<Card> hand;

    //TODO hit method,
    // Take another card.
    abstract void hit();

    //TODO stand method,
    // Take no more cards; also known as "stand pat", "stick", or "stay".
    abstract void stand();

    //TODO doubleDown method,
    // Increase the initial bet by 100% and take exactly one more card.
    //Some games permit the player to increase the bet by amounts smaller than 100%.
    //TODO research non-controlling player
    //!!Non-controlling players may or may not double their wager, but they still only take one card.!!
    abstract void doubleDown();

    //TODO split method,
    // Create two hands from a starting hand where both cards are the same value.
    //Each new hand gets another card so that the player has two starting hands. This requires an additional bet on the second hand.
    //The two hands are played out independently, and the wager on each hand is won or lost independently.
    // In the case of cards worth 10 points, some casinos only allow splitting when the cards are the same rank.
    //!!Non-controlling players can opt to put up a second bet or not. If they do not, they only get paid or lose on one of the two post-split hands.!!
    abstract void split();

    //TODO surrender method,
    // Forfeit half the bet and end the hand immediately.
    abstract void surrender();

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
}
