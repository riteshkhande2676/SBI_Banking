package com.crystaltech.SBI.Controller;

import com.crystaltech.SBI.Dto.AccountDto;
import com.crystaltech.SBI.Service.AccountService;
import com.crystaltech.SBI.Service.businessLogic.AccountServiceImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long accountNumber){
           AccountDto accountDto =accountService.getAccountById(accountNumber);
           return ResponseEntity.ok(accountDto);
    }

    // creating a REST APIs
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
       return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }


    @PutMapping("/{accountNumber}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long accountNumber,@RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(accountNumber,amount);
        return ResponseEntity.ok(accountDto);


    }

    @PutMapping("/{accountNumber}/withdraw")
    public ResponseEntity<AccountDto> withdrawal(@PathVariable Long accountNumber,@RequestBody Map<String, Double> request ) {
        Double amount =request.get("amount");
        AccountDto accountDto = accountService.withdraw(accountNumber, amount);
        return ResponseEntity.ok(accountDto);

    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts  =accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }


    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long accountNumber){
        AccountDto accountDto = accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok(accountDto);
    }

}
