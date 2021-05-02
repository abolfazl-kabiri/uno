package com.company;

/**
 * DrawCard class
 * gives 2 cards to next player if he/she can't play card
 */
public class DrawCard extends ActionCard {

    /**
     * creates a draw card
     * @param cardCharacter character of card
     * @param color color of card
     * @param score score of card
     */
    public DrawCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    /**
     * if next player has no "7" card
     * player will receive 2 cards
     * @param gm game manager
     */
    @Override
    public void action(GameManager gm) {
        int numberOfCards = gm.getNumberOfCardsForNextPlayer();
        numberOfCards += 2;
        gm.setNumberOfCardsForNextPlayer(numberOfCards);
        System.out.println("number of cards for next player: " + numberOfCards);
        gm.setTurn(gm.updateTurn());
    }
}
