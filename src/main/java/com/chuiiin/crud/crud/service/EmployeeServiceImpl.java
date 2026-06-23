package com.chuiiin.crud.crud.service;

import com.chuiiin.crud.crud.dto.EmployeeDto;
import com.chuiiin.crud.crud.entity.Employee;
import com.chuiiin.crud.crud.exception.ResourceNotFoundException;
import com.chuiiin.crud.crud.mapper.EmployeeMapper;
import com.chuiiin.crud.crud.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.hibernate.query.SyntaxException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto); //convert Dto to Employee
        Employee saveEmployee = employeeRepository.save(employee); // save in Jpa and store in saveEmployee
        return EmployeeMapper.mapToEmployeeDto(saveEmployee); // return the employee saved and map it back to employee Dto
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with the given id : " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();   //store list of employees in employees
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee doesn't exist with the given id : " + employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee doesn't exist with the given id : "+ employeeId));

        employeeRepository.deleteById(employeeId);

    }

}