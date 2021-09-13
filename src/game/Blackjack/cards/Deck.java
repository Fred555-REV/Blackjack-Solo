package game.Blackjack.cards;

import game.Blackjack.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        int deckAmount = Validate.inputInt("How many decks? 3-6", 3, 6);
//Can create any amount of full decks of 52 total of 208
        for (int i = 0; i < deckAmount; i++) {
            for (int j = 0; j < Card.values.size(); j++) {
                for (int k = 0; k < Card.suits.size(); k++) {
//                System.out.printf("I %s | K %s\n",i,k);
                    cards.add(new Card(j, k, 1));
                }
            }
        }
        shuffle();
        System.out.println(cards);
    }

    public void shuffle() {
        cards.forEach(card -> card.move(cards.size()));
        Collections.sort(cards, Card::compareTo);
        System.out.println(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
