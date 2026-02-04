package com.crystaltech.SBI.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
//@Data is used to generate  things like - setter, getter methods , constructor also
@Data
@AllArgsConstructor
public class AccountDto {
    private Long accountNumber;
    private String accountHolderName;
    private Double balance;
}



