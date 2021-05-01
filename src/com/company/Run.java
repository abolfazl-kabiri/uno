package com.company;

public class Run {

    GameManager gm;

    public Run() {
        gm = new GameManager();
    }

    public void startGame() throws InterruptedException {
        System.out.println();
        System.out.println();
        while (gm.continueGame())
        {
            System.out.println(gm.getRotation());
            System.out.print("card on the board:\t");
            gm.getBoard().getBoardCard().print();
            gm.putCard();
        }
        gm.ranking();
    }
}
