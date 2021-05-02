package com.company;

import java.util.ArrayList;

/**
 * Bot class
 * can play as a player by itself
 */
public class Bot extends Player{


    /**
     * same as a player
     * creates a bot player with 7 cards
     * @param playerCards initial cards
     * @param name name of bot
     */
    public Bot(ArrayList<Card> playerCards, String name) {
        super(playerCards, name);
    }

    /**
     * bot plays a card
     * @param gm game manager
     * @return selected card
     * @throws InterruptedException
     */
    @Override
    public Card playCard(GameManager gm) throws InterruptedException {
        Card selectedCard = null;
        ArrayList<Card> validCards = checkValidCards(gm);
        if(validCards.size() == 0)
        {
            System.out.println("no playable card");
            selectedCard = pickCard(gm, selectedCard);
        }
        else
        {
            selectedCard = selectCard(validCards, gm);
        }
        super.getPlayerCards().remove(selectedCard);
        return selectedCard;
    }


    /**
     * bot selects a card to play
     * @param validCards list of playable cards
     * @param gm game manager
     * @return selected card
     */
    @Override
    public Card selectCard(ArrayList<Card> validCards, GameManager gm) {
        Card selectedCard = null;

        if(validCards.size() == 1)
            selectedCard = validCards.get(0);
        else
        {
            selectedCard = randomCard(validCards);
        }
        validCards.clear();
        System.out.print("selected card: ");
        selectedCard.print();
        return selectedCard;
    }
}
