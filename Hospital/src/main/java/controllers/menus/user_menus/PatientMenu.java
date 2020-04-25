package main.java.controllers.menus.user_menus;

import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.date.GregorianDate;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.usersdb.DoctorDB;
import main.java.usersdb.PatientDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PatientMenu {
    private Patient patient;

    public PatientMenu(Patient patient) {
        this.patient = patient;
    }

    public int getVariant() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void showVariants() {
        System.out.println("Choose:");
        System.out.println(
                "1. My profile\n" +
                "2. Make an appointment\n" +
                "3. My appointments\n" +
                "4. My treatments\n" +
                "5. Exit"
        );
    }

    public void initMenu() {
        int variant = 0;
        do {
            showVariants();
            variant = this.getVariant();
            switch(variant) {
                case 1:
                    System.out.println(this.patient.showProfile());
                    break;
                case 2:
                    this.appointMenu();
                    break;
                case 3:
                    ArrayList<Appointment> appointments = this.patient.getAppointments();
                    System.out.println("My appointments:");
                    System.out.println("Overall: " + appointments.size());
                    System.out.println(Arrays.toString(appointments.toArray()));
                    break;
                case 4:
                    ArrayList<Treatment> treatments = this.patient.getTreatments();
                    System.out.println("My treatments:");
                    System.out.println("Overall: " + treatments.size());
                    System.out.println(Arrays.toString(treatments.toArray()));
                    break;
                default:
                    break;
            }
        } while (variant != 5);
    }

    private void appointMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write doctor name");
        String name = in.nextLine();
        System.out.println("Write doctor surname");
        String surname = in.nextLine();
        DoctorDB doctorDB = new DoctorDB();
        Doctor doctor = doctorDB.getDoctor(name, surname);
        System.out.println("Write a year of the appointment");
        int year = in.nextInt();
        System.out.println("Write a month of the appointment");
        int month = in.nextInt();
        System.out.println("Write a day of the appointment");
        int day = in.nextInt();
        Appointment appointment = new Appointment(doctor, doctor.getDepartment(), new GregorianDate(year, month, day));
        PatientDB patientDB = new PatientDB();
        patientDB.writeAppointment(this.patient, appointment);
        System.out.println("Done");
    }
}
