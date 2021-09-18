package game.Blackjack.actors;

import game.Blackjack.Color;
import game.Blackjack.cards.DeckInterface;
import game.Blackjack.cards.PlayingCards;

public class AutoDealer extends Actor {
    public AutoDealer(String name, String color, int wallet) {
        super(name, color, wallet);
        isPlaying = true;
    }

    @Override
    public void getSelection(DeckInterface deck) {
        if (getValue(1) >= 17) {
            stand();
        } else {
            hit(deck.deal());
        }
    }

    @Override
    void hit(PlayingCards card) {
        hand.add(card);
    }

    @Override
    void stand() {
        isPlaying = false;
    }

    @Override
    void doubleDown(PlayingCards card) {

    }

    @Override
    void split() {

    }

    @Override
    void surrender() {

    }

    @Override
    public String toString() {
        return String.format(
                "%sDealer: %s\nHand: %s%s",
                Color.getColor(this), name, hand, Color.RESET
        );
    }
}
