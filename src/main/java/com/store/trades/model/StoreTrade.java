package com.store.trades.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;

@Entity
public class StoreTrade {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String tradeId;
	private int version;
	private String cpId;
	private String bookId;
	@FutureOrPresent
	private LocalDate maturityDate;
	private LocalDate createdDate;
	private boolean expired;

	public StoreTrade() {

	}

	public StoreTrade(String tradeId, int version, String cpId, String bookId, @FutureOrPresent LocalDate maturityDate,
			LocalDate createdDate, boolean expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.cpId = cpId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

}
