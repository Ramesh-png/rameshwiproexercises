package com.example.employee.service.impl;

import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.model.Department;
import com.example.employee.model.Employee;
import com.example.employee.repository.DepartmentRepository;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee create(Employee e) {
        // Ensure department exists if provided
        if (e.getDepartment() != null && e.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(e.getDepartment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
            e.setDepartment(dept);
        }
        return employeeRepository.save(e);
    }

    @Override
    public Employee update(Long id, Employee e) {
        Employee existing = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        existing.setName(e.getName());
        existing.setEmail(e.getEmail());
        existing.setType(e.getType());
        existing.setAddress(e.getAddress());
        if (e.getDepartment() != null) {
            if (e.getDepartment().getId() != null) {
                Department dept = departmentRepository.findById(e.getDepartment().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
                existing.setDepartment(dept);
            } else {
                existing.setDepartment(e.getDepartment());
            }
        } else {
            existing.setDepartment(null);
        }
        return employeeRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    @Transactional(readOnly = true)
        public List<Employee> searchByName(String name) {
            return employeeRepository.findByNameContainingIgnoreCase(name == null ? "" : name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
