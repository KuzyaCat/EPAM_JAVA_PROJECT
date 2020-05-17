package main.java.controllers.resource_controllers;

import main.java.dbconnection.SessionProvider;
import main.java.tasklogs.NurseTaskLog;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.date.GregorianDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;

public class DBUpdater {
    private SessionProvider sessionProvider = new SessionProvider();
    private Session session;
    static Logger logger = LogManager.getLogger();

    public DBUpdater(Session session) {
        this.session = session;
    }

    public DBUpdater() {
        this.session = this.sessionProvider.getSessionFactory().openSession();
    }

//    public void deletePatientRow(int patientId) {
//        session.beginTransaction();
//
//        String deletePatientQuery = "DELETE FROM Patient WHERE id= :patientId";
//        Query query = session.createQuery(deletePatientQuery);
//        query.setInteger("patientId", patientId);
//        query.executeUpdate();
//
//        session.getTransaction().commit();
//    }

    public void deletePatientRow(Patient patient) {
        session.beginTransaction();
        session.delete(patient);
        session.getTransaction().commit();
    }

//    public void deleteAppointmentRow(int appointmentId) {
//        session.beginTransaction();
//
//        String deleteAppointmentQuery = "DELETE FROM Appointment WHERE id= :appointmentId";
//        Query query = session.createQuery(deleteAppointmentQuery);
//        query.setInteger("appointmentId", appointmentId);
//        query.executeUpdate();
//
//        session.getTransaction().commit();
//    }

    public void deleteAppointmentRow(Appointment appointment) {
        session.beginTransaction();
        session.delete(appointment);
        session.getTransaction().commit();
    }

//    public void deleteTreatmentRow(int treatmentId) {
//        session.beginTransaction();
//
//        String deleteTreatmentQuery = "DELETE FROM Treatment WHERE id= :treatmentId";
//        Query query = session.createQuery(deleteTreatmentQuery);
//        query.setInteger("treatmentId", treatmentId);
//        query.executeUpdate();
//
//        session.getTransaction().commit();
//    }

    public void deleteTreatmentRow(Treatment treatment) {
        session.beginTransaction();
        session.delete(treatment);
        session.getTransaction().commit();
    }

    public void deleteRowFromNurseTaskLog(NurseTaskLog nurseTaskLog) {
        session.beginTransaction();
        session.delete(nurseTaskLog);
        session.getTransaction().commit();
    }

    public void addPatient(Patient newPatient) {
        session.beginTransaction();
        session.save(newPatient);
        session.getTransaction().commit();
    }

    public void addAppointment(Appointment newAppointment) {
        session.beginTransaction();
        session.save(newAppointment);
        session.getTransaction().commit();
    }

    public void addTreatment(Treatment treatment) {
        session.beginTransaction();
        session.save(treatment);
        session.getTransaction().commit();
    }

    public void addRowToNurseTaskLog(NurseTaskLog nurseTaskLog) {
        session.beginTransaction();
        session.save(nurseTaskLog);
        session.getTransaction().commit();
    }

    public void updatePatient(Patient updatedPatient) {
        session.beginTransaction();

        String updatePatientQuery = "UPDATE Patient set name= :name," +
                "surname= :surname," +
                "age= :age," +
                "login= :login," +
                "password= :password," +
                "recovered= :recovered WHERE id= :patientId";
        Query query = session.createQuery(updatePatientQuery);
        query.setString("name", updatedPatient.getName());
        query.setString("surname", updatedPatient.getSurname());
        query.setInteger("age", updatedPatient.getAge());
        query.setString("login", updatedPatient.getLogin());
        query.setString("password", updatedPatient.getPassword());
        query.setBoolean("recovered", updatedPatient.isRecovered());
        query.setInteger("patientId", updatedPatient.getId());

        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void updateDoctor(Doctor updatedDoctor) {
        session.beginTransaction();

        String updateDoctorQuery = "UPDATE Doctor set name= :name," +
                "surname= :surname," +
                "age= :age," +
                "login= :login," +
                "password= :password," +
                "department= :department," +
                "isHeadOfDepartment= :isHeadOfDepartment WHERE id= :doctorId";
        Query query = session.createQuery(updateDoctorQuery);
        query.setString("name", updatedDoctor.getName());
        query.setString("surname", updatedDoctor.getSurname());
        query.setInteger("age", updatedDoctor.getAge());
        query.setString("login", updatedDoctor.getLogin());
        query.setString("password", updatedDoctor.getPassword());
        query.setString("department", updatedDoctor.getDepartment());
        query.setBoolean("isHeadOfDepartment", updatedDoctor.isHeadOfDepartment());
        query.setInteger("doctorId", updatedDoctor.getId());

        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void changeRecoveredStatusByPatient(Patient patient, boolean isRecovered) {
        session.beginTransaction();

        String updatePatientQuery = "UPDATE Patient set recovered= :recovered WHERE id= :patientId";
        Query query = session.createQuery(updatePatientQuery);
        query.setBoolean("recovered", isRecovered);
        query.setInteger("patientId", patient.getId());

        query.executeUpdate();
        session.getTransaction().commit();
    }
}
