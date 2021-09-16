package game.Blackjack.cards;

import java.util.List;

public class PlayingCards {
    public static final List<String> ranks = List.of(
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
    public final String name;
    public final String rank;
    public final String suit;
    private int position;

    protected PlayingCards(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        name = String.format("%s of %s",
                rank, suit);

    }

    public PlayingCards(int rankI, int suitI, int amount) {
        this.rank = ranks.get(rankI - 1);
        this.suit = suits.get(suitI - 1);
        name = String.format("%s of %s",
                rank, suit);

    }
        //TODO value should be assigned in table
//    private int getValue(int valueI) {
//        valueI++;
//        return valueI;
//    }

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
