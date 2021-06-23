package GameApplication;

import java.sql.*;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Represents a PlayerRecords object. The class first reads the list of previous
 * usernames from a file. If a user has played before (same username), then it
 * checks for previous points from the file input. This class also has a
 * function to write the player usernames, points to the same text file as
 * output after the game ends/forfeited. It also prints the highest scores from
 * all previous players to display at the start.
 *
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public class PlayerRecords {

    public static HashMap<String, Integer> players = new HashMap();
    private final String fileName;
    static String records = "";
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public PlayerRecords() {
        this.fileName = "./resources/PlayerRecords.txt";
        dbManager = DBManager.getDBManagerInstance();
        conn = dbManager.getConnection();
        checkPlayersDB();
        readPlayerRecordDB();

    }

    /**
     * Checks the table for Players in the DB.
     * If not, then calls the create Table function to create a new table.
     */
    public void checkPlayersDB() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "UNO", "PLAYERS", null);
            if(!resultSet.next()) {
                createPlayersDB();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method reads the Players table from the DB storing it into a hash map of players.
     * This is used to prevent recurring of the same username and also to access the points of the players easily.
     */
    public void readPlayerRecordDB() {
        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT USERNAME, POINTS FROM UNO.PLAYERS");

            while (rs.next()) {
                String userName = rs.getString("USERNAME");
                int userPoints = rs.getInt("POINTS");
                players.put(userName, userPoints);
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * The method writes the player 1 and player 2 usernames and points into the Players table in the DB.
     * The method is used to update the points of the username that is within the table by reading the username inputted.
     * 
     * @param player1 as Player 1
     * @param player2 as Player 2
     */
    public void writePlayerRecordDB(Player player1, Player player2) {
        try {
            statement = conn.createStatement();
            String sql = "UPDATE UNO.PLAYERS SET POINTS = ? WHERE USERNAME = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, player1.getPoints());
            preparedStatement.setString(2, player1.getUserName());
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, player2.getPoints());
            preparedStatement.setString(2, player2.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            readPlayerRecordDB();
        }
    }

    /**
     * checkPlayerDB method checks a particular username of the player from the Table DB.
     * If a player has already played before, it pops up a welcome back message and 
     * resets the points of that username to 0. (This is caught by an exception 
     * catching the same username entry - as username column is set as unique).
     * If a new player plays, it stores its username and points as 0 in the table.
     * 
     * @param un as a String
     */
    public void checkPlayerDB(String un) {
        String userName = un.toUpperCase();
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO UNO.PLAYERS VALUES (?, 0)");
            pstmt.setString(1, userName);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Welcome " + userName+", you have been added as a new player. Your Score is 0.","",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Welcome back " + userName+", Your score has been reset to 0","",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * The method creates a Players table in the DB with the specified columns.
     */
    public void createPlayersDB() {
        try {
            Statement statement = conn.createStatement();
            String createTable = "CREATE TABLE UNO.PLAYERS (USERNAME VARCHAR(50) UNIQUE, POINTS INT)";
            statement.executeUpdate(createTable);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
} 