package com.emp.springbootcrudstandard.service.impl;

import com.emp.springbootcrudstandard.dto.EmployeeDto;
import com.emp.springbootcrudstandard.entity.EmployeeModel;
import com.emp.springbootcrudstandard.repository.EmployeeRepository;
import com.emp.springbootcrudstandard.service.EmployeeService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
//import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    /*------- Save employee one by one ------*/
    @Override
    public void saveEmployee(EmployeeDto employeeDto) {
        EmployeeModel employeeModel = modelMapper.map(employeeDto, EmployeeModel.class);
        employeeRepository.save(employeeModel);
    }

    /*------- Save All employee ------*/
    @Override
    public List<EmployeeDto> saveAllEmployee(List<EmployeeModel> employeeModels) {
        List<EmployeeModel> saveEmp = employeeRepository.saveAll(employeeModels);
        return saveEmp.stream().map(empl -> modelMapper.map(empl, EmployeeDto.class)).collect(Collectors.toList());
    }

    /*------- Get All employee ------*/
    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeModel> empmodel = employeeRepository.findAll();
        return empmodel.stream().map(emp -> modelMapper.map(emp, EmployeeDto.class))
                .collect(Collectors.toList());

    }

    /*------- Edit employee (Get by id) ------*/
    @Override
    public EmployeeDto getEmployeeByID(Long id) {
        EmployeeModel employeeModel = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return modelMapper.map(employeeModel, EmployeeDto.class);
    }

    /*------- Update employee ------*/
    @Override
    public Void updateEmployee(EmployeeDto employeeDto) {
        EmployeeModel employeeModel = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        modelMapper.map(employeeDto, employeeModel);
        employeeRepository.save(employeeModel);
        return null;
    }

    /*------- Delete employee ------*/
    @Override
    public void deleteEmployee(Long id) {
        EmployeeModel employeeModel = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        employeeRepository.delete(employeeModel);
    }

    /*------- Delete All employee ------*/
    @Override
    public void deleteAllEmployee() {
        List<EmployeeModel> allEmployees = employeeRepository.findAll();
        employeeRepository.deleteAll(allEmployees);
    }

}
