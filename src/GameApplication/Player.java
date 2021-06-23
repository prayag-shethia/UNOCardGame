package GameApplication;

/**
 * Represents a player object.
 * Player object contains a hand object holding the cards to be played by the 
 * player and is assigned a username from the users input.
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class Player {
    
    private final String userName;
    private int points=0;
    Hand hand;
    public boolean turn;
    
    public Player(String userName, DrawPile drawPile){
        this.userName = userName;
        this.points = 0;
        hand = new Hand(drawPile);
        turn = false;
    }
    
    /**
     * Returns the players username
     * @return username A String representing the players username
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * Sets if it is this players turn using the turn boolean passed.
     * @param turn A boolean representing if it is this players turn
     */   
    public void setTurn(Boolean turn) {
        this.turn = turn;
    }
    
    /**
     * Returns the points the player currently has
     * @return points An int representing the player's points
     */
    public int getPoints() {
        return points;
    }
    
    /**
     * Sets the players points using the points variable passed
     * @param points An int containing the player's points
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    /**
     * Returns the players hand object
     * @return hand object containing cards the player can play
     */
    public Hand getHand() {
        return hand;
    }
}
