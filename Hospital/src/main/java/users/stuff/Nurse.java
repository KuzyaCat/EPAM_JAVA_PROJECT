package main.java.users.stuff;

import main.java.users.User;

public class Nurse extends User {
    public Nurse(String name, String surname, int age, String login, String password){
        super(name, surname, age, login, password);
    }

    public Nurse(User user) {
        super(user.getName(), user.getSurname(), user.getAge(), user.getLogin(), user.getPassword());
    }

    public Nurse() {
        super("", "", 0, "", "");
    }

    public void doTreatment(){
        System.out.println("Лечение назначено");
    }

    public String toString() {
        return super.toString();
    }

    public Nurse parseString(String str) {
        return new Nurse(super.parseString(str));
    }
}
