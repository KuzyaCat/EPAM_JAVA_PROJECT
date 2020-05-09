package main.java.users.stuff;

import main.java.components.Treatment;
import main.java.components.Appointment;
import main.java.users.Patient;
import main.java.users.User;
import main.java.usersdb.PatientDB;

public class Nurse extends User {
    public Nurse(String name, String surname, int age, String login, String password){
        super(name, surname, age, login, password);
    }

    public Nurse(User user) {
        super(user.getName(), user.getSurname(), user.getAge(), user.getLogin(), user.getPassword());
    }

    public Nurse() {
        super("", "", 0, "", "");
    }

    public void setTreatmentToPatient(Patient patient, Appointment appointment, Treatment treatment) {
        (new PatientDB()).writeTreatment(patient, appointment, treatment);
    }
    public String showProfile() {
        return this.getName() + " " +
                this.getSurname() + '\n' +
                this.getAge() + " years old\n";
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Nurse parseString(String str) {
        return new Nurse(super.parseString(str));
    }
}
