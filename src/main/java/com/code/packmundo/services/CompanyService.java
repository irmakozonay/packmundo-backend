package com.code.packmundo.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.code.packmundo.models.Company;
import com.code.packmundo.models.DeliveryCompany;
import com.code.packmundo.models.repositories.CompanyRepository;
import com.code.packmundo.models.repositories.DeliveryCompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    
    private final CompanyRepository companyRepository;
    private final DeliveryCompanyRepository deliveryCompanyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, DeliveryCompanyRepository deliveryCompanyRepository) {
        this.companyRepository = companyRepository;
        this.deliveryCompanyRepository = deliveryCompanyRepository;
    }

    public Company saveCompany(Company company) { //todo addressId olarak geliyor ama ya address ile gelmeli ya da once address yaratilmali
        company.setUuid(UUID.randomUUID());
        company.setIntime(LocalDateTime.now());
        return companyRepository.save(company);
    }

    public DeliveryCompany saveDeliveryCompany(DeliveryCompany deliveryCompany) { //todo addressId olarak geliyor ama ya address ile gelmeli ya da once address yaratilmali
        deliveryCompany.setUuid(UUID.randomUUID());
        deliveryCompany.setIntime(LocalDateTime.now());
        return deliveryCompanyRepository.save(deliveryCompany);
    }

    public Company getCompany(int companyId){
        return companyRepository.findById(companyId).get();
    }

    public DeliveryCompany getDeliveryCompany(int deliveryCompanyId){
        return deliveryCompanyRepository.findById(deliveryCompanyId).get();
    }

    public int getCompanyIdByUuid(UUID companyUuid){
        return companyRepository.getIdByUuid(companyUuid);
    }

    public int getDeliveryCompanyIdByUuid(UUID deliveryCompanyUuid){
        return deliveryCompanyRepository.getIdByUuid(deliveryCompanyUuid);
    }

}