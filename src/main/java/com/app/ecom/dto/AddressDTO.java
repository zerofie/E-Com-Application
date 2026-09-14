package com.app.ecom.dto;

import lombok.Data;

@Data
public class AddressDTO {
//    private long id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
