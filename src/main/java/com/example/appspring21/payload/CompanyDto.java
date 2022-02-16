package com.example.appspring21.payload;

import com.example.appspring21.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private String corpName;

    private String directorName;

    private Integer addressId;
}
