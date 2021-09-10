package game.Blackjack;

import game.Blackjack.cards.Card;

import java.util.Collections;
import java.util.List;

public class Display {
    public static void cards(List<Card> list) {
//        Collections.sort(list, Cards::compareTo);
//        System.out.println(Color.BLUE);
        StringBuilder cards = new StringBuilder();
        StringBuilder part1 = new StringBuilder();
        StringBuilder part2 = new StringBuilder();
        StringBuilder part3 = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).value == 10) {
                part1.append("|¯¯¯¯| ");
                part2.append("| ").append(list.get(i).value).append(" | ");
                part3.append("|____| ");
            } else {
                part1.append("|¯¯¯| ");
                part2.append("| ").append(list.get(i).value).append(" | ");
                part3.append("|___| ");
            }
        }
        part1.append("\n");
        part2.append("\n");
        part3.append("\n");
        cards.append(part1).append(part2).append(part3);
        System.out.println(cards);
//        System.out.println(Color.RESET);
    }
}
