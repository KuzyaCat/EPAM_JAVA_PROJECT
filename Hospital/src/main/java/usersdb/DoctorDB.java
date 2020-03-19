package main.java.usersdb;

import main.java.controllers.resource_controllers.DataBaseIO;
import main.java.users.stuff.Doctor;

public class DoctorDB {
    private Doctor[] getAllDoctors() {
        DataBaseIO dbio = new DataBaseIO();

        try {
            String[] doctorsStr = dbio.readArrayByUserGroup('d');
            Doctor[] doctors = new Doctor[doctorsStr.length];

            for(int i = 0; i < doctorsStr.length; i += 1) {
                doctors[i] = (new Doctor()).parseString(doctorsStr[i]);
            }

            return doctors;
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbio.shutdown();

        throw new NullPointerException("Cannot read doctors");
    }

    public Doctor getDoctor(String name, String surname) {
        Doctor[] doctors = this.getAllDoctors();

        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(name) && doctor.getSurname().equals(surname)) {
                return doctor;
            }
        }
        throw new IllegalArgumentException("User not found");
    }

    public Doctor getDepartmentHead() {
        Doctor[] doctors = this.getAllDoctors();

        for (Doctor doctor : doctors) {
            if (doctor.isHeadOfDepartment()) {
                return doctor;
            }
        }
        throw new IllegalArgumentException("User not found");
    }
}
