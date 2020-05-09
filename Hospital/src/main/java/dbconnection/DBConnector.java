package main.java.dbconnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DBConnector {
    private String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
    private String user = "root";
    private String password = "mysql";
    private Connection connection;

    static Logger logger = LogManager.getLogger();

    public DBConnector() {
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            this.connection.close();
        } catch ( SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void connectToDataBase() {
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public ResultSet getQueryResultAsResultSet(String query) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(query);
    }

    public void executeInsertOrUpdateOrDeleteQuery(String query) {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public boolean isConnected() {
        try {
            return !this.connection.isClosed();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public void closeStream() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
