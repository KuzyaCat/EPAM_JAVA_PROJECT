package main.java;

import main.java.components.Appointment;
import main.java.controllers.menus.DoctorMenu;
import main.java.controllers.menus.PatientMenu;
import main.java.controllers.resource_controllers.DataBaseIO;
import main.java.users.Patient;
import main.java.users.leadership.ChiefPhysician;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;
import main.java.usersdb.DoctorDB;
import main.java.usersdb.PatientDB;
import main.java.controllers.resource_controllers.Authorizer;

public class Main {
    public static void main(String[] args) {
        /*Authorizer auth = new Authorizer();
        try {
            System.out.println(auth.logIn().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*PatientDB patientDB = new PatientDB();
        PatientMenu menu = new PatientMenu(patientDB.getPatient("Mihail", "Sobolev"));
        menu.initMenu();*/

        DoctorDB doctorDB = new DoctorDB();
        DoctorMenu menu = new DoctorMenu(doctorDB.getDoctor("Gennadiy", "Gorin"));
        menu.initMenu();
    }
}
