package GameApplication;

import javax.swing.JOptionPane;

/**
* Represents an abstract card that is extended by ActionCard, NumberCard and WildCard
* 
* @author Daniel Kathiresan
* @author Taylor Pringle
* @author Prayag Shethia
*/
public abstract class Card {

   int points;
   String type;
   int idNumber;
   char colour;
   static public UnoPlayScreen playingScreen;
   String imageRef;

    public Card(String type, char colour, int points, int idNumber, String imageRef) {
        this.type = type;
        this.points = points;
        this.idNumber = idNumber;
        this.colour = colour;
        this.imageRef = imageRef;
    }
    /**
     * Function to be overwritten for each subclass. 
     * Used to complete action of relating card type.
     */
    public abstract void implementCard();
    
    /**
     * Uses switch statement to call relating function based on type of card 
     * played.
     * Runs moveToDiscard function once relating functions have been called 
     * which moves the card from the players hand to the discardPile. Also 
     * adds points to player for playing the card.
     */
    public void playCard(){
        Game.log.writeGameLogDB((Game.player1.turn? Game.PLAYER1NAME : Game.PLAYER2NAME) + " plays " + this + " card.");
        
        switch (this.type){
        case "Action":
            ActionCard actionCard = (ActionCard)this;
            actionCard.implementCard();
            break;

        case "Number":
            break;

        case "Wild":
            WildCard wildCard = (WildCard)this;
            if(Game.playing){
               WildColour wildColour = new WildColour(playingScreen,wildCard);
                wildColour.setVisible(true);
                playingScreen.setEnabled(false); 
            }
            break;
        }
        Game.currentPlayer.hand.moveToDiscard(this);
        Game.currentPlayer.setPoints(this.points+Game.currentPlayer.getPoints());
        if(Game.currentPlayer.hand.hand.size() == 1 && !type.equals("Wild")){
            JOptionPane.showMessageDialog(null, "It's the last card!\n------**UNO**-----","LAST CARD",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Sets the colour of the card with the variable passed
     * @param colour a char representing the chosen colour of the card
     */
    public void setColour(char colour) {
        this.colour = colour;
    }
    
    /**
     * Sets the imageRef of the card for setting the icon
     * @param imageRef a String to set the location of the image
     */
    public void setImageRef(String imageRef){
        this.imageRef = imageRef;
    }
    
    /**
     * Checks the card can be played by comparing the current card with the card 
     * to be played.
     * If the card being played is a wild card, it always returns true otherwise 
     * it checks if the cards colours match or action/numbers match. If they match, 
     * it returns true meaning the card can be played otherwise returns false,
     * @return checkCard boolean representing if the card can be played
     */
    public boolean checkCard() {
        boolean checkCard = false;
        if (this.type.equals("Wild")) {
           checkCard = true;
        } 
        else {
            //Checks colours
            if (this.colour == Game.playedCard.colour) {
                checkCard = true;
            } 
            else {
                //Checks if actions are equal
                if(this.type.equals("Action") && Game.playedCard.type.equals("Action")) {
                   ActionCard ac = (ActionCard)this;
                   ActionCard playedAC = (ActionCard)Game.playedCard;
                   if(ac.action.equals(playedAC.action)) {
                       checkCard = true;
                   } 
                }
                //checks if number values are equal
                if(this.type.equals("Number") && Game.playedCard.type.equals("Number")) {
                   NumberCard nc = (NumberCard)this;
                   NumberCard playedNC = (NumberCard)Game.playedCard;
                   if(nc.value == playedNC.value) {
                       checkCard = true;
                   } 
                }
            }
        }
        return checkCard;
    }
}
