package sghexaddd.domain.event;

import java.math.BigDecimal;
import java.util.Date;

public final class AccountWithdrawedEvent extends AccountEvent {

	public AccountWithdrawedEvent( Long id, Long accountId, Date created, BigDecimal amount, BigDecimal balance) {
		super(id, accountId, created, AccountEventType.WITHDRAW, amount, balance);
	}
	
	public AccountWithdrawedEvent(Long accountId, BigDecimal amount, BigDecimal balance) {
		super(accountId, AccountEventType.WITHDRAW, amount, balance);
	}
}
