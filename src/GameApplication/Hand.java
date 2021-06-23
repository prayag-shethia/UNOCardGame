package GameApplication;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Represents the hand object that contains an ArrayList of cards.
 * The hand object also contains logic for selecting a card from the hand,
 * generating the hand from the static DrawPile, displaying the cards in the 
 * hand and moving cards to the static DiscardPile when played.
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class Hand {
    Scanner scan = new Scanner(System.in);
    ArrayList<Card> hand= new ArrayList();
    int initialHandSize = 7;
    static UnoPlayScreen playingScreen;
    
    public Hand(DrawPile drawPile) {
        generateDeck(drawPile);
    }
    
    /**
     * Asks the user to enter the number value of the card they would like to play.
     * Displays the players hand, reads the input from the user and assigns the 
     * static variable playedCard from the Game class, the card the user has selected.
     */ 
    //public void selectCard(){ //>>>>>>>>>>>>>>>> changed this
    public void selectCard(int index){
        Card selectedCard=null;
        selectedCard = hand.get(index);
        if(!selectedCard.checkCard()) {
            JOptionPane.showMessageDialog(null, "This card cannot be played. Check the colour, value or type of card and try again!","",JOptionPane.PLAIN_MESSAGE);
        }
        else{
            Game.playedCard = selectedCard;
            selectedCard.playCard();
            if(selectedCard.type!="Wild" && Game.playing){
                Turns.showToast(selectedCard.type);
                Turns.endTurn();
                playingScreen.setButtons();
            } 
        }
    }
    
    /**
     * Generates the hand by drawing the number of cards specified in the 
     * variable initialHandSize from the passed drawPile.
     * @param drawPile Static drawPile object containing the cards for the game.
     */
    public void generateDeck(DrawPile drawPile) {      
        for(int i = 0; i < initialHandSize; i++) {        
            Card drawnCard;
            drawnCard = drawPile.deck.get(0);
            drawPile.deck.remove(0);
            hand.add(drawnCard);           
        }
    }
    
    /**
     * Selects the card passed to the function from the players hand and adds 
     * it to the discardPile
     * @param selectedCard card to be removed from the hand and added to 
     * discardPile
     */
    public void moveToDiscard(Card selectedCard) {
        Card playedCard = null;
        for(int i=0;i<hand.size();i++) {
            if(selectedCard.idNumber == hand.get(i).idNumber) {
                playedCard = hand.get(i);
                hand.remove(playedCard);
                Game.discardPile.deck.add(playedCard);
            }
        }
    }  
}