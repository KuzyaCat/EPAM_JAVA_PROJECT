package main.java.stuff;

import main.java.User;

public class Nurse extends User {
    Nurse(String name, String surname, int age, String login, String password){
        super(name, surname, age, login, password);
    }
    public void doTreatment(){
        System.out.println("Лечение назначено");
    }
}
