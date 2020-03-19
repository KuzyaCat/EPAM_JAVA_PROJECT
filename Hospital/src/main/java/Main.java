package main.java;

import main.java.controllers.menus.user_menus.NurseMenu;
import main.java.usersdb.NurseDB;

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

        /*DoctorDB doctorDB = new DoctorDB();
        DoctorMenu menu = new DoctorMenu(doctorDB.getDoctor("Gennadiy", "Gorin"));
        menu.initMenu();*/

        NurseDB nurseDB = new NurseDB();
        NurseMenu menu = new NurseMenu(nurseDB.getNurse("Nina", "Nikitishna"));
        menu.initMenu();
    }
}
