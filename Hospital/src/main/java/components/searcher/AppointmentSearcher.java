package main.java.components.searcher;

import main.java.components.Appointment;
import main.java.users.stuff.Doctor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppointmentSearcher {
    private List<Appointment> appointmentList;

    public AppointmentSearcher(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Appointment> findAppointmentsByDate(int year, int month, int day) {
        Stream<Appointment> appointmentStream = this.appointmentList.stream();

        return appointmentStream
                .filter(a -> a.getAppDate().getYear() == year && a.getAppDate().getMonth() == month && a.getAppDate().getDay() == day)
                .sorted(Comparator.comparingInt(a -> a.getAppDate().getYear()))
                .collect(Collectors.toList());
    }

    public List<Appointment> findAppointmentsByDoctor(Doctor doctor) {
        Stream<Appointment> appointmentStream = this.appointmentList.stream();

        return appointmentStream
                .filter(a -> a.getDoctor().getName().equals(doctor.getName()) && a.getDoctor().getSurname().equals(doctor.getSurname()))
                .sorted(Comparator.comparingInt(a -> a.getGregorianAppDate().getYear()))
                .collect(Collectors.toList());
    }
}
