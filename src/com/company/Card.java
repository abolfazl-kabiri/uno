package com.company;

/**
 * Card class
 * identifies cards identity
 */
public class Card {

    private String cardCharacter;
    private String color;
    private  int score;

    /**
     * constructor of Card class
     * @param cardCharacter character of card
     * @param color color of card
     * @param score score of card
     */
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

    /**
     * get color of card
     * @return color field
     */
    public String getColor() {
        return color;
    }

    /**
     * get score of card
     * @return score field
     */
    public int getScore() {
        return score;
    }

    /**
     * get character of card
     * @return cardCharacter field
     */
    public String getCardCharacter() {
        return cardCharacter;
    }

    /**
     * get color of card to print
     * @return color of card to print
     */
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

    /**
     * print card
     */
    public void print()
    {
        System.out.println();
        String str = getPrintColor() +"┍━━━━━┑\n";
        str += "|" + cardCharacter;
        if(cardCharacter.equals("10"))
            str += "   |\n";
        else
            str += " \t  |\n";
        str += "|\t  |\n";
        str += "┕━━━━━┙\n" + ANSI_RESET;
        System.out.println(str);



    }

    /**
     * check whether is card playable or not
     * @param gm game manager
     * @return is card playable or not
     */
    public boolean isPlayable(GameManager gm) {
        boolean playable = false;
        //if card on the board has "7" character and no one has been punished
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
