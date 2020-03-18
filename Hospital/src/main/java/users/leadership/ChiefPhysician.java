package main.java.users.leadership;

import main.java.users.User;

public class ChiefPhysician extends User {
    ChiefPhysician(String name, String surname, int age, String login, String password){
        super(name, surname, age, login, password);
    }

    public void appointDepHead(){
        System.out.println("Заведующий отделением назначен");
    }
}
