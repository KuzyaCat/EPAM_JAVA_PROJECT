package main.java.users;

import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.users.User;

import java.util.ArrayList;
import java.util.Arrays;

public class Patient extends User {
    private Appointment[] appointments;
    private Treatment[] treatments;
    private boolean recovered;

    public Patient(String name, String surname, int age, String login, String password, Appointment[] appointments, Treatment[] treatments, boolean recovered){
        super(name, surname, age, login, password);
        this.appointments = appointments;
        this.treatments = treatments;
        this.recovered = recovered;
    }

    public Patient(String name, String surname, int age, String login, String password, ArrayList<Appointment> appointments, ArrayList<Treatment> treatments, boolean recovered){
        super(name, surname, age, login, password);
        this.appointments = appointments.toArray(new Appointment[0]);
        this.treatments = treatments.toArray(new Treatment[0]);
        this.recovered = recovered;
    }

    public Patient(User user, Appointment[] appointments, Treatment[] treatments, boolean recovered) {
        super(user.getName(), user.getSurname(), user.getAge(), user.getLogin(), user.getPassword());
        this.appointments = appointments;
        this.treatments = treatments;
        this.recovered = recovered;
    }

    public Patient() {
        this(new User(), new Appointment[0], new Treatment[0], false);
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public Treatment[] getTreatments() {
        return treatments;
    }

    public boolean isRecovered() { return recovered; }

    public void setAppointments(Appointment[] appointments) {
        this.appointments = appointments;
    }

    public void setRecovered(boolean recovered) {
        this.recovered = recovered;
    }

    public void setTreatments(Treatment[] treatments) {
        this.treatments = treatments;
    }

    public String showProfile() {
        return this.getName() + " " +
                this.getSurname() + '\n' +
                this.getAge() + " years old\n" +
                "Appointments: " + Arrays.toString(this.appointments) + '\n' +
                "Treatments: " + Arrays.toString(this.treatments) + '\n';
    }

    @Override
    public String toString() {
        String res = "";
        res += "[" + super.toString() + "] {";

        for(int i = 0; i < this.appointments.length; i ++) {
            if(i != 0) {
                res += " " + "[" + this.appointments[i].toString() + "]";
            }
            else {
                res += "[" + this.appointments[i].toString() + "]";
            }
        }
        res += "}" + " ";

        res += "|";

        for(int i = 0; i < this.treatments.length; i ++) {
            if(i != 0) {
                res += " " + "[" + this.treatments[i].toString() + "]";
            }
            else {
                res += "[" + this.treatments[i].toString() + "]";
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

    /*@Override
    public Patient parseString(String str) {
        int firstOpeningBrIndex = 0;
        int firstClosingBrIndex = str.indexOf("]");

        String userStr = str.substring(firstOpeningBrIndex + 1, firstClosingBrIndex);
        User user = (new User()).parseString(userStr);

        int openingCurlyBrIndex = str.indexOf("{");
        int closingCurlyBrIndex = str.indexOf("}");
        Appointment[] appointments = new Appointment[0];

        if(openingCurlyBrIndex + 1 != closingCurlyBrIndex) {
            String appointmentsStr = str.substring(openingCurlyBrIndex + 2, closingCurlyBrIndex - 1);
            String[] appointmentsArrStr = appointmentsStr.split("] \\[");

            appointments = new Appointment[appointmentsArrStr.length];
            for(int i = 0; i < appointmentsArrStr.length; i ++) {
                appointments[i] = (new Appointment()).parseString(appointmentsArrStr[i]);
            }
        }

        int openingVerticalBrIndex = str.indexOf("|");
        int closingVerticalBrIndex = str.indexOf("|", openingVerticalBrIndex + 1);
        Treatment[] treatments = new Treatment[0];

        if(openingVerticalBrIndex + 1 != closingVerticalBrIndex) {
            String treatmentsStr = str.substring(openingVerticalBrIndex + 2, closingVerticalBrIndex - 1);
            String[] treatmentsArrStr = treatmentsStr.split("] \\[");

            treatments = new Treatment[treatmentsArrStr.length];
            for(int i = 0; i < treatmentsArrStr.length; i ++) {
                treatments[i] = (new Treatment()).parseString(treatmentsArrStr[i]);
            }
        }

        int secondOpeningCurlyBrIndex = closingVerticalBrIndex + 2;
        int secondClosingCurlyBrIndex = str.indexOf("}", secondOpeningCurlyBrIndex + 1);
        String[] diagnoses = new String[0];

        if(secondOpeningCurlyBrIndex + 1 != secondClosingCurlyBrIndex) {
            String diagnosesStr = str.substring(secondOpeningCurlyBrIndex + 1, secondClosingCurlyBrIndex);
            diagnoses = diagnosesStr.split(" ");
        }

        String recoveredStr = str.substring(secondClosingCurlyBrIndex + 2);
        boolean recovered = (recoveredStr.equals("true"));

        return new Patient(user, appointments, treatments, diagnoses, recovered);
    }*/
}
