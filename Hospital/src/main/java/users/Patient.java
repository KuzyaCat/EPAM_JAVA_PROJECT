package main.java.users;

import main.java.components.Appointment;
import main.java.components.Treatment;

public class Patient extends User {
    private Appointment[] appointments;
    private Treatment[] treatments;
    private String[] diagnoses;
    private boolean recovered;
    Patient(String name, String surname, int age, String login, String password, Treatment[] treatments, String[]diagnoses, boolean recovered){
        super(name, surname, age, login, password);
        this.treatments = treatments;
        this.diagnoses = diagnoses;
        this.recovered = recovered;
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public String[] getDiagnoses() {
        return diagnoses;
    }

    public Treatment[] getTreatments() {
        return treatments;
    }

    public void setDiagnoses(String[] diagnoses) {
        this.diagnoses = diagnoses;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    public void setRecovered(boolean recovered) {
        this.recovered = recovered;
    }

    public void setTreatments(Treatment[] treatments) {
        this.treatments = treatments;
    }
}
