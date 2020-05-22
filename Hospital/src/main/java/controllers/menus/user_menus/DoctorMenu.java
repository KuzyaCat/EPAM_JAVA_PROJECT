package main.java.controllers.menus.user_menus;

import main.java.components.Treatment;
import main.java.components.searcher.PatientSearcher;
import main.java.controllers.resource_controllers.DBReader;
import main.java.tasklogs.NurseTaskLog;
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
                "6. Search patients\n" +
                "7. Exit"
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

        Treatment treatment = new Treatment(appointment, procedure, medicine, operation, diagnose);

        this.doctor.setTreatmentToPatient(treatment);
        System.out.println("Done");
    }

    private void addGoToPlannedAppointmentsMenu() {
        DoctorDB doctorDB = new DoctorDB();
        Scanner in = new Scanner(System.in);

        ArrayList<Appointment> plannedAppointments = doctorDB.getDbReader()
                .getPlannedAppointmentsByDoctor(this.doctor);

        if(plannedAppointments.size() == 0) {
            System.out.println("No planned appointments");
        }
        else {
            for(int i = 0; i < plannedAppointments.size(); i++) {
                System.out.println((i + 1) + ". " + plannedAppointments.get(i).toString());
            }

            System.out.println("Print the number of an appointment:");
            int chosenAppointmentNumber = in.nextInt();

            if(chosenAppointmentNumber < 1 || chosenAppointmentNumber > plannedAppointments.size()) {
                System.out.println("Incorrect appointment number");
            }
            else {
                Appointment chosenAppointment = plannedAppointments.get(chosenAppointmentNumber - 1);

                this.addTreatmentMenu(
                        chosenAppointment,
                        doctorDB.getDbReader().getPatientByAppointment(chosenAppointment));
            }
        }
    }

    public void addNurseAppointmentMenu(Appointment appointment) {
        Scanner in = new Scanner(System.in);
        NurseDB nurseDB = new NurseDB();

        System.out.println("Nurse name: ");
        String name = in.nextLine();

        System.out.println("Nurse surname: ");
        String surname = in.nextLine();

        Nurse chosenNurse = nurseDB.getDbReader().getNurseByNameAndSurname(name, surname);
        nurseDB.getDbUpdater().addRowToNurseTaskLog(new NurseTaskLog(appointment, chosenNurse));

        System.out.println("Nurse is appointed");
    }

    public void addTreatmentMenu(Appointment chosenAppointment, Patient patient) {
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
                this.addNurseAppointmentMenu(chosenAppointment);
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

        boolean newIsRecovered = false;
        System.out.println("Recovered status (type 'y' if recovered, else type something else):");
        String inputStr = in.nextLine();
        char recoveredFlag = inputStr.charAt(0);
        if(recoveredFlag == 'y' && inputStr.length() == 1) {
            newIsRecovered = true;
        }

        Patient patient = null;
        try {
            patient = patientDB.getPatient(name, surname);
        } catch (Exception e) {
            System.out.println("Couldn't find a patient with these name and surname");
        }

        if(patient != null) {
            this.doctor.setRecoverToPatient(patient, newIsRecovered);
            System.out.println("Done");
        }

        patientDB.shutdown();
    }

    private void printSearchPatientsMenu() {
        System.out.println("Choose:");
        System.out.println(
                "1. Search patients by first name\n" +
                "2. Search patients by surname\n" +
                "3. Search patients by full name\n" +
                "4. Search recovered patients\n" +
                "5. Search ill patients\n" +
                "6. Search patients by diagnose\n" +
                "7. Search patients by procedure\n" +
                "8. Search patients by medicine\n" +
                "9. Search patients by operation\n" +
                "10. Search patients by age\n" +
                "11. Exit"
        );
    }

    private void initSearchPatientsMenu() {
        DBReader dbReader = new DBReader();
        List<Patient> allPatients = dbReader.getAllPatients();
        PatientSearcher myPatientSearcher = new PatientSearcher(allPatients);
        List<Patient> myPatients = myPatientSearcher.findPatientsByDoctor(this.doctor);

        PatientSearcher patientSearcher = new PatientSearcher(myPatients);
        Scanner in = new Scanner(System.in);
        List<Patient> resultList = new ArrayList<>();

        int variant = 0;
        do {
            this.printSearchPatientsMenu();
            variant = this.getVariant();
            switch(variant) {
                case 1:
                    System.out.println("Enter first name:");
                    String firstName = in.nextLine();
                    resultList = patientSearcher.findPatientsByFirstName(firstName);
                    System.out.println("Found " + resultList.size() + " patients");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 2:
                    System.out.println("Enter surname:");
                    String surname = in.nextLine();
                    resultList = patientSearcher.findPatientsBySurname(surname);
                    System.out.println("Found " + resultList.size() + " patients");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 3:
                    System.out.println("Enter first name:");
                    String first = in.nextLine();
                    System.out.println("Enter surname:");
                    String sur = in.nextLine();
                    resultList = patientSearcher.findPatientsByFullName(first, sur);
                    System.out.println("Found " + resultList.size() + " patients");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 4:
                    resultList = patientSearcher.findRecoveredPatients();
                    System.out.println("Found " + resultList.size() + " patients");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 5:
                    resultList = patientSearcher.findIllPatients();
                    System.out.println("Found " + resultList.size() + " patients");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 6:
                    System.out.println("Enter diagnose:");
                    String diagnose = in.nextLine();
                    resultList = patientSearcher.findPatientsByDiagnose(diagnose);
                    System.out.println("Found " + resultList.size() + " patients");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 7:
                    System.out.println("Enter procedure:");
                    String procedure = in.nextLine();
                    resultList = patientSearcher.findPatientsByProcedure(procedure);
                    System.out.println("Found " + resultList.size() + " patients");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 8:
                    System.out.println("Enter medicine:");
                    String medicine = in.nextLine();
                    resultList = patientSearcher.findPatientsByMedicine(medicine);
                    System.out.println("Found " + resultList.size() + " patients");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 9:
                    System.out.println("Enter operation:");
                    String operation = in.nextLine();
                    resultList = patientSearcher.findPatientsByOperation(operation);
                    System.out.println("Found " + resultList.size() + " patients");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 10:
                    System.out.println("Enter age:");
                    int age = in.nextInt();
                    resultList = patientSearcher.findPatientsByAge(age);
                    System.out.println("Found " + resultList.size() + " patients");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                default:
                    break;
            }
        } while (variant != 11);
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
                case 6:
                    this.initSearchPatientsMenu();
                default:
                    break;
            }
        } while (variant != 7);
    }
}
