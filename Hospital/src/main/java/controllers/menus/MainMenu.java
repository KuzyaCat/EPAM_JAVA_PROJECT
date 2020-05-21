package main.java.controllers.menus;

import main.java.controllers.menus.user_menus.DoctorMenu;
import main.java.controllers.menus.user_menus.NurseMenu;
import main.java.controllers.menus.user_menus.PatientMenu;
import main.java.controllers.resource_controllers.Authorizer;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;
import main.java.usersdb.DoctorDB;
import main.java.usersdb.NurseDB;
import main.java.usersdb.PatientDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    static Logger logger = LogManager.getLogger();

    public MainMenu() {
        this.initMenu();
    }

    private void initMenu() {
        int variant = 0;
        do {
            this.showVariants();
            variant = this.getVariant();
            switch(variant) {
                case 1:
                    this.initMenuForSignUp();
                    break;
                case 2:
                    this.initMenuForAuthorizedUser();
                    break;
                default:
                    break;
            }
        } while (variant != 3);
    }

    private void showVariants() {
        System.out.println("Choose:");
        System.out.println(
                "1. Sign Up\n" +
                "2. Log In\n" +
                "3. Exit"
        );
    }

    private int getVariant() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private void initMenuForSignUp() {
        Authorizer auth = new Authorizer();
        Patient newPatient = auth.signUp();

        PatientMenu menu = new PatientMenu(newPatient);
        menu.initMenu();
    }

    private void initMenuForAuthorizedUser() {
        Object authorizedUser = null;
        Authorizer auth = new Authorizer();
        try {
            authorizedUser = auth.logIn();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        if (authorizedUser instanceof Patient) {
            PatientDB patientDB = new PatientDB();
            ArrayList<Patient> patients = patientDB.getAllPatients();
            Patient patient = null;

            for (Patient value : patients) {
                if (value.getLogin().equals(((Patient) authorizedUser).getLogin()) && value.getPassword().equals(((Patient) authorizedUser).getPassword())) {
                    patient = value;
                    break;
                }
            }
            PatientMenu menu = new PatientMenu(patient);
            menu.initMenu();
        } else if (authorizedUser instanceof Doctor) {
            DoctorDB doctorDB = new DoctorDB();
            ArrayList<Doctor> doctors = doctorDB.getAllDoctors();
            Doctor doctor = null;

            for (Doctor value : doctors) {
                if (value.getLogin().equals(((Doctor) authorizedUser).getLogin()) && value.getPassword().equals(((Doctor) authorizedUser).getPassword())) {
                    doctor = value;
                    break;
                }
            }
            DoctorMenu menu = new DoctorMenu(doctor);
            menu.initMenu();
        } else if (authorizedUser instanceof Nurse) {
            NurseDB nurseDB = new NurseDB();
            ArrayList<Nurse> nurses = nurseDB.getAllNurses();
            Nurse nurse = null;

            for (Nurse value : nurses) {
                if (value.getLogin().equals(((Nurse) authorizedUser).getLogin()) && value.getPassword().equals(((Nurse) authorizedUser).getPassword())) {
                    nurse = value;
                    break;
                }
            }
            NurseMenu menu = new NurseMenu(nurse);
            menu.initMenu();
        }
    }
}
