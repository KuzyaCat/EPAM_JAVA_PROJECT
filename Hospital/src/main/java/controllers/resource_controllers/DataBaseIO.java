package main.java.controllers.resource_controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

// класс DataBaseIO
//
// поля: три пути к фалам бд: бд с пациентами, бд с докторами и бд с медсестрами
//       соответственно, три потока ввода и три потока вывода
//
// конструктор, без параметров
//
// методы:
//       set[Patients/Doctors/Nurses]OStreamMode(boolean toAppend) - внутренний метод класса; используется для того,
//           чтобы изменить флажок append потока вывода на true / false (если append false, то файл
//           с каждым разом переписывается, если true - то вся инфа добавляется в конец файла)
//       readArrayByUserGroup(char userGroupCh) - считывает содержимое файла; userGroupCh - специальный флажок,
//           и если он равен:
//               p , то считывается содержимое бд пациентов
//               d , бд докторов
//               n , бд медсестер
//           содержимое файла считывается в массив строк
//       append[Patient/Doctor/Nurse](String str) - добавляет новую запись в конец соотв. файла
//       insert[Patient/Doctor/Nurse](String str, int insertIndex) - вставка новой записи на позицию insertIndex в соотв. файле
//       remove[Patient/Doctor/Nurse](int removedIndex) - удаление записи, стоящей на позиции removedIndex в соотв. файле
//       clear[Patient/Doctor/Nurse]DB() - удаление всего содержимого соотв. файла
//       shutdown() - закрытие всех потоков ввода и вывода


public class DataBaseIO {
    static Logger logger = LogManager.getLogger();

    private String patientsFilePath;
    private String doctorsFilePath;
    private String nursesFilePath;

    private FileReader patientsIStream;
    private FileReader doctorsIStream;
    private FileReader nursesIStream;

    private FileWriter patientsOStream;
    private FileWriter doctorsOStream;
    private FileWriter nursesOStream;

    public DataBaseIO() {
        String rootDir = System.getProperty("user.dir");

        this.patientsFilePath = rootDir + "/database/patients.txt";
        this.doctorsFilePath = rootDir + "/database/doctors.txt";
        this.nursesFilePath = rootDir + "/database/nurses.txt";

        this.openStreams();
    }



