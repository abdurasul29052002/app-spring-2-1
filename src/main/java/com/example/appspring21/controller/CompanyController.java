package com.example.appspring21.controller;

import com.example.appspring21.entity.Company;
import com.example.appspring21.payload.CompanyDto;
import com.example.appspring21.payload.Result;
import com.example.appspring21.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping
    public Result addCompany(@RequestBody CompanyDto companyDto) {
        return companyService.addCompany(companyDto);
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Integer id) {
        return companyService.getCompany(id);
    }

    @PutMapping("/{id}")
    public Result updateCompany(@PathVariable Integer id,@RequestBody CompanyDto companyDto){
        return companyService.updateCompany(id,companyDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteCompany(@PathVariable Integer id) {
        return companyService.deleteCompany(id);
    }

}
