package com.dmma.fxjai.shared.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BarDTO implements IsSerializable {
	private Integer openDateTime;
	private Double  open;
	private Double  high;
	private Double  low;
	private Double  close;
	private Integer volume;
	
	public BarDTO() {
	}

	public Integer getOpenDateTime() {
		return openDateTime;
	}

	public void setOpenDateTime(Integer openDateTime) {
		this.openDateTime = openDateTime;
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
