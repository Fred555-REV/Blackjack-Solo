package game.Blackjack.cards;

import game.Blackjack.Validate;

import java.util.ArrayList;
import java.util.List;

public class CheatDeck implements DeckInterface {
    private List<PlayingCards> cards = new ArrayList<>();

    @Override
    public void createDeck() {

    }

    @Override
    public void shuffle() {

    }

    @Override
    public PlayingCards deal() {
        return new PlayingCards(
                Validate.inputInt(PlayingCards.ranks.toString(),1,PlayingCards.ranks.size()),
                Validate.inputInt(PlayingCards.suits.toString(),1,PlayingCards.suits.size()),
                1
        );

    }
}
