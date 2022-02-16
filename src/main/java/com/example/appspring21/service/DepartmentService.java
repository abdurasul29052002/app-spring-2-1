package com.example.appspring21.service;

import com.example.appspring21.entity.Company;
import com.example.appspring21.entity.Department;
import com.example.appspring21.payload.DepartmentDto;
import com.example.appspring21.payload.Result;
import com.example.appspring21.repository.CompanyRepository;
import com.example.appspring21.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public Result addDepartment(DepartmentDto departmentDto) {
        if (departmentDto.getName() == null) {
            return new Result("Name is empty",false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new Result("Company not found",false);
        }
        Company company = optionalCompany.get();
        Optional<Department> optionalDepartment = departmentRepository.findByName(departmentDto.getName());
        if (optionalDepartment.isPresent()){
            Department department = optionalDepartment.get();
            department.setName(departmentDto.getName());
            List<Company> list = department.getCompanies();
            list.add(company);
            department.setCompanies(list);
        }else {
            Department department = new Department();
            department.setName(departmentDto.getName());
            List<Company> list = new ArrayList<>();
            list.add(company);
            department.setCompanies(list);
        }
        return new Result("Department saved",true);
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    public Result updateDepartment(Integer id, DepartmentDto departmentDto) {
        if (departmentDto.getName()==null){
            return new Result("Name is empty",false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) {
            return new Result("Department not found",false);
        }
        Department department = optionalDepartment.get();
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()) {
            return new Result("COmpany not found",false);
        }
        Company company = optionalCompany.get();
        department.setName(departmentDto.getName());
        department.getCompanies().add(company);
        departmentRepository.save(department);
        return new Result("Department updated",true);
    }

    public Result deleteDepartment(Integer id) {
        try {
            departmentRepository.deleteById(id);
        }catch (Exception e){
            return new Result("Error",false);
        }
        return new Result("Department deleted",true);
    }
}
