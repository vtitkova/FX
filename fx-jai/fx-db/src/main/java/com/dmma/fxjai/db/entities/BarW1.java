package com.dmma.fxjai.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "barw1")
public class BarW1{
	@Id()
	@GeneratedValue
	@Column protected Long    id;
	@Column protected Integer clientId;
	@Column protected Integer symbolId;
	@Column protected Integer openDateTime;
	@Column protected Double  open;
	@Column protected Double  high;
	@Column protected Double  low;
	@Column protected Double  close;
	@Column protected Integer volume;
	
	public BarW1() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getOpenDateTime() {
		return openDateTime;
	}

	public void setOpenDateTime(Integer openDateTime) {
		this.openDateTime = openDateTime;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(Integer symbolId) {
		this.symbolId = symbolId;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}
}
