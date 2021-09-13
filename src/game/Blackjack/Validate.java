package game.Blackjack;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Validate {
    public static List<String> CHEATS = List.of(
            "FREDDY",
            "FRED",
            "SHACO",
            "SHAGGY"
    );

    public static int inputInt(String prompt) {
        if (!prompt.equals("")) {
            System.out.println(prompt);
        }
        boolean validOption = true;
        int input = -1;
        do {
            try {

                Scanner scan = new Scanner(System.in);
                input = scan.nextInt();

            } catch (ClassCastException | InputMismatchException cce) {
                System.out.println("Your input is invalid, please try again");
                validOption = false;
            }
        } while (!validOption);
        return input;
    }

    public static int inputInt(String prompt, int min, int max) {
        if (!prompt.equals("")) {
            System.out.println(prompt);
        }
        int input = -1;
        do {
            try {

                Scanner scan = new Scanner(System.in);
                input = scan.nextInt();

            } catch (ClassCastException | InputMismatchException cce) {
                System.out.println("Your input is invalid, please try again");
            }

        } while (input > max || input < min);
        return input;
    }
}