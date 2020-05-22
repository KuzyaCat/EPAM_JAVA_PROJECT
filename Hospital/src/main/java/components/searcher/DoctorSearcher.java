package main.java.components.searcher;

import main.java.users.stuff.Doctor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DoctorSearcher {
    private List<Doctor> doctorList;

    public DoctorSearcher(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<Doctor> findDoctorsByDepartment(String department) {
        Stream<Doctor> doctorStream = this.doctorList.stream();

        Comparator<Doctor> compareByName = Comparator
                .comparing(Doctor::getSurname)
                .thenComparing(Doctor::getName);

        return doctorStream
                .filter(d -> d.getDepartment().equals(department))
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public Doctor findHeadOfDepartment(String department) {
        Stream<Doctor> doctorStream = this.doctorList.stream();

        return doctorStream
                .filter(d -> d.getDepartment().equals(department))
                .findAny()
                .orElse(new Doctor());
    }

    public List<Doctor> findDoctorsByFirstName(String firstName) {
        Stream<Doctor> doctorStream = this.doctorList.stream();

        Comparator<Doctor> compareByName = Comparator
                .comparing(Doctor::getSurname)
                .thenComparing(Doctor::getName);

        return doctorStream
                .filter(d -> d.getName().equals(firstName))
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Doctor> findDoctorsBySurname(String surname) {
        Stream<Doctor> doctorStream = this.doctorList.stream();

        Comparator<Doctor> compareByName = Comparator
                .comparing(Doctor::getSurname)
                .thenComparing(Doctor::getName);

        return doctorStream
                .filter(d -> d.getSurname().equals(surname))
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Doctor> findDoctorsByFullName(String firstName, String surname) {
        Stream<Doctor> doctorStream = this.doctorList.stream();

        Comparator<Doctor> compareByName = Comparator
                .comparing(Doctor::getSurname)
                .thenComparing(Doctor::getName);

        return doctorStream
                .filter(d -> d.getSurname().equals(surname) && d.getName().equals(firstName))
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Doctor> findDoctorsByAge(int age) {
        Stream<Doctor> doctorStream = this.doctorList.stream();

        Comparator<Doctor> compareByAge = Comparator
                .comparing(Doctor::getAge);

        return doctorStream
                .filter(d -> d.getAge() == age)
                .sorted(compareByAge)
                .collect(Collectors.toList());
    }
}
