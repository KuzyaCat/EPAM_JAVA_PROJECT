package main.java.components.searcher;

import main.java.components.Appointment;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PatientSearcher {
    private List<Patient> patientList;

    public PatientSearcher(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public List<Patient> findRecoveredPatients() {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByName = Comparator
                .comparing(Patient::getSurname)
                .thenComparing(Patient::getName);

        return patientStream
                .filter(Patient::isRecovered)
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Patient> findIllPatients() {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByName = Comparator
                .comparing(Patient::getSurname)
                .thenComparing(Patient::getName);

        return patientStream
                .filter(p -> !p.isRecovered())
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByProcedure(String procedure) {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByName = Comparator
                .comparing(Patient::getSurname)
                .thenComparing(Patient::getName);

        return patientStream
                .filter(
                        patient -> patient.getAppointments().stream()
                                .map(Appointment::getTreatment)
                                .anyMatch(treatment -> treatment.getProcedure().equals(procedure))
                )
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByDiagnose(String diagnose) {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByName = Comparator
                .comparing(Patient::getSurname)
                .thenComparing(Patient::getName);

        return patientStream
                .filter(
                        patient -> patient.getAppointments().stream()
                                .map(Appointment::getTreatment)
                                .anyMatch(treatment -> treatment.getDiagnose().equals(diagnose))
                )
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByMedicine(String medicine) {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByName = Comparator
                .comparing(Patient::getSurname)
                .thenComparing(Patient::getName);

        return patientStream
                .filter(
                        patient -> patient.getAppointments().stream()
                                .map(Appointment::getTreatment)
                                .anyMatch(treatment -> treatment.getMedicine().equals(medicine))
                )
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByOperation(String operation) {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByName = Comparator
                .comparing(Patient::getSurname)
                .thenComparing(Patient::getName);

        return patientStream
                .filter(
                        patient -> patient.getAppointments().stream()
                                .map(Appointment::getTreatment)
                                .anyMatch(treatment -> treatment.getOperation().equals(operation))
                )
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByDoctor(Doctor doctor) {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByName = Comparator
                .comparing(Patient::getSurname)
                .thenComparing(Patient::getName);

        return patientStream
                .filter(p -> {
                    AtomicBoolean contains = new AtomicBoolean(false);
                    p.getAppointments().forEach(a -> {
                        if (a.getDoctor().getName().equals(doctor.getName()) && a.getDoctor().getSurname().equals(doctor.getSurname())) {
                            contains.set(true);
                        }
                    });
                    return contains.get();
                })
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByFirstName(String firstName) {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByName = Comparator
                .comparing(Patient::getSurname)
                .thenComparing(Patient::getName);

        return patientStream
                .filter(p -> p.getName().equals(firstName))
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsBySurname(String surname) {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByName = Comparator
                .comparing(Patient::getSurname)
                .thenComparing(Patient::getName);

        return patientStream
                .filter(p -> p.getSurname().equals(surname))
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByFullName(String firstName, String surname) {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByName = Comparator
                .comparing(Patient::getSurname)
                .thenComparing(Patient::getName);

        return patientStream
                .filter(p -> p.getSurname().equals(surname) && p.getName().equals(firstName))
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByAge(int age) {
        Stream<Patient> patientStream = this.patientList.stream();

        Comparator<Patient> compareByAge = Comparator
                .comparing(Patient::getAge);

        return patientStream
                .filter(p -> p.getAge() == age)
                .sorted(compareByAge)
                .collect(Collectors.toList());
    }
}
