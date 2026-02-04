package com.crystaltech.SBI.Mapper;

import com.crystaltech.SBI.Dto.AccountDto;
import com.crystaltech.SBI.Entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto) {

        Account account;
        account = new Account(
                accountDto.getAccountNumber(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );

        return account;
    }

    public static AccountDto mapToAccountDto(Account account) {

        AccountDto accountDto;
        accountDto= new AccountDto(
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
