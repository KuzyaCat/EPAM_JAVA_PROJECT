package main.java;

import main.java.controllers.resource_controllers.DataBaseIO;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;

public class Main {
    public static void main(String[] args) {
        DataBaseIO dbio = new DataBaseIO();
        try {
            String[] patientsStr = dbio.readArrayByUserGroup('p');
            Patient[] patients = new Patient[patientsStr.length];

            for(int i = 0; i < patientsStr.length; i ++) {
                patients[i] = (new Patient()).parseString(patientsStr[i]);
                System.out.println(patients[i].toString());
            }
            System.out.println("");



            String[] doctorsStr = dbio.readArrayByUserGroup('d');
            Doctor[] doctors = new Doctor[doctorsStr.length];

            for(int i = 0; i < doctorsStr.length; i ++) {
                doctors[i] = (new Doctor()).parseString(doctorsStr[i]);
                System.out.println(doctors[i].toString());
            }
            System.out.println("");



            String[] nursesStr = dbio.readArrayByUserGroup('n');
            Nurse[] nurses = new Nurse[nursesStr.length];

            for(int i = 0; i < nursesStr.length; i ++) {
                nurses[i] = (new Nurse()).parseString(nursesStr[i]);
                System.out.println(nurses[i].toString());
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbio.shutdown();
    }
}
