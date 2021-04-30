package com.company;

public class SkipCard extends ActionCard{

    public SkipCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    @Override
    public void action(GameManager gm) {
        gm.setTurn(gm.updateTurn(2));
    }

}
