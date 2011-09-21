package com.dmma.fxjai.shared.dto;


public class BarDTO {
	private Long    id;
	private Integer clientId;
	private Integer symbolId;
	private Integer openDateTime;
	private Integer period;
	private Double  open;
	private Double  high;
	private Double  low;
	private Double  close;
	private Integer volume;
	
	public BarDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
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

	public Integer getOpenDateTime() {
		return openDateTime;
	}

	public void setOpenDateTime(Integer openDateTime) {
		this.openDateTime = openDateTime;
	}
	
	
}
