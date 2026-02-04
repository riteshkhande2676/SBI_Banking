package com.crystaltech.SBI.Service.businessLogic;

import com.crystaltech.SBI.Dto.AccountDto;
import com.crystaltech.SBI.Entity.Account;
import com.crystaltech.SBI.Mapper.AccountMapper;
import com.crystaltech.SBI.Repository.AccountRepository;
import com.crystaltech.SBI.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImp implements AccountService {

    AccountRepository accountRepository;
    @Autowired
    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account=AccountMapper.mapToAccount(accountDto);
        Account saveaccount =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveaccount);
    }

    @Override
    public AccountDto getAccountById(Long accountNumber) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new RuntimeException("account not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long accountNumber, Double amount) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new RuntimeException("account not found"));
         double total_balance  =account.getBalance()+amount;
         account.setBalance(total_balance);
         Account saved_account=accountRepository.save(account);
         return AccountMapper.mapToAccountDto(saved_account);
    }

    @Override
    public AccountDto withdraw(Long accountNumber, Double amount) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new RuntimeException("account not found"));

        if(account.getBalance()-amount<0){
            throw new RuntimeException("account balance is negative");
        }
        else {
            double total_balance = account.getBalance() - amount;
            account.setBalance(total_balance);
            Account saved_account = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(saved_account);
        }
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts;
        accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }


    @Override
    public AccountDto deleteAccount(Long accountNumber) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new RuntimeException("account not found"));

         accountRepository.deleteById(accountNumber);

        return null;
    }


}
