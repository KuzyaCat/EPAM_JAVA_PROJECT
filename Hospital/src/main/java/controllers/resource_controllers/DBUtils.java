package controllers.resource_controllers;

import dbconnection.DBConnector;
import dbconnection.exceptions.StreamIsClosedException;
import dbconnection.exceptions.WrongQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;
import main.java.components.Appointment;
import main.java.components.Treatment;

import main.java.date.GregorianDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DBUtils {
    private DBConnector dbConnector;
    static Logger logger = LogManager.getLogger();

    public DBUtils(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public ArrayList<Treatment> getTreatmentsByPatientId(int patientId) throws StreamIsClosedException {
        try {
            ArrayList<Treatment> treatments = new ArrayList<Treatment>();
            String query = "SELECT ID_APPOINTMENT FROM APPOINTMENT WHERE ID_PATIENT = " + patientId;
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            while (resultSet.next()) {
                String treatmentQuery = "SELECT T_PROCEDURE, MEDICINE, OPERATION FROM TREATMENT WHERE ID_APPOINTMENT = " +
                        resultSet.getInt("ID_APPOINTMENT");
                ResultSet treatmentResultSet = this.dbConnector.getQueryResultAsResultSet(treatmentQuery);
                treatmentResultSet.next();
                treatments.add(new Treatment(
                        treatmentResultSet.getString("MEDICINE"),
                        treatmentResultSet.getString("OPERATION"),
                        treatmentResultSet.getString("T_PROCEDURE")));
            }

            return treatments;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        throw new StreamIsClosedException();
    }

    public ArrayList<String> getDiagnosesByPatientId(int patientId) throws StreamIsClosedException {
        try {
            ArrayList<String> diagnoses = new ArrayList<String>();
            String query = "SELECT ID_APPOINTMENT FROM APPOINTMENT WHERE ID_PATIENT = " + patientId;
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            while (resultSet.next()) {
                String treatmentQuery = "SELECT DIAGNOSE FROM TREATMENT WHERE ID_APPOINTMENT = " +
                        resultSet.getInt("ID_APPOINTMENT");
                ResultSet treatmentResultSet = this.dbConnector.getQueryResultAsResultSet(treatmentQuery);
                treatmentResultSet.next();
                diagnoses.add(treatmentResultSet.getString("DIAGNOSE"));
            }

            return diagnoses;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        throw new StreamIsClosedException();
    }

    public ArrayList<Appointment> getAppointmentsByPatientId(int id) throws StreamIsClosedException {
        try {
            ArrayList<Appointment> appointments = new ArrayList<Appointment>();
            String appointmentsQuery = "SELECT * FROM APPOINTMENT WHERE ID_PATIENT = " + id;
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(appointmentsQuery);
            while (resultSet.next()) {
                appointments.add(this.getAppointmentById(resultSet.getInt("ID_APPOINTMENT")));
            }

            return appointments;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        throw new StreamIsClosedException();
    }

    public Appointment getAppointmentById(int appointmentId) throws StreamIsClosedException {
        try {
            String appointmentsQuery = "SELECT * FROM APPOINTMENT WHERE ID_APPOINTMENT = " + appointmentId;
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(appointmentsQuery);
            resultSet.next();

            Doctor appointmentDoctor = this.getDoctorById(resultSet.getInt("ID_DOCTOR"));

            String appointmentDepartment = appointmentDoctor.getDepartment();

            Date appointmentDate = resultSet.getDate("APPOINTMENT_DATE");
            GregorianDate appointmentGregDate = new GregorianDate(appointmentDate);

            return new Appointment(appointmentDoctor, appointmentDepartment, appointmentGregDate);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        throw new StreamIsClosedException();
    }

    public Doctor getDoctorById(int doctorId) throws StreamIsClosedException {
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
            logger.error(e.getMessage());
        }
        throw new StreamIsClosedException();
    }

    public int getPatientIdByLogin(String login) throws StreamIsClosedException {
        try {
            String query = "SELECT ID_PATIENT FROM PATIENT WHERE LOGIN = '" + login + "'";
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            resultSet.next();

            return resultSet.getInt("ID_PATIENT");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        throw new StreamIsClosedException();
    }

    public int getTreatmentIdByAppointmentId(int appointmentId) throws StreamIsClosedException {
        try {
            String query = "SELECT ID_TREATMENT FROM TREATMENT WHERE ID_APPOINTMENT = " + appointmentId;
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            resultSet.next();

            return resultSet.getInt("ID_TREATMENT");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        throw new StreamIsClosedException();
    }

    public ArrayList<Doctor> getDoctorsByPatient(Patient patient) {
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        try {
            int patientId = this.getPatientIdByLogin(patient.getLogin());

            String doctorIdsQuery = "SELECT ID_DOCTOR FROM APPOINTMENT WHERE ID_PATIENT = " + patientId;
            ResultSet doctorIdsSet = this.dbConnector.getQueryResultAsResultSet(doctorIdsQuery);
            ArrayList<Integer> doctorIds = new ArrayList<Integer>();
            while(doctorIdsSet.next()) {
                int currentDoctorId = doctorIdsSet.getInt("ID_DOCTOR");

                if(!doctorIds.contains(currentDoctorId)) {
                    doctors.add(this.getDoctorById(currentDoctorId));
                    doctorIds.add(currentDoctorId);
                }
            }

        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        } catch (StreamIsClosedException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return doctors;
    }

    public Doctor getDoctorByNameAndSurname(String name, String surname) {
        try {
            String doctorQuery = "SELECT ID_DOCTOR FROM DOCTOR WHERE FIRST_NAME = '" + name + "', SECOND_NAME = '" + surname + "'";
            ResultSet oneDoctorSet = this.dbConnector.getQueryResultAsResultSet(doctorQuery);
            oneDoctorSet.next();
            return this.getDoctorById(oneDoctorSet.getInt("ID_DOCTOR"));
        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        } catch (StreamIsClosedException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    public int getPatientIdByNameAndSurname(String name, String surname) {
        try {
            String patientQuery = "SELECT ID_PATIENT FROM PATIENT WHERE FIRST_NAME = '" + name + "', SECOND_NAME = '" + surname + "'";
            ResultSet onePatientSet = this.dbConnector.getQueryResultAsResultSet(patientQuery);
            onePatientSet.next();
            return onePatientSet.getInt("ID_DOCTOR");
        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return -1;
    }

    public int getDoctorIdByNameAndSurname(String name, String surname) {
        try {
            String doctorQuery = "SELECT ID_DOCTOR FROM DOCTOR WHERE FIRST_NAME = '" + name + "', SECOND_NAME = '" + surname + "'";
            ResultSet oneDoctorSet = this.dbConnector.getQueryResultAsResultSet(doctorQuery);
            oneDoctorSet.next();
            return oneDoctorSet.getInt("ID_DOCTOR");
        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return -1;
    }

    public Patient getPatientById(int patientId) throws StreamIsClosedException {
        try {
            String patientsQuery = "SELECT * FROM PATIENT WHERE ID_PATIENT = " + patientId;
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(patientsQuery);
            resultSet.next();

            String name = resultSet.getString("FIRST_NAME");
            String surname = resultSet.getString("SECOND_NAME");
            int age = resultSet.getInt("AGE");
            String login = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");

            ArrayList<Appointment> appointments = this.getAppointmentsByPatientId(patientId);
            ArrayList<Treatment> treatments = this.getTreatmentsByPatientId(patientId);
            ArrayList<String> diagnoses = this.getDiagnosesByPatientId(patientId);

            boolean recovered = resultSet.getBoolean("RECOVERED");

            return new Patient(name, surname, age, login, password, appointments, treatments, diagnoses, recovered);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        throw new StreamIsClosedException();
    }

    public ArrayList<Patient> getPatientsByDoctor(Doctor doctor){
        ArrayList<Patient> patients = new ArrayList<Patient>();
        try {
            int doctorId = this.getDoctorIdByNameAndSurname(doctor.getName(), doctor.getSurname());

            String patientIdsQuery = "SELECT ID_PATIENT FROM APPOINTMENT WHERE ID_DOCTOR = " + doctorId;
            ResultSet patientIdsSet = this.dbConnector.getQueryResultAsResultSet(patientIdsQuery);
            ArrayList<Integer> patientIds = new ArrayList<Integer>();
            while(patientIdsSet.next()) {
                int currentPatientId = patientIdsSet.getInt("ID_PATIENT");

                if(!patientIds.contains(currentPatientId)) {
                    patients.add(this.getPatientById(currentPatientId));
                    patientIds.add(currentPatientId);
                }
            }

        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        } catch (StreamIsClosedException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return patients;
    }

    public int getLengthOfTable(String table) {
        try {
            int counter = 0;

            String lengthQuery = "SELECT * FROM " + table;
            ResultSet lengthSet = this.dbConnector.getQueryResultAsResultSet(lengthQuery);
            while(lengthSet.next()) {
                counter++;
            }
            return counter;
        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return -1;
    }

    public boolean appointmentIdIsInTreatments(int appointmentId) {
        String query = "SELECT ID_APPOINTMENT FROM TREATMENT";
        try {
            ResultSet resultSet = this.dbConnector.getQueryResultAsResultSet(query);
            while(resultSet.next()) {
                if(resultSet.getInt("ID_APPOINTMENT") == appointmentId) {
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public HashMap<Appointment, Integer> getHashMapWithPlannedAppointmentsByDoctorToAppointmentIds(Doctor doctor) {
        HashMap<Appointment, Integer> plannedAppointmentsToAppointmentIds = new HashMap<Appointment, Integer>();
        try {
            int doctorId = this.getDoctorIdByNameAndSurname(doctor.getName(), doctor.getSurname());

            String allDoctorAppointmentsQuery = "SELECT * FROM APPOINTMENT WHERE ID_DOCTOR = " + doctorId;
            ResultSet allDoctorAppointmentsSet = this.dbConnector.getQueryResultAsResultSet(allDoctorAppointmentsQuery);
            while(allDoctorAppointmentsSet.next()) {
                int currentAppointmentId = allDoctorAppointmentsSet.getInt("ID_APPOINTMENT");
                if(!this.appointmentIdIsInTreatments(currentAppointmentId)) {
                    plannedAppointmentsToAppointmentIds.put(
                            this.getAppointmentById(currentAppointmentId),
                            currentAppointmentId);
                }
            }

        } catch (WrongQueryException e) {
            logger.error(e.getMessage());
        } catch (StreamIsClosedException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return plannedAppointmentsToAppointmentIds;
    }
}
