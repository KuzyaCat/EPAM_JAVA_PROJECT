package main.java.components;

public class Treatment {
    private String procedures;
    private String medicines;
    private String operations;
    private String diagnoses;

    public Treatment(String medicines, String operations, String procedures, String diagnoses){
        this.medicines = medicines;
        this.operations = operations;
        this.procedures = procedures;
        this.diagnoses = diagnoses
    }

    public Treatment() {
        this("", "", "", "");
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

    public String getDiagnoses() {
        return diagnoses;
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

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    @Override
    public String toString() {
        return this.medicines + " " + this.operations + " " + this.procedures + " " + this.diagnoses;
    }

    public Treatment parseString(String str) {
        String[] fields = str.split(" ");
        return new Treatment(fields[0], fields[1], fields[2], fields[3]);
    }
}
