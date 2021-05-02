package com.company;

/**
 * SkipCard class
 * next player can't play
 */
public class SkipCard extends ActionCard{

    /**
     * creates a skip card
     * @param cardCharacter character of card
     * @param color color of card
     * @param score score of card
     */
    public SkipCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    /**
     * next player can't move
     * @param gm game manager
     */
    @Override
    public void action(GameManager gm) {
        gm.setTurn(gm.updateTurn(2));
    }

}
