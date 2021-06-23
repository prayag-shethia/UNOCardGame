package GameApplication;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Creates a GameLog object.
 * The object uses the writeGameLogDB function to take in Strings 
 * and write them to a Table called GameLog in the DB. It also uses the readGameLog 
 * function to read strings from the table GameLog and displays them in the 
 * GUI.
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class GameLog {
    
    String fileName = "./resources/GameLog.txt";
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    boolean tableCleared = false;
    boolean tableExists = false;
    public static UnoPlayScreen playingScreen;
    
    public GameLog() {
        dbManager = DBManager.getDBManagerInstance();
        conn = dbManager.getConnection();
        checkGameLogDB();
        if(!tableCleared) {
            this.resetGameLogDB();
        }
    }
    
    /**
     * The method checks if there is an existing Game Log table within the DB.
     * If not, then it calls the createTable method to create a new table.
     */
    public void checkGameLogDB() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "UNO", "GAMELOG", null);
            if(!resultSet.next()) {
                createGameLogTable();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());       
        }
    }
    
    /**
     * The method is used to create a new table for Game Log in the DB with the column names specified.
     */
    public void createGameLogTable() {
        try{
            Statement statement = conn.createStatement();
            String createTable = "CREATE TABLE GAMELOG (TURN_NUMBER INTEGER  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, LOG VARCHAR(300), PRIMARY KEY (TURN_NUMBER))";
            statement.executeUpdate(createTable);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());      
            System.out.println("fails");  
        }
    }
    
    /**
     * The writeGameLogDB method updates the playing log to update every new activity on the GUI.
     * It also stores the entire Log from start to end of the game to store it inside the table in the DB.
     * 
     * @param log as a String to add to the Game Log table in the DB.
     */
    public void writeGameLogDB(String log) {
        try {
            playingScreen.updateLog(log);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO UNO.GAMELOG (LOG) VALUES (?)");
          
            pstmt.setString(1, log);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * The method is used to read the GameLog from the table and store it in an arraylist.
     * It makes a result set to read the specified columns and returns the array list of strings.
     * 
     * @return logs as an arrayList of String
     */
    public ArrayList<String> readGameLogDB() {
        ArrayList<String> logs = new ArrayList();
        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT TURN_NUMBER, LOG FROM UNO.GAMELOG");

            while (rs.next()) {
                String log = rs.getString("LOG");
                logs.add(log);
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return logs;
    }
    
    /**
     * The method converts the array list of Strings into a String format.
     * This is used to display on the HUI later.
     * 
     * @param list
     * @return stringList as a String
     */
    public String gameLogToString(ArrayList<String> list) {
        String stringList = "";
        for(String line: list) {
            stringList+=line;
            stringList+="\n";
        }
      return stringList;  
    }

    /**
     * Reset Game Log DB is used to delete the existing Game Log table.
     * It also automatically sets the next entry of the game log as a turn that keeps adding.
     * After the table is cleared, it sets the static variable tableCleared as true.
     */
    public void resetGameLogDB() {
        try {
            Statement statement = conn.createStatement();
            String clearTable = "DELETE FROM UNO.GAMELOG WHERE 1=1";
            statement.executeUpdate(clearTable);
            String resetTurnNumber = "ALTER TABLE UNO.GAMELOG ALTER COLUMN TURN_NUMBER RESTART WITH 1";
            statement.executeUpdate(resetTurnNumber);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        tableCleared = true;
    }
}