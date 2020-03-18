package main.java.controllers.resource_controllers;

import main.java.users.User;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.users.stuff.Nurse;
import main.java.controllers.resource_controllers.DataBaseIO;

public class Authorizer {
    private DataBaseIO dbio;
    private Patient[] patients;
    private Doctor[] doctors;
    private Nurse[] nurses;

    public Authorizer() {
        this.dbio = new DataBaseIO();

    }
}
