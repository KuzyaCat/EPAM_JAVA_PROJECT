package controllers.resource_controllers;

import dbconnection.DBConnector;

import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;
import main.java.components.Appointment;
import main.java.components.Treatment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;


public class DBReader {
    private DBConnector dbConnector;
    private DBUtils dbUtils;
    static Logger logger = LogManager.getLogger();

    public DBReader(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
        if(!this.dbConnector.isConnected()) {
            this.dbConnector.connectToDataBase();
        }
        this.dbUtils = new DBUtils(this.dbConnector);
    }

    public DBUtils getDbUtils() {
        return dbUtils;
    }

    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> allPatients = new ArrayList<Patient>();
        String query = "SELECT * FROM PATIENT";
        try {
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_PATIENT");
                String name = resultSet.getString("FIRST_NAME");
                String surname = resultSet.getString("SECOND_NAME");
                int age = resultSet.getInt("AGE");
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");

                ArrayList<Appointment> appointments = this.dbUtils.getAppointmentsByPatientId(id);
                ArrayList<Treatment> treatments = this.dbUtils.getTreatmentsByPatientId(id);

                boolean recovered = resultSet.getBoolean("RECOVERED");

                allPatients.add(new Patient(name, surname, age, login, password, appointments, treatments, recovered));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return allPatients;
    }

    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> allDoctors = new ArrayList<Doctor>();
        String query = "SELECT * FROM DOCTOR";
        try {
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            while (resultSet.next()) {
                String name = resultSet.getString("FIRST_NAME");
                String surname = resultSet.getString("SECOND_NAME");
                int age = resultSet.getInt("AGE");
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");
                String department = resultSet.getString("DEPARTMENT");
                boolean isHeadOfDepartment = resultSet.getBoolean("IS_HEAD_OF_DEPARTMENT");

                allDoctors.add(new Doctor(name, surname, age, login, password, department, isHeadOfDepartment));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return allDoctors;
    }

    public ArrayList<Nurse> getAllNurses() {
        ArrayList<Nurse> allNurses = new ArrayList<Nurse>();
        String query = "SELECT * FROM NURSE";
        try {
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            while (resultSet.next()) {
                String name = resultSet.getString("FIRST_NAME");
                String surname = resultSet.getString("SECOND_NAME");
                int age = resultSet.getInt("AGE");
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");

                allNurses.add(new Nurse(name, surname, age, login, password));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return allNurses;
    }

    public int getPatientId(Patient patient) {
        return this.dbUtils.getPatientIdByLogin(patient.getLogin());
    }

    public int getDoctorId(Doctor doctor) {
        return this.dbUtils.getDoctorIdByNameAndSurname(doctor.getName(), doctor.getSurname());
    }

    public int getAppointmentIdByPatientIdAndAppointment(int patientId, Appointment appointment) {
        int doctorId = this.getDoctorId(appointment.getDoctor());
        String dateStr = "'" + appointment.getAppDate().toString().replace("_", "-") + "'";
        String appointmentIdQuery = "SELECT ID_APPOINTMENT FROM APPOINTMENT WHERE ID_PATIENT = " + patientId +
                " AND ID_DOCTOR = " + doctorId +
                " AND APPOINTMENT_DATE = " + dateStr;
        ResultSet appointmentIdSet = null;
        try {
            appointmentIdSet = this.dbConnector.getQueryResultAsResultSet(appointmentIdQuery);
            appointmentIdSet.next();

            return appointmentIdSet.getInt("ID_APPOINTMENT");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return -1;
    }
}
