package sghexaddd.domain.event;

import java.math.BigDecimal;
import java.util.Date;

public abstract class AccountEvent {
    protected Long id;
    protected Long accountId;
    protected Date created;
    protected AccountEventType operation;
    protected BigDecimal amount;
    protected BigDecimal balance;
    
    
	public AccountEvent(Long id, Long accountId, Date created, AccountEventType operation, BigDecimal amount, BigDecimal balance) {
		this.id = id;
		this.accountId = accountId;
		this.created = created;
		this.operation = operation;
		this.amount = amount;
		this.balance = balance;
	}
    
	public AccountEvent(Long accountId, AccountEventType operation, BigDecimal amount, BigDecimal balance) {
		this.accountId = accountId;
		this.created = new Date();
		this.operation = operation;
		this.amount = amount;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public Long getAccountId() {
		return accountId;
	}
	
	public Date getCreated() {
		return created;
	}

	public AccountEventType getOperation() {
		return operation;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
