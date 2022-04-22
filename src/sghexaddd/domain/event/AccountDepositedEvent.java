package sghexaddd.domain.event;

import java.math.BigDecimal;
import java.util.Date;

public final class AccountDepositedEvent extends AccountEvent{

	public AccountDepositedEvent( Long id, Long accountId, Date created, BigDecimal amount, BigDecimal balance) {
		super(id, accountId, created, AccountEventType.DEPOSIT, amount, balance);
	}
	
	public AccountDepositedEvent(Long accountId, BigDecimal amount, BigDecimal balance) {
		super(accountId, AccountEventType.DEPOSIT, amount, balance);
	}

}
