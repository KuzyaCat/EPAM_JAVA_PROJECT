package main.java.usersdb;

import controllers.resource_controllers.DBReader;
import controllers.resource_controllers.DBUpdater;
import dbconnection.DBConnector;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;

import java.util.ArrayList;

public class DoctorDB {
    private DBConnector dbConnector;
    private DBReader dbReader;
    private DBUpdater dbUpdater;

    public DoctorDB() {
        this.dbConnector = new DBConnector();
        this.dbReader = new DBReader(this.dbConnector);
        this.dbUpdater = new DBUpdater(this.dbConnector);
    }
    public ArrayList<Doctor> getAllDoctors() {
        return this.dbReader.getAllDoctors();
    }

    public Doctor getDoctor(String name, String surname) {
        return this.dbReader.getDbUtils().getDoctorByNameAndSurname(name, surname);
    }

    public Doctor getDepartmentHead(String department) {
        ArrayList<Doctor> doctors = this.getAllDoctors();

        for (Doctor doctor : doctors) {
            if (doctor.isHeadOfDepartment() && doctor.getDepartment().equals(department)) {
                return doctor;
            }
        }
        throw new IllegalArgumentException("User not found");
    }

    public void editDoctor(Doctor editedDoctor) {
        this.dbUpdater.updateDoctor(editedDoctor);
    }

    public void writeIsDepartmentHead(Doctor doctor) {
        Doctor head = this.getDepartmentHead(doctor.getDepartment());
        head.setHeadOfDepartment(false);
        this.editDoctor(head);
        doctor.setHeadOfDepartment(true);
        this.editDoctor(doctor);
    }

    public String getAppointments(Doctor doctor) {
        String allPatientsOfDoctorStr = "";
        ArrayList<Patient> allPatientsOfDoctor = this.dbReader.getDbUtils().getPatientsByDoctor(doctor);
        for(Patient patient: allPatientsOfDoctor) {
            allPatientsOfDoctorStr += patient.getName() + " " + patient.getSurname() + ";\n";
        }

        return allPatientsOfDoctorStr;
    }
}
