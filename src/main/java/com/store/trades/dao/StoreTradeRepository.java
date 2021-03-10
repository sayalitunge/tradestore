package com.store.trades.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.store.trades.model.StoreTrade;

public interface StoreTradeRepository extends CrudRepository<StoreTrade, Long> {
	StoreTrade findFirstByTradeIdAndCpIdOrderByVersionDesc(String tradeId, String cpId);

	List<StoreTrade> findAllByExpired(boolean expired);
}
