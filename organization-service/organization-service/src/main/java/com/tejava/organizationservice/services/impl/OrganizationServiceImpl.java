package com.tejava.organizationservice.services.impl;

import com.tejava.organizationservice.dto.OrganizationDto;
import com.tejava.organizationservice.entity.Organization;
import com.tejava.organizationservice.repository.OrganizationRepository;
import com.tejava.organizationservice.services.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private ModelMapper modelMapper;
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = modelMapper.map(organizationDto,Organization.class);
        Organization savedOrganization = organizationRepository.save(organization);
        return modelMapper.map(savedOrganization,OrganizationDto.class);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {

        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        if(organization==null){
            throw new RuntimeException("Organization not found with organizationCode: "+organizationCode);
        }

        return modelMapper.map(organization,OrganizationDto.class);
    }
}
