package game.Blackjack.actors;

import game.Blackjack.cards.DeckInterface;
import game.Blackjack.cards.PlayingCards;

public class CasinoDealer extends Actor {


    public CasinoDealer(String name, String color, int wallet) {
        super(name, color, wallet);
    }

    @Override
    public void getSelection(DeckInterface deck) {

    }

    @Override
    void hit(PlayingCards card) {

    }

    @Override
    void stand() {

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

}
