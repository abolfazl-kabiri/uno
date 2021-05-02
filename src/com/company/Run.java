package com.company;

import java.util.Scanner;

/**
 * Run class to run the game loop
 */
public class Run {

    private GameManager gm;

    /**
     * the constructor of Run class
     */
    public Run() {
        gm = new GameManager();
    }

    /**
     * starting game
     * @throws InterruptedException
     */
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
