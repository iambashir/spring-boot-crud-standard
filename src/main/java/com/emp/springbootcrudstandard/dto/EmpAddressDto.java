package com.emp.springbootcrudstandard.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpAddressDto  {

  private Long id;
  private String location;
  private String homeDistrict;
  private Integer zipCode;

}
