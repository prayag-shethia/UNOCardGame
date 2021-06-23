package GameApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Represents a DrawPile object that contains a deck of cards
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class DrawPile {

    static ArrayList<Card> deck;
    
    public DrawPile() {
        deck = new ArrayList();
        generateDeck(); 
    }

    /**
     * Calls readCards which generates all the cards then assigns them to the deck ArrayList. 
     * It then calls the shuffle function which shuffles the order of the cards in the deck
     */
    public void generateDeck() {
        GenerateDeck cards = new GenerateDeck();
        deck = cards.readDeckDB();
        shuffle();
    }
    
    /**
     * Draws the first card from the drawPile to be played.
     * Displays card drawn and logs in game log.
     * 
     * @return firstCard
     */
    public Card drawFirstCard() {
        Random r1 = new Random();
        int random = r1.nextInt(108);
        Card firstCard = null;
        do {
         random = r1.nextInt(108);
         firstCard = deck.get(random);   
        } while(firstCard.type != "Number");
        deck.remove(random);
        Game.discardPile.deck.add(firstCard);
        System.out.println("\nThe starting card is: " + firstCard);
        Game.gameLog.add("Starting card is " + firstCard);
        
        return firstCard;
    }
    
    /**
     * Draws the number of cards specified in the numberOfCards variable from 
     * the drawPile and adds them to the hand ArrayList
     * Checks if the discardPile has enough cards and calls shuffleDiscardPile 
     * if required 
     * 
     * @param numberOfCards int representing the number of cards to draw
     */
    public void drawCard(int numberOfCards) {
        ArrayList<Card> drawnCards = new ArrayList();
        Player drawingPlayer;
        Card drawnCard = null;
        if(Game.player1.turn){
            drawingPlayer = Game.player2;
        }
        else{
            drawingPlayer = Game.player1;
        }
        if (this.deck.size() <= numberOfCards){
            this.shuffleDiscardPile();
        }
        if(Game.currentPlayer.hand.hand.size()>=22){
            JOptionPane.showMessageDialog(null, "Hand is Full. Please play a card.","",JOptionPane.ERROR_MESSAGE);
            Game.playing = false;
        }else{
           Game.playing = true;
            if(numberOfCards == 1){
                Turns.showToast("draw");
                drawnCard = this.deck.get(0);
                this.deck.remove(0);
                Game.currentPlayer.hand.hand.add(drawnCard);
                Game.log.writeGameLogDB((Game.player1.turn? Game.PLAYER1NAME : Game.PLAYER2NAME) + " draws a card.");
            }else{ //draws and displays number of cards specified
                for(int i = 0; i < numberOfCards; i++) {  
                    drawnCard = this.deck.get(0);
                    this.deck.remove(0);
                    drawingPlayer.hand.hand.add(drawnCard);
                    drawnCards.add(drawnCard);         
                }
            } 
        }  
    }
    
    /**
     * Shuffles the order of cards in the deck
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }
    
    /**
     * Takes the cards from the static discardPile object and puts them back into the static drawPile object.
     * It then shuffles the drawPile deck.
     */
    public void shuffleDiscardPile() {
        for(int i = 1; i < Game.discardPile.deck.size(); i++) {  
            Card drawnCard;
            drawnCard = Game.discardPile.deck.get(0);
            //resets colour of wild cards
            if(drawnCard.type.equals("Wild")){
                drawnCard.setColour(' ');
            }
            deck.remove(0);
            DrawPile.deck.add(drawnCard);
        }
        shuffle();  
    }
}
