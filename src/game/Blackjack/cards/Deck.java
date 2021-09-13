package game.Blackjack.cards;

import game.Blackjack.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<PlayingCards> playingCards = new ArrayList<>();

    public Deck() {


    }

    public void createDeck(){
        int deckAmount = Validate.inputInt("How many decks? 3-6", 3, 6);
//Can create any amount of full decks of 52 total of 208
        for (int i = 0; i < deckAmount; i++) {
            for (int j = 0; j < PlayingCards.values.size(); j++) {
                for (int k = 0; k < PlayingCards.suits.size(); k++) {
//                System.out.printf("I %s | K %s\n",i,k);
                    playingCards.add(new PlayingCards(j, k,1));
                }
            }
        }

    }

    public void shuffle() {
        playingCards.forEach(card -> card.move(playingCards.size()));
        Collections.sort(playingCards, PlayingCards::compareTo);
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
