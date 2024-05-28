# README
## Payroll Processing System (PPC) Assignment
### Overview
This application is designed to process payroll data for PPC, a Payroll solution provider company. It manages the payroll for various companies by processing employee data from onboarding to exit, including salary, bonus, and reimbursement events. The application accepts input data in plain text or CSV format and generates various reports based on the provided data.
### Requirements
- **Java Version**: Java 1.8 or higher
- **Input Format**: Plain text (.txt) or CSV (.csv) file
### Data Points
Each employee record in the input file should include the following data points:
1. **SequenceNo**: Sequence number of the record
2. **EmpID**: Unique employee ID
3. **EmpFName**: Employee's first name
4. **EmpLName**: Employee's last name
5. **Designation**: Employee's designation
6. **Event**: Type of event (ONBOARD, SALARY, BONUS, EXIT, REIMBURSEMENT)
7. **Value**: Event-specific value
8. **EventDate**: Date of the event (DD-MM-YYYY format)
9. **Notes**: Additional details about the event
### Event Formats
- **ONBOARD**: `SequenceNo, EmpID, EmpFName, EmpLName, Designation, Event, Value, EventDate, Notes`
- **SALARY**: `SequenceNo, EmpID, Event, Value, EventDate, Notes`
- **BONUS**: `SequenceNo, EmpID, Event, Value, EventDate, Notes`
- **EXIT**: `SequenceNo, EmpID, Event, Value, EventDate, Notes`
- **REIMBURSEMENT**: `SequenceNo, EmpID, Event, Value, EventDate, Notes`

### Output
The program should generate the following reports:
1. **Total Number of Employees**: In the organization.
2. **Monthly Reports**:
    - Employees joined with details (EmpID, Designation, Name, Surname)
    - Employees exited with details (Name, Surname)
3. **Monthly Salary Report**: `Month, Total Salary, Total Employees`
4. **Employee-wise Financial Report**: `Employee ID, Name, Surname, Total Amount Paid`
5. **Monthly Amount Released Report**: `Month, Total Amount (Salary + Bonus + REIMBURSEMENT), Total Employees`
6. **Yearly Financial Report**: `Event, EmpID, Event Date, Event Value`
### Example Input
```plaintext
1 emp101 Bill Gates Software Engineer ONBOARD 1-11-2022 10-10-2022 "Bill Gates is going to join DataOrb on 1st November as a SE."
2 emp102 Steve Jobs Architect ONBOARD 1-10-2022 10-10-2022 "Steve Jobs joined DataOrb on 1st October as an Architect."
3 emp103 Martin Fowler Software Engineer ONBOARD 15-10-2022 10-10-2022 "Martin has joined DataOrb as a SE."
4 emp102 SALARY 3000 10-10-2022 "Oct Salary of Steve."
...
```
### How to Run the Application
1. Clone the repository from Git.
2. Open the project in your preferred IDE.
3. Build the project.
4. Run the main application file.
5. Upload input files as prompted.
6. View the generated reports.
