package com.bank.service;

import java.util.List;

import com.bank.Dto.AccountDto;

public interface AccountService  {
	
  AccountDto createAccount(AccountDto accountDto);
  
  AccountDto getAccountById(Long id);
  AccountDto deposit(Long id,double  amount);
  
  AccountDto withdraw(Long id ,double amount);
  List<AccountDto> getAllAccount();
  void deleteAccount(Long id);

}
