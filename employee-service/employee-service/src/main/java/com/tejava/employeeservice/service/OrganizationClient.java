package com.tejava.employeeservice.service;

import com.tejava.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationClient {

    @GetMapping("api/organization/{organization-code}")
    OrganizationDto getOrganizationByCode(@PathVariable("organization-code") String organizationCode);
}
