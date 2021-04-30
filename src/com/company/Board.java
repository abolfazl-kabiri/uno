package com.company;

public class Board {

    private Card boardCard;
    private String boardColor;

    public Board(Card boardCard) {
        this.boardCard = boardCard;
        boardColor = boardCard.getColor();
    }

    public Card getBoardCard() {
        return boardCard;
    }

    public Card setBoardCard(Card card)
    {
        Card c = boardCard;
        boardCard = card;
       setBoardColor(card.getColor());
        return c;
    }

    public String getBoardColor() {
        return boardColor;
    }



    public void setBoardColor(String color)
    {
        boardColor = color;
    }
}
