package main.java.users.leadership;

import main.java.users.User;
import main.java.users.stuff.Doctor;
import main.java.usersdb.DoctorDB;

public class ChiefPhysician extends User {
    public ChiefPhysician(String name, String surname, int age, String login, String password){
        super(name, surname, age, login, password);
    }

    public void appointDepHead(Doctor doctor){
        DoctorDB doctorDB = new DoctorDB();
        doctorDB.writeIsDepartmentHead(doctor);
    }
}
