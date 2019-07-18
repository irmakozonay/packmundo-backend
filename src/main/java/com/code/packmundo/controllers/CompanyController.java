package com.code.packmundo.controllers;

import com.code.packmundo.models.Company;
import com.code.packmundo.models.DeliveryCompany;
import com.code.packmundo.services.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(value = "save")
    public Company saveCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @PostMapping(value = "delivery/save")
    public DeliveryCompany saveDeliveryCompany(@RequestBody DeliveryCompany deliveryCompany) {
        return companyService.saveDeliveryCompany(deliveryCompany);
    }

}