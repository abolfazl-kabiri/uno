package com.company;
import java.util.Random;

/**
 * WildCard class
 * player can change color of board
 */
public class WildCard extends ActionCard {

    /**
     * creates a wild card
     * @param cardCharacter character of card
     * @param color color of card
     * @param score score of card
     */
    public WildCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    /**
     * player can change color of board
     * @param gm game manager
     */
    @Override
    public void action(GameManager gm) {
        String[] colors = {"black", "blue", "red", "green"};
        Player player = gm.getPlayers()[gm.getTurn() % gm.getNumberOfPlayers()];
        int input = 5;
        if(player instanceof Bot)
        {
            Random random = new Random();
            input = random.nextInt(4) +1;
        }
        else
        {
            System.out.println("1. black\n" +
                    "2. blue\n" +
                    "3. red\n" +
                    "4. green\n");
            input = gm.scanner.nextInt();
            while (input < 1 || input > 4)
            {
                System.out.println("invalid. try again.");
                input = gm.scanner.nextInt();
            }
        }
        System.out.println("selected color: " + colors[input-1]);
        gm.getBoard().setBoardColor(colors[input-1]);
        gm.setTurn(gm.updateTurn());
    }
}
