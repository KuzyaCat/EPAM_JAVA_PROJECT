package main.java.usersdb;

import main.java.controllers.resource_controllers.DBReader;
import main.java.controllers.resource_controllers.DBUpdater;
import main.java.users.stuff.Nurse;

import java.util.ArrayList;

public class NurseDB {
    private DBReader dbReader;
    private DBUpdater dbUpdater;

    public NurseDB() {
        this.dbReader = new DBReader();
        this.dbUpdater = new DBUpdater();
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

    public void shutdown() {
        this.dbReader.shutdown();
        this.dbUpdater.shutdown();
    }
}
