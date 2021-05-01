package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    private String name;
    private int score;
    private ArrayList<Card> playerCards;

    public Player(ArrayList<Card> playerCards, String name) {
        this.playerCards = playerCards;
        this.name = name;
        score = 0;
    }

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card)
    {
        playerCards.add(card);
    }

    public void removeCard(Card card)
    {
        playerCards.remove(card);
    }

    public void calculateScores()
    {
        for (Card card: playerCards)
            score += card.getScore();
    }

    public boolean hasCard()
    {
        if(playerCards.size() == 0)
            return false;
        else
            return true;
    }

    Random random = new Random();
    public Card randomCard(ArrayList<Card> cards)
    {
        Card card = cards.get(random.nextInt(cards.size()));
        return card;
    }

    public int getNumberOfCards() {
        return playerCards.size();
    }

    public static final String ANSI_RESET = "\u001B[0m";
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

    private Card pickCard(GameManager gm, Card selectedCard) throws InterruptedException {
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

    private ArrayList<Card> checkValidCards(GameManager gm)
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
