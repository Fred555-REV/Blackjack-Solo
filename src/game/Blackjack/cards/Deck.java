package game.Blackjack.cards;

import game.Blackjack.Validate;

import java.util.ArrayList;
import java.util.List;

public class Deck implements DeckInterface {
    private List<PlayingCards> cards = new ArrayList<>();

    public Deck() {


    }

    public void createDeck() {
        int deckAmount = Validate.inputInt("How many decks? 3-6", 3, 6);
//Can create any amount of full decks of 52 total of 208
        for (int i = 0; i < deckAmount; i++) {
            for (String rank : PlayingCards.ranks) {
                for (String suit : PlayingCards.suits) {
                    cards.add(new PlayingCards(rank, suit));
                }
            }
        }

    }

    public void shuffle() {
        cards.forEach(card -> card.move(cards.size()));
        cards.sort(PlayingCards::compareTo);
//        System.out.println(cards);
    }

    @Override
    public PlayingCards deal() {
        return cards.remove(cards.size() - 1);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards + "}";
    }
}
