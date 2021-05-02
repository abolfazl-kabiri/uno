package com.company;

/**
 * ActionCard class
 * identifies action cards' identity
 */
public abstract class ActionCard extends Card {
    /**
     * creates an action card
     * @param cardCharacter character of card
     * @param color color of card
     * @param score score of card
     */
    public ActionCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    /**
     * action of action card
     * @param gm game manager
     */
    public abstract void action(GameManager gm);
}
