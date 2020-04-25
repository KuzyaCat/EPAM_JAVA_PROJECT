package main.java.controllers.menus;

import main.java.controllers.menus.user_menus.DoctorMenu;
import main.java.controllers.menus.user_menus.NurseMenu;
import main.java.controllers.menus.user_menus.PatientMenu;
import main.java.controllers.resource_controllers.Authorizer;
import main.java.users.Patient;
import main.java.users.User;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;
import main.java.usersdb.DoctorDB;
import main.java.usersdb.NurseDB;
import main.java.usersdb.PatientDB;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
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
                    Authorizer auth = new Authorizer();
                    try {
                        auth.signUp();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    private void initMenuForAuthorizedUser() {
        User authorizedUser = null;
        Authorizer auth = new Authorizer();
        try {
            authorizedUser = auth.logIn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (authorizedUser instanceof Patient) {
            PatientDB patientDB = new PatientDB();
            ArrayList<Patient> patients = patientDB.getAllPatients();
            Patient patient = null;

            for (Patient value : patients) {
                if (value.getLogin().equals(authorizedUser.getLogin()) && value.getPassword().equals(authorizedUser.getPassword())) {
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
                if (value.getLogin().equals(authorizedUser.getLogin()) && value.getPassword().equals(authorizedUser.getPassword())) {
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
                if (value.getLogin().equals(authorizedUser.getLogin()) && value.getPassword().equals(authorizedUser.getPassword())) {
                    nurse = value;
                    break;
                }
            }
            NurseMenu menu = new NurseMenu(nurse);
            menu.initMenu();
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
