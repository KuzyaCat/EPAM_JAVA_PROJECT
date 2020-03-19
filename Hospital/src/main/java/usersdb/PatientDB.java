package main.java.usersdb;

import main.java.controllers.resource_controllers.DataBaseIO;
import main.java.users.Patient;

public class PatientDB {

    public Patient getPatientFromFile(String name, String surname) {
        DataBaseIO dbio = new DataBaseIO();

        try {
            String[] patientsStr = dbio.readArrayByUserGroup('p');
            Patient[] patients = new Patient[patientsStr.length];

            for(int i = 0; i < patientsStr.length; i ++) {
                patients[i] = (new Patient()).parseString(patientsStr[i]);
                if (patients[i].getName().equals(name) && patients[i].getSurname().equals(surname)) {
                    return patients[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbio.shutdown();

        throw new IllegalArgumentException("User not found");
    }
}