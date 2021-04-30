package com.company;

public abstract class ActionCard extends Card {
    public ActionCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    public abstract void action(GameManager gm);
}
