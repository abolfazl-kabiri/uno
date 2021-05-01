package com.company;

import java.util.Random;


public class DonationCard extends ActionCard {

    public DonationCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }


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
