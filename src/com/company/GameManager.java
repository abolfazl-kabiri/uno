package com.company;

import java.util.Scanner;

public class GameManager {

    Scanner scanner = new Scanner(System.in);

    private ManageCards manageCards;
    private Board board;
    private int numberOfPlayers;
    private Player [] players;
    private int turn;
    private String rotation;
    private int numberOfCardsForNextPlayer;

    public GameManager()
    {
        numberOfPlayers = setNumberOfPlayers();
        players = new Player[numberOfPlayers];
        manageCards = new ManageCards();
        board = new Board(manageCards.setBoardCard());
        turn = 0;
        rotation = "clockwise";
        numberOfCardsForNextPlayer = 0;
        createPlayers();
    }

    public ManageCards getManageCards() {
        return manageCards;
    }

    private int setNumberOfPlayers()
    {
        System.out.print("enter number of players: ");
        numberOfPlayers = scanner.nextInt();
        while (numberOfPlayers > 5 || numberOfPlayers < 3)
            numberOfPlayers = scanner.nextInt();
        return numberOfPlayers;
    }

    public int getNumberOfCardsForNextPlayer() {
        return numberOfCardsForNextPlayer;
    }

    public void setNumberOfCardsForNextPlayer(int numberOfCardsForNextPlayer) {
        this.numberOfCardsForNextPlayer = numberOfCardsForNextPlayer;
    }

    private void createPlayers()
    {
        System.out.print("enter your name: ");
        String name = scanner.next();
        Player player = new Player(manageCards.setPlayerCards(), name);
        players[0] = player;

        for (int i=1; i<numberOfPlayers; i++)
        {
            Player botPlayer = new Bot(manageCards.setPlayerCards(), "bot" + i);
            players[i] = botPlayer;
        }
    }

    public Board getBoard() {
        return board;
    }

    public void showPlayers()
    {
        int counter = 1;
        for (Player player : players)
        {
            System.out.println( counter + ". " + player.getName() +
                    "\tnumber of cards: " + player.getNumberOfCards());
            counter++;
        }
    }


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getTurn() {
        return turn;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int updateTurn(int summand)
    {
        if(rotation.equals("clockwise"))
            turn += summand;
        else
            turn -= summand;

        return turn % numberOfPlayers;
    }

    public int updateTurn()
    {
        if(rotation.equals("clockwise"))
            turn++;
        else
            turn--;
        return turn % numberOfPlayers;
    }



    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }


    public boolean continueGame()
    {
        boolean continueIt = true;
        for(int i=0; i<numberOfPlayers; i++)
        {
            Player player = players[i];
            if(!(player.hasCard()))
            {
                continueIt = false;
                break;
            }
        }
        return continueIt;
    }


    public void setTurn(int turn) {
        if(turn < 0)
            turn += numberOfPlayers;
        this.turn = turn;
    }

    public void ranking()
    {
        for (Player player: players)
            player.calculateScores();
        orderPlayers();
    }

    public void orderPlayers()
    {
        for (int i=0; i<numberOfPlayers; i++)
        {
            for (int j=i; j<numberOfPlayers; j++)
            {
                if(players[i].getScore() > players[j].getScore())
                {
                    Player swap = players[i];
                    players[i] = players[j];
                    players[j] = swap;
                }
            }
        }
        printRanking();
    }

    public void printRanking()
    {
        int counter = 1;
        for(Player player: players)
        {
            System.out.println(counter + ". " + player.getName() +"\t score: " + player.getScore());
            counter++;
        }

    }

    public void putCard()
    {
        System.out.println(rotation);
        Card card = null;
        while (turn < 0)
            turn+=numberOfPlayers;
        Player player = players[turn % numberOfPlayers];
        System.out.println("turn: " + turn);
        System.out.println("player: " + player.getName());
        card = player.playCard(this);
        if(card != null)
        {
            Card oldCard = board.setBoardCard(card);
            manageCards.addCards(oldCard);
            if(card instanceof ActionCard)
                ((ActionCard) card).action(this);
            else
              turn = updateTurn();
        }
        else
           turn = updateTurn();
        System.out.println("--------------------------------------------------------------------------");
    }

}
