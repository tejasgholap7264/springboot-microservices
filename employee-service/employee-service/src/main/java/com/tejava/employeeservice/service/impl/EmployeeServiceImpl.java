package com.tejava.employeeservice.service.impl;

import com.tejava.employeeservice.dto.APIResponseDto;
import com.tejava.employeeservice.dto.DepartmentDto;
import com.tejava.employeeservice.dto.EmployeeDto;
import com.tejava.employeeservice.dto.OrganizationDto;
import com.tejava.employeeservice.entity.Employee;
import com.tejava.employeeservice.exception.ResourceNotFoundException;
import com.tejava.employeeservice.repository.EmployeeRepository;
import com.tejava.employeeservice.service.DepartmentClient;
import com.tejava.employeeservice.service.EmployeeService;
import com.tejava.employeeservice.service.OrganizationClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    //private RestTemplate restTemplate;
    //private WebClient webClient;
    private DepartmentClient departmentClient;

    private OrganizationClient organizationClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto,Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
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

        DepartmentDto departmentDto= departmentClient.getDepartment(employee.getDepartmentCode());

        OrganizationDto organizationDto= organizationClient.getOrganizationByCode(employee.getOrganizationCode());

        EmployeeDto employeeDto= modelMapper.map(employee,EmployeeDto.class);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);
        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId,Exception exception){

        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research & Development Department");

        OrganizationDto organizationDto=new OrganizationDto();
        organizationDto.setOrganizationCode("TCS001");
        organizationDto.setOrganizationDescription("IT services and consultancy");
        organizationDto.setOrganizationName("TCS");
        organizationDto.setCreatedDate(LocalDateTime.now());

        EmployeeDto employeeDto= modelMapper.map(employee,EmployeeDto.class);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);
        return apiResponseDto;
    }
}
