package com.emp.springbootcrudstandard.dto;


import  com.emp.springbootcrudstandard.dto.BaseDto;
//import com.emp.springbootcrudstandard.dto.BaseDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto extends BaseDto{

  private String firstName;
  private String lastName;
  private String email;
  private List<EmpAddressDto> empAddress;

}
