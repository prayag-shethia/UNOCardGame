/*
 * The programs are designed for PDC paper
 */
package GameApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * DBManager is used to manage the DataBase and the connections made to it.
 * DB Manager class uses as singleton pattern to ever use one and only one instance of the class
 * 
 * @author Daniel Kathiresan
 * @author Taylor Pringle
 * @author Prayag Shethia
 */
public final class DBManager {

    private static DBManager dBManager;
    private static final String USER_NAME = "uno"; //your DB username
    private static final String PASSWORD = "uno"; //your DB password
    private static final String URL = "jdbc:derby:UNODB_Ebd;create=true";   //url of the DB host

    Connection conn;

    private DBManager() {
        establishConnection();
    }
    
    /**
     * The method is used to pass on the one instance created.
     * If there hasn't been an object created before, it creates a new one.
     * Otherwise, it returns the same instance created before.
     * 
     * @return dBManager as the DBManager object
     */
    public static synchronized DBManager getDBManagerInstance(){
        if(dBManager == null){
            dBManager = new DBManager();
        }
        return dBManager;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public Connection getConnection() {
        return this.conn;
    }

    /**
     * The method is used to create a connection with the DB using the Username, password and the URL for connection
     */
    public void establishConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    /**
     * CloseConnections() method is used to close any existing connections to the DB
     */
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * This method is used to execute the query of SQL String passed on as a parameter.
     * It uses the DB connection to create the statement
     * 
     * @param sql
     * @return resultSet an instance of ResultSet
     */
    public ResultSet queryDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    /**
     * The method is used to update the DB by executing the SQL statement passed on.
     * 
     * @param sql 
     */
    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Droptable() method is used to drop the Deck table in the DB is previously made.
     * It executes the updated query and creates the statement using the DB connection.
     */
    public void dropTable() {

        Connection connection = this.conn;
        Statement statement = null;

        try {
            String s = "DROP TABLE DECK";
            statement = connection.createStatement();
            statement.executeUpdate(s);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
