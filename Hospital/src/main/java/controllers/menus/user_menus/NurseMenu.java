package main.java.controllers.menus.user_menus;

import controllers.resource_controllers.DBUtils;
import main.java.components.Treatment;
import main.java.users.stuff.Nurse;
import main.java.users.Patient;
import main.java.components.Appointment;
import main.java.usersdb.NurseDB;

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

    private void addTreatmentMenu(Patient patient, Appointment appointment, int appointmentId) {
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

        Treatment treatment = new Treatment(medicine, operation, procedure, diagnose);

        this.nurse.setTreatmentToPatient(patient, appointment, treatment);
        int nurseId = nurseDB.getDbReader().getDbUtils().getNurseIdByNameAndSurname(this.nurse.getName(), this.nurse.getSurname());
        nurseDB.getDbUpdater().deleteRowFromNurseTaskLog(appointmentId, nurseId);

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
                    DBUtils dbUtils = nurseDB.getDbReader().getDbUtils();
                    int nurseId = dbUtils.getNurseIdByNameAndSurname(this.nurse.getName(), this.nurse.getSurname());

                    int appointmentId = dbUtils.getAppointmentIdByNurseId(nurseId);
                    Appointment appointment = dbUtils.getAppointmentById(appointmentId);
                    Patient patient = dbUtils.getPatientById(dbUtils.getPatientIdByAppointmentId(appointmentId));

                    this.addTreatmentMenu(patient, appointment, appointmentId);
                    break;
                default:
                    break;
            }
        } while (variant != 3);
    }
}
