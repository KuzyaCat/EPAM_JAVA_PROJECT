package main.java;

import main.java.components.Appointment;
import main.java.components.Treatment;
import main.java.controllers.menus.MainMenu;
import main.java.controllers.resource_controllers.DBReader;
import main.java.controllers.resource_controllers.DBUpdater;
import main.java.dbconnection.SessionProvider;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import org.hibernate.Session;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // MainMenu mainMenu = new MainMenu();
        SessionProvider sessionProvider = new SessionProvider();
        Session session = sessionProvider.getSessionFactory().openSession();
        DBReader dbReader = new DBReader(session);

        Patient st = dbReader.getPersistentPatientById(1);
        System.out.println(st.getName());
        Doctor d = dbReader.getDoctorByNameAndSurname("Boris", "Levin");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.DAY_OF_MONTH, 21);
        Date date = cal.getTime();

        Appointment appointment = new Appointment(st, d, d.getDepartment(), date);

        DBUpdater dbUpdater = new DBUpdater(session);
        dbUpdater.addAppointment(appointment);

        dbReader.shutdown();
        dbUpdater.shutdown();
    }
}
