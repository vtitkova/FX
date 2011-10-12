package com.dmma.fxjai.shared.shared.filters;

import com.dmma.fxjai.shared.shared.types.PeriodType;
import com.dmma.fxjai.shared.shared.types.SymbolType;
import com.google.gwt.user.client.rpc.IsSerializable;

public class BarSearchFilter implements IsSerializable{
	private Integer accountId;
	private SymbolType symbol;
	private PeriodType period;
	private Integer from;
	private Integer to;
	
	public BarSearchFilter() {
	}

	public SymbolType getSymbol() {
		return symbol;
	}

	public void setSymbol(SymbolType symbol) {
		this.symbol = symbol;
	}

	public PeriodType getPeriod() {
		return period;
	}

	public void setPeriod(PeriodType period) {
		this.period = period;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public boolean isValid() {
		if(accountId == null || symbol == null || period == null)
			return false;
		return true;
	}

}
