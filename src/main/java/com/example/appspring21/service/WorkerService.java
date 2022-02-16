package com.example.appspring21.service;

import com.example.appspring21.entity.Address;
import com.example.appspring21.entity.Department;
import com.example.appspring21.entity.Worker;
import com.example.appspring21.payload.Result;
import com.example.appspring21.payload.WorkerDto;
import com.example.appspring21.repository.AddressRepository;
import com.example.appspring21.repository.DepartmentRepository;
import com.example.appspring21.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressRepository addressRepository;

    public Result addWorker(WorkerDto workerDto) {
        if (workerDto.getName() == null) {
            return new Result("Name is empty",false);
        }
        if (workerDto.getPhoneNumber()==null){
            return new Result("Phone number is empty", false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()){
            return new Result("Department not found",false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent()){
            return new Result("Address not found",false);
        }
        Optional<Worker> optionalWorker = workerRepository.findByPhoneNumber(workerDto.getPhoneNumber());
        Worker worker;
        if (optionalWorker.isPresent()){
            worker = optionalWorker.get();
            worker.setName(workerDto.getName());
            worker.setPhoneNumber(workerDto.getPhoneNumber());
            worker.setAddress(optionalAddress.get());
            worker.getDepartments().add(optionalDepartment.get());
        }else {
            worker = new Worker();
            worker.setName(workerDto.getName());
            worker.setPhoneNumber(workerDto.getPhoneNumber());
            worker.setAddress(optionalAddress.get());
            List<Department> list = new ArrayList<>();
            list.add(optionalDepartment.get());
            worker.setDepartments(list);
        }
        workerRepository.save(worker);
        return new Result("Worker saved",true);
    }

    public List<Worker> getWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }

    public Result updateWorker(Integer id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new Result("Worker not found",false);
        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Address not found",false);
        worker.setAddress(optionalAddress.get());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new Result("Department not found",false);
        worker.getDepartments().add(optionalDepartment.get());
        workerRepository.save(worker);
        return new Result("Worker updated",true);
    }

    public Result deleteWorker(Integer id) {
        try{
            workerRepository.deleteById(id);
            return new Result("Worker deleted",true);
        }catch (Exception e){
            return new Result("Error",false);
        }
    }
}
