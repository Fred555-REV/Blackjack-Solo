package game.Blackjack;

import game.Blackjack.actors.Actor;
import game.Blackjack.actors.CasinoDealer;
import game.Blackjack.actors.Player;
import game.Blackjack.cards.CheatDeck;
import game.Blackjack.cards.DeckInterface;
import game.Blackjack.cards.PlayingCards;
import game.Blackjack.cards.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Table {
    private static final Scanner scan = new Scanner(System.in);
    private DeckInterface deck;
    private List<Actor> actors = new ArrayList<>();
    private CasinoDealer dealer;
    private Turn turn = new Turn(10);   //Right now max turns do nothing
    private final List<String> CONTROL_MENU = List.of(
            "(1) Hit",
            "(2) Stand",
            "(3) DoubleDown",
            "(4) Split",
            "(5) Surrender"
    );

    public Table() {
        setDeck();
    }

    private void setDeck() {
        System.out.println("Welcome To Blackjack");
        System.out.println("press enter to continue");
        String selection = scan.nextLine();
        if (selection.toLowerCase(Locale.ROOT).equals("chicken")) {
            deck = new CheatDeck();
        } else {
            deck = new Deck();
        }
    }

    public void setup() {

        createDealer();

        createPlayers();
        //deck creation happens at setUp for now
        deck.createDeck();
        deck.shuffle();

    }

    private void createPlayers() {
        int playerAmount = Validate.inputInt("How many players will be playing?", 1, 5);
        for (int i = 0; i < playerAmount; i++) {
            addPlayer();
        }
    }

    private void createDealer() {
        if (Validate.inputInt("Will there be a dealer? (1) Yes\t (2) No", 1, 2) == 1) {
            System.out.println("Enter Name: ");
            String name = scan.next();
            if (Validate.CHEATS.contains(name)) {
                actors.add(new CasinoDealer(name, "Black", 500_00));
            } else {
                actors.add(new CasinoDealer(name, "Black", 300_00));
            }
        }
    }

    private void draw(int drawAmount) {
        for (int i = 0; i < drawAmount; i++) {
            for (Actor actor : actors) {
//                System.out.println(actor.getName());
//                System.out.println(deckC);
                actor.getCard(getTopCard());
            }
        }
    }

    //TODO Check over progress logically
    // at some point in the round there should be a bet method
    // probably everyone bets before they play instead of everyone betting and playing a turn
    public void round(int drawAmount) {
        System.out.println("Drawing Cards...");
        draw(drawAmount);
        bet();
        while (roundIsNotOver()) {
            turn();
            turn.pass(actors);
        }
        displayActors();
        clearTable();
        if (Validate.inputInt("(1)Play Again\n(2)Exit", 1, 2) == 1) {
            round(drawAmount);
        }
    }

    private void turn() {
        //Player plays hand then changes hand
        //The logic for if there is no other hand is in changehand method in actor
//        getSelection(getActivePlayer());
//        getActivePlayer().changeHand();
//        if (getActivePlayer().getActiveHandCounter() != 0) {
//            getSelection(getActivePlayer());
//            getActivePlayer().changeHand();
//        }
        while (getActivePlayer().isPlaying()) {
            getSelection(getActivePlayer());
        }

    }

    //TODO at the end before clearing get results
    // everyone gets compared to dealer
    private void displayActors() {
        actors.forEach(System.out::println);
    }

    private void clearTable() {
        for (Actor actor : actors) {
            actor.clear();
        }
    }


    private void bet() {
        for (Actor actor : actors) {
            //TODO try bet here

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
        return deck.deal();

    }

    public DeckInterface getDeck() {
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
