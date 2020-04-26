package main.java.components.searcher;

import main.java.components.Appointment;
import main.java.users.stuff.Doctor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppointmentSearcher {
    private List<Appointment> appointmentList;
    private Stream<Appointment> appointmentStream;

    public AppointmentSearcher(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
        this.appointmentStream = this.appointmentList.stream();
    }

    public List<Appointment> findAppointmentsByDate(int year, int month, int day) {
        return appointmentStream
                .filter(a -> a.getAppDate().getYear() == year && a.getAppDate().getMonth() == month && a.getAppDate().getDayOfMonth() == day)
                .collect(Collectors.toList());
    }

    public List<Appointment> findAppointmentsByDoctor(Doctor doctor) {
        return appointmentStream
                .filter(a -> a.getDoctor().equals(doctor))
                .collect(Collectors.toList());
    }
}
