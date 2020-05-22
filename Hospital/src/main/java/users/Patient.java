package main.java.users;

import main.java.components.Appointment;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Patient implements Serializable {

    private int id;
    private String name;
    private String surname;
    private int age;
    private String login;
    private String password;
    private boolean recovered;
    private Set<Appointment> appointments;

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

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public boolean isRecovered() { return recovered; }

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

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String showProfile() {
        return name + " " + surname + ", " + age;
    }

    @Override
    public String toString() {
        return showProfile();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return getId() == patient.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
