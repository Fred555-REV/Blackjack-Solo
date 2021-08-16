package game.Blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {

    public static int InputInt(String prompt) {
        if (!prompt.equals("")) {
            System.out.println(prompt);
        }
        Scanner scan = new Scanner(System.in);
        boolean validOption = true;
        int input = -1;
        try {

            input = scan.nextInt();

        } catch (ClassCastException | InputMismatchException cce) {
            System.out.println("Your input is invalid, please try again");
            validOption = false;
        }
        if (!validOption) {
            InputInt(prompt);
        }
        return input;
    }

}
