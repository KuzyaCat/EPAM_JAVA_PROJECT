package main.java.users;

import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.users.User;

import java.util.ArrayList;
import java.util.Arrays;

public class Patient extends User {
    private ArrayList<Appointment> appointments;
    private ArrayList<Treatment> treatments;
    private boolean recovered;

    public Patient(String name, String surname, int age, String login, String password, ArrayList<Appointment> appointments, ArrayList<Treatment> treatments, boolean recovered){
        super(name, surname, age, login, password);
        this.appointments = appointments;
        this.treatments = treatments;
        this.recovered = recovered;
    }

    public Patient(User user, ArrayList<Appointment> appointments, ArrayList<Treatment> treatments, boolean recovered) {
        super(user.getName(), user.getSurname(), user.getAge(), user.getLogin(), user.getPassword());
        this.appointments = appointments;
        this.treatments = treatments;
        this.recovered = recovered;
    }

    public Patient() {
        this(new User(), new ArrayList<Appointment>(), new ArrayList<Treatment>(), false);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    public boolean isRecovered() { return recovered; }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setRecovered(boolean recovered) {
        this.recovered = recovered;
    }

    public void setTreatments(ArrayList<Treatment> treatments) {
        this.treatments = treatments;
    }

    public String showProfile() {
        return this.getName() + " " +
                this.getSurname() + '\n' +
                this.getAge() + " years old\n" +
                "Appointments: " + Arrays.toString(this.appointments.toArray()) + '\n' +
                "Treatments: " + Arrays.toString(this.treatments.toArray()) + '\n';
    }

    @Override
    public String toString() {
        String res = "";
        res += "[" + super.toString() + "] {";

        for(int i = 0; i < this.appointments.size(); i ++) {
            if(i != 0) {
                res += " " + "[" + this.appointments.get(i).toString() + "]";
            }
            else {
                res += "[" + this.appointments.get(i).toString() + "]";
            }
        }
        res += "}" + " ";

        res += "|";

        for(int i = 0; i < this.treatments.size(); i ++) {
            if(i != 0) {
                res += " " + "[" + this.treatments.get(i).toString() + "]";
            }
            else {
                res += "[" + this.treatments.get(i).toString() + "]";
            }
        }

        res += "|" + " ";

        String recStr;
        if(this.recovered) {
            recStr = "true";
        }
        else {
            recStr = "false";
        }

        res += recStr;
        return res;
    }
}
