package game.Blackjack;

public class Test {
    public static void main(String[] args) {
        System.out.println("Test");
        Table table = new Table();
        table.setup(2);
        table.round();
    }
}
