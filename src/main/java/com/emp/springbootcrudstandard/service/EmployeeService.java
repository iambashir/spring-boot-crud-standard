package com.emp.springbootcrudstandard.service;

import com.emp.springbootcrudstandard.entity.EmployeeModel;
import com.emp.springbootcrudstandard.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {

    /*------- Save employee one by one ------*/
    void saveEmployee(EmployeeDto employeeDto);

    /*------- Save All employee ------*/
    List<EmployeeDto> saveAllEmployee(List<EmployeeModel> employeeModels);

    /*------- Get All employee ------*/
    List<EmployeeDto> getAllEmployee();

    /*------- Edit employee ------*/
    EmployeeDto getEmployeeByID(Long id);

    /*------- Update employee ------*/
    Void updateEmployee(EmployeeDto employeeDto);

    /*------- Delete employee ------*/
    void deleteEmployee(Long id);

    /*------- Delete All employee ------*/
    void deleteAllEmployee();

}
