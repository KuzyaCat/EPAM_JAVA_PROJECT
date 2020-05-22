package main.java.controllers.menus.user_menus;

import main.java.components.Treatment;
import main.java.users.stuff.Nurse;
import main.java.components.Appointment;
import main.java.usersdb.NurseDB;

import java.util.ArrayList;
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
                "2. Go to appointed treatments\n" +
                "3. Exit"
        );
    }

    private void addTreatmentMenu(Appointment appointment) {
        Scanner in = new Scanner(System.in);
        NurseDB nurseDB = new NurseDB();

        System.out.println("Medicine: ");
        String medicine = in.nextLine();

        System.out.println("Operation: ");
        String operation = in.nextLine();

        System.out.println("Procedure: ");
        String procedure = in.nextLine();

        System.out.println("Diagnose: ");
        String diagnose = in.nextLine();

        Treatment treatment = new Treatment(appointment, procedure, medicine, operation, diagnose);

        this.nurse.setTreatmentToPatient(treatment);
        nurseDB.getDbUpdater().deleteNurseTaskLogByAppointmentAndNurse(appointment, this.nurse);

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
                    NurseDB nurseDB = new NurseDB();

                    ArrayList<Appointment> plannedAppointments = nurseDB.getDbReader().getAppointmentsByNurse(this.nurse);

                    int counter = 1;
                    for(Appointment appointment: plannedAppointments) {
                        System.out.println(counter + ". " + appointment.toString());
                        counter++;
                    }

                    if(plannedAppointments.size() == 0) {
                        System.out.println("No planned appointments");
                    }
                    else {
                        System.out.println("Print the number of an appointment:");
                        int chosenAppointmentNumber = (new Scanner(System.in)).nextInt();

                        if(chosenAppointmentNumber < 1 || chosenAppointmentNumber >= counter) {
                            System.out.println("Incorrect appointment number");
                        }
                        else {
                            this.addTreatmentMenu(plannedAppointments.get(chosenAppointmentNumber - 1));
                        }
                    }

                    break;
                default:
                    break;
            }
        } while (variant != 3);
    }
}
