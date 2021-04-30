package com.company;

public class MoveAgainCard extends ActionCard{

    public MoveAgainCard(String cardCharacter, String color, int score) {
        super(cardCharacter, color, score);
    }

    @Override
    public void action(GameManager gm) {
        gm.updateTurn(0);
        System.out.println("it's your turn again");
    }
}
