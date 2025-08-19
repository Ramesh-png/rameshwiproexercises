package com.example.employee.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Employee type is required")
    private EmployeeType type;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {}

    public Employee(String name, String email, EmployeeType type, Address address, Department department) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.address = address;
        this.department = department;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public EmployeeType getType() { return type; }
    public void setType(EmployeeType type) { this.type = type; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
