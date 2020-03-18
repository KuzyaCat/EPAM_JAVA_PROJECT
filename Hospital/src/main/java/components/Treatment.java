package main.java.components;

public class Treatment {
    private String[] procedures;
    private String[] medicines;
    private String[] operations;

    public Treatment(String[] medicines, String[] operations, String[] procedures){
        this.medicines = medicines;
        this.operations = operations;
        this.procedures = procedures;
    }

    public Treatment() {
        this(new String[0], new String[0], new String[0]);
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

    @Override
    public String toString() {
        String res = "";
        res += "{";

        for(int i = 0; i < this.medicines.length; i ++) {
            if(i != 0) {
                res += " " + this.medicines[i];
            }
            else {
                res += this.medicines[i];
            }
        }
        res += "} {";

        for(int i = 0; i < this.operations.length; i ++) {
            if(i != 0) {
                res += " " + this.operations[i];
            }
            else {
                res += this.operations[i];
            }
        }
        res += "} {";

        for(int i = 0; i < this.procedures.length; i ++) {
            if(i != 0) {
                res += " " + this.procedures[i];
            }
            else {
                res += this.procedures[i];
            }
        }
        res += "}";

        return res;
    }

    public Treatment parseString(String str) {
        int firstOpeningBrIndex = 0;
        int firstClosingBrIndex = str.indexOf("}");
        int secondOpeningBrIndex = firstClosingBrIndex + 2;
        int secondClosingBrIndex = str.indexOf("}", firstClosingBrIndex + 1);
        int thirdOpeningBrIndex = secondClosingBrIndex + 2;
        int thirdClosingBrIndex = str.indexOf("}", secondClosingBrIndex + 1);

        String medicinesStr = str.substring(firstOpeningBrIndex + 1, firstClosingBrIndex);
        String[] medicines = medicinesStr.split(" ");

        String operationsStr = str.substring(secondOpeningBrIndex + 1, secondClosingBrIndex);
        String[] operations = operationsStr.split(" ");

        String proceduresStr = str.substring(thirdOpeningBrIndex + 1, thirdClosingBrIndex);
        String[] procedures = proceduresStr.split(" ");

        return new Treatment(medicines, operations, procedures);
    }
}
