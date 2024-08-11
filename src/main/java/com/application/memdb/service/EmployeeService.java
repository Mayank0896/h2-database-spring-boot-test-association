package com.application.memdb.service;

import com.application.memdb.entity.Employee;
import com.application.memdb.exception.custom.EmployeeNotFoundException;
import com.application.memdb.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    public Employee createEmployee(Employee employee) {
        logger.info("createEmployee() called");
        Employee savedEmp = this.employeeRepository.save(employee);
        return savedEmp;
    }

    public Employee getEmployeeById(Long empId) throws EmployeeNotFoundException{
        Optional<Employee> employeeOptional = this.employeeRepository.findById(empId);
        if(employeeOptional.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not found for Id:" + empId, empId);
        }

        return employeeOptional.get();
    }
}
