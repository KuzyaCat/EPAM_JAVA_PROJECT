package main.java.controllers.menus.user_menus;

import main.java.components.Treatment;
import main.java.users.stuff.Nurse;
import main.java.usersdb.PatientDB;

import java.util.Scanner;

public class NurseMenu {
    private Nurse nurse;

    public NurseMenu(Nurse nurse) {
        this.nurse = nurse;
    }

    public int getVariant() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void showVariants() {
        System.out.println("Choose:");
        System.out.println(
                "1. My profile\n" +
                "2. Make a treatment to the patient\n" +
                "3. Exit"
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

        this.nurse.setTreatmentToPatient(patientDB.getPatient(name, surname), treatment);
        System.out.println("Done");
    }

    public void initMenu() {
        int variant = 0;
        do {
            this.showVariants();
            variant = this.getVariant();
            switch(variant) {
                case 1:
                    System.out.println(this.nurse.showProfile());
                    break;
                case 2:
                    this.addTreatmentMenu();
                    break;
                default:
                    break;
            }
        } while (variant != 3);
    }
}
