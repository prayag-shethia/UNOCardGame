package GameApplication;

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * Represents a GenerateDeck object.
 * This class reads the cards from the Deck table in the DB and breaks input into tokens.
 * Every token represents the type, color, value, action, etc of the card.
 * Each card type is represented by a char which tells the program to either create a 
 * NumberCard if 'N', an ActionCard if 'A' or a WildCard if 'W'
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class GenerateDeck {
    
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    ArrayList deck;
    boolean cardsExist = false;
    
    public GenerateDeck() {
            dbManager = DBManager.getDBManagerInstance();
            conn = dbManager.getConnection();
            checkDeckDB();
    }    
    
    /**
     * The method checks the connection to the DB and if the table for Deck exists.
     * If it does not exist, it calls the create table method that creates a new table.
     */
    public void checkDeckDB() {
        try {
            System.out.println("runs");
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "UNO", "DECK", null);
            
            if(!resultSet.next()) {
                createCardsTable();
            } else {
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("select * from UNO.DECK");
         
                if(!rs.next()) {
                    fillCardsTable();
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * The Create Cards Table function creates a new table in the DB for the cards by executing the SQL staement.
     * It creates the table with the specified columns and calls the fillCardsTable to read the card details.
     *
     */    
    public void createCardsTable() {
        try {
            statement = conn.createStatement();
            String createTable = "CREATE TABLE DECK (ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1), CARD_TYPE VARCHAR(30), COLOUR VARCHAR(10), VALUE VARCHAR(20), POINTS INT, IMAGE_REF VARCHAR(200))";
            statement.executeUpdate(createTable);
            
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = statement.executeQuery("select * from UNO.DECK");
               if(!rs.next()) {
                   fillCardsTable();
                }
        } catch (SQLException ex) {
            System.out.println(ex);
        }      
    }
    
    /**
     * The fillCardsTable() reads the text file that stores all the information of all cards.
     * It is broken down to extract the information and then stored in the columns within the table in the DB.
     */
    public void fillCardsTable() {
        try{
            File cardList = new File("./resources/cardsList.txt");
            FileReader fr = new FileReader(cardList);
            BufferedReader inputStream = new BufferedReader(fr);
            String line = null;
            statement = conn.createStatement();  
            String sql = "INSERT INTO UNO.DECK (CARD_TYPE, COLOUR, VALUE, POINTS, IMAGE_REF) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            while((line=inputStream.readLine())!=null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                ArrayList<String> cardDetails = new ArrayList();
                while(st.hasMoreTokens()) {
                    cardDetails.add(st.nextToken());
                } 
                preparedStatement.setString(1, cardDetails.get(0));
                preparedStatement.setString(2, cardDetails.get(1));
                preparedStatement.setString(3, cardDetails.get(2));
                preparedStatement.setInt(4, Integer.parseInt(cardDetails.get(3)));
                preparedStatement.setString(5, cardDetails.get(4));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());      
        }catch(FileNotFoundException e){
            System.out.println("File not found" + e);
        }catch(IOException e){
            System.out.println("Error reading from file");
        }
    }
    
    /**
     * readDeckDB reads the Deck table from the DB and extracts the data within to store into the card attributes.
     * It creates the different card types and their corresponding attributes.
     * 
     * @return deck as an ArrayList of cards
     */
    public ArrayList readDeckDB() {
        try {
            statement = conn.createStatement();
            ArrayList<Card> deck = new ArrayList();
            ResultSet rs = statement.executeQuery("SELECT * FROM UNO.DECK");
            Card card;         
            while (rs.next()) { 
                switch(rs.getString("CARD_TYPE")) {                    
                    case "N":     
                        card = new NumberCard("Number", rs.getString("COLOUR").charAt(0), Integer.parseInt(rs.getString("VALUE")), Integer.parseInt(rs.getString("POINTS")), rs.getInt("ID"), rs.getString("IMAGE_REF"));
                        deck.add(card);
                        break;
                    case "W":
                        card = new WildCard("Wild", ' ', rs.getString("VALUE").equalsIgnoreCase("PlusFour"), Integer.parseInt(rs.getString("POINTS")), rs.getInt("ID"), rs.getString("IMAGE_REF"));
                        deck.add(card);
                        break;
                    case "A":
                        card = new ActionCard("Action", rs.getString("COLOUR").charAt(0), rs.getString("VALUE"), Integer.parseInt(rs.getString("POINTS")), rs.getInt("ID"), rs.getString("IMAGE_REF"));
                        deck.add(card);
                        break;
                }
            } 
            rs.close();
            return deck; 

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        return null;
    }
}