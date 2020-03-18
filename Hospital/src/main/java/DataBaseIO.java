package main.java;

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

        this.patientsFilePath = rootDir + "\\database\\patients.txt";
        this.doctorsFilePath = rootDir + "\\database\\doctors.txt";
        this.nursesFilePath = rootDir + "\\database\\nurses.txt";

        try{
            this.patientsOStream = new FileWriter(this.patientsFilePath, true);
            this.doctorsOStream = new FileWriter(this.doctorsFilePath, true);
            this.nursesOStream = new FileWriter(this.nursesFilePath, true);

            this.patientsIStream = new FileReader(this.patientsFilePath);
            this.doctorsIStream = new FileReader(this.doctorsFilePath);
            this.nursesIStream = new FileReader(this.nursesFilePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
        final int MAX_PATIENTS_AMOUNT = 1000;
        String[] initPatients = new String[MAX_PATIENTS_AMOUNT];
        String[] patients = new String[0];
        String tempStr = "";
        int c;
        int length = 0;

        FileReader istream;

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
                if (c == 10 || c == 13) {
                    initPatients[length] = tempStr;
                    tempStr = "";
                    length ++;
                }
                else {
                    tempStr += currentSymbol;
                }
            }
            initPatients[length] = tempStr;
            length ++;

            patients = new String[length];
            for(int i = 0; i < length; i ++) {
                patients[i] = initPatients[i];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patients;
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

            if(insertIndex >= allPatients.length || insertIndex < 0) {
                System.out.println("Error: incorrect patient (insert) index");
                //тут может быть ваш логгер
            }
            else {
                this.clearPatientDB();

                for(int i = 0; i < allPatients.length; i ++) {
                    if(insertIndex == i) {
                        this.appendPatient(str + '\n');
                    }

                    if(i != allPatients.length - 1) {
                        this.appendPatient(allPatients[i] + '\n');
                    }
                    else {
                        this.appendPatient(allPatients[i]);
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
                System.out.println("Error: incorrect patient (removed) index");
                //тут может быть ваш логгер
            }
            else {
                this.clearPatientDB();

                for(int i = 0; i < allPatients.length; i ++) {
                    if(removedIndex != i) {
                        if(i + 1 == removedIndex && removedIndex == allPatients.length - 1) {
                            this.appendPatient(allPatients[i]);
                        }
                        else {
                            if(i != allPatients.length - 1) {
                                this.appendPatient(allPatients[i] + '\n');
                            }
                            else {
                                this.appendPatient(allPatients[i]);
                            }
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

            if(insertIndex >= allDoctors.length || insertIndex < 0) {
                System.out.println("Error: incorrect doctor (insert) index");
                //тут может быть ваш логгер
            }
            else {
                this.clearDoctorsDB();

                for(int i = 0; i < allDoctors.length; i ++) {
                    if(insertIndex == i) {
                        this.appendDoctor(str + '\n');
                    }

                    if(i != allDoctors.length - 1) {
                        this.appendDoctor(allDoctors[i] + '\n');
                    }
                    else {
                        this.appendDoctor(allDoctors[i]);
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
                System.out.println("Error: incorrect doctor (removed) index");
                //тут может быть ваш логгер
            }
            else {
                this.clearDoctorsDB();

                for(int i = 0; i < allDoctors.length; i ++) {
                    if(removedIndex != i) {
                        if(i + 1 == removedIndex && removedIndex == allDoctors.length - 1) {
                            this.appendDoctor(allDoctors[i]);
                        }
                        else {
                            if(i != allDoctors.length - 1) {
                                this.appendDoctor(allDoctors[i] + '\n');
                            }
                            else {
                                this.appendDoctor(allDoctors[i]);
                            }
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

            if(insertIndex >= allNurses.length || insertIndex < 0) {
                System.out.println("Error: incorrect nurse (insert) index");
                //тут может быть ваш логгер
            }
            else {
                this.clearNursesDB();

                for(int i = 0; i < allNurses.length; i ++) {
                    if(insertIndex == i) {
                        this.appendNurse(str + '\n');
                    }

                    if(i != allNurses.length - 1) {
                        this.appendNurse(allNurses[i] + '\n');
                    }
                    else {
                        this.appendNurse(allNurses[i]);
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
                System.out.println("Error: incorrect nurse (removed) index");
                //тут может быть ваш логгер
            }
            else {
                this.clearNursesDB();

                for(int i = 0; i < allNurses.length; i ++) {
                    if(removedIndex != i) {
                        if(i + 1 == removedIndex && removedIndex == allNurses.length - 1) {
                            this.appendNurse(allNurses[i]);
                        }
                        else {
                            if(i != allNurses.length - 1) {
                                this.appendNurse(allNurses[i] + '\n');
                            }
                            else {
                                this.appendNurse(allNurses[i]);
                            }
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
