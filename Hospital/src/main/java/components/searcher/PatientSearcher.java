package main.java.components.searcher;

import main.java.users.Patient;
import main.java.users.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PatientSearcher {
    private List<Patient> patientList;
    private Stream<Patient> patientStream;

    public PatientSearcher(List<Patient> patientList) {
        this.patientList = patientList;
        this.patientStream = this.patientList.stream();
    }

    public List<User> findUsersByFirstName(String firstName) {
        return patientStream
                .filter(p -> p.getName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<User> findUsersBySurname(String surname) {
        return patientStream
                .filter(p -> p.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    public List<User> findUsersByFullName(String firstName, String surname) {
        return patientStream
                .filter(p -> p.getSurname().equals(surname) && p.getName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<User> findUsersByAge(int age) {
        return patientStream
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }

    public List<Patient> findRecoveredUsers() {
        return patientStream
                .filter(Patient::isRecovered)
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByProcedure(String procedure) {
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
        return patientStream
                .filter(p -> {
                    AtomicBoolean contains = new AtomicBoolean(false);
                    p.getTreatments().forEach(t -> {
                        if (t.getMedicines().contains(medicine)) {
                            contains.set(true);
                        } else {
                            contains.set(false);
                        }
                    });
                    return contains.get();
                })
                .collect(Collectors.toList());
    }

    public List<Patient> findPatientsByOperation(String operation) {
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
}
