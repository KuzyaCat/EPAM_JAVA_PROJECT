package main.java;

import main.java.controllers.resource_controllers.DataBaseIO;

public class Main {
    public static void main(String[] args) {
        DataBaseIO dbio = new DataBaseIO();
        dbio.shutdown();
    }
}
