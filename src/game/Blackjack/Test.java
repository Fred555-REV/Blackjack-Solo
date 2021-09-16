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
