package main.java.usersdb;

import main.java.controllers.resource_controllers.DBReader;
import main.java.controllers.resource_controllers.DBUpdater;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;

import java.util.ArrayList;

public class DoctorDB {
    private DBReader dbReader;
    private DBUpdater dbUpdater;

    public DoctorDB() {
        this.dbReader = new DBReader();
        this.dbUpdater = new DBUpdater();
    }

    public DBReader getDbReader() {
        return dbReader;
    }

    public ArrayList<Doctor> getAllDoctors() {
        return this.dbReader.getAllDoctors();
    }

    public Doctor getDoctor(String name, String surname) {
        return this.dbReader.getDoctorByNameAndSurname(name, surname);
    }

    public Doctor getDepartmentHead(String department) {
        ArrayList<Doctor> doctors = this.getAllDoctors();

        for (Doctor doctor : doctors) {
            if (doctor.getIsHeadOfDepartment() && doctor.getDepartment().equals(department)) {
                return doctor;
            }
        }
        throw new IllegalArgumentException("Doctor not found");
    }

    public void editDoctor(Doctor editedDoctor) {
        this.dbUpdater.updateDoctor(editedDoctor);
    }

    public void writeIsDepartmentHead(Doctor doctor) {
        Doctor head = this.getDepartmentHead(doctor.getDepartment());
        head.setIsHeadOfDepartment(false);
        this.editDoctor(head);
        doctor.setIsHeadOfDepartment(true);
        this.editDoctor(doctor);
    }

    public String getAppointments(Doctor doctor) {
        String allPatientsOfDoctorStr = "";
        ArrayList<Patient> allPatientsOfDoctor = this.dbReader.getPatientsByDoctor(doctor);
        for(Patient patient: allPatientsOfDoctor) {
            allPatientsOfDoctorStr += patient.getName() + " " + patient.getSurname() + ";\n";
        }

        return allPatientsOfDoctorStr;
    }

    public void shutdown() {
        this.dbReader.shutdown();
        this.dbUpdater.shutdown();
    }
}
