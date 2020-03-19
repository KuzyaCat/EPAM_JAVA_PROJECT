package main.java;

import main.java.components.Appointment;
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

        DoctorDB doctorDB = new DoctorDB();
        Doctor newHead = doctorDB.getDoctor("Nikolai", "Grachev");
        ChiefPhysician chiefPhysician = new ChiefPhysician("Greg", "House", 50, "drhouse", "drhouse");
        chiefPhysician.appointDepHead(newHead);
    }
}
