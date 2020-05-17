package main.java.tasklogs;

import main.java.components.Appointment;
import main.java.users.stuff.Nurse;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL)
@Table(name = "NURSE_TASK_LOG", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID_NURSE_TASK_LOG")
})

public class NurseTaskLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NURSE_TASK_LOG", unique = true, nullable = false)
    private int id;

//    @Column(name = "ID_NURSE", unique = false, nullable = false)
//    private int nurseId;

    @Column(name = "ID_APPOINTMENT", unique = false, nullable = false)
    private int appointmentId;

    private Appointment appointment;

    private Nurse nurse;

    public NurseTaskLog() {}

    public NurseTaskLog(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getId() {
        return id;
    }

    public int getAppointmentId() {
        return appointmentId;
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

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    @Override
    public String toString() {
        return nurse.toString() + " " + appointmentId;
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
