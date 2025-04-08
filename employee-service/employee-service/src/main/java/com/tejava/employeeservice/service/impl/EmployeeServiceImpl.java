package com.tejava.employeeservice.service.impl;

import com.tejava.employeeservice.dto.APIResponseDto;
import com.tejava.employeeservice.dto.DepartmentDto;
import com.tejava.employeeservice.dto.EmployeeDto;
import com.tejava.employeeservice.entity.Employee;
import com.tejava.employeeservice.exception.ResourceNotFoundException;
import com.tejava.employeeservice.repository.EmployeeRepository;
import com.tejava.employeeservice.service.APIClient;
import com.tejava.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    //private RestTemplate restTemplate;
    //private WebClient webClient;
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto,Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                                .orElseThrow(()->new ResourceNotFoundException("Employee not found with employeeId: "+employeeId));

//        ResponseEntity<DepartmentDto> responseEntity =
//                restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(),
//                        DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto=webClient.get().uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode())
//                                                                                .retrieve().bodyToMono(DepartmentDto.class).block();

        DepartmentDto departmentDto= apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto= modelMapper.map(employee,EmployeeDto.class);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }
}
