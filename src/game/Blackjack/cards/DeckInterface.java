package game.Blackjack.cards;

import java.util.List;

public interface DeckInterface {
    void createDeck();

    void shuffle();

    List<PlayingCards> getCards();

}
