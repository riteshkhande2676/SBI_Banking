package com.crystaltech.SBI.Service;

import com.crystaltech.SBI.Dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface
AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long accountNumber);
    AccountDto deposit(Long accountNumber , Double amount);
    AccountDto withdraw(Long accountNumber, Double amount);
    List<AccountDto> getAllAccounts();
    AccountDto deleteAccount(Long accountNumber);
}
