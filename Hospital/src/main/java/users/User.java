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

    public User() {
        this("", "", 0, "", "");
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

    @Override
    public String toString() {
        return this.name + " " + this.surname + " " + this.age + " " + this.login + " " + this.password;
    }

    public User parseString(String str) {
        String[] strArr = str.split(" ");
        int age = Integer.parseInt(strArr[2]);
        return new User(strArr[0], strArr[1], age, strArr[3], strArr[4]);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof User)) {
            return false;
        }

        User user = (User) o;
        return (this.name == user.name &&
                this.surname == user.surname &&
                this.age == user.age &&
                this.login == user.login &&
                this.password == user.password);
    }

    public void logOut(){
        System.out.println("Пользователь вышел из приложения");
    }
}
