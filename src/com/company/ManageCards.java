package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * ManageCards class
 * manages cards in bank
 */
public class ManageCards {

    private ArrayList<Card> cards;

    /**
     * constructor of ManagerCards class
     * creates an empty arraylist to be filled with initial cards
     */
    public ManageCards() {
        cards = new ArrayList<>();
    }

    /**
     * shuffle cards
     */
    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    /**
     * add the old card of board to cards in bank
     * @param card
     */
    public void addCards(Card card)
    {
        cards.add(card);
    }

    /**
     * remove a card from bank
     * @param card card to be removed
     */
    public void removeCard(Card card)
    {
        cards.remove(card);
    }

    /**
     * create ordinary cards
     */
    public void createOrdinaryCards()
    {
        String[] colors = {"black", "blue", "red", "green"};
        for (int j = 0; j<4; j++)
        {
            Card card = new Card("3",colors[j],3);
            addCards(card);

            card = new Card("4",colors[j],4);
            addCards(card);

            card = new Card("5",colors[j],5);
            addCards(card);

            card = new Card("6",colors[j],6);
            addCards(card);

            card = new Card("9",colors[j],9);
            addCards(card);

            card = new Card("C",colors[j],12);
            addCards(card);

            card = new Card("D",colors[j],13);
            addCards(card);
        }
    }

    /**
     * create action cards
     */
    public void createActionCards()
    {
        String[] colors = {"black", "blue", "red", "green"};
            for(int j=0; j<4; j++)
            {
                DonationCard donationCard = new DonationCard ("2", colors[j], 2);
                addCards(donationCard);

                MoveAgainCard moveAgainCard = new MoveAgainCard("8", colors[j], 8);
                addCards(moveAgainCard);

                ReverseCard reverseCard = new ReverseCard("10", colors[j], 10);
                addCards(reverseCard);

                SkipCard skipCard = new SkipCard("A", colors[j], 11);
                addCards(skipCard);

                WildCard wildCard = new WildCard("B", colors[j], 12);
                addCards(wildCard);


            }

            for(int j=1; j<4; j++)
            {
                DrawCard drawCard = new DrawCard("7", colors[j], 10);
                addCards(drawCard);
            }

            WildDrawCard wildDrawCard = new WildDrawCard("7", colors[0], 15);
            addCards(wildDrawCard);
    }

    /**
     * set the initial board's card by random
     */
    Random random = new Random();
    public Card setBoardCard()
    {
        createOrdinaryCards();
        createActionCards();
        shuffle();
        Card card = cards.get( random.nextInt(cards.size()));

        while (card instanceof ActionCard)
            card = cards.get(random.nextInt(cards.size()));
        removeCard(card);
        return card;
    }

    /**
     * gives a single card to a player
     * @return a card
     */
    public Card giveSingleCard()
    {
        Card card = cards.get(random.nextInt(cards.size()));
        removeCard(card);
        return card;
    }

    /**
     * set initial players' cards
     * @return cards of each player
     */
    public ArrayList<Card> setPlayerCards()
    {
        ArrayList<Card> playerCards = new ArrayList<>();
        while (playerCards.size() < 7)
        {
            Card card = cards.get(0);
            playerCards.add(card);
            cards.remove(card);
        }
        return playerCards;
    }
}
