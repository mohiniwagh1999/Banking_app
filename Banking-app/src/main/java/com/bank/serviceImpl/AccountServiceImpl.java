package com.bank.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bank.Dto.AccountDto;
import com.bank.entity.Account;
import com.bank.mapper.AccountMapper;
import com.bank.repository.AccountRepo;
import com.bank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {


	private AccountRepo accountRepo;
	
	
	public AccountServiceImpl(AccountRepo accountRepo) {
		super();
		// TODO Auto-generated constructor stub
		this.accountRepo=accountRepo;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		
		Account account=accountRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account=accountRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));
		double  total =account.getBalance()+ amount;
		account.setBalance(total);
		Account savedAccount=accountRepo.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account=accountRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));
		
		if(account.getBalance()<amount)
		{
			throw new RuntimeException("insufficient");
		}
			double total=account.getBalance()-amount;
			account.setBalance(total);
			Account savedAccount=accountRepo.save(account);
		
		
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccount() {
		// TODO Auto-generated method stub
		List<Account> accounts=accountRepo.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
		     .collect(Collectors.toList());
		
	}


	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account=accountRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));
		accountRepo.deleteById(id);
		
	}

}
