package com.emp.springbootcrudstandard.controller;

import com.emp.springbootcrudstandard.dto.EmployeeDto;
import com.emp.springbootcrudstandard.entity.EmployeeModel;
import com.emp.springbootcrudstandard.service.EmployeeService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeMvcController {

    private final EmployeeService employeeService;

    public EmployeeMvcController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*----- Get All Employee ------*/
    @GetMapping("/list")
    public String listEmployees(Model model) {
        try {
            model.addAttribute("employees", employeeService.getAllEmployee());
        } catch (Exception e) {
            // Handle the exception, e.g., log it or show an error message
            e.printStackTrace();
        }
        return "mvc-rest-crud/employee-list";
    }

    /*-------- Get Add Employee page -----------*/
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employeeDto", new EmployeeDto());
        return "mvc-rest-crud/create-employee";
    }

    /*----- Save Employee One by One ------*/
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employeeDto") EmployeeDto employeeDto) {
        try {
            employeeService.saveEmployee(employeeDto);
        } catch (Exception e) {
            // Handle the exception, e.g., log it or show an error message
            e.printStackTrace();
        }
        return "redirect:/employees/list";
    }

    /*------- Save All employee ------*/
    @PostMapping("/saveAllEmployee")
    public String saveAllEmployees(@RequestBody List<EmployeeModel> employeeModel, Model model) {
        try {
            List<EmployeeDto> savedEmployees = employeeService.saveAllEmployee(employeeModel);
            model.addAttribute("savedEmployees", savedEmployees);
        } catch (Exception e) {
            // Handle the exception, e.g., log it or show an error message
            e.printStackTrace();
        }
        return "employee-list";
    }

    /*-------- Edit employee -----------*/
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            EmployeeDto employeeDto = employeeService.getEmployeeByID(id);
            model.addAttribute("employeeDto", employeeDto);
        } catch (Exception e) {
            // Handle the exception, e.g., log it or show an error message
            e.printStackTrace();
        }
        return "mvc-rest-crud/edit-employee";
    }

    /*-------- Update employee -----------*/
    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employeeDto") EmployeeDto employeeDto) {
        try {
            employeeDto.setId(id);
            employeeService.updateEmployee(employeeDto);
        } catch (Exception e) {
            // Handle the exception, e.g., log it or show an error message
            e.printStackTrace();
        }
        return "redirect:/employees/list";
    }

    /*--------- Delete employee ---------*/
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
        } catch (Exception e) {
            // Handle the exception, e.g., log it or show an error message
            e.printStackTrace();
        }
        return "redirect:/employees/list";
    }
}
