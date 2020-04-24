package main.java;

import controllers.resource_controllers.DBReader;
import dbconnection.DBConnector;
import dbconnection.exceptions.StreamIsClosedException;
import dbconnection.exceptions.WrongQueryException;
import main.java.controllers.menus.MainMenu;
import main.java.controllers.menus.user_menus.NurseMenu;
import main.java.usersdb.NurseDB;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*PatientDB patientDB = new PatientDB();
        PatientMenu menu = new PatientMenu(patientDB.getPatient("Mihail", "Sobolev"));
        menu.initMenu();*/

        /*DoctorDB doctorDB = new DoctorDB();
        DoctorMenu menu = new DoctorMenu(doctorDB.getDoctor("Gennadiy", "Gorin"));
        menu.initMenu();*/

        /*NurseDB nurseDB = new NurseDB();
        NurseMenu menu = new NurseMenu(nurseDB.getNurse("Nina", "Nikitishna"));
        menu.initMenu();*/

        //MainMenu mainMenu = new MainMenu();

        DBConnector dbConnector = new DBConnector();
        DBReader dbReader = new DBReader(dbConnector);
        try {
            ArrayList<Patient> allPatients = dbReader.getAllPatients();
            for(Patient p: allPatients) {
                System.out.println(p.toString());
            }
        } catch (WrongQueryException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (StreamIsClosedException e) {
            e.printStackTrace();
        }
    }
}
