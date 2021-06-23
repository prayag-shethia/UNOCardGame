/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameApplication;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Represents the turns object which holds the logic for a players entire turn. 
 * The turns object is called from the game class and completes the entire turn 
 * cycle by ending the turn. The end turn function checks if the game should still proceed 
 * by checking if a player has an empty hand or if they have reached the winning number of points 
 * before swapping the turn to the other player.
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class Turns {
    
    Scanner scan = new Scanner(System.in);
    static UnoPlayScreen playingScreen;
    static Toast toast = new Toast("text");
    
        public static void showToast(String turn) {
        if(Game.skipNextTurn == true && Game.player1.turn == true) {
            toast.makeToast("<html><div style='text-align: center;'>" + Game.player2.getUserName() + ", your turn has been skipped!<br> It's " + Game.player1.getUserName() + "\'s turn again!</div></html>", 2);    
        } else if(Game.skipNextTurn == true && Game.player2.turn == true) {
            toast.makeToast("<html><div style='text-align: center;'>" + Game.player1.getUserName() + ", your turn has been skipped!<br> It's " + Game.player2.getUserName() + "\'s turn again!</div></html>", 2);    
        } else {
            if(turn.equals("draw")) {
                if(Game.player1.turn){
                    toast.makeToast("<html>It's " + Game.PLAYER1NAME + "\'s turn!</html>", 1);
                } else {
                    toast.makeToast("<html>It's " + Game.PLAYER2NAME + "\'s turn!</html>", 1);  
                }
            } else if(turn.equals("Wild")) {
                WildCard wCard = (WildCard)Game.playedCard;

                if(Game.playing && !Game.skipNextTurn) {
                    if(Game.player1.turn){
                        if(wCard.isPlusFour()) {
                            toast.makeToast("<html><div style='text-align: center;'> " +  Game.PLAYER1NAME + " has changed the colour to " + wCard.getFullColour() + " and has forced " + Game.PLAYER2NAME + " to pick up 4 cards. <br>It is now " + Game.PLAYER2NAME + "\'s turn!</div></html>", 6);
                        } else {
                            toast.makeToast("<html><div style='text-align: center;'> " +  Game.PLAYER1NAME + " has changed the colour to " + wCard.getFullColour() + "<br>It is now " + Game.PLAYER2NAME + "\'s turn!</div></html>", 2);
                        }    
                    } else {
                        if(wCard.isPlusFour()) {
                              toast.makeToast("<html><div style='text-align: center;'> " +  Game.PLAYER2NAME + " has changed the colour to " + wCard.getFullColour() + " and has forced " + Game.PLAYER1NAME + " to pick up 4 cards. <br>It is now " + Game.PLAYER1NAME + "\'s turn!</div></html>", 6);
                          } else {
                          toast.makeToast("<html><div style='text-align: center;'> " +  Game.PLAYER2NAME + " has changed the colour to " + wCard.getFullColour() + "<br>It is now " + Game.PLAYER1NAME + "\'s turn!</div></html>", 2);
                        }
                 }
            }
         } else if(turn.equals("Action")) {
           ActionCard aCard = (ActionCard)Game.playedCard;
            if(Game.playing && !Game.skipNextTurn) {
                if(Game.player1.turn){
                    if(aCard.action.equals("PlusTwo")) {
                        toast.makeToast("<html><div style='text-align: center;'>" +  Game.PLAYER1NAME + " has forced " + Game.PLAYER2NAME  + " to pick up two cards, it's now " + Game.PLAYER2NAME +  "\'s turn!</div></html>", 2);
                    } else {
                        toast.makeToast("<html>It's " + Game.PLAYER2NAME + "\'s turn!</html>", 1);
                    }
                } else {
                    if(aCard.action.equals("PlusTwo")) {
                        toast.makeToast("<html><div style='text-align: center;'>" +  Game.PLAYER2NAME + " has forced " + Game.PLAYER1NAME  + " to pick up two cards, it's now " + Game.PLAYER1NAME +  "\'s turn!</div></html>", 2);
                    } else {
                    toast.makeToast("<html>It's " + Game.PLAYER1NAME + "\'s turn!</html>", 1);
                    }
                 }
            }
        } else {
             if(Game.player1.turn){
                toast.makeToast("<html>It's " + Game.PLAYER1NAME + "\'s turn!</html>", 1);
             } else {
                toast.makeToast("<html>It's " + Game.PLAYER2NAME + "\'s turn!</html>", 1); 
             }
         }
    }
}

    /**
     * Runs endTurn checks to check if players hand are empty, they have reached maximum points, skipNextTurn is false and playing is true.
     * If these are true, change the player turn otherwise end the game.
     */
    public static void endTurn() {
        Game.forfeit = false;
        if(Game.currentPlayer.hand.hand.isEmpty() || Game.currentPlayer.getPoints()>=300){
            Game.endGame();
            JOptionPane.showMessageDialog(null, Game.winner.getUserName()+" won the game!","THAT'S A WIN",JOptionPane.INFORMATION_MESSAGE);
            playingScreen.dispose();
            playingScreen.unoGUIForm.setVisible(true);
            playingScreen.unoGUIForm.endGamePanelDisplay();
        }
        
        if(Game.playing && !Game.skipNextTurn) {
            if(Game.player1.turn){
                Game.player1.turn = false;
                Game.player2.turn = true;
                Game.currentPlayer = Game.player2;
            }
            else{
                Game.player2.turn = false;
                Game.player1.turn = true;
                Game.currentPlayer = Game.player1;
            }    
        }
        Game.skipNextTurn = false;
        //Game.playing = true;
    }
}

