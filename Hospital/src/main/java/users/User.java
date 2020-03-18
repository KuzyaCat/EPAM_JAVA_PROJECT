package main.java.users;

public class User {
    private String name;
    private String surname;
    private int age;
    private String login;
    private String password;

    public User(String name, String surname, int age, String login, String password){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.login = login;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void logOut(){
        System.out.println("Пользователь вышел из приложения");
    }
}
