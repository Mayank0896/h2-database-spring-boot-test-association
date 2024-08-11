package com.application.memdb.exception.custom;

// Never use Exception class instead use RuntimeException as it is unchecked exception
public class EmployeeNotFoundException extends Exception{
    private Long employeeId;
    public EmployeeNotFoundException(String message, Long id) {
        super(message);
        this.employeeId = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
