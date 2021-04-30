package com.company;

public class ReverseCard extends ActionCard {

    public ReverseCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    @Override
    public void action(GameManager gm) {
        if(gm.getRotation().equals("clockwise"))
             gm.setRotation("anticlockwise");
        else
            gm.setRotation(("clockwise"));
        gm.setTurn(gm.updateTurn());
    }
}
