package main.java.tasklogs;

import main.java.components.Appointment;
import main.java.users.stuff.Nurse;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class NurseTaskLog implements Serializable {

    private int id; //NurseTaskLogId

    private Appointment appointment;
    private Nurse nurse;

    public NurseTaskLog() {}

    public NurseTaskLog(Appointment appointment, Nurse nurse) {
        this.appointment = appointment;
        this.nurse = nurse;
    }

    public int getId() {
        return id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    @Override
    public String toString() {
        return nurse.toString() + " " + appointment.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NurseTaskLog that = (NurseTaskLog) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
