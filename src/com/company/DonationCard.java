package com.company;

import java.util.Random;

/**
 * DonationCard class
 * gives a card of player to another player
 */
public class DonationCard extends ActionCard {

    /**
     * creates a donation card
     * @param cardCharacter character of card
     * @param color color of card
     * @param score score of card
     */
    public DonationCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }


    /**
     * gives a card of player to another player
     * selected card is random
     * but if the player is human can select player
     * and if player is bot it selects everything random by itself
     * @param gm
     */
    @Override
    public void action(GameManager gm) {
        Player player = gm.getPlayers()[gm.getTurn() % gm.getNumberOfPlayers()];
        Player p2 = null;
        Card card = player.randomCard(player.getPlayerCards());
        player.removeCard(card);
        if(player instanceof Bot)
        {
            Random random = new Random();
            p2 = gm.getPlayers()[random.nextInt(gm.getNumberOfPlayers())];
            while (p2 == player)
                p2 = gm.getPlayers()[random.nextInt(gm.getNumberOfPlayers())];
            System.out.println("selected player: " + p2.getName());
        }
        else
        {
            System.out.println("choose a player");
            gm.showPlayers();
            p2 = gm.getPlayers()[gm.scanner.nextInt() - 1];
            while (p2 == player)
            {
                System.out.println("you can't choose yourself.\ntry again: ");
                p2 = gm.getPlayers()[gm.scanner.nextInt() - 1];
            }
        }
        p2.addCard(card);
        gm.setTurn(gm.updateTurn());
    }
}
