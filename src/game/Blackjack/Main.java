package game.Blackjack;

import game.Blackjack.cards.Deck;

public class Main {

    public static void main(String[] args) {

        Table table = new Table();
        table.setup();
        table.round(2);
    }
}
