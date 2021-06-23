package GameApplication;

import static GameApplication.Hand.playingScreen;
import java.util.Scanner;

/**
 * Represents a wild card. 
 * Extends the card class and overrides the implementCard function to allow the 
 * player to change the colour of the card and if the plusFour variable is true, 
 * force the other player to draw 4 cards.
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class WildCard extends Card {
    Scanner scan = new Scanner(System.in);
    
    boolean plusFour;

    public WildCard(String type, char colour, boolean plusFour, int points, int idNumber,String imageRef) {
        super(type, colour, points, idNumber,imageRef);
        this.plusFour = plusFour;
    }
    
    /**
     * Returns a boolean representing if the card is a plus four card
     * @return isPlusFour a boolean representing if the card is a plus four card
     */
    public boolean isPlusFour() {
        return plusFour;
    }
    
    /**
     * Returns an int representing the cards points if played
     * @return points An int representing the cards points
     */
    public int getPoints() {
        return points;
    }
    
    /**
     * Returns a string representing the cards type
     * @return type A string containing "Wild"
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the colour of the card with the variable passed
     * @param colour a char representing the chosen colour of the card
     */
    @Override
    public void setColour(char colour) {
        this.colour = colour;
        //set image ref
    }  
    
    /**
    * Creates a string representing the colour of the card.
    * @return String representing colour
    */
    public String getFullColour() {
        String fColour = "null";
        switch(colour) {
            case 'R':
                fColour = "Red";
                break;
            case 'Y':
                fColour = "Yellow";
                break;
            case 'B':
                fColour = "Blue";
                break;
            case 'G':
                fColour = "Green";
                break;
                
        }
        return fColour;
    }

    /**
    * Creates toString with String that contains Plus Four if isPlusFour is true
    * @return String to print
    */
    @Override
    public String toString() {
        return "[ Wild " + (isPlusFour() ? "Plus Four " : "") + (colour != ' ' ? getFullColour() + " ]": "]");
    }  

    /**
     * Implements the wild card action of the card played.
     * Asks the user to choose the desired colour and reads it.
     * Changes the colour of the wild card and draws 4 card if its a wild plus four.
     */
    @Override
    public void implementCard() {
        char changeColour = WildColour.colour;
        switch(changeColour){
          case 'R':
              Game.playedCard.setColour('R');
              Game.log.writeGameLogDB((Game.player1.turn? Game.PLAYER1NAME : Game.PLAYER2NAME) + " has changed the colour to Red.");
              break;
          case 'B':
              Game.playedCard.setColour('B');
              Game.log.writeGameLogDB((Game.player1.turn? Game.PLAYER1NAME : Game.PLAYER2NAME) + " has changed the colour to Blue.");
              break;
          case 'G':
              Game.playedCard.setColour('G');
              Game.log.writeGameLogDB((Game.player1.turn? Game.PLAYER1NAME : Game.PLAYER2NAME) + " has changed the colour to Green.");
              break;
          case 'Y':
              Game.playedCard.setColour('Y');
              Game.log.writeGameLogDB((Game.player1.turn? Game.PLAYER1NAME : Game.PLAYER2NAME) + " has changed the colour to Yellow.");
              break; 
          }    
 
        if(Game.player1.turn){
            if(this.plusFour){
                Game.log.writeGameLogDB(Game.PLAYER2NAME+" is forced to draw 4 cards.");
                Game.drawPile.drawCard(4);
            }
        }
        else if(Game.player2.turn){
            if(this.plusFour){
                Game.log.writeGameLogDB(Game.PLAYER1NAME+" is forced to draw 4 cards.");
                Game.drawPile.drawCard(4);
            }
        }
        Turns.endTurn();
        playingScreen.setButtons();
    }
}