package com.company;

public class Card {

    private String cardCharacter;
    private String color;
    private  int score;

    public Card(String cardCharacter, String color, int score) {
        this.cardCharacter = cardCharacter;
        this.color = color;
        this.score = score;
    }


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";


    public String getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public String getCardCharacter() {
        return cardCharacter;
    }


    public String getPrintColor()
    {
        String str = null;
        if (this.color.equals("black"))
            str = ANSI_BLACK;
        else if(this.color.equals("blue"))
            str = ANSI_BLUE;
        else if(this.color.equals("red"))
            str = ANSI_RED;
        else if(this.color.equals("green"))
            str = ANSI_GREEN;
        return str;
    }

    public void print()
    {
        System.out.println(cardCharacter + " " + color);
    }

    public boolean isPlayable(GameManager gm) {
        boolean playable = false;
        if (gm.getBoard().getBoardCard().getCardCharacter().equals("7") && gm.getNumberOfCardsForNextPlayer() != 0) {
            if(this.getCardCharacter().equals("7"))
                playable = true;
            else
                playable = false;

        } else {
            String color = gm.getBoard().getBoardColor();
            String boardChar = gm.getBoard().getBoardCard().getCardCharacter();
            if (this.getColor().equals(color) || this.getCardCharacter().equals(boardChar) || (this instanceof WildCard)) {
                playable = true;
            } else
                playable = false;
        }
        return playable;
    }
}
