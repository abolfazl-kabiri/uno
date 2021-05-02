package com.company;

import java.util.Scanner;

/**
 * GameManager class to managing the game
 * and preparing the game to get started
 */
public class GameManager {

    Scanner scanner = new Scanner(System.in);

    private ManageCards manageCards;
    private Board board;
    private int numberOfPlayers;
    private Player [] players;
    //to recognize player's turn
    private int turn;
    //rotation direction of game
    private String rotation;
    //number of cards to be given next player after "7" cards
    private int numberOfCardsForNextPlayer;
    //recognizes playing with bots or friends
    private int gameType;

    /**
     * constructor of GameManager class
     * creates a gameManager with
     */
    public GameManager()
    {
        gameType = setGameType();
        numberOfPlayers = setNumberOfPlayers();
        players = new Player[numberOfPlayers];
        manageCards = new ManageCards();
        board = new Board(manageCards.setBoardCard());
        turn = 0;
        rotation = "clockwise";
        numberOfCardsForNextPlayer = 0;
        createPlayers(gameType);
    }

    /**
     * choosing type of game
     * @return gameType field
     */
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

    /**
     * get cardManager
     * @return manageCard field
     */
    public ManageCards getManageCards() {
        return manageCards;
    }

    /**
     * set number of players
     * @return numberOfPlayers field
     */
    private int setNumberOfPlayers()
    {
        System.out.print("enter number of players: ");
        numberOfPlayers = scanner.nextInt();
        while (numberOfPlayers > 5 || numberOfPlayers < 3)
            numberOfPlayers = scanner.nextInt();
        return numberOfPlayers;
    }

    /**
     * get number of cards after putting a "7" card
     * @return numberOfCardsForNextPlayer field
     */
    public int getNumberOfCardsForNextPlayer() {
        return numberOfCardsForNextPlayer;
    }

    /**
     * changing number of cards to give bext player after putting a "7" card
     * @param numberOfCardsForNextPlayer
     */
    public void setNumberOfCardsForNextPlayer(int numberOfCardsForNextPlayer) {
        this.numberOfCardsForNextPlayer = numberOfCardsForNextPlayer;
    }

    /**
     * creating players of game
     * @param gameType to set bot or human players
     */
    private void createPlayers(int gameType)
    {
        if(gameType == 1)
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

        else
        {
            for(int i=0; i<numberOfPlayers; i++)
            {
                System.out.println("player " + (i+1) + " enter your name: ");
                String name = scanner.next();
                Player player = new Player(manageCards.setPlayerCards(), name);
                players[i] = player;
            }
        }

    }

    /**
     * getting board of the game
     * @return board field
     */
    public Board getBoard() {
        return board;
    }

    /**
     * print information of players
     * after putting a donation card
     */
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

    /**
     * get number of players
     * @return numberOfPlayers field
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * getting turn of game
     * @return turn field
     */
    public int getTurn() {
        return turn;
    }

    /**
     * getting players of the game
     * @return players field
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * to change turn after a skip card
     * @param summand to recognize next player
     * @return turn field
     */
    public int updateTurn(int summand)
    {
        if(rotation.equals("clockwise"))
            turn += summand;
        else
            turn -= summand;

        return turn % numberOfPlayers;
    }

    /**
     * change turn after non"7" cards
     * @return turn field
     */
    public int updateTurn()
    {
        if(rotation.equals("clockwise"))
            turn++;
        else
            turn--;
        return turn % numberOfPlayers;
    }

    /**
     * get rotation direction
     * @return rotation field
     */
    public String getRotation() {
        return rotation;
    }

    /**
     * changing rotation direction after playing a reverse card
     * @param rotation new rotation
     */
    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    /**
     * checkin whether game continues or not
     * @return continue or not condition
     */
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

    /**
     * calculating scores of each player after end of game loop
     */
    public void ranking()
    {
        for (Player player: players)
            player.calculateScores();
        orderPlayers();
    }

    /**
     * order players from least point to most
     */
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

    /**
     * printing ranking of game
     */
    public void printRanking()
    {
        int counter = 1;
        for(Player player: players)
        {
            System.out.println(counter + ". " + player.getName() +"\t score: " + player.getScore());
            counter++;
        }

    }

    /**
     * player can put card in it's turn
     * @throws InterruptedException
     */
    public void putCard() throws InterruptedException {
        Card card = null;
        while (turn < 0)
            turn+=numberOfPlayers;
        Thread.sleep(1000);
        Player player = players[turn % numberOfPlayers];
        System.out.println("player: " + player.getName());
        card = player.playCard(this);
        if(card != null)
        {
            Thread.sleep(1000);
            Card oldCard = board.setBoardCard(card);
            manageCards.addCards(oldCard);
            if(card instanceof ActionCard)
            {
                if(!(player.hasCard()) && (card instanceof MoveAgainCard || card instanceof DonationCard || card instanceof WildCard))
                    turn = updateTurn();
                else
                     ((ActionCard) card).action(this);
            }

            else
              turn = updateTurn();
        }
        else
           turn = updateTurn();
        System.out.println("--------------------------------------------------------------------------");
    }

}
