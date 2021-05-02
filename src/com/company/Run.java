package com.company;

import java.util.Scanner;

public class Run {

    private int gameType;
    private GameManager gm;

    public Run() {
        this.gameType = setGameType();
        gm = new GameManager(gameType);
    }


    public int setGameType()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. play with bot\n" +
                          "2. play with friends\n");
        int input = scanner.nextInt();
        while (input > 2 || input <1)
            {
                System.out.print("invalid input.\ntry again: ");
                input = scanner.nextInt();
            }
        return input;
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
