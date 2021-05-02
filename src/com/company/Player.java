package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Player class
 * identifies player identity
 */
public class Player {

    private String name;
    private int score;
    private ArrayList<Card> playerCards;

    /**
     * constructor of Player class
     * creates a player with 7 cards
     * @param playerCards initial cards
     * @param name name of player
     */
    public Player(ArrayList<Card> playerCards, String name) {
        this.playerCards = playerCards;
        this.name = name;
        score = 0;
    }

    /**
     * get cards of player
     * @return playerCards field
     */
    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    /**
     * get score of player
     * @return score field
     */
    public int getScore() {
        return score;
    }

    /**
     * get name of player
     * @return name field
     */
    public String getName() {
        return name;
    }

    /**
     * if player can't play any card
     * receives a single card
     * @param card a new card
     */
    public void addCard(Card card)
    {
        playerCards.add(card);
    }

    /**
     * if player plays a card
     * the card removes from player's cards
     * @param card played card
     */
    public void removeCard(Card card)
    {
        playerCards.remove(card);
    }

    /**
     * calculate score of player
     */
    public void calculateScores()
    {
        for (Card card: playerCards)
            score += card.getScore();
    }

    /**
     * check whether the player has card or not
     * @return
     */
    public boolean hasCard()
    {
        if(playerCards.size() == 0)
            return false;
        else
            return true;
    }

    Random random = new Random();

    /**
     * choosing a card among player cards in random way
     * @param cards a list of cards
     * @return a random chosen card among cards
     */
    public Card randomCard(ArrayList<Card> cards)
    {
        Card card = cards.get(random.nextInt(cards.size()));
        return card;
    }

    /**
     * get number of cards of player
     * @return number of cards of player
     */
    public int getNumberOfCards() {
        return playerCards.size();
    }

    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * print player all cards or playable cards
     * @param cards list of cards
     */
    public void printCards(ArrayList<Card> cards)
    {
       String str = "";
       for(int i=0; i<cards.size(); i++)
           str += cards.get(i).getPrintColor() +"┍━━━";
       str += "━━┑\n";
       for(int i=0; i<cards.size(); i++)
       {
           str += cards.get(i).getPrintColor() + "|";
           if(cards.get(i).getCardCharacter().equals("10"))
               str += cards.get(i).getCardCharacter() +" ";
           else
               str += " " +  cards.get(i).getCardCharacter() + " ";
       }
       str += "  |\n";
       for(int i=0; i<cards.size(); i++)
           str += cards.get(i).getPrintColor() + "|   ";
       str += "  |\n";
       for(int i=0; i<cards.size();i++)
           str += cards.get(i).getPrintColor() + "┕━━━";
       str += "━━┙\n" + ANSI_RESET;
       for(int i=0; i<cards.size(); i++)
       {
           if(i >= 9)
               str += "" + (i+1) + "  ";
           else
               str += " " + (i+1) + "\t ";
       }

       System.out.println(str);
    }

    /**
     * putting a card
     * @param gm game manager
     * @return selected card
     * @throws InterruptedException
     */
    public Card playCard(GameManager gm) throws InterruptedException {
        System.out.println("cards: ");
        printCards(playerCards);
        Card selectedCard = null;
        ArrayList<Card> validCards = checkValidCards(gm);
        if(validCards.size() == 0)
        {
            System.out.println("no playable card");
            selectedCard = pickCard(gm, selectedCard);
        }
        else
        {
            selectedCard = selectCard(validCards, gm);
        }
        playerCards.remove(selectedCard);
        return selectedCard;
    }

    /**
     * choose a cart to play
     * @param validCards list of playable cards
     * @param gm game manager
     * @return selected card
     */
    public Card selectCard(ArrayList<Card> validCards, GameManager gm)
    {
        Card selectedCard = null;
        System.out.println("playable cards: ");
        printCards(validCards);
        System.out.print("select a card: ");
        int cardIndex = gm.scanner.nextInt();
        while (cardIndex > validCards.size() || cardIndex < 1)
        {
            System.out.println("invalid. try again: ");
            cardIndex = gm.scanner.nextInt();
        }
        selectedCard = validCards.get(cardIndex-1);
        validCards.clear();
        return selectedCard;
    }

    /**
     * if player can't play card
     * picks a card
     * @param gm game manager
     * @param selectedCard field of card
     * @return the selected card
     * @throws InterruptedException
     */
    public Card pickCard(GameManager gm, Card selectedCard) throws InterruptedException {
        System.out.println("picking card...");
        Thread.sleep(1000);
        if(gm.getBoard().getBoardCard().getCardCharacter().equals("7"))
        {
            for(int i=0; i<gm.getNumberOfCardsForNextPlayer(); i++)
            {
                Card card = gm.getManageCards().giveSingleCard();
                playerCards.add(card);
            }
            gm.setNumberOfCardsForNextPlayer(0);
            ArrayList<Card> newPlayableCards = checkValidCards(gm);
            if(newPlayableCards.size()>0)
                selectedCard = selectCard(newPlayableCards,gm);

        }
        else
        {
            Card card = gm.getManageCards().giveSingleCard();
            playerCards.add(card);
            if(card.isPlayable(gm))
            {
                ArrayList<Card> valids = new ArrayList<>();
                valids.add(card);
                selectedCard = selectCard(valids,gm);
            }
        }

        return selectedCard;
    }

    /**
     * checking playable cards
     * @param gm game manager
     * @return list of playable cards
     */
    public ArrayList<Card> checkValidCards(GameManager gm)
    {
        ArrayList<Card> valids = new ArrayList<>();
        for(Card card : playerCards)
        {
            if(card.isPlayable(gm))
            {
                valids.add(card);
            }
        }
        return valids;
    }


}
