package sghexaddd.domain.eventstore;

import java.util.List;

import sghexaddd.domain.event.AccountEvent;

public interface IEventRepository {

	void store (AccountEvent event);
	
	AccountEvent findAccountEventById(Long id);
	
	List<AccountEvent> findAllAccountEvents(Long accountId);
}
