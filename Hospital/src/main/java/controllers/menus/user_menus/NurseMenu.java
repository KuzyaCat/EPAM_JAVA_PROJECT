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

        System.out.println("How many medicines?");
        String[] medicinesStr = new String[in.nextInt()];
        in.nextLine();
        for (int i = 0; i < medicinesStr.length; i += 1) {
            System.out.println("Write the medicine number " + (i + 1));
            medicinesStr[i] = in.nextLine();
        }

        System.out.println("How many operations?");
        String[] operationsStr = new String[in.nextInt()];
        in.nextLine();
        for (int i = 0; i < operationsStr.length; i += 1) {
            System.out.println("Write the operation number " + (i + 1));
            operationsStr[i] = in.nextLine();
        }

        System.out.println("How many procedure?");
        String[] proceduresStr = new String[in.nextInt()];
        in.nextLine();
        for (int i = 0; i < proceduresStr.length; i += 1) {
            System.out.println("Write the procedure number " + (i + 1));
            proceduresStr[i] = in.nextLine();
        }

        Treatment treatment = new Treatment(medicinesStr, operationsStr, proceduresStr);

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
