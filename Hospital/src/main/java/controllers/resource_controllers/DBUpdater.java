package controllers.resource_controllers;

import dbconnection.DBConnector;
import dbconnection.exceptions.StreamIsClosedException;
import dbconnection.exceptions.WrongQueryException;

import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;
import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.date.GregorianDate;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(query);

        this.dbConnector.closeStream();
    }

    public void deleteAppointmentRow(int appointmentId) {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        String query = "DELETE FROM APPOINTMENT WHERE ID_APPOINTMENT = " + appointmentId;
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(query);

        this.dbConnector.closeStream();
    }

    public void deleteTreatmentRow(int treatmentId) {
        this.dbConnector.connectToDataBase();

        String query = "DELETE FROM TREATMENT WHERE ID_TREATMENT = " + treatmentId;
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(query);

        this.dbConnector.closeStream();
    }

    public void deletePatient(Patient removedPatient) {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        String removedPatientLogin = removedPatient.getLogin();
        try {
            int removedPatientId = dbUtils.getPatientIdByLogin(removedPatientLogin);
            String selectAppointmentIdsByPatientIdQuery = "SELECT ID_APPOINTMENT FROM APPOINTMENT WHERE ID_PATIENT = " + removedPatientId;

            ResultSet appointmentIdsSet = this.dbConnector.getQueryResultAsResultSet(selectAppointmentIdsByPatientIdQuery);
            while(appointmentIdsSet.next()) {
                int currentAppointmentId = appointmentIdsSet.getInt("ID_APPOINTMENT");
                int currentTreatmentId = dbUtils.getTreatmentIdByAppointmentId(currentAppointmentId);

                this.deleteTreatmentRow(currentTreatmentId);
                this.deleteAppointmentRow(currentAppointmentId);
            }
            this.deletePatientRow(removedPatientId);

        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        } catch (StreamIsClosedException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        this.dbConnector.closeStream();
    }

    public void addAppointment(Appointment newAppointment, int patientId) {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        try {
            int appointmentId = dbUtils.getLengthOfTable("APPOINTMENT") + 1;

            Doctor appointmentDoctor = newAppointment.getDoctor();
            int doctorId = dbUtils.getDoctorIdByNameAndSurname(appointmentDoctor.getName(), appointmentDoctor.getSurname());

            GregorianDate appointmentGregDate = newAppointment.getAppDate();
            String dateString = "'" + appointmentGregDate.toString().replace("_", "-") + "'";

            String insertAppointmentQuery = "INSERT INTO APPOINTMENT VALUES(" + appointmentId +
                    ", " + doctorId +
                    ", " + patientId +
                    ", " + dateString + ")";
            this.dbConnector.executeInsertOrUpdateOrDeleteQuery(insertAppointmentQuery);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        this.dbConnector.closeStream();
    }

    public void addTreatment(Treatment treatment, String diagnose, int appointmentId) {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        try {
            int treatmentId = dbUtils.getLengthOfTable("TREATMENT") + 1;

            String insertTreatmentQuery = "INSERT INTO APPOINTMENT VALUES(" + treatmentId +
                    ", '" + treatment.getProcedures() + "'" +
                    ", '" + treatment.getMedicines() + "'" +
                    ", '" + treatment.getOperations() + "'" +
                    ", '" + diagnose + "'" +
                    ", " + appointmentId + ")";
            this.dbConnector.executeInsertOrUpdateOrDeleteQuery(insertTreatmentQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.dbConnector.closeStream();
    }

    public void changeRecoveredStatusByPatientNameAndSurname(String name, String surname, boolean isRecovered) {
        this.dbConnector.connectToDataBase();
        DBUtils dbUtils = new DBUtils(this.dbConnector);

        try {
            int patientId = dbUtils.getPatientIdByNameAndSurname(name, surname);

            String updatePatientRecoverStatusQuery = "UPDATE PATIENT SET RECOVERED = " + isRecovered + " WHERE ID_PATIENT = " + patientId;
            this.dbConnector.executeInsertOrUpdateOrDeleteQuery(updatePatientRecoverStatusQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.dbConnector.closeStream();
    }
}
