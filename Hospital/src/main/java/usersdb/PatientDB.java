package main.java.usersdb;

import main.java.controllers.resource_controllers.DBReader;
import main.java.controllers.resource_controllers.DBUpdater;
import main.java.dbconnection.DBConnector;
import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.users.Patient;

import java.util.ArrayList;

public class PatientDB {
    private DBConnector dbConnector;
    private DBReader dbReader;
    private DBUpdater dbUpdater;

    public PatientDB() {
        this.dbConnector = new DBConnector();
        this.dbReader = new DBReader(this.dbConnector);
        this.dbUpdater = new DBUpdater(this.dbConnector);
    }

    public ArrayList<Patient> getAllPatients() {
        return this.dbReader.getAllPatients();
    }

    public Patient getPatient(String name, String surname) {
        return this.dbReader.getDbUtils().getPatientByNameAndSurname(name, surname);
    }

    public void editPatient(Patient editedPatient) {
        this.dbUpdater.updatePatient(editedPatient);
    }

    public void writeAppointment(Patient patient, Appointment appointment) {
        ArrayList<Appointment> appointments = patient.getAppointments();
        appointments.add(appointment);
        patient.setAppointments(appointments);
        this.dbUpdater.addAppointment(appointment, this.dbReader.getPatientId(patient));
    }

    public void writeTreatment(Patient patient, Appointment appointment, Treatment treatment) {
        ArrayList<Treatment> treatments = patient.getTreatments();
        treatments.add(treatment);
        patient.setTreatments(treatments);
        this.dbUpdater.addTreatment(treatment, this.dbReader.getAppointmentIdByPatientIdAndAppointment(
                this.dbReader.getPatientId(patient), appointment
        ));
    }

    public void writeIsRecovered(Patient patient, boolean isRecovered) {
        patient.setRecovered(isRecovered);
        this.editPatient(patient);
    }
}