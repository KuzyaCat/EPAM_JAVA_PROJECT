package main.java.users.stuff;

import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.users.Patient;
import main.java.usersdb.DoctorDB;
import main.java.usersdb.PatientDB;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL)
@Table(name = "DOCTOR", uniqueConstraints = {
        @UniqueConstraint(columnNames = "LOGIN")
})

public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOCTOR", unique = true, nullable = false)
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

    @Column(name = "DEPARTMENT", unique = false, nullable = false, length = 100)
    private String department;

    @Column(name = "IS_HEAD_OF_DEPARTMENT", unique = false, nullable = false)
    private boolean isHeadOfDepartment;

    private Set<Appointment> appointments;

    public Doctor() {}

    public Doctor(String name, String surname, int age, String login, String password, String department, boolean isHeadOfDepartment) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.login = login;
        this.password = password;
        this.department = department;
        this.isHeadOfDepartment = isHeadOfDepartment;
    }

    public String showProfile() {
        return name + " " + surname + ", " + age;
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

    public String getDepartment() {
        return department;
    }

    public boolean isHeadOfDepartment() {
        return isHeadOfDepartment;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
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

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setHeadOfDepartment(boolean headOfDepartment) {
        isHeadOfDepartment = headOfDepartment;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setTreatmentToPatient(Patient patient, Appointment appointment, Treatment treatment) {
        (new PatientDB()).writeTreatment(patient, appointment, treatment);
    }

    public void setRecoverToPatient(Patient patient, boolean isRecovered) {
        (new PatientDB()).writeIsRecovered(patient, isRecovered);
    }

    public String getPatientAppointments() {
        DoctorDB doctorDB = new DoctorDB();
        return doctorDB.getAppointments(this);
    }

    @Override
    public String toString() {
        return "[" + showProfile() + "]" + " " + department + " " + isHeadOfDepartment;
    }

//    @Override
//    public Doctor parseString(String str) {
//        int openingBrIndex = 0;
//        int closingBrIndex = str.indexOf(']');
//
//        String userString = "";
//        for(int i = openingBrIndex + 1; i < closingBrIndex; i ++) {
//            userString += str.charAt(i);
//        }
//
//        User user = (new User()).parseString(userString);
//
//        String cutString = str.substring(closingBrIndex + 2);
//        String[] cutStringArr = cutString.split(" ");
//
//        String department = cutStringArr[0];
//        String isHeadOfDepartment = cutStringArr[1];
//
//        boolean isHead = (isHeadOfDepartment.equals("true"));
//
//        return new Doctor(user, department, isHead);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return getId() == doctor.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
