package game.Blackjack;

import game.Blackjack.cards.Deck;

public class Main {

    public static void main(String[] args) {

        Table table = new Table();
        //Assignment
        //setup creates dealer if needed/wanted and players
        //also creates the deck and for the assignment displays all cards drawn
        table.setup(5);
        System.out.println(table.getDeck().getCards().size());
    }
}
