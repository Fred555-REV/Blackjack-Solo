package game.Blackjack;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("Test");
        Table table = new Table();
        table.setup();
        table.round(2);
    }
}
/*
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
                setup();
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

 */

/*
Freddy's Plan of attack
TODO Classes
 Actor
 Cards
 CasinoDealer
 Main
 Player
 Table
 Test
 Turn
 Validation

TODO Methods

TODO table methods
 create deck
 create players/dealer
 draw
 bet
 round
 turn
 end
 results

TODO hit method,
 take another card.

TODO stand method,
 Take no more cards, end your turn

TODO doubleDown method,
 Increase the initial bet by 100% and take exactly one more card.
Some games permit the player to increase the bet by amounts smaller than 100%.

TODO split method,
 Create two hands from a starting hand where both cards are the same value.
Each new hand gets another card so that the player has two starting hands. This requires an additional bet on the second hand.
The two hands are played out independently, and the wager on each hand is won or lost independently.
 In the case of cards worth 10 points, some casinos only allow splitting when the cards are the same rank.
!!Non-controlling players can opt to put up a second bet or not. If they do not, they only get paid or lose on one of the two post-split hands.!!

TODO surrender method,
 Forfeit half the bet and end the hand immediately.

TODO research/implement insurance (optional)
 If the dealer shows an ace, an "insurance" bet is allowed. Insurance is a side bet that the dealer has a blackjack.
 The dealer asks for insurance bets before the first player plays.
Insurance bets of half the player's current bet are placed on the "insurance bar" above player's cards.
 If the dealer has a blackjack, insurance pays 2 to 1.
 In most casinos, the dealer looks at the down card and pays off or takes the insurance bet immediately.
 In other casinos, the payoff waits until the end of the play.
In face-down games, if a player has more than one hand, they are allowed to look at all their hands before deciding.
 This is the only condition where a player can look at multiple hands.






 */

