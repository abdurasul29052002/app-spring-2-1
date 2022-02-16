package com.example.appspring21.controller;

import com.example.appspring21.entity.Address;
import com.example.appspring21.payload.AddressDto;
import com.example.appspring21.payload.Result;
import com.example.appspring21.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping
    public Result addAddress(@RequestBody AddressDto addressDto){
        return addressService.addAddress(addressDto);
    }

    @GetMapping
    public List<Address> getAddresses(){
        return addressService.getAddresses();
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Integer id){
        return addressService.getAddress(id);
    }

    @PutMapping("/{id}")
    public Result updateAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto){
        return addressService.updateAddress(addressDto,id);
    }
    @DeleteMapping("/{id}")
    public Result deleteAddress(@PathVariable Integer id){
        return addressService.deleteAddress(id);
    }

}
