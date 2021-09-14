package game.Blackjack.cards;

import java.util.List;

public class PlayingCards {
    public static final List<String> values = List.of(
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
    public static final List<String> suits = List.of(
            "Clubs",
            "Diamonds",
            "Hearts",
            "Spades"
    );
    private String name;
    private int value;
    private String suit;
    private int position;

    protected PlayingCards(int valueI, int suitI) {
        value = getValue(valueI);
        suit = suits.get(suitI);
        name = String.format("%s of %s",
                values.get(valueI), suits.get(suitI));

    }

    public PlayingCards(int valueI, int suitI, int amount) {
        value = getValue(valueI);
        suit = suits.get(suitI);
        name = String.format("%s of %s",
                values.get(valueI), suit);

    }

    private int getValue(int valueI) {
        //TODO make logic for ace either 1/11 as value
        valueI++;
        return valueI;
    }

    public void move(int deckSize) {
            position = (int) Math.floor(Math.random() * deckSize);
    }

    public int compareTo(PlayingCards comparePlayingCards) {
        int comparage = comparePlayingCards.position;
        return this.position - comparage;
    }

    @Override
    public String toString() {
        //TODO add the card display to the to string
        return "\n" + name;
    }
}