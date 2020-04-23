package controllers.resource_controllers;

import dbconnection.DBConnector;
import dbconnection.exceptions.StreamIsClosedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;
import main.java.components.Appointment;
import main.java.components.Treatment;

import main.java.date.GregorianDate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class DBUtils {
    private DBConnector dbConnector;
    static Logger logger = LogManager.getLogger();

    public DBUtils(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public ArrayList<String> getDiagnosesByPatientId(int id) throws StreamIsClosedException {
        try {
            ArrayList<String> diagnoses = new ArrayList<String>();
            String query = "SELECT * FROM DIAGNOSE WHERE ID_PATIENT = " + id;
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            while (resultSet.next()) {
                diagnoses.add(resultSet.getString("NAME_OF_DIAGNOSE"));
            }

            return diagnoses;
        }
        catch (Exception e) {
            logger.error("Stream is closed");
        }
        throw new StreamIsClosedException();
    }

    public ArrayList<Appointment> getAppointmentsByPatientId(int id) throws StreamIsClosedException {
        try {
            ArrayList<Appointment> appointments = new ArrayList<Appointment>();
            String appointmentsQuery = "SELECT * FROM APPOINTMENT WHERE ID_PATIENT = " + id;
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(appointmentsQuery);
            while (resultSet.next()) {
                int appointmentDoctorId = resultSet.getInt("ID_DOCTOR");
                Doctor appointmentDoctor = this.getDoctorsById(appointmentDoctorId);

                String appointmentDepartment = appointmentDoctor.getDepartment();

                Date appointmentDate = resultSet.getDate("APPOINTMENT_DATE");
                GregorianDate appointmentGregDate = new GregorianDate(appointmentDate);

                appointments.add(new Appointment(appointmentDoctor, appointmentDepartment, appointmentGregDate));
            }

            return appointments;
        }
        catch (Exception e) {
            logger.error("Stream is closed");
        }
        throw new StreamIsClosedException();
    }

    public Doctor getDoctorsById(int doctorId) throws StreamIsClosedException {
        try {
            String doctorsQuery = "SELECT * FROM DOCTOR WHERE ID_DOCTOR = " + doctorId;
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(doctorsQuery);
            resultSet.next();

            String name = resultSet.getString("FIRST_NAME");
            String surname = resultSet.getString("SECOND_NAME");
            int age = resultSet.getInt("AGE");
            String login = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");
            String department = resultSet.getString("DEPARTMENT");
            boolean isHeadOfDepartment = resultSet.getBoolean("IS_HEAD_OF_DEPARTMENT");

            return new Doctor(name, surname, age, login, password, department, isHeadOfDepartment);
        }
        catch (Exception e) {
            logger.error("Stream is closed");
        }
        throw new StreamIsClosedException();
    }

    public ArrayList<Treatment> getTreatmentsByPatientId(int id) throws StreamIsClosedException {
        try {
            ArrayList<Treatment> treatments = new ArrayList<Treatment>();
            String treatmentsQuery = "SELECT * FROM TREATMENT WHERE ID_PATIENT = " + id;
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(treatmentsQuery);
            while (resultSet.next()) {
                String medicines = resultSet.getString("MEDICINE");
                String operations = resultSet.getString("OPERATION");
                String procedures = resultSet.getString("T_PROCEDURE");

                treatments.add(new Treatment(medicines, operations, procedures));
            }

            return treatments;
        }
        catch (Exception e) {
            logger.error("Stream is closed");
        }
        throw new StreamIsClosedException();
    }
}
