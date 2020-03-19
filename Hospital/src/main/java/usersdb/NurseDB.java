package main.java.usersdb;

import main.java.controllers.resource_controllers.DataBaseIO;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;

public class NurseDB {
    private Nurse[] getAllNurses() {
        DataBaseIO dbio = new DataBaseIO();

        try {
            String[] nursesStr = dbio.readArrayByUserGroup('n');
            Nurse[] nurses = new Nurse[nursesStr.length];

            for(int i = 0; i < nursesStr.length; i += 1) {
                nurses[i] = (new Nurse()).parseString(nursesStr[i]);
            }

            return nurses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbio.shutdown();

        throw new NullPointerException("Cannot read nurses");
    }

    public Nurse getNurse(String name, String surname) {
        Nurse[] nurses = this.getAllNurses();

        for (Nurse nurse : nurses) {
            if (nurse.getName().equals(name) && nurse.getSurname().equals(surname)) {
                return nurse;
            }
        }
        throw new IllegalArgumentException("User not found");
    }
}
