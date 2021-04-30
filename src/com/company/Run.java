package com.company;

public class Run {

    GameManager gm;

    public Run() {
        gm = new GameManager();
    }

    public void startGame()
    {
        System.out.println("game started");
        while (gm.continueGame())
        {
            System.out.print("card on the board:\t");
            gm.getBoard().getBoardCard().print();
            gm.putCard();
        }
        gm.ranking();
    }
}
