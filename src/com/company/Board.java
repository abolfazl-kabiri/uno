package com.company;

/**
 * Board class
 * holds board's information
 */
public class Board {

    private Card boardCard;
    //if player plays "B" card
    //color board and color of boardCard are different
    private String boardColor;

    /**
     * constructor of Board class
     * creates a board with a card on it
     * @param boardCard locates on board
     */
    public Board(Card boardCard) {
        this.boardCard = boardCard;
        boardColor = boardCard.getColor();
    }

    /**
     * get card on board
     * @return boardCard field
     */
    public Card getBoardCard() {
        return boardCard;
    }

    /**
     * set new card on board after player's mover
     * @param card new card
     * @return the old card
     */
    public Card setBoardCard(Card card)
    {
        Card c = boardCard;
        boardCard = card;
       setBoardColor(card.getColor());
        return c;
    }

    /**
     * get color of board
     * @return boardColor field
     */
    public String getBoardColor() {
        return boardColor;
    }

    /**
     * change color of board after card"B"
     * @param color new color
     */
    public void setBoardColor(String color)
    {
        boardColor = color;
    }
}
