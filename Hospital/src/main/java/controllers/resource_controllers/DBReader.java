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
    static Logger logger = LogManager.getLogger();

    public DBReader(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public ArrayList<Patient> getAllPatients() {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        ArrayList<Patient> allPatients = new ArrayList<Patient>();
        String query = "SELECT * FROM PATIENT";
        ResultSet resultSet = null;
        try {
            resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_PATIENT");
                String name = resultSet.getString("FIRST_NAME");
                String surname = resultSet.getString("SECOND_NAME");
                int age = resultSet.getInt("AGE");
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");

                ArrayList<Appointment> appointments = dbUtils.getAppointmentsByPatientId(id);
                ArrayList<Treatment> treatments = dbUtils.getTreatmentsByPatientId(id);

                boolean recovered = resultSet.getBoolean("RECOVERED");

                allPatients.add(new Patient(name, surname, age, login, password, appointments, treatments, recovered));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.dbConnector.closeStream();
        return allPatients;
    }

    public ArrayList<Doctor> getAllDoctors() {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        ArrayList<Doctor> allDoctors = new ArrayList<Doctor>();
        String query = "SELECT * FROM DOCTOR";
        ResultSet resultSet = null;
        try {
            resultSet = this.dbConnector.getQueryResultAsResultSet(query);
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
            e.printStackTrace();
        }

        this.dbConnector.closeStream();
        return allDoctors;
    }

    public ArrayList<Nurse> getAllNurses() {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        ArrayList<Nurse> allNurses = new ArrayList<Nurse>();
        String query = "SELECT * FROM NURSE";
        ResultSet resultSet = null;
        try {
            resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            while (resultSet.next()) {
                String name = resultSet.getString("FIRST_NAME");
                String surname = resultSet.getString("SECOND_NAME");
                int age = resultSet.getInt("AGE");
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");

                allNurses.add(new Nurse(name, surname, age, login, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.dbConnector.closeStream();
        return allNurses;
    }
}
