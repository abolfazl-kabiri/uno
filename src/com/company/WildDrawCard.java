package com.company;

public class WildDrawCard extends ActionCard {

    public WildDrawCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    @Override
    public void action(GameManager gm) {

        int numberOfCards = gm.getNumberOfCardsForNextPlayer();
        numberOfCards += 4;
        gm.setNumberOfCardsForNextPlayer(numberOfCards);
        gm.setTurn(gm.updateTurn());
    }
}
