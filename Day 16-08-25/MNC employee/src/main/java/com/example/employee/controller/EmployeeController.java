package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeType;
import com.example.employee.repository.DepartmentRepository;
import com.example.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepository;

    public EmployeeController(EmployeeService employeeService, DepartmentRepository departmentRepository) {
        this.employeeService = employeeService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String name, Model model) {
        model.addAttribute("employees", name == null ? employeeService.getAll() : employeeService.searchByName(name));
        model.addAttribute("name", name);
        return "employees/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("types", EmployeeType.values());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employees/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("types", EmployeeType.values());
            model.addAttribute("departments", departmentRepository.findAll());
            return "employees/form";
        }
        employeeService.create(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        model.addAttribute("types", EmployeeType.values());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employees/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("types", EmployeeType.values());
            model.addAttribute("departments", departmentRepository.findAll());
            return "employees/form";
        }
        employeeService.update(id, employee);
        return "redirect:/employees";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
