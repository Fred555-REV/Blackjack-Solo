package game.Blackjack.cards;

import game.Blackjack.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements DeckInterface {
    private List<PlayingCards> playingCards = new ArrayList<>();

    public Deck() {


    }

    public void createDeck() {
        int deckAmount = Validate.inputInt("How many decks? 3-6", 3, 6);
//Can create any amount of full decks of 52 total of 208
        for (int i = 0; i < deckAmount; i++) {
            for (String rank : PlayingCards.ranks) {
                for (String suit:PlayingCards.suits) {
                    playingCards.add(new PlayingCards(rank, suit, 1));
                }
            }
        }

    }

    public void shuffle() {
        playingCards.forEach(card -> card.move(playingCards.size()));
        playingCards.sort(PlayingCards::compareTo);
        System.out.println(playingCards);
    }

    public List<PlayingCards> getCards() {
        return playingCards;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + playingCards + "}";
    }
}
