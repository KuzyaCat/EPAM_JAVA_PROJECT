package main.java.controllers.resource_controllers;

import main.java.users.User;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;

import main.java.components.Appointment;
import main.java.components.Treatment;

import main.java.controllers.resource_controllers.DataBaseIO;
import java.util.Scanner;

public class Authorizer {
    private DataBaseIO dbio;
    private Patient[] patients;
    private Doctor[] doctors;
    private Nurse[] nurses;
    private Scanner scanner;

    public Authorizer(DataBaseIO existingDbio, Patient[] patients, Doctor[] doctors, Nurse[] nurses) {
        this.dbio = existingDbio;
        this.patients = patients;
        this.doctors = doctors;
        this.nurses = nurses;
        this.scanner = new Scanner(System.in);
    }

    public Authorizer(DataBaseIO existingDbio) {
        this.dbio = existingDbio;

        try {
            String[] patientsStr = dbio.readArrayByUserGroup('p');
            this.patients = new Patient[patientsStr.length];

            for(int i = 0; i < patientsStr.length; i ++) {
                this.patients[i] = (new Patient()).parseString(patientsStr[i]);
            }

            String[] doctorsStr = dbio.readArrayByUserGroup('d');
            this.doctors = new Doctor[doctorsStr.length];

            for(int i = 0; i < doctorsStr.length; i ++) {
                this.doctors[i] = (new Doctor()).parseString(doctorsStr[i]);
            }

            String[] nursesStr = dbio.readArrayByUserGroup('n');
            this.nurses = new Nurse[nursesStr.length];

            for(int i = 0; i < nursesStr.length; i ++) {
                this.nurses[i] = (new Nurse()).parseString(nursesStr[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.scanner = new Scanner(System.in);
    }

    public Authorizer() {
        this(new DataBaseIO());
    }

    public User findUserByLoginAndPassword(String login, String password) {
        for(Patient patient: this.patients) {
            if(login.equals(patient.getLogin()) && password.equals(patient.getPassword())) {
                return patient;
            }
        }

        for(Doctor doctor: this.doctors) {
            if(login.equals(doctor.getLogin()) && password.equals(doctor.getPassword())) {
                return doctor;
            }
        }

        for(Nurse nurse: this.nurses) {
            if(login.equals(nurse.getLogin()) && password.equals(nurse.getPassword())) {
                return nurse;
            }
        }

        return new User();
    }

    public User logIn() throws Exception {
        final int LOG_IN_ATTEMPTS = 3;
        String login, password;
        for(int i = 0; i < LOG_IN_ATTEMPTS; i ++) {
            System.out.println("login: ");
            login = this.scanner.nextLine();
            System.out.println("password: ");
            password = this.scanner.nextLine();

            User foundUser = this.findUserByLoginAndPassword(login, password);
            if(!(foundUser.equals(new User()))) {
                return foundUser;
            }
            else {
                System.out.println("Error: incorrect login or password, try again");
            }
        }

        throw new Exception("You are out of attempts");
    }

    public void signUp() {
        System.out.println("Name: ");
        String name = this.scanner.nextLine();

        System.out.println("Surname: ");
        String surname = this.scanner.nextLine();

        System.out.println("Age: ");
        String ageStr = this.scanner.nextLine();
        int age = Integer.parseInt(ageStr);

        System.out.println("Your login: ");
        String login = this.scanner.nextLine();

        System.out.println("Your password: ");
        String password = this.scanner.nextLine();

        User newUser = new User(name, surname, age, login, password);
        Appointment[] appointments = new Appointment[0];
        Treatment[] treatments = new Treatment[0];
        String[] diagnoses = new String[0];
        boolean recovered = true;

        Patient newPatient = new Patient(newUser, appointments, treatments, diagnoses, recovered);
        this.dbio.appendPatient(newPatient.toString());
    }
}
