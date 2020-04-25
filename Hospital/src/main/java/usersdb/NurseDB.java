package main.java.usersdb;

import controllers.resource_controllers.DBReader;
import controllers.resource_controllers.DBUpdater;
import dbconnection.DBConnector;
import main.java.users.stuff.Nurse;

import java.util.ArrayList;

public class NurseDB {
    private DBConnector dbConnector;
    private DBReader dbReader;
    private DBUpdater dbUpdater;

    public NurseDB() {
        this.dbConnector = new DBConnector();
        this.dbReader = new DBReader(this.dbConnector);
        this.dbUpdater = new DBUpdater(this.dbConnector);
    }

    public DBReader getDbReader() {
        return dbReader;
    }

    public DBUpdater getDbUpdater() {
        return dbUpdater;
    }

    public ArrayList<Nurse> getAllNurses() {
        return this.dbReader.getAllNurses();
    }
}
