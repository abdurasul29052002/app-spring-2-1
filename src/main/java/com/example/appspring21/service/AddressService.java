package com.example.appspring21.service;

import com.example.appspring21.entity.Address;
import com.example.appspring21.payload.AddressDto;
import com.example.appspring21.payload.Result;
import com.example.appspring21.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Result addAddress(AddressDto addressDto){
        Address address = new Address();
        if (addressDto.getHomeNumber()==null){
            return  new Result("Home number is empty",false);
        }

        if (addressDto.getStreet()==null){
            return new Result("Street is empty",false);
        }
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return new Result("Address saved",true);
    }

    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    public Address getAddress(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public Result updateAddress(AddressDto addressDto,Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return new Result("Address not found",false);
        }

        Address address = optionalAddress.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new Result("Address updated",true);
    }

    public Result deleteAddress(Integer id){
        try {
            addressRepository.deleteById(id);
            return new Result("Address deleted", true);
        } catch (Exception e) {
            return new Result("Error",false);
        }
    }
}
