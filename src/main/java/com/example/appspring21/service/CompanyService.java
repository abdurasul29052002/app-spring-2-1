package com.example.appspring21.service;

import com.example.appspring21.entity.Address;
import com.example.appspring21.entity.Company;
import com.example.appspring21.payload.CompanyDto;
import com.example.appspring21.payload.Result;
import com.example.appspring21.repository.AddressRepository;
import com.example.appspring21.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;


    public Result addCompany(CompanyDto companyDto) {
        if (companyDto.getCorpName()==null){
            return new Result("Name is empty",false);
        }
        if (companyDto.getDirectorName()==null){
            return new Result("Director name is empty",false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()){
            return new Result("Address is not found",false);
        }
        Address address = optionalAddress.get();
        companyRepository.save(new Company(null,companyDto.getCorpName(),companyDto.getDirectorName(),address));
        return new Result("Company saved",true);
    }

    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    public Result updateCompany(Integer id,CompanyDto companyDto){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()){
            return new Result("Company not found",false);
        }
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()) {
            return new Result("Address not found",false);
        }
        Address address = optionalAddress.get();
        company.setAddress(address);
        companyRepository.save(company);
        return new Result("Company updated",true);
    }

    public Result deleteCompany(Integer id) {
        try{
            companyRepository.deleteById(id);
            return new Result("Company deleted",true);
        }catch (Exception e){
            return new Result("Error",false);
        }
    }
}
