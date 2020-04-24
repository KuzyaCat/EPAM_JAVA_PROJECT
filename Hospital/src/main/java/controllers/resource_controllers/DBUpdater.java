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

public class DBUpdater {
    private DBConnector dbConnector;
    static Logger logger = LogManager.getLogger();

    public DBUpdater(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void deletePatientRow(int patientId) {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        String query = "DELETE FROM PATIENT WHERE ID_PATIENT = " + patientId;
        try {
            this.dbConnector.executeUpdateOrDeleteQuery(query);
        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        }

        this.dbConnector.closeStream();
    }

    public void deleteAppointmentRow(int appointmentId) {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        String query = "DELETE FROM APPOINTMENT WHERE ID_APPOINTMENT = " + appointmentId;
        try {
            this.dbConnector.executeUpdateOrDeleteQuery(query);
        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        }

        this.dbConnector.closeStream();
    }

    public void deleteTreatmentmentRow(int treatmentId) {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        String query = "DELETE FROM TREATMENT WHERE ID_TREATMENT = " + treatmentId;
        try {
            this.dbConnector.executeUpdateOrDeleteQuery(query);
        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        }

        this.dbConnector.closeStream();
    }
}
