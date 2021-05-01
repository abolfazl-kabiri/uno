package com.company;

public class DrawCard extends ActionCard {

    public DrawCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }


    @Override
    public void action(GameManager gm) {
        int numberOfCards = gm.getNumberOfCardsForNextPlayer();
        numberOfCards += 2;
        gm.setNumberOfCardsForNextPlayer(numberOfCards);
        System.out.println("number of cards for next player: " + numberOfCards);
        gm.setTurn(gm.updateTurn());
    }
}
