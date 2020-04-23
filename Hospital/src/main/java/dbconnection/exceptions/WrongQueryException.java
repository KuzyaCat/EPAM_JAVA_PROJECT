package dbconnection.exceptions;

public class WrongQueryException extends Exception {

    public WrongQueryException() {
        super("An entered query cannot be executed");
    }
}
