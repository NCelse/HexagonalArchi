package sghexaddd.domain.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import sghexaddd.domain.account.Account;
import sghexaddd.domain.event.AccountDepositedEvent;
import sghexaddd.domain.event.AccountEvent;
import sghexaddd.domain.event.AccountWithdrawedEvent;
import sghexaddd.domain.eventstore.IEventRepository;
import sghexaddd.domain.exception.AccountNotFoundException;
import sghexaddd.domain.repository.IAccountRepository;
import sghexaddd.domain.service.IAccountService;

public class AccountService implements IAccountService{
	
	IAccountRepository accountrepository;
	
	IEventRepository eventStore;
	

	public AccountService(IAccountRepository accountrepository, IEventRepository eventStore) {
		this.accountrepository = accountrepository;
		this.eventStore = eventStore;
	}

	@Override
	public boolean depositMoney(Long accountId, BigDecimal depositAmount) throws AccountNotFoundException {
		Account account = accountrepository.findAccountById(accountId).orElseThrow(() -> new AccountNotFoundException("Account with provided Id not found" + accountId.toString()));
		Account newAccountState = new Account(account.getId(),account.getCurrentBalance().add(depositAmount));	
		AccountDepositedEvent depositEvent = new AccountDepositedEvent(accountId, account.getCurrentBalance(),
				depositAmount);
		accountrepository.save(newAccountState);
		eventStore.store(depositEvent);
		return true;
	}

	@Override
	public boolean withdrawMoney(Long accountId, BigDecimal withdrawAmount) throws AccountNotFoundException {
		Account account = accountrepository.findAccountById(accountId).orElseThrow(() -> new AccountNotFoundException("Account with provided Id not found" + accountId.toString()));
		if (!isWithdrawalAvailable(account, withdrawAmount)) return false;
		
		Account newAccountState = new Account(account.getId(),account.getCurrentBalance().add(withdrawAmount.negate()));	
		AccountWithdrawedEvent withdrawEvent = new AccountWithdrawedEvent(accountId, account.getCurrentBalance(), withdrawAmount);
		accountrepository.save(newAccountState);
		eventStore.store(withdrawEvent);
		return true;
	}

	@Override
	public List<AccountEvent> computeAccountHistory(Long accountId) {
		 List<AccountEvent> accountHistory = eventStore.findAllAccountEvents(accountId);
		 
		 if(accountHistory == null) return new ArrayList<>();
		 if(accountHistory.isEmpty()) return accountHistory;
		 
		 return accountHistory.stream().sorted(Comparator.comparing(AccountEvent::getCreated))
				 .collect(Collectors.toList());
		 
	}

	private boolean isWithdrawalAvailable(Account account, BigDecimal withdrawAmount) {
		return account.getCurrentBalance()
				.add(withdrawAmount.negate())
				.compareTo(BigDecimal.ZERO) >= 0;
	}
}
