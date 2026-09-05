package com.chuiiin.crud.crud.controller;


import com.chuiiin.crud.crud.dto.EmployeeDto;
import com.chuiiin.crud.crud.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController // = @Controller + @ResponseBody
@RequestMapping("/api/employee")
public class EmployeeController { // To handle Http Requests

    private EmployeeService employeeService;

    // Build Add Employee REST API Create
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee (@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Get employee by Id REST API
    @GetMapping("/Id/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById (@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    //Get all employees REST API
    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
       List<EmployeeDto> employees = employeeService.getAllEmployees();
       return ResponseEntity.ok(employees);
    }

    //Update employees by Id REST API
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDto);  // id, object,text.
    }

    //Delete Api by Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
