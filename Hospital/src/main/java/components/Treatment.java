package main.java.components;

import java.io.Serializable;
import java.util.Objects;

public class Treatment implements Serializable {

    private int id; //appointmentId
    private String procedure;
    private String medicine;
    private String operation;
    private String diagnose;

    private Appointment appointment;

    public Treatment() {}

    public Treatment(Appointment appointment, String procedure, String medicine, String operation, String diagnose) {
        this.procedure = procedure;
        this.medicine = medicine;
        this.operation = operation;
        this.diagnose = diagnose;

        this.id = appointment.getId();
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

    public Appointment getAppointment() {
        return appointment;
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

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        return this.medicine + " " + this.operation + " " + this.procedure + " " + this.diagnose;
    }

    public boolean isEmpty() {
        return procedure.equals("") && medicine.equals("") && operation.equals("") && diagnose.equals("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treatment treatment = (Treatment) o;
        return getId() == treatment.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
