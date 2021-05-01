package com.company;

import java.util.ArrayList;

public class Bot extends Player{


    public Bot(ArrayList<Card> playerCards, String name) {
        super(playerCards, name);
    }

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
