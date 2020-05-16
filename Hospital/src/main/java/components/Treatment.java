package main.java.components;

import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL)
@Table(name = "TREATMENT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID_TREATMENT")
})

public class Treatment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TREATMENT", unique = true, nullable = false)
    private int id;

    @Column(name = "T_PROCEDURE", unique = false, nullable = false, length = 100)
    private String procedure;

    @Column(name = "MEDICINE", unique = false, nullable = false, length = 100)
    private String medicine;

    @Column(name = "OPERATION", unique = false, nullable = false, length = 100)
    private String operation;

    @Column(name = "DIAGNOSE", unique = false, nullable = false, length = 100)
    private String diagnose;

    @Column(name = "ID_APPOINTMENT", unique = false, nullable = false, length = 100)
    private int appointmentId;

    public Treatment() {}

    public Treatment(String procedure, String medicine, String operation, String diagnose, int appointmentId) {
        this.procedure = procedure;
        this.medicine = medicine;
        this.operation = operation;
        this.diagnose = diagnose;
        this.appointmentId = appointmentId;
    }

    public int getId() {
        return id;
    }

    public String getProcedure() {
        return procedure;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getOperation() {
        return operation;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

//        @Override
//    public String toString() {
//        return this.medicines + " " + this.operations + " " + this.procedures + " " + this.diagnoses;
//    }

    @Override
    public String toString() {
        return medicine + " " + operation + " " + procedure + " " + diagnose;
    }
}
