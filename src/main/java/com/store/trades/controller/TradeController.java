package com.store.trades.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.trades.model.StoreTrade;
import com.store.trades.service.TradeService;
import com.store.trades.util.StoreTradeException;

@RestController
@RequestMapping("/trade")
public class TradeController {

	@Autowired
	TradeService service;

	@PostMapping
	public ResponseEntity<String> save(@RequestBody @Valid StoreTrade storeTrade) {
		System.out.println("Inside save method of Trade Store");
		try {
			service.save(storeTrade);
		} catch (StoreTradeException e) {
			System.out.println("Error inside save method: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(storeTrade.getTradeId());
	}

	@GetMapping
	public List<StoreTrade> getAllTrades() {
		return service.getAll();
	}

	@Scheduled(cron = "0 0 0 * * ?")
	public void checkExpiry() throws InterruptedException {
		System.out.println("Inside checkExpiry method of Cron Job");
		service.checkExpiry();
	}

}
