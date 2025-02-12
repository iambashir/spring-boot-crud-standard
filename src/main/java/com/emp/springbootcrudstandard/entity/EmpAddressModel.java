package com.emp.springbootcrudstandard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="emp_address")
public class EmpAddressModel extends BaseModel {

  private String location;
  private String homeDistrict;
  private Integer zipCode;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "empAddress")
  private EmployeeModel employeeModel;


}
