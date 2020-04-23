package controllers.resource_controllers;

import dbconnection.DBConnector;
import dbconnection.exceptions.StreamIsClosedException;
import dbconnection.exceptions.WrongQueryException;

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

    public ArrayList<Patient> getAllPatients() throws WrongQueryException, SQLException, StreamIsClosedException {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        ArrayList<Patient> allPatients = new ArrayList<Patient>();
        String query = "SELECT * FROM Patients";
        ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("ID_PATIENT");
            String name = resultSet.getString("FIRST_NAME");
            String surname = resultSet.getString("SECOND_NAME");
            int age = resultSet.getInt("AGE");
            String login = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");

            ArrayList<Appointment> appointments = dbUtils.getAppointmentsByPatientId(id);
            ArrayList<Treatment> treatments = dbUtils.getTreatmentsByPatientId(id);
            ArrayList<String> diagnoses = dbUtils.getDiagnosesByPatientId(id);

            boolean recovered = resultSet.getBoolean("RECOVERED");

            allPatients.add(new Patient(name, surname, age, login, password, appointments, treatments, diagnoses, recovered));
        }

        this.dbConnector.closeStream();
        return allPatients;
    }
}
