package com.store.trades.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.trades.dao.StoreTradeRepository;
import com.store.trades.model.StoreTrade;
import com.store.trades.util.StoreTradeException;

@Service
public class TradeService {

	@Autowired
	StoreTradeRepository storeTradeRepository;

	public void save(final StoreTrade storeTrade) throws StoreTradeException {
		if (storeTrade.getMaturityDate().isBefore(LocalDate.now())) {
			throw new StoreTradeException("Maturity Date should not be less than today's date");
		}

		StoreTrade highestVersionTrade = getHighterVersionTradeByTradeIdAndcpId(storeTrade.getTradeId(),
				storeTrade.getCpId());

		if (highestVersionTrade != null && highestVersionTrade.getVersion() >= storeTrade.getVersion()) {
			throw new StoreTradeException("Lower trade version cannot be accepted");
		}

		storeTrade.setCreatedDate(LocalDate.now());
		storeTradeRepository.save(storeTrade);
	}

	public StoreTrade getHighterVersionTradeByTradeIdAndcpId(String tradeId, String cpId) {
		return storeTradeRepository.findFirstByTradeIdAndCpIdOrderByVersionDesc(tradeId, cpId);
	}

	public List<StoreTrade> getAll() {
		List<StoreTrade> trades = new ArrayList<>();
		storeTradeRepository.findAll().forEach(trade -> trades.add(trade));
		return trades;
	}

	public List<StoreTrade> getActiveTrades() {
		return storeTradeRepository.findAllByExpired(false);
	}

	public void checkExpiry() {
		List<StoreTrade> trades = getActiveTrades();
		trades.stream().filter(t -> t.getMaturityDate().isBefore(LocalDate.now())).map(t -> {
			t.setExpired(true);
			return t;
		}).forEach(trade -> storeTradeRepository.save(trade));
	}
}
