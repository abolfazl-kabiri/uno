package com.company;

/**
 * WildDrawCard class
 * if next player can't play any card
 * he/she will receive 4 cards
 */
public class WildDrawCard extends ActionCard {

    /**
     * creates a wildDraw card
     * @param cardCharacter character of card
     * @param color color of card
     * @param score score of card
     */
    public WildDrawCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    /**
     * if next player can't play any card
     * he/she will receive 4 cards
     * @param gm game manager
     */
    @Override
    public void action(GameManager gm) {

        int numberOfCards = gm.getNumberOfCardsForNextPlayer();
        numberOfCards += 4;
        gm.setNumberOfCardsForNextPlayer(numberOfCards);
        System.out.println("number of cards for next player: " + numberOfCards);
        gm.setTurn(gm.updateTurn());
    }
}
