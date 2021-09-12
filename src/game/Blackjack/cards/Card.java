package game.Blackjack.cards;

import java.util.List;

public class Card {
    private List<String> names = List.of(
            "A",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "J",
            "Q",
            "K"
    );
    private List<String> suits = List.of(
            "Clubs",
            "Diamonds",
            "Hearts",
            "Spades"
    );
    private int value;

    public Card() {

    }

    @Override
    public String toString() {
        //TODO add the card display to the to string
        return "";
    }
}
