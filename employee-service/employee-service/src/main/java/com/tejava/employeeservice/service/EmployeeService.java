package com.tejava.employeeservice.service;

import com.tejava.employeeservice.dto.APIResponseDto;
import com.tejava.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);
}
