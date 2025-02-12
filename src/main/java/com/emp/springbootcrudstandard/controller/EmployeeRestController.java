package com.emp.springbootcrudstandard.controller;

import com.emp.springbootcrudstandard.dto.EmployeeDto;
import com.emp.springbootcrudstandard.entity.EmployeeModel;
import com.emp.springbootcrudstandard.service.EmployeeService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /*----- Save Employee One by One ------*/
    @PostMapping("/save-emp")
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.saveEmployee(employeeDto);
            return ResponseEntity.ok("Employee saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while saving the employee: " + e.getMessage());
        }
    }

    /*----- Save All Employee ------*/
    @PostMapping("/save-all-emp")
    public ResponseEntity<List<EmployeeDto>> saveAllEmployees(@RequestBody List<EmployeeModel> employeeDtos) {
        try {
            List<EmployeeDto> savedEmployees = employeeService.saveAllEmployee(employeeDtos);
            return new ResponseEntity<>(savedEmployees, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*----- Get All Employee ------*/
    @GetMapping("/get-all-emp")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        try {
            List<EmployeeDto> employees = employeeService.getAllEmployee();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*-------- Edit employee -----------*/
    @GetMapping("edit-emp/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeDto employeeDto = employeeService.getEmployeeByID(id);
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*-------- Update employee -----------*/
    @PutMapping("update-emp/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        try {
            employeeDto.setId(id); // Ensure the correct ID is set in the DTO
            employeeService.updateEmployee(employeeDto);
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the employee", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*--------- Delete employee ---------*/
    @DeleteMapping("delete-emp/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id );
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the employee", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*--------- Delete all employee ----------*/
    @DeleteMapping("delete-all-emp")
    public ResponseEntity<String> deleteAllEmployees() {
        try {
            employeeService.deleteAllEmployee();
            return ResponseEntity.ok("All employees have been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting employees: " + e.getMessage());
        }
    }



}
