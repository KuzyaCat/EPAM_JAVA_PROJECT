package main.java.users.stuff;

import main.java.components.Treatment;
import main.java.tasklogs.NurseTaskLog;
import main.java.usersdb.PatientDB;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Nurse implements Serializable {

    private int id;
    private String name;
    private String surname;
    private int age;
    private String login;
    private String password;
    private Set<NurseTaskLog> nurseTaskLogSet;

    public Nurse() {}

    public Nurse(String name, String surname, int age, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public Nurse(String name, String surname, int age, String login, String password, int id) {
        this(name, surname, age, login, password);
        this.setId(id);
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

    public Set<NurseTaskLog> getNurseTaskLogSet() {
        return nurseTaskLogSet;
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

    public void setNurseTaskLogSet(Set<NurseTaskLog> nurseTaskLogSet) {
        this.nurseTaskLogSet = nurseTaskLogSet;
    }

    public void setTreatmentToPatient(Treatment treatment) {
        (new PatientDB()).writeTreatment(treatment);
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
        Nurse nurse = (Nurse) o;
        return getId() == nurse.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
