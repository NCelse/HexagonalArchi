package sghexaddd.domain.repository;

import java.util.Optional;

import sghexaddd.domain.account.Account;

public interface IAccountRepository {

	Optional<Account> findAccountById(Long id);
	
	Account save(Account account);
}
