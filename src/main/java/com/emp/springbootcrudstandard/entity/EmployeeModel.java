package com.emp.springbootcrudstandard.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeModel extends BaseModel {

  private String firstName;
  private String lastName;
  private String email;
  private String userid;
  private String password;
  @OneToMany(cascade = CascadeType.ALL)
  private List<EmpAddressModel> empAddress;

}
