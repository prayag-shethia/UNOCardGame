package GameApplication;

/**
 * Represents an action card. 
 * Extends the card class and overrides the implementCard function to complete 
 * the action of the card.
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class ActionCard extends Card {
    
    String action;

    public ActionCard(String type, char colour, String action, int points, int idNumber,String imageRef) {
        super(type, colour, points, idNumber,imageRef);
        this.action = action;
    }
    /**
     * Returns a char representing the colour of card
     * @return colour A char representing the colour of card
     */
    public char getColour() {
        return colour;
    }
    /**
     * Returns a string representing the action of the card
     * @return action A string representing the action of the card
     */
    public String getAction() {
        return action;
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
     * @return type A string containing "Number"
     */
    public String getType() {
        return type;
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
     * Displays a message if a players turn has been skipped due to a skip or reverse card being played.
     * Also logs the players turn has been skipped in the game log
     */
    public void displaySkipMessage() {
        if(Game.player1.turn){
            //System.out.println("Player 2: " + Game.PLAYER2NAME+ "'s turn has been skipped!");
            //make ^ pop up
            Game.log.writeGameLogDB(Game.PLAYER2NAME+"'s turn has been skipped!");
        }
        else if(Game.player2.turn){
            //System.out.println("Player 1: " + Game.PLAYER1NAME + "'s turn has been skipped!");
            //make ^ pop up
            Game.log.writeGameLogDB(Game.PLAYER1NAME+"'s turn has been skipped!");
        }
    }
    
/**
 * Creates toString with String from getFullColour and action
 * @return String to print
 */
    @Override
    public String toString() {
        return "[ " + getFullColour() + " " + action + " ]";
    }
    
/**
 * Implements the action of the card played.
 * Forces the other player to draw 2 cards if 'PlusTwo' is played and skips the 
 * other players next turn if 'Skip' or 'Reverse' is played.
 */
    @Override
    public void implementCard() {
        ActionCard card = (ActionCard)Game.playedCard;
        if(Game.player1.turn){
            if(this.action.equalsIgnoreCase("PlusTwo")) {
                //System.out.println("\nPlayer 2: " + Game.PLAYER2NAME+" has been forced to Draw 2!");
                Game.log.writeGameLogDB(Game.PLAYER2NAME+" is forced to draw 2 cards.");
                Game.drawPile.drawCard(2);
                //pop up for +2 added
            } else if(this.action.equalsIgnoreCase("Skip")) {
                Game.skipNextTurn = true;               
                card.displaySkipMessage();
                //make ^ pop up
            } else if(this.action.equalsIgnoreCase("Reverse")) {
                //System.out.println("Turn order has been reversed!");
                //make ^ pop up
                Game.log.writeGameLogDB("Turn order reversed!");
                Game.skipNextTurn = true;
                card.displaySkipMessage();
            }
        } else {
        if(this.action.equalsIgnoreCase("PlusTwo")) {
                //System.out.println("\nPlayer 1: " + Game.PLAYER1NAME+" has been forced to Draw 2!");
                //make ^ pop up
                Game.log.writeGameLogDB(Game.PLAYER1NAME+" is forced to draw 2 cards.");
                Game.drawPile.drawCard(2);
            } else if(this.action.equalsIgnoreCase("Skip")) {
                Game.skipNextTurn = true;
                card.displaySkipMessage();
            } else if(this.action.equalsIgnoreCase("Reverse")) {
                //System.out.println("Turn order has been reversed!");
                //make ^ pop up
                Game.log.writeGameLogDB("Turn order reversed!");
                Game.skipNextTurn = true;
                card.displaySkipMessage();
            }
        }
    }
}