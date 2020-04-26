package main.java.controllers.menus.user_menus;

import main.java.components.Treatment;
import main.java.users.stuff.Doctor;
import main.java.usersdb.PatientDB;
import main.java.usersdb.DoctorDB;
import main.java.usersdb.NurseDB;
import main.java.users.Patient;
import main.java.users.stuff.Nurse;
import main.java.components.Appointment;

import java.util.*;

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
                "3. Go to planned appointments\n" +
                "4. Change recover status of the patient\n" +
                "5. My department\n" +
                "6. Exit"
        );
    }

    private void addTreatmentMenu(Patient patient, Appointment appointment) {
        Scanner in = new Scanner(System.in);

        System.out.println("Medicine: ");
        String medicine = in.nextLine();

        System.out.println("Operation: ");
        String operation = in.nextLine();

        System.out.println("Procedure: ");
        String procedure = in.nextLine();

        System.out.println("Diagnose: ");
        String diagnose = in.nextLine();

        Treatment treatment = new Treatment(medicine, operation, procedure, diagnose);

        this.doctor.setTreatmentToPatient(patient, appointment, treatment);
        System.out.println("Done");
    }

    private void addGoToPlannedAppointmentsMenu() {
        ArrayList<Appointment> plannedAppointments = new ArrayList<Appointment>();
        ArrayList<Integer> appointmentIds = new ArrayList<Integer>(0);
        DoctorDB doctorDB = new DoctorDB();
        Scanner in = new Scanner(System.in);

        HashMap<Appointment, Integer> plannedAppointmentsToAppointmentIds =
                doctorDB.getDbReader().getDbUtils().getHashMapWithPlannedAppointmentsByDoctorToAppointmentIds(this.doctor);

        int optionCounter = 1;
        for(Map.Entry<Appointment, Integer> entry: plannedAppointmentsToAppointmentIds.entrySet()) {
            plannedAppointments.add(entry.getKey());
            appointmentIds.add(entry.getValue());

            Patient currentAppointmentPatient = doctorDB.getDbReader().getPatientByAppointmentId(entry.getValue());
            System.out.println(optionCounter + ". " +
                    currentAppointmentPatient.getName() +
                    " " + currentAppointmentPatient.getSurname());
            optionCounter++;
        }

        int chosenAppointmentNumber = 0;
        while(chosenAppointmentNumber < 1 || chosenAppointmentNumber > plannedAppointments.size()) {
            System.out.println("Print the number of an appointment:");
            chosenAppointmentNumber = in.nextInt();

            if(chosenAppointmentNumber < 1 || chosenAppointmentNumber > plannedAppointments.size()) {
                System.out.println("Incorrect appointment number");
            }
        }

        Appointment chosenAppointment = plannedAppointments.get(chosenAppointmentNumber - 1);
        int chosenAppointmentId = appointmentIds.get(chosenAppointmentNumber - 1);

        this.addTreatmentMenu(
                chosenAppointment,
                chosenAppointmentId,
                doctorDB.getDbReader().getPatientByAppointmentId(chosenAppointmentId));
    }

    public void addNurseAppointmentMenu(int appointmentId) {
        Scanner in = new Scanner(System.in);
        NurseDB nurseDB = new NurseDB();

        System.out.println("Nurse name: ");
        String name = in.nextLine();

        System.out.println("Nurse surname: ");
        String surname = in.nextLine();

        int chosenNurseId = nurseDB.getDbReader().getDbUtils().getNurseIdByNameAndSurname(name, surname);
        System.out.println(chosenNurseId);
        nurseDB.getDbUpdater().addRowToNurseTaskLog(appointmentId, chosenNurseId);

        System.out.println("Nurse is appointed");
    }

    public void addTreatmentMenu(Appointment chosenAppointment, int chosenAppointmentId, Patient patient) {
        Scanner in = new Scanner(System.in);
        System.out.println("Type 1 or 2");
        System.out.println("1. Do treatment");
        System.out.println("2. Appoint a nurse to do treatment");

        int option = in.nextInt();
        switch (option) {
            case 1:
                this.addTreatmentMenu(patient, chosenAppointment);
                break;
            case 2:
                this.addNurseAppointmentMenu(chosenAppointmentId);
                break;
            default:
                System.out.println("Wrong input");
                break;
        }
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
                    this.addGoToPlannedAppointmentsMenu();
                    break;
                case 4:
                    this.addRecoverMenu();
                    break;
                case 5:
                    System.out.println(this.doctor.getDepartment());
                    break;
                default:
                    break;
            }
        } while (variant != 6);
    }
}
