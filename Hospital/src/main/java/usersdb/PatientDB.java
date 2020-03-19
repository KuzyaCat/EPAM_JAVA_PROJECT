package main.java.usersdb;

import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.controllers.resource_controllers.DataBaseIO;
import main.java.users.Patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientDB {
    public Patient[] getAllPatients() {
        DataBaseIO dbio = new DataBaseIO();

        try {
            String[] patientsStr = dbio.readArrayByUserGroup('p');
            Patient[] patients = new Patient[patientsStr.length];

            for(int i = 0; i < patientsStr.length; i += 1) {
                patients[i] = (new Patient()).parseString(patientsStr[i]);
            }

            return patients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbio.shutdown();

        throw new NullPointerException("Cannot read patients");
    }

    private int getIndex(Patient patient) {
        Patient[] patients = this.getAllPatients();

        for (int i = 0; i < patients.length; i += 1) {
            if (patients[i].getName().equals(patient.getName()) && patients[i].getSurname().equals(patient.getSurname())) {
                return i;
            }
        }
        throw new IllegalArgumentException("User not found");
    }

    public Patient getPatient(String name, String surname) {
        Patient[] patients = this.getAllPatients();

        for (Patient patient : patients) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) {
                return patient;
            }
        }
        throw new IllegalArgumentException("User not found");
    }

    public void editPatient(Patient editedPatient) {
        DataBaseIO dbio = new DataBaseIO();
        int patientToEditIndex = this.getIndex(editedPatient);
        dbio.removePatient(patientToEditIndex);
        dbio.insertPatient(editedPatient.toString(), patientToEditIndex);
    }

    public void writeAppointment(Patient patient, Appointment appointment) {
        Appointment[] appointments = patient.getAppointments();

        List<Appointment> appointmentsList = new ArrayList<>();
        Collections.addAll(appointmentsList, appointments);
        appointmentsList.add(appointment);
        appointments = appointmentsList.toArray(appointments);
        patient.setAppointments(appointments);
        this.editPatient(patient);
    }

    public void writeTreatment(Patient patient, Treatment treatment) {
        Treatment[] treatments = patient.getTreatments();

        List<Treatment> treatmentsList = new ArrayList<>();
        Collections.addAll(treatmentsList, treatments);
        treatmentsList.add(treatment);
        treatments = treatmentsList.toArray(treatments);
        patient.setTreatments(treatments);
        this.editPatient(patient);
    }

    public void writeDiagnose(Patient patient, String diagnose) {
        String[] diagnoses = patient.getDiagnoses();

        List<String> diagnosesList = new ArrayList<>();
        Collections.addAll(diagnosesList, diagnoses);
        diagnosesList.add(diagnose);
        diagnoses = diagnosesList.toArray(diagnoses);
        patient.setDiagnoses(diagnoses);
        this.editPatient(patient);
    }

    public void writeIsRecovered(Patient patient, boolean isRecovered) {
        patient.setRecovered(isRecovered);
        this.editPatient(patient);
    }
}