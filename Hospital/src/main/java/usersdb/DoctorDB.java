package main.java.usersdb;

import main.java.components.Appointment;
import main.java.controllers.resource_controllers.DataBaseIO;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;

import java.util.ArrayList;
import java.util.List;

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

    private int getIndex(Doctor doctor) {
        Doctor[] doctors = this.getAllDoctors();

        for (int i = 0; i < doctors.length; i += 1) {
            if (doctors[i].getName().equals(doctor.getName()) && doctors[i].getSurname().equals(doctor.getSurname())) {
                return i;
            }
        }
        throw new IllegalArgumentException("User not found");
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

    public void editDoctor(Doctor editedDoctor) {
        DataBaseIO dbio = new DataBaseIO();
        int doctorToEditIndex = this.getIndex(editedDoctor);
        dbio.removeDoctor(doctorToEditIndex);
        dbio.insertDoctor(editedDoctor.toString(), doctorToEditIndex);
    }

    public void writeIsDepartmentHead(Doctor doctor) {
        Doctor head = this.getDepartmentHead();
        head.setHeadOfDepartment(false);
        editDoctor(head);
        doctor.setHeadOfDepartment(true);
        editDoctor(doctor);
    }

    public String getAppointments(Doctor doctor) {
        PatientDB patientDB = new PatientDB();
        Patient[] patients = patientDB.getAllPatients();
        StringBuilder appointmentsStr = new StringBuilder();
        System.out.println(doctor.toString());

        for (int i = 0; i < patients.length; i += 1) {
            Appointment[] iAppointments = patients[i].getAppointments();
            for (Appointment iAppointment : iAppointments) {
                if (iAppointment.getDoctor().getName().equals(doctor.getName()) &&
                        iAppointment.getDoctor().getSurname().equals(doctor.getSurname())) {
                    appointmentsStr.append(patients[i].getName()).append(" ").append(patients[i].getSurname()).append("; ");
                }
            }
        }
        if (appointmentsStr.length() == 0) {
            return "No appointments";
        }

        return String.valueOf(appointmentsStr);
    }
}
