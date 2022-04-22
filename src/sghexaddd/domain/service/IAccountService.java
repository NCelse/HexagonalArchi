package sghexaddd.domain.service;

import java.math.BigDecimal;
import java.util.List;

import sghexaddd.domain.event.AccountEvent;
import sghexaddd.domain.exception.AccountNotFoundException;

public interface IAccountService {

	boolean depositMoney(Long accountId, BigDecimal depositAmount) throws AccountNotFoundException;
	
	boolean withdrawMoney(Long accountId, BigDecimal withdrawAmount) throws AccountNotFoundException;
	
	List<AccountEvent> computeAccountHistory(Long accountId);
}
