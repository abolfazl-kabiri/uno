package com.company;

/**
 * MoveAgainCard class
 * player moves again
 */
public class MoveAgainCard extends ActionCard{

    /**
     * creates a MoveAgain card
     * @param cardCharacter character of card
     * @param color color of card
     * @param score score of card
     */
    public MoveAgainCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    /**
     * the player plays again
     * @param gm game manager
     */
    @Override
    public void action(GameManager gm) {
        gm.updateTurn(0);
        System.out.println("it's your turn again");
    }
}
