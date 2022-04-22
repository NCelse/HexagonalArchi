package sghexaddd.domain.account;

import java.math.BigDecimal;

public final class Account {
	private final Long id;
	private final BigDecimal currentBalance;
	
	
	public Account(Long id, BigDecimal currentBalance) {
		this.id = id;
		this.currentBalance = currentBalance;
	}

	public Long getId() {
		return id;
	}
	
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
}
