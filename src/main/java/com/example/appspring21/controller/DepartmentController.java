package com.example.appspring21.controller;

import com.example.appspring21.entity.Department;
import com.example.appspring21.payload.DepartmentDto;
import com.example.appspring21.payload.Result;
import com.example.appspring21.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public Result addDepartment(@RequestBody DepartmentDto departmentDto){
        return departmentService.addDepartment(departmentDto);
    }

    @GetMapping
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Integer id){
        return departmentService.getDepartment(id);
    }

    @PutMapping("/{id}")
    public Result updateDepartment(@PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
        return departmentService.updateDepartment(id,departmentDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteDepartment(@PathVariable Integer id){
        return departmentService.deleteDepartment(id);
    }
}
