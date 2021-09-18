package game.Blackjack.cards;

import game.Blackjack.Color;

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
            "C",
            "D",
            "H",
            "S"
    );
    public final String name;
    public final String rank;
    public final String suit;
    public final String color;
    public final int value;
    private int position;

    protected PlayingCards(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        value = getValue(rank);
        color = getColor(suit);
        name = String.format("%s%s of %s%s",
                Color.getColor(color), rank, suit, Color.RESET);

    }

    public PlayingCards(int rankI, int suitI, int amount) {
        rank = ranks.get(rankI - 1);
        suit = suits.get(suitI - 1);
        value = rankI;
        color = getColor(suit);
        name = String.format("%s%s of %s%s",
                Color.getColor(color), rank, suit, Color.RESET);

    }

    private int getValue(String rank) {
        switch (rank) {
            case "A":
                return 11;
            case "J":
            case "Q":
            case "K":
                return 10;
            default:
                return Integer.parseInt(rank);
        }
    }

    private String getColor(String suit) {
        switch (suit) {
            case "C":
            case "S":
                return "Black";
            case "H":
            case "D":
                return "Red";
            default:
                return "White";
        }
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
