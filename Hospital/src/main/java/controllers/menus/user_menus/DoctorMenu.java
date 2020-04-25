package main.java.controllers.menus.user_menus;

import main.java.components.Treatment;
import main.java.users.stuff.Doctor;
import main.java.usersdb.PatientDB;

import java.util.Arrays;
import java.util.Scanner;

public class DoctorMenu {
    private Doctor doctor;

    public DoctorMenu(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getVariant() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void showVariants() {
        System.out.println("Choose:");
        System.out.println(
                "1. My profile\n" +
                "2. My patients\n" +
                "3. Add a treatment to the patient\n" +
                "4. Change recover status of the patient\n" +
                "5. My department\n" +
                "6. Exit"
        );
    }

    private void addTreatmentMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write patient name");
        String name = in.nextLine();
        System.out.println("Write patient surname");
        String surname = in.nextLine();

        PatientDB patientDB = new PatientDB();

        System.out.println("Medicine: ");
        String medicine = in.nextLine();

        System.out.println("Operation: ");
        String operation = in.nextLine();

        System.out.println("Procedure: ");
        String procedure = in.nextLine();

        Treatment treatment = new Treatment(medicine, operation, procedure);

        this.doctor.setTreatmentToPatient(patientDB.getPatient(name, surname), treatment);
        System.out.println("Done");
    }

    private void addDiagnoseMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write patient name");
        String name = in.nextLine();
        System.out.println("Write patient surname");
        String surname = in.nextLine();

        PatientDB patientDB = new PatientDB();

        System.out.println("Write a diagnose");
        String diagnose = in.nextLine();

        this.doctor.setDiagnoseToPatient(patientDB.getPatient(name, surname), diagnose);
        System.out.println("Done");
    }

    private void addRecoverMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write patient name");
        String name = in.nextLine();
        System.out.println("Write patient surname");
        String surname = in.nextLine();

        PatientDB patientDB = new PatientDB();

        this.doctor.setRecoverToPatient(patientDB.getPatient(name, surname), true);
        System.out.println("Done");
    }

    public void initMenu() {
        int variant = 0;
        do {
            this.showVariants();
            variant = this.getVariant();
            switch(variant) {
                case 1:
                    System.out.println(this.doctor.showProfile());
                    break;
                case 2:
                    System.out.println(this.doctor.getPatientAppointments());
                    break;
                case 3:
                    this.addTreatmentMenu();
                    break;
                case 4:
                    this.addDiagnoseMenu();
                    break;
                case 5:
                    this.addRecoverMenu();
                    break;
                case 6:
                    String[] strs = this.doctor.getDepartment().split("_");
                    String result = "";
                    for (String str : strs) {
                        result += str + " ";
                    }
                    System.out.println(result);
                    break;
                default:
                    break;
            }
        } while (variant != 7);
    }
}
