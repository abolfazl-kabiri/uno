package com.company;

/**
 * ReverseCard class
 * change rotation direction
 */
public class ReverseCard extends ActionCard {

    /**
     * creates a reverse card
     * @param cardCharacter character of card
     * @param color color of card
     * @param score score of card
     */
    public ReverseCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    /**
     * changes rotation direction
     * @param gm game manager
     */
    @Override
    public void action(GameManager gm) {
        if(gm.getRotation().equals("clockwise"))
             gm.setRotation("anticlockwise");
        else
            gm.setRotation(("clockwise"));
        gm.setTurn(gm.updateTurn());
    }
}
