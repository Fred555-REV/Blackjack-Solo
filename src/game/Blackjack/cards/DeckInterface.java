package game.Blackjack.cards;

import java.util.List;

public interface DeckInterface {
    PlayingCards deal();
    void createDeck();

    void shuffle();

}
