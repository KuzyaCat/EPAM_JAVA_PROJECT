package main.java.controllers.resource_controllers;

import main.java.dbconnection.SessionProvider;
import main.java.tasklogs.NurseTaskLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;
import main.java.components.Appointment;
import main.java.components.Treatment;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DBReader {
    private SessionProvider sessionProvider = new SessionProvider();
    private Session session;
    static Logger logger = LogManager.getLogger();

    public DBReader(Session session) {
        this.session = session;
    }

    public DBReader() {
        this.session = this.sessionProvider.getSessionFactory().openSession();
    }

    public ArrayList<Patient> getAllPatients() {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Patient.class);
        ArrayList<Patient> patients = (ArrayList<Patient>) criteria.list();

        session.getTransaction().commit();

        return patients;
    }

    public Patient getPersistentPatientById(int id) {
        session.beginTransaction();
        Patient patient = (Patient) session.load(Patient.class, id);
        session.getTransaction().commit();

        return patient;
    }

    public ArrayList<Doctor> getAllDoctors() {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Doctor.class);
        ArrayList<Doctor> doctors = (ArrayList<Doctor>) criteria.list();

        session.getTransaction().commit();

        return doctors;
    }

    public ArrayList<Nurse> getAllNurses() {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Nurse.class);
        ArrayList<Nurse> nurses = (ArrayList<Nurse>) criteria.list();

        session.getTransaction().commit();

        return nurses;
    }

    public ArrayList<NurseTaskLog> getAllNurseTaskLogs() {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(NurseTaskLog.class);
        ArrayList<NurseTaskLog> nurseTaskLogs = (ArrayList<NurseTaskLog>) criteria.list();

        session.getTransaction().commit();

        return nurseTaskLogs;
    }

    public ArrayList<Appointment> getAllAppointments() {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Appointment.class);
        ArrayList<Appointment> appointments = (ArrayList<Appointment>) criteria.list();

        session.getTransaction().commit();

        return appointments;
    }

    public ArrayList<Treatment> getAllTreatments() {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Treatment.class);
        ArrayList<Treatment> treatments = (ArrayList<Treatment>) criteria.list();

        session.getTransaction().commit();

        return treatments;
    }

    public ArrayList<Treatment> getTreatmentsByPatient(Patient patient) {
        return patient.getAppointments().stream()
                .map(Appointment::getTreatment)
                .filter(treatment -> !treatment.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Patient getPatientByLogin(String login) {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Patient.class);
        criteria.add(Restrictions.eq("login", login));

        session.getTransaction().commit();

        return (Patient) criteria.list().get(0);
    }

    public ArrayList<Doctor> getDoctorsByPatient(Patient patient) {
        return patient.getAppointments().stream()
                .map(Appointment::getDoctor)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Patient getPatientByNameAndSurname(String name, String surname) {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Patient.class);
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("surname", surname));

        session.getTransaction().commit();

        return (Patient) criteria.list().get(0);
    }

    public Doctor getDoctorByNameAndSurname(String name, String surname) {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Doctor.class);
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("surname", surname));

        session.getTransaction().commit();

        return (Doctor) criteria.list().get(0);
    }

    public Nurse getNurseByNameAndSurname(String name, String surname) {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Nurse.class);
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("surname", surname));

        session.getTransaction().commit();

        return (Nurse) criteria.list().get(0);
    }

    public int getNurseIdByNameAndSurname(String name, String surname) {
        return getNurseByNameAndSurname(name, surname).getId();
    }

    public int getPatientIdByNameAndSurname(String name, String surname) {
        return this.getPatientByNameAndSurname(name, surname).getId();
    }

    public int getDoctorIdByNameAndSurname(String name, String surname) {
        return this.getDoctorByNameAndSurname(name, surname).getId();
    }

    public ArrayList<Patient> getPatientsByDoctor(Doctor doctor){
        return doctor.getAppointments().stream()
                .map(Appointment::getPatient)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean appointmentIdIsInTreatments(int appointmentId) {
        Treatment treatment = getAllTreatments().stream()
                .filter(t -> t.getId() == appointmentId)
                .collect(Collectors.toCollection(ArrayList::new)).get(0);

        return !treatment.isEmpty();
    }

    public Patient getPatientByAppointment(Appointment appointment) {
        return appointment.getPatient();
    }

    public ArrayList<Appointment> getAppointmentsByNurse(Nurse nurse) {
        return nurse.getNurseTaskLogSet().stream()
                .map(NurseTaskLog::getAppointment)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Appointment> getPlannedAppointmentsByDoctor(Doctor doctor) {
        return doctor.getAppointments().stream()
                .filter(appointment -> !this.appointmentIdIsInTreatments(appointment.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void shutdown() {
        this.sessionProvider.shutdown();
    }
}
