package main.java.components;

import main.java.tasklogs.NurseTaskLog;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.date.GregorianDate;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL)
@Table(name = "APPOINTMENT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID_APPOINTMENT")
})

public class Appointment implements Serializable {
    private String department;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_APPOINTMENT", unique = true, nullable = false)
    private int id;

//    @Column(name = "ID_DOCTOR", unique = false, nullable = false)
//    private int doctorId;
//
//    @Column(name = "ID_PATIENT", unique = false, nullable = false)
//    private int patientId;

    @Column(name = "APPOINTMENT_DATE", unique = false, nullable = false)
    private Date appDate;

    private Patient patient;
    private Doctor doctor;

    private Treatment treatment;
    private NurseTaskLog nurseTaskLog;

    public Appointment() {}

    public Appointment(String department, Date appDate) {
        this.department = department;
        this.appDate = appDate;
    }

    public Appointment(Patient patient, Doctor doctor) {
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getDepartment() {
        return department;
    }

    public int getId() {
        return id;
    }

//    public int getDoctorId() {
//        return doctorId;
//    }
//
//    public int getPatientId() {
//        return patientId;
//    }

    public Date getAppDate() {
        return appDate;
    }

    public GregorianDate getGregorianAppDate() {
        return new GregorianDate(appDate);
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public NurseTaskLog getNurseTaskLog() {
        return nurseTaskLog;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public void setDoctorId(int doctorId) {
//        this.doctorId = doctorId;
//    }
//
//    public void setPatientId(int patientId) {
//        this.patientId = patientId;
//    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public void setNurseTaskLog(NurseTaskLog nurseTaskLog) {
        this.nurseTaskLog = nurseTaskLog;
    }

    @Override
    public String toString() {
        return "[" + this.doctor.toString() + "] " + this.department + " " + this.appDate.toString();
    }

//    @Override
//    public String toString() {
//        return "[" + this.doctorId + "] " + this.department + " " + this.appDate.toString();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
