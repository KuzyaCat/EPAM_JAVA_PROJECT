package main.java.components;

public class Treatment {
    private String procedures;
    private String medicines;
    private String operations;

    public Treatment(String medicines, String operations, String procedures){
        this.medicines = medicines;
        this.operations = operations;
        this.procedures = procedures;
    }

    public Treatment() {
        this("", "", "");
    }

    public String getMedicines() {
        return medicines;
    }

    public String getOperations() {
        return operations;
    }

    public String getProcedures() {
        return procedures;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public void setProcedures(String procedures) {
        this.procedures = procedures;
    }

    @Override
    public String toString() {
        return this.medicines + " " + this.operations + " " + this.procedures;
    }

    public Treatment parseString(String str) {
        String[] fields = str.split(" ");
        return new Treatment(fields[0], fields[1], fields[2]);
    }
}
