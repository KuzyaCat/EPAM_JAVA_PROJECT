package dbconnection;

import dbconnection.exceptions.WrongQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DataBaseIO {
    private String url = "database";
    private String user = "user";
    private String password = "";
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
}
