package controllers.resource_controllers;

import dbconnection.DBConnector;

import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;
import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.date.GregorianDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DBUpdater {
    private DBConnector dbConnector;
    private DBUtils dbUtils;
    static Logger logger = LogManager.getLogger();

    public DBUpdater(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
        if(!this.dbConnector.isConnected()) {
            this.dbConnector.connectToDataBase();
        }
        this.dbUtils = new DBUtils(this.dbConnector);
    }

    public void deletePatientRow(int patientId) {
        String query = "DELETE FROM PATIENT WHERE ID_PATIENT = " + patientId;
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(query);
    }

    public void deleteAppointmentRow(int appointmentId) {
        String query = "DELETE FROM APPOINTMENT WHERE ID_APPOINTMENT = " + appointmentId;
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(query);
    }

    public void deleteTreatmentRow(int treatmentId) {
        String query = "DELETE FROM TREATMENT WHERE ID_TREATMENT = " + treatmentId;
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(query);
    }

    public void deletePatient(Patient removedPatient) {
        String removedPatientLogin = removedPatient.getLogin();
        try {
            int removedPatientId = this.dbUtils.getPatientIdByLogin(removedPatientLogin);
            String selectAppointmentIdsByPatientIdQuery = "SELECT ID_APPOINTMENT FROM APPOINTMENT WHERE ID_PATIENT = " + removedPatientId;

            ResultSet appointmentIdsSet = this.dbConnector.getQueryResultAsResultSet(selectAppointmentIdsByPatientIdQuery);
            while(appointmentIdsSet.next()) {
                int currentAppointmentId = appointmentIdsSet.getInt("ID_APPOINTMENT");
                int currentTreatmentId = this.dbUtils.getTreatmentIdByAppointmentId(currentAppointmentId);

                this.deleteTreatmentRow(currentTreatmentId);
                this.deleteAppointmentRow(currentAppointmentId);
            }
            this.deletePatientRow(removedPatientId);

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void addPatient(Patient newPatient) {
        int patientId = this.dbUtils.getLengthOfTable("PATIENT") + 1;
        String name = "'" + newPatient.getName() + "'";
        String surname = "'" + newPatient.getSurname() + "'";
        int age = newPatient.getAge();
        String login = "'" + newPatient.getLogin() + "'";
        String password = "'" + newPatient.getPassword() + "'";
        boolean recovered = newPatient.isRecovered();

        String insertPatientQuery = "INSERT INTO PATIENT VALUES(" + patientId +
                ", " + name +
                ", " + surname +
                ", " + age +
                ", " + login +
                ", " + password +
                ", " + recovered + ")";
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(insertPatientQuery);
    }

    public void addAppointment(Appointment newAppointment, int patientId) {
        int appointmentId = this.dbUtils.getLengthOfTable("APPOINTMENT") + 1;

        Doctor appointmentDoctor = newAppointment.getDoctor();
        int doctorId = this.dbUtils.getDoctorIdByNameAndSurname(appointmentDoctor.getName(), appointmentDoctor.getSurname());

        GregorianDate appointmentGregDate = newAppointment.getAppDate();
        String dateString = "'" + appointmentGregDate.toString().replace("_", "-") + "'";

        String insertAppointmentQuery = "INSERT INTO APPOINTMENT VALUES(" + appointmentId +
                ", " + doctorId +
                ", " + patientId +
                ", " + dateString + ")";
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(insertAppointmentQuery);
    }

    public void addTreatment(Treatment treatment, int appointmentId) {
        int treatmentId = this.dbUtils.getLengthOfTable("TREATMENT") + 1;

        String insertTreatmentQuery = "INSERT INTO APPOINTMENT VALUES(" + treatmentId +
                ", '" + treatment.getProcedures() + "'" +
                ", '" + treatment.getMedicines() + "'" +
                ", '" + treatment.getOperations() + "'" +
                ", '" + treatment.getDiagnoses() + "'" +
                ", " + appointmentId + ")";
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(insertTreatmentQuery);
    }

    public void updatePatient(Patient updatedPatient) {
        String name = "'" + updatedPatient.getName() + "'";
        String surname = "'" + updatedPatient.getSurname() + "'";
        int age = updatedPatient.getAge();
        String login = "'" + updatedPatient.getLogin() + "'";
        String password = "'" + updatedPatient.getPassword() + "'";
        boolean recovered = updatedPatient.isRecovered();

        int updatedPatientId = this.dbUtils.getPatientIdByLogin(updatedPatient.getLogin());
        String updatePatientQuery = "UPDATE PATIENT SET " +
                "FIRST_NAME = " + name +
                ", SECOND_NAME = " + surname +
                ", AGE = " + age +
                ", LOGIN = " + login +
                ", PASSWORD = " + password +
                ", RECOVERED = " + recovered +
                " WHERE ID_PATIENT = " + updatedPatientId;
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(updatePatientQuery);
    }

    public void updateDoctor(Doctor updatedDoctor) {
        String name = "'" + updatedDoctor.getName() + "'";
        String surname = "'" + updatedDoctor.getSurname() + "'";
        int age = updatedDoctor.getAge();
        String login = "'" + updatedDoctor.getLogin() + "'";
        String password = "'" + updatedDoctor.getPassword() + "'";
        String department = "'" + updatedDoctor.getDepartment() + "'";
        boolean isHeadOfDepartment = updatedDoctor.isHeadOfDepartment();

        int updatedDoctorId = this.dbUtils.getDoctorIdByNameAndSurname(name, surname);
        String updateDoctorQuery = "UPDATE DOCTOR SET " +
                "FIRST_NAME = " + name +
                ", SECOND_NAME = " + surname +
                ", AGE = " + age +
                ", LOGIN = " + login +
                ", PASSWORD = " + password +
                ", DEPARTMENT = " + department +
                ", IS_HEAD_OF_DEPARTMENT = " + isHeadOfDepartment +
                " WHERE ID_DOCTOR = " + updatedDoctorId;
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(updateDoctorQuery);
    }

    public void addRowToNurseTaskLog(int appointmentId, int nurseId) {
        int nurseTaskLogId = this.dbUtils.getLengthOfTable("NURSE_TASK_LOG") + 1;

        String insertNurseTaskLogRowQuery = "INSERT INTO NURSE_TASK_LOG VALUES(" + nurseTaskLogId +
                ", " + nurseId +
                ", " + appointmentId + ")";
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(insertNurseTaskLogRowQuery);
    }

    public void deleteRowFromNurseTaskLog(int appointmentId, int nurseId) {
        String deleteNurseTaskLogQuery = "DELETE FROM NURSE_TASK_LOG WHERE ID_NURSE = " + nurseId + " AND ID_APPOINTMENT = " + appointmentId;
        this.dbConnector.executeInsertOrUpdateOrDeleteQuery(deleteNurseTaskLogQuery);
    }

    public void changeRecoveredStatusByPatientNameAndSurname(String name, String surname, boolean isRecovered) {
        try {
            int patientId = this.dbUtils.getPatientIdByNameAndSurname(name, surname);

            String updatePatientRecoverStatusQuery = "UPDATE PATIENT SET RECOVERED = " + isRecovered + " WHERE ID_PATIENT = " + patientId;
            this.dbConnector.executeInsertOrUpdateOrDeleteQuery(updatePatientRecoverStatusQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
