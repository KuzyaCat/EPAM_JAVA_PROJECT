package main.java;

public class Treatment {
    private String[] procedures;
    private String[] medicines;
    private String[] operations;

    public Treatment(String[] procedures, String[] medicines, String[] operations){
        this.medicines = medicines;
        this.operations = operations;
        this.procedures = procedures;
    }

    public String[] getMedicines() {
        return medicines;
    }

    public String[] getOperations() {
        return operations;
    }

    public String[] getProcedures() {
        return procedures;
    }

    public void setMedicines(String[] medicines) {
        this.medicines = medicines;
    }

    public void setOperations(String[] operations) {
        this.operations = operations;
    }

    public void setProcedures(String[] procedures) {
        this.procedures = procedures;
    }
}
