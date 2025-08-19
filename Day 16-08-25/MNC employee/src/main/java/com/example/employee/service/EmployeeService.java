package com.example.employee.service;

import com.example.employee.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee create(Employee e);
    Employee update(Long id, Employee e);
    void delete(Long id);
    Employee getById(Long id);
    List<Employee> searchByName(String name);
    List<Employee> getAll();
}
