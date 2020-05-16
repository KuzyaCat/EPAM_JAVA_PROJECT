package main.java.users;

import main.java.components.Appointment;
import main.java.components.Treatment;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL)
@Table(name = "PATIENT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "LOGIN")
})

public class Patient implements Serializable {
//    private ArrayList<Appointment> appointments;
//    private ArrayList<Treatment> treatments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PATIENT", unique = true, nullable = false)
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

    @Column(name = "RECOVERED", unique = false, nullable = false)
    private boolean recovered;

    public Patient() {}

    public Patient(String name, String surname, int age, String login, String password, boolean recovered) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.login = login;
        this.password = password;
        this.recovered = recovered;
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

//    public ArrayList<Appointment> getAppointments() {
//        return appointments;
//    }
//
//    public ArrayList<Treatment> getTreatments() {
//        return treatments;
//    }

    public boolean isRecovered() { return recovered; }

//    public void setAppointments(ArrayList<Appointment> appointments) {
//        this.appointments = appointments;
//    }

//    public void setTreatments(ArrayList<Treatment> treatments) {
//        this.treatments = treatments;
//    }

//    public String showProfile() {
//        return this.getName() + " " +
//                this.getSurname() + '\n' +
//                this.getAge() + " years old\n" +
//                "Appointments: " + Arrays.toString(this.appointments.toArray()) + '\n' +
//                "Treatments: " + Arrays.toString(this.treatments.toArray()) + '\n';
//    }


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

    public void setRecovered(boolean recovered) {
        this.recovered = recovered;
    }

    public String showProfile() {
        return name + " " + surname + ", " + age;
    }

    @Override
    public String toString() {
        return showProfile();
    }

//    @Override
//    public String toString() {
//        String res = "";
//        res += "[" + super.toString() + "] {";
//
//        for(int i = 0; i < this.appointments.size(); i ++) {
//            if(i != 0) {
//                res += " " + "[" + this.appointments.get(i).toString() + "]";
//            }
//            else {
//                res += "[" + this.appointments.get(i).toString() + "]";
//            }
//        }
//        res += "}" + " ";
//
//        res += "|";
//
//        for(int i = 0; i < this.treatments.size(); i ++) {
//            if(i != 0) {
//                res += " " + "[" + this.treatments.get(i).toString() + "]";
//            }
//            else {
//                res += "[" + this.treatments.get(i).toString() + "]";
//            }
//        }
//
//        res += "|" + " ";
//
//        String recStr;
//        if(this.recovered) {
//            recStr = "true";
//        }
//        else {
//            recStr = "false";
//        }
//
//        res += recStr;
//        return res;
//    }
}
