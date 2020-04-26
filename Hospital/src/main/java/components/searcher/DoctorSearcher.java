package main.java.components.searcher;

import main.java.users.stuff.Doctor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DoctorSearcher {
    private List<Doctor> doctorList;
    private Stream<Doctor> doctorStream;

    public DoctorSearcher(List<Doctor> doctorList) {
        this.doctorList = doctorList;
        this.doctorStream = this.doctorList.stream();
    }

    public List<Doctor> findDoctorsByDepartment(String department) {
        return doctorStream
                .filter(d -> d.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public Doctor findHeadOfDepartment(String department) {
        return doctorStream
                .filter(d -> d.getDepartment().equals(department))
                .findAny()
                .orElse(new Doctor());
    }

    public List<Doctor> findDoctorsByFirstName(String firstName) {
        return doctorStream
                .filter(d -> d.getName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<Doctor> findDoctorsBySurname(String surname) {
        return doctorStream
                .filter(d -> d.getName().equals(surname))
                .collect(Collectors.toList());
    }

    public List<Doctor> findDoctorsByFullName(String firstName, String surname) {
        return doctorStream
                .filter(d -> d.getSurname().equals(surname) && d.getName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<Doctor> findDoctorsByAge(int age) {
        return doctorStream
                .filter(d -> d.getAge() == age)
                .collect(Collectors.toList());
    }
}
