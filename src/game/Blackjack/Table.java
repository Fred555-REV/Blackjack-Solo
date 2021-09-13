package game.Blackjack;

import game.Blackjack.actors.Actor;
import game.Blackjack.actors.CasinoDealer;
import game.Blackjack.actors.Player;
import game.Blackjack.cards.PlayingCards;
import game.Blackjack.cards.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Table {
    //TODO test single large deck vs multiple normal decks
    //ie. 208 cards in one deck shuffled or 52 cards in 4 decks shuffled and changing decks
    private static final Scanner scan = new Scanner(System.in);
    private Deck deck;
//    private List<Card> deck = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();
    private Turn turn = new Turn(10);   //Right now max turns do nothing
    private final List<String> CONTROL_MENU = List.of(
            "(1) Hit",
            "(2) Stand",
            "(3) DoubleDown",
            "(4) Split",
            "(5) Surrender"
    );

    public Table() {
        deck = new Deck();
    }

    public Table(int x, int y, int z, String word) {
        //Freddy's Testing Table
        if (x == 5 && y == x && z == y && word.equals("ChickEn")) {
            System.out.println("Hello Freddy");

            //TODO make test controls
//            int selection = Validate.inputInt("What do you want to test?");
//            switch (selection) {
//                default:
//                    System.out.println("Nice");
//                    break;
//            }

//      Test 2
            System.out.println(Color.GREEN + "Setting Up" + Color.RESET);
            setup(2);
            System.out.println(Color.GREEN + "Drawing 2" + Color.RESET);
            draw(2);
            System.out.println(Color.GREEN + "Starting test run" + Color.RESET);
            //added roundisnotover to test if stand/surrender is working
            while (roundIsNotOver()) {
                // Hit is working fine
                //
                getSelection(getActivePlayer());
                turn.pass(actors);
            }

        } else {
            System.out.println("BEGONE");
            System.exit(555);
        }
    }

    public void setup(int drawAmount) {
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
        //deck creation happens at setUp for now
        deck.createDeck();
        deck.shuffle();

        draw(drawAmount);
        if (drawAmount == 5) {
            for (Actor actor : actors) {
                System.out.println(Color.getColor(actor) + actor.getName());
                System.out.println(actor.getActiveHand());
                System.out.print(Color.RESET);
            }
        }

    }

    private void draw(int drawAmount) {
        for (int i = 0; i < drawAmount; i++) {
            for (Actor actor : actors) {
//                System.out.println(actor.getName());
//                System.out.println(deckC);
                List<PlayingCards> playingCards = deck.getCards();
                actor.getCard(playingCards.remove((playingCards.size() - 1)));
            }
        }
    }

    //TODO Check over progress logically
    // at some point in the round there should be a bet method
    // probably everyone bets before they play instead of everyone betting and playing a turn
    public void round() {
        System.out.println("Drawing Cards...");
        draw(2);

        //This checks every turn if all the people have stopped playing
        // with either stand, double down, or surrender
        while (roundIsNotOver()) {
            for (Actor actor : actors) {
                //TODO try bet here

            }
            //Player plays hand then changes hand
            //The logic for if there is no other hand is in changehand method in actor
            getSelection(getActivePlayer());
            getActivePlayer().changeHand();
            if (getActivePlayer().getActiveHandCounter() != 0) {
                getSelection(getActivePlayer());
                getActivePlayer().changeHand();
            }

        }
        for (Actor actor : actors) {
            actor.clear();
        }
    }

    public void getSelection(Actor actor) {
        displayActivePlayer();
        CONTROL_MENU.forEach(System.out::println);
        int selection = Validate.inputInt("", 1, 10);
        switch (selection) {
            case 1:
                hit();
                break;
            case 2:
                stand();
                break;
            case 3:
                doubleDown();
                break;
            case 4:
                split();
                break;
            case 5:
                surrender();
                break;
        }

    }

    private void hit() {
        getActivePlayer().hit(getTopCard());
    }

    private void stand() {
        getActivePlayer().stand();
    }

    private void doubleDown() {
        getActivePlayer().doubleDown(getTopCard());
    }

    private void split() {
        getActivePlayer().split();

    }

    private void surrender() {
        getActivePlayer().surrender();

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
        //At the moment error happens here after drawing
        return actors.get(turn.getCounter());
    }


    private void displayActivePlayer() {
        System.out.printf("%sIt is %s's turn\n"
                , Color.getColor(getActivePlayer()),
                getActivePlayer().getName());

        //TODO make a display active hand method
        String hand;
        if (getActivePlayer().getActiveHand() == getActivePlayer().getHand()) {
            hand = "Main Hand";
        } else {
            hand = "Split Hand";
        }
        System.out.printf("Using %s\n%s\n", hand, getActivePlayer().getActiveHand());

    }

    private void addPlayer() {
        System.out.println("Enter Name: ");
        String name = scan.next();
        System.out.println("Enter Color: ");
        String color = scan.next();
        actors.add(new Player(name, color, 50_00));
    }

    private PlayingCards getTopCard() {
        return deck.getCards().remove(deck.getCards().size() - 1);

    }

    public Deck getDeck() {
        return deck;
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
