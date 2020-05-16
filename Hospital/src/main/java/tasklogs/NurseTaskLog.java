package main.java.tasklogs;

import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "ID_NURSE", unique = false, nullable = false)
    private int nurseId;

    @Column(name = "ID_APPOINTMENT", unique = false, nullable = false)
    private int appointmentId;

    public NurseTaskLog() {}

    public NurseTaskLog(int nurseId, int appointmentId) {
        this.nurseId = nurseId;
        this.appointmentId = appointmentId;
    }

    public int getId() {
        return id;
    }

    public int getNurseId() {
        return nurseId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public String toString() {
        return nurseId + " " + appointmentId;
    }
}
