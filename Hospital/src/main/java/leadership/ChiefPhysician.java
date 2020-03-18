package main.java.leadership;

import main.java.User;

public class ChiefPhysician extends User {
    ChiefPhysician(String name, String surname, int age, String login, String password){
        super(name, surname, age, login, password);
    }

    public void appointDepHead(){
        System.out.println("Заведующий отделением назначен");
    }
}
