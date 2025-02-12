package com.emp.springbootcrudstandard.repository;

import com.emp.springbootcrudstandard.entity.EmpAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpAddressRepository extends JpaRepository<EmpAddressModel, Long> {

}
