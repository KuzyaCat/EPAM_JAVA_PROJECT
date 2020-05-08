package main.java.controllers.menus.user_menus;

import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.components.searcher.AppointmentSearcher;
import main.java.components.searcher.DoctorSearcher;
import main.java.controllers.resource_controllers.DBReader;
import main.java.date.GregorianDate;
import main.java.dbconnection.DBConnector;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.usersdb.DoctorDB;
import main.java.usersdb.PatientDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                "5. Search doctors\n" +
                "6. Search appointments\n" +
                "7. Exit"
        );
    }

    private void printSearchDoctorsMenu() {
        System.out.println("Choose:");
        System.out.println(
                "1. Search doctors by first name\n" +
                "2. Search doctors by surname\n" +
                "3. Search doctors by full name\n" +
                "4. Search doctors by age\n" +
                "5. Search head of department\n" +
                "6. Search doctors by department\n" +
                "7. Exit"
        );
    }

    public void initSearchDoctorsMenu() {
        int variant = 0;

        DBReader dbReader = new DBReader(new DBConnector());
        List<Doctor> allDoctors = dbReader.getAllDoctors();
        DoctorSearcher doctorSearcher = new DoctorSearcher(allDoctors);

        do {
            printSearchDoctorsMenu();
            variant = this.getVariant();
            Scanner in = new Scanner(System.in);
            List<Doctor> resultList = new ArrayList<>();

            switch(variant) {
                case 1:
                    System.out.println("Enter first name:");
                    String firstName = in.nextLine();
                    resultList = doctorSearcher.findDoctorsByFirstName(firstName);
                    System.out.println("Found " + resultList.size() + " doctors");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 2:
                    System.out.println("Enter surname:");
                    String surname = in.nextLine();
                    resultList = doctorSearcher.findDoctorsBySurname(surname);
                    System.out.println("Found " + resultList.size() + " doctors");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 3:
                    System.out.println("Enter first name:");
                    String first = in.nextLine();
                    System.out.println("Enter surname:");
                    String sur = in.nextLine();
                    resultList = doctorSearcher.findDoctorsByFullName(first, sur);
                    System.out.println("Found " + resultList.size() + " doctors");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 4:
                    System.out.println("Enter age:");
                    int age = in.nextInt();
                    resultList = doctorSearcher.findDoctorsByAge(age);
                    System.out.println("Found " + resultList.size() + " doctors");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 5:
                    System.out.println("Enter department:");
                    String departmentOfHead = in.nextLine();
                    Doctor headOfDepartment = doctorSearcher.findHeadOfDepartment(departmentOfHead);
                    if (headOfDepartment != null) {
                        System.out.println(headOfDepartment.toString());
                    } else {
                        System.out.println("The head of the department not found");
                    }
                    break;
                case 6:
                    System.out.println("Enter department:");
                    String department = in.nextLine();
                    resultList = doctorSearcher.findDoctorsByDepartment(department);
                    System.out.println("Found " + resultList.size() + " doctors");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                default:
                    break;
            }
        } while (variant != 7);
    }

    private void printSearchAppointmentsMenu() {
        System.out.println("Choose:");
        System.out.println(
                "1. Search appointments by doctor\n" +
                "2. Search appointments by date\n" +
                "3. Exit"
        );
    }

    public void initSearchAppointmentsMenu() {
        DBReader dbReader = new DBReader(new DBConnector());
        List<Doctor> allDoctors = dbReader.getAllDoctors();
        AppointmentSearcher appointmentSearcher = new AppointmentSearcher(this.patient.getAppointments());

        int variant = 0;

        do {
            printSearchAppointmentsMenu();
            variant = this.getVariant();
            Scanner in = new Scanner(System.in);
            List<Appointment> resultList = new ArrayList<>();

            switch(variant) {
                case 1:
                    System.out.println("Enter doctor's first name:");
                    String firstName = in.nextLine();
                    System.out.println("Enter doctor's surname:");
                    String surname = in.nextLine();
                    Doctor doctor = new DoctorSearcher(allDoctors).findDoctorsByFullName(firstName, surname).get(0);
                    resultList = appointmentSearcher.findAppointmentsByDoctor(doctor);
                    System.out.println("Found " + resultList.size() + " appointments");
                    resultList.forEach(p -> System.out.println(p.toString()));
                    break;
                case 2:
                    System.out.println("Enter year:");
                    int year = in.nextInt();
                    System.out.println("Enter month:");
                    int month = in.nextInt();
                    System.out.println("Enter day:");
                    int day = in.nextInt();
                    resultList = appointmentSearcher.findAppointmentsByDate(year, month, day);
                    System.out.println("Found " + resultList.size() + " appointments");
                    resultList.forEach(a -> System.out.println(a.toString()));
                    break;
                default:
                    break;
            }
        } while (variant != 3);
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
                case 5:
                    initSearchDoctorsMenu();
                    break;
                case 6:
                    initSearchAppointmentsMenu();
                    break;
                default:
                    break;
            }
        } while (variant != 7);
    }

    private void appointMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write doctor name");
        String name = in.nextLine();
        System.out.println("Write doctor surname");
        String surname = in.nextLine();
        DoctorDB doctorDB = new DoctorDB();

        Doctor doctor = doctorDB.getDoctor(name, surname);

        if(doctor != null) {
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
        else {
            System.out.println("Can't find a doctor with these name and surname");
        }
    }
}
