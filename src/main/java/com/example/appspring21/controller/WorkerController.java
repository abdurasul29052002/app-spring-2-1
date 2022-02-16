package com.example.appspring21.controller;

import com.example.appspring21.entity.Worker;
import com.example.appspring21.payload.Result;
import com.example.appspring21.payload.WorkerDto;
import com.example.appspring21.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @PostMapping
    public Result addWorker(@RequestBody WorkerDto workerDto) {
        return workerService.addWorker(workerDto);
    }

    @GetMapping
    public List<Worker> getWorkers(){
        return workerService.getWorkers();
    }

    @GetMapping("/{id}")
    public Worker getWorker(@PathVariable Integer id){
        return workerService.getWorker(id);
    }

    @PutMapping("/{id}")
    public Result updateWorker(@PathVariable Integer id,@RequestBody WorkerDto workerDto){
        return workerService.updateWorker(id,workerDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteWorker(@PathVariable Integer id){
        return workerService.deleteWorker(id);
    }
}
