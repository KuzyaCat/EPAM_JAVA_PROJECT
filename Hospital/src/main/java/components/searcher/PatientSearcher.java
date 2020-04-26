package main.java.components.searcher;

import main.java.users.Patient;
import main.java.users.stuff.Doctor;

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

        return patientStream
                .filter(Patient::isRecovered)
                .collect(Collectors.toList());
    }

    public List<Patient> findIllPatients() {
        Stream<Patient> patientStream = this.patientList.stream();

        return patientStream
                .filter(p -> !p.isRecovered())
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByProcedure(String procedure) {
        Stream<Patient> patientStream = this.patientList.stream();

        return patientStream
                .filter(p -> {
                    AtomicBoolean contains = new AtomicBoolean(false);
                    p.getTreatments().forEach(t -> {
                        if (t.getProcedures().contains(procedure)) {
                            contains.set(true);
                        } else {
                            contains.set(false);
                        }
                    });
                    return contains.get();
                })
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByDiagnose(String diagnose) {
        Stream<Patient> patientStream = this.patientList.stream();

        return patientStream
                .filter(p -> {
                    AtomicBoolean contains = new AtomicBoolean(false);
                    p.getTreatments().forEach(t -> {
                        if (t.getDiagnoses().contains(diagnose)) {
                            contains.set(true);
                        } else {
                            contains.set(false);
                        }
                    });
                    return contains.get();
                })
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByMedicine(String medicine) {
        Stream<Patient> patientStream = this.patientList.stream();

        return patientStream
                .filter(p -> {
                    AtomicBoolean contains = new AtomicBoolean(false);
                    p.getTreatments().forEach(t -> {
                        if (t.getMedicines().contains(medicine)) {
                            contains.set(true);
                        }
                    });
                    return contains.get();
                })
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByOperation(String operation) {
        Stream<Patient> patientStream = this.patientList.stream();

        return patientStream
                .filter(p -> {
                    AtomicBoolean contains = new AtomicBoolean(false);
                    p.getTreatments().forEach(t -> {
                        if (t.getOperations().contains(operation)) {
                            contains.set(true);
                        } else {
                            contains.set(false);
                        }
                    });
                    return contains.get();
                })
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByDoctor(Doctor doctor) {
        Stream<Patient> patientStream = this.patientList.stream();

        return patientStream
                .filter(p -> {
                    AtomicBoolean contains = new AtomicBoolean(false);
                    p.getAppointments().forEach(a -> {
                        if (a.getDoctor().equals(doctor)) {
                            contains.set(true);
                        }
                    });
                    return contains.get();
                })
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByFirstName(String firstName) {
        Stream<Patient> patientStream = this.patientList.stream();

        return patientStream
                .filter(p -> p.getName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsBySurname(String surname) {
        Stream<Patient> patientStream = this.patientList.stream();

        return patientStream
                .filter(p -> p.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByFullName(String firstName, String surname) {
        Stream<Patient> patientStream = this.patientList.stream();

        return patientStream
                .filter(p -> p.getSurname().equals(surname) && p.getName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByAge(int age) {
        Stream<Patient> patientStream = this.patientList.stream();
        
        return patientStream
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }
}