    public void setPatientsOStreamMode(boolean toAppend) {
        try {
            this.patientsOStream = new FileWriter(this.patientsFilePath, toAppend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDoctorsOStreamMode(boolean toAppend) {
        try {
            this.doctorsOStream = new FileWriter(this.doctorsFilePath, toAppend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNursesOStreamMode(boolean toAppend) {
        try {
            this.nursesOStream = new FileWriter(this.nursesFilePath, toAppend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String[] readArrayByUserGroup(char userGroupCh) throws Exception {
        final int MAX_USERS_AMOUNT = 1000;
        String[] initUsers = new String[MAX_USERS_AMOUNT];
        String[] users = new String[0];
        String tempStr = "";
        int c;
        int length = 0;

        FileReader istream;
        this.restart();

        switch(userGroupCh) {
            case 'p':
                istream = this.patientsIStream;
                break;
            case 'd':
                istream = this.doctorsIStream;
                break;
            case 'n':
                istream = this.nursesIStream;
                break;
            default:
                throw new Exception("Error: incorrect user group input");
        }

        try {
            while ((c = istream.read()) != -1) {
                char currentSymbol = (char)c;
                if ((c == 10 || c == 13) && !tempStr.equals("")) {
                    initUsers[length] = tempStr;
                    tempStr = "";
                    length ++;
                }
                else {
                    if(!(c == 10 || c == 13)) {
                        tempStr += currentSymbol;
                    }
                }
            }
            initUsers[length] = tempStr;
            length ++;

            users = new String[length];
            for(int i = 0; i < length; i ++) {
                users[i] = initUsers[i];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }



    public void appendPatient(String str) {
        try {
            this.patientsOStream.write(str);
            this.patientsOStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertPatient(String str, int insertIndex) {
        try {
            String[] allPatients = this.readArrayByUserGroup('p');

            if(insertIndex == allPatients.length) {
                this.appendPatient("\n" + str);
            }
            else if(insertIndex > allPatients.length || insertIndex < 0) {
                logger.error("Incorrect patient (insert) index");
            }
            else {
                this.clearPatientDB();

                for(int i = 0; i < allPatients.length; i ++) {
                    if(insertIndex == i) {
                        if(insertIndex == 0) {
                            this.appendPatient(str);
                        }
                        else {
                            this.appendPatient("\n" + str);
                        }
                    }

                    if(i == 0) {
                        if(insertIndex == 0) {
                            this.appendPatient("\n" + allPatients[i]);
                        }
                        else {
                            this.appendPatient(allPatients[i]);
                        }
                    }
                    else {
                        this.appendPatient("\n" + allPatients[i]);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removePatient(int removedIndex) {
        try {
            String[] allPatients = this.readArrayByUserGroup('p');

            if(removedIndex >= allPatients.length || removedIndex < 0) {
                logger.error("Incorrect patient (removed) index");
            }
            else {
                this.clearPatientDB();

                for(int i = 0; i < allPatients.length; i ++) {
                    if(removedIndex != i) {
                        if(i != 0) {
                            if(i == 1 && removedIndex == 0) {
                                this.appendPatient(allPatients[i]);
                            }
                            else {
                                this.appendPatient("\n" + allPatients[i]);
                            }
                        }
                        else {
                            this.appendPatient(allPatients[i]);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearPatientDB() {
        try {
            this.setPatientsOStreamMode(false);
            this.patientsOStream.write("");
            this.patientsOStream.flush();
            this.setPatientsOStreamMode(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void appendDoctor(String str) {
        try {
            this.doctorsOStream.write(str);
            this.doctorsOStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertDoctor(String str, int insertIndex) {
        try {
            String[] allDoctors = this.readArrayByUserGroup('d');

            if(insertIndex == allDoctors.length) {
                this.appendDoctor("\n" + str);
            }
            else if(insertIndex > allDoctors.length || insertIndex < 0) {
                logger.error("Incorrect doctor (insert) index");
            }
            else {
                this.clearDoctorsDB();

                for(int i = 0; i < allDoctors.length; i ++) {
                    if(insertIndex == i) {
                        if(insertIndex == 0) {
                            this.appendDoctor(str);
                        }
                        else {
                            this.appendDoctor("\n" + str);
                        }
                    }

                    if(i == 0) {
                        if(insertIndex == 0) {
                            this.appendDoctor("\n" + allDoctors[i]);
                        }
                        else {
                            this.appendDoctor(allDoctors[i]);
                        }
                    }
                    else {
                        this.appendDoctor("\n" + allDoctors[i]);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeDoctor(int removedIndex) {
        try {
            String[] allDoctors = this.readArrayByUserGroup('d');

            if(removedIndex >= allDoctors.length || removedIndex < 0) {
                logger.error("Incorrect doctor (removed) index");
            }
            else {
                this.clearDoctorsDB();

                for(int i = 0; i < allDoctors.length; i ++) {
                    if(removedIndex != i) {
                        if(i != 0) {
                            if(i == 1 && removedIndex == 0) {
                                this.appendDoctor(allDoctors[i]);
                            }
                            else {
                                this.appendDoctor("\n" + allDoctors[i]);
                            }
                        }
                        else {
                            this.appendDoctor(allDoctors[i]);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearDoctorsDB() {
        try {
            this.setDoctorsOStreamMode(false);
            this.doctorsOStream.write("");
            this.doctorsOStream.flush();
            this.setDoctorsOStreamMode(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void appendNurse(String str) {
        try {
            this.nursesOStream.write(str);
            this.nursesOStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertNurse(String str, int insertIndex) {
        try {
            String[] allNurses = this.readArrayByUserGroup('n');

            if(insertIndex == allNurses.length) {
                this.appendNurse("\n" + str);
            }
            else if(insertIndex > allNurses.length || insertIndex < 0) {
                logger.error("Incorrect nurse (insert) index");
            }
            else {
                this.clearNursesDB();

                for(int i = 0; i < allNurses.length; i ++) {
                    if(insertIndex == i) {
                        if(insertIndex == 0) {
                            this.appendNurse(str);
                        }
                        else {
                            this.appendNurse("\n" + str);
                        }
                    }

                    if(i == 0) {
                        if(insertIndex == 0) {
                            this.appendNurse("\n" + allNurses[i]);
                        }
                        else {
                            this.appendNurse(allNurses[i]);
                        }
                    }
                    else {
                        this.appendNurse("\n" + allNurses[i]);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeNurse(int removedIndex) {
        try {
            String[] allNurses = this.readArrayByUserGroup('n');

            if(removedIndex >= allNurses.length || removedIndex < 0) {
                logger.error("Incorrect nurse (removed) index");
            }
            else {
                this.clearNursesDB();

                for(int i = 0; i < allNurses.length; i ++) {
                    if(removedIndex != i) {
                        if(i != 0) {
                            if(i == 1 && removedIndex == 0) {
                                this.appendNurse(allNurses[i]);
                            }
                            else {
                                this.appendNurse("\n" + allNurses[i]);
                            }
                        }
                        else {
                            this.appendNurse(allNurses[i]);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearNursesDB() {
        try {
            this.setNursesOStreamMode(false);
            this.nursesOStream.write("");
            this.nursesOStream.flush();
            this.setNursesOStreamMode(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void openStreams() {
        try {
            this.patientsOStream = new FileWriter(this.patientsFilePath, true);
            this.doctorsOStream = new FileWriter(this.doctorsFilePath, true);
            this.nursesOStream = new FileWriter(this.nursesFilePath, true);

            this.patientsIStream = new FileReader(this.patientsFilePath);
            this.doctorsIStream = new FileReader(this.doctorsFilePath);
            this.nursesIStream = new FileReader(this.nursesFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void restart() {
        this.shutdown();
        this.openStreams();
    }



    public void shutdown() {
        try {
            this.patientsIStream.close();
            this.doctorsIStream.close();
            this.nursesIStream.close();

            this.patientsOStream.close();
            this.doctorsOStream.close();
            this.nursesOStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
