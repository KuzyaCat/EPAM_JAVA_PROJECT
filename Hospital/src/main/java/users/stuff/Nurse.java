package main.java.users.stuff;

import main.java.components.Treatment;
import main.java.components.Appointment;
import main.java.users.Patient;
import main.java.usersdb.PatientDB;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL)
@Table(name = "NURSE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "LOGIN")
})

public class Nurse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NURSE", unique = true, nullable = false)
    private int id;

    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
    private String name;

    @Column(name = "SECOND_NAME", unique = false, nullable = false, length = 100)
    private String surname;

    @Column(name = "AGE", unique = false, nullable = false)
    private int age;

    @Column(name = "LOGIN", unique = true, nullable = false, length = 100)
    private String login;

    @Column(name = "PASSWORD", unique = true, nullable = false, length = 100)
    private String password;

    public Nurse() {}

    public Nurse(String name, String surname, int age, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //    public void setTreatmentToPatient(Patient patient, Appointment appointment, Treatment treatment) {
//        (new PatientDB()).writeTreatment(patient, appointment, treatment);
//    }

    public String showProfile() {
        return name + " " + surname + ", " + age;
    }

    @Override
    public String toString() {
        return showProfile();
    }

//    @Override
//    public Nurse parseString(String str) {
//        return new Nurse(super.parseString(str));
//    }
}
