package main.java.components;

import main.java.users.stuff.Doctor;
import main.java.date.GregorianDate;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @Column(name = "ID_DOCTOR", unique = false, nullable = false)
    private int doctorId;

    @Column(name = "ID_PATIENT", unique = false, nullable = false)
    private int patientId;

    @Column(name = "APPOINTMENT_DATE", unique = false, nullable = false)
    private Date appDate;

    public Appointment() {}

    public Appointment(String department, int doctorId, int patientId, Date appDate) {
        this.department = department;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appDate = appDate;
    }

    public String getDepartment() {
        return department;
    }

    public int getId() {
        return id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

//    @Override
//    public String toString() {
//        return "[" + this.doctor.toString() + "] " + this.department + " " + this.appDate.toString();
//    }

    @Override
    public String toString() {
        return "[" + this.doctorId + "] " + this.department + " " + this.appDate.toString();
    }
}
