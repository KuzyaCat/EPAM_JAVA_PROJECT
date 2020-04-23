package dbconnection;

import dbconnection.exceptions.WrongQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DBConnector {
    private String url = "localhost:3306";
    private String user = "root";
    private String password = "mysql123";
    private Connection connection;

    static Logger logger = LogManager.getLogger();

    public void connectToDataBase() {
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            logger.error("Cannot connect to database");
        }
    }

    public ResultSet getQueryResultAsResultSet(String query) throws WrongQueryException {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            return queryResult;
        } catch (SQLException e) {
            logger.error("Cannot execute query");
        }
        throw new WrongQueryException();
    }

    public void closeStream() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
