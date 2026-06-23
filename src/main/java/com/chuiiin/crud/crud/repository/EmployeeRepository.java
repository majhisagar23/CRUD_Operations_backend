package com.chuiiin.crud.crud.repository;

import com.chuiiin.crud.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
