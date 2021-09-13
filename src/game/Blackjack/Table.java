package game.Blackjack;

import game.Blackjack.actors.Actor;
import game.Blackjack.actors.CasinoDealer;
import game.Blackjack.actors.Player;
import game.Blackjack.cards.Card;
import game.Blackjack.cards.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Table {
    //TODO test single large deck vs multiple normal decks
    //ie. 208 cards in one deck shuffled or 52 cards in 4 decks shuffled and changing decks
    private static final Scanner scan = new Scanner(System.in);
    private Deck deckC;
    private List<Card> deck = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();
    private Turn turn;
    private final List<String> CONTROL_MENU = List.of(
            "(1) Hit",
            "(2) Stand",
            "(3) DoubleDown",
            "(4) Split",
            "(5) Surrender"
    );

    public Table() {
        //Assignment
        setup();
        deckC = new Deck();
        deckC.shuffle();
        draw(5);
        for(Actor actor: actors){
            System.out.println(actor.getName());
            System.out.println(actor.getActiveHand());
        }

//        setup();
//        createDeck();
    }

    public Table(int x, int y, int z, String word) {
        if (x == 5 && y == x && z == y && word.equals("ChickEn")) {
            System.out.println("Hello Freddy");

            //TODO make test controls
//            int selection = Validate.inputInt("What do you want to test?");
//            switch (selection) {
//                default:
//                    System.out.println("Nice");
//                    break;
//            }
            setup();
            deckC = new Deck();
            deckC.shuffle();
            draw(5);
            for(Actor actor: actors){
                System.out.println(actor.getName());
                System.out.println(actor.getActiveHand());
            }

        } else {
            System.out.println("BEGONE");
            System.exit(555);
        }
    }

    //TODO idk if I want a deck class or a list of cards as the deck
    public void createDeck() {

        int deckAmount = Validate.inputInt("How many decks?", 3, 6);
//Can create any amount of full decks of 52 total of 208
        for (int i = 0; i < deckAmount; i++) {
            for (int j = 0; j < Card.values.size(); j++) {
                for (int k = 0; k < Card.suits.size(); k++) {
//                System.out.printf("I %s | K %s\n",i,k);
                    deck.add(new Card(j, k, 1));
                }
            }

        }
//        int count = 0;
//        for (Card card : deck) {
//            System.out.println(card + "   " + (count++));
//
//        }
        //TODO use remove to add the card to each player it returns the card while removing
        // no need to do switcheroo
//        System.out.println(deck.remove(deck.size() - 1));
//        System.out.println(deck.get(deck.size() - 1));
//        Card card = deck.remove(deck.size() - 1);
//        System.out.println(card);
//        System.out.println(deck.get(deck.size() - 1));
    }

    public void setup() {
        System.out.println("Welcome To Blackjack");

        int leader = Validate.inputInt("Will there be a dealer? (1) Yes\t (2) No", 1, 2);
        if (leader == 1) {
            System.out.println("Enter Name: ");
            String name = scan.next();
            if (Validate.CHEATS.contains(name)) {
                actors.add(new CasinoDealer(name, "Cyan", 500_00));
            } else {
                actors.add(new CasinoDealer(name, "White", 300_00));
            }
        }

        int playerAmount = Validate.inputInt("How many players will be playing?", 1, 5);
        for (int i = 0; i < playerAmount; i++) {
            addPlayer();
        }
    }

    private void draw(int drawAmount) {
        for (int i = 0; i < drawAmount; i++) {
            for (Actor actor : actors) {
                System.out.println(actor.getName());
                System.out.println(deckC);
                List<Card> cards = deckC.getCards();
                actor.getCard(cards.remove((cards.size() - 1)));
            }
        }
    }

    //TODO Check over progress logically
    public void round() {
        System.out.println("Drawing Cards...");
        draw(2);

        while (roundIsNotOver()) {
            //Player plays hand then changes hand
            getSelection(getActivePlayer());
            getActivePlayer().changeHand();
            if (getActivePlayer().getActiveHandCounter() != 0) {
                getSelection(getActivePlayer());
                getActivePlayer().changeHand();
            }

        }
        for (Actor actor : actors) {
            actor.clearHands();
        }
    }

    public void getSelection(Actor actor) {
        CONTROL_MENU.forEach(System.out::println);
        int selection = Validate.inputInt("", 1, 1);
        switch (selection) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }

    }

    private boolean roundIsNotOver() {
        int counter = 0;
        for (Actor actor : actors) {
            if (actor.isPlaying()) {
                counter++;
            }
        }
        if (counter == 0) {
            return false;
        }
        return true;
    }

    private Actor getActivePlayer() {
        return actors.get(turn.getCounter());
    }


    private void displayActivePlayer() {
        System.out.printf("%sIt is %s's turn\n"
                , Color.getColor(getActivePlayer()),
                getActivePlayer().getName());

        //TODO make a display active hand method

    }

    private void addPlayer() {
        System.out.println("Enter Name: ");
        String name = scan.next();
        System.out.println("Enter Color: ");
        String color = scan.next();
        actors.add(new Player(name, color, 50_00));
    }

}
//TODO instructions research
/*
At a blackjack table, the dealer faces five to seven playing positions from behind a semicircular table.
Between one and eight standard 52-card decks are shuffled together.
To start each round, players place bets in the "betting box" at each position.
In jurisdictions allowing back betting, up to three players can be at each position.
The player whose bet is at the front of the betting box controls the position,
and the dealer consults the controlling player for playing decisions; the other bettors "play behind".
A player can usually control or bet in as many boxes as desired at a single table,
but an individual cannot play on more than one table at a time or place multiple bets within a single box.
In many U.S. casinos, players are limited to playing one to three positions at a table.
*/
