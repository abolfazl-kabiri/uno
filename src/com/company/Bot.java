package com.company;

import java.util.ArrayList;

public class Bot extends Player{

    int score;
    int numberOfCards;
    public Bot(ArrayList<Card> playerCards, String name) {
        super(playerCards, name);
        score = 0;
        numberOfCards = 7;
    }

    @Override
    public Card selectCard(ArrayList<Card> validCards, GameManager gm) {
        Card selectedCard = null;
        System.out.println("playable cards: ");
        printCards(validCards);
        if(validCards.size() == 1)
            selectedCard = validCards.get(0);
        else
        {
            selectedCard = randomCard(validCards);
        }
        validCards.clear();
        System.out.println("selected card: " + selectedCard.getCardCharacter() +" " + selectedCard.getColor());
        return selectedCard;
    }
}
