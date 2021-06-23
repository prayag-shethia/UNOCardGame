package GameApplication;

/**
 * Represents a number card. 
 * Extends the card class
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class NumberCard extends Card {
    
    int value;
    
    public NumberCard(String type, char colour, int value , int points, int idNumber,String imageRef) {
        super(type, colour, points, idNumber,imageRef);
        this.value = value;
    }
    
    /**
     * Returns a char representing the colour of card
     * @return colour A char representing the colour of card
     */
    public char getColour() {
        return colour;
    }
    
    /**
     * Returns an int representing the cards value
     * @return value An int representing the cards value
     */
    public int getValue() {
        return value;
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
     * Creates a string representing the english word of the cards value.
     * @return String representing value
     */       
    public String getNumberText() {
        String tValue = "null";
        switch(value) {
            case 0:
                tValue = "Zero";
                break;
            case 1:
                tValue = "One";
                break;
            case 2:
                tValue = "Two";
                break;
            case 3:
                tValue = "Three";
                break;
            case 4:
                tValue = "Four";
                break;
            case 5:
                tValue = "Five";
                break;
            case 6:
                tValue = "Six";
                break;
            case 7:
                tValue = "Seven";
                break;
            case 8:
                tValue = "Eight";
                break;
            case 9:
                tValue = "Nine";
                break;
            case 10:
                tValue = "Ten";
                break;
        }
        return tValue;
    }
    
    /**
     * Creates toString with String from getFullColour and getNumberText
     * @return String to print
     */
    @Override
    public String toString() {
        return "[ " + getFullColour() + " " + getNumberText() + " ]";
    }

    /**
     * Overrides the implementCard function with an empty function as there are 
     * no additional actions required when playing a numberCard.
     */
    @Override
    public void implementCard() {}
}