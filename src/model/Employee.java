package src.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {
    private int sequenceNo;
    private String empID;
    private String empFName;
    private String empLName;
    private String designation;
    private String event;
    public enum ValueType {
        ONBOARD(LocalDate.class),
        SALARY(Integer.class),
        BONUS(Integer.class),
        EXIT(LocalDate.class),
        REIMBURSEMENT(Integer.class);   

        private final Class<?> type;

        ValueType(Class<?> type) {
            this.type = type;
        }

        public Class<?> getType() {
            return type;
        }
    }
    private Object value;
    private LocalDate eventDate;
    private String notes;

    // Constructor
    public Employee(int sequenceNo, String empID, String empFName, String empLName, String designation, String event, Object value, LocalDate eventDate, String notes) {
        this.sequenceNo = sequenceNo;
        this.empID = empID;
        this.empFName = empFName;
        this.empLName = empLName;
        this.designation = designation;
        this.event = event;
        this.value = value;
        this.eventDate = eventDate;
        this.notes = notes;
    }

    // Getters and Setters
    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpFName() {
        return empFName;
    }

    public void setEmpFName(String empFName) {
        this.empFName = empFName;
    }

    public String getEmpLName() {
        return empLName;
    }

    public void setEmpLName(String empLName) {
        this.empLName = empLName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Parsing method
    public static Employee parseEmployeeData(String line) {
        String[] data = line.split(",");
        int sequenceNo = Integer.parseInt(data[0].trim());
        String empID = data[1].trim();
        String empFName = data[2].trim();
        String empLName = data[3].trim();
        String designation = data[4].trim();
        String event = data[5].trim();

        Object value = null;
        if (event.equals("ONBOARD") || event.equals("EXIT")) {
            value = LocalDate.parse(data[6].trim(), DateTimeFormatter.ofPattern("d-MM-yyyy"));
        } else {
            value = Integer.parseInt(data[6].trim());
        }

        LocalDate eventDate = LocalDate.parse(data[7].trim(), DateTimeFormatter.ofPattern("d-MM-yyyy"));
        String notes = data[8].trim();

        return new Employee(sequenceNo, empID, empFName, empLName, designation, event, value, eventDate, notes);
    }
}
