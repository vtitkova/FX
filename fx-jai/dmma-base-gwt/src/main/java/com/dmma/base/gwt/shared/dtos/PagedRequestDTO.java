package com.dmma.base.gwt.shared.dtos;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PagedRequestDTO<T extends IsSerializable> implements IsSerializable{
	private Integer page;
	private Integer itemsOnPage;
	private T filter;
	
	public PagedRequestDTO() {
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if(page == null )
			this.page = 1;
		else
			this.page = page;
	}

	public Integer getItemsOnPage() {
		return itemsOnPage;
	}

	public void setItemsOnPage(Integer itemsOnPage) {
		this.itemsOnPage = itemsOnPage;
	}

	public T getFilter() {
		return filter;
	}

	public void setFilter(T filter) {
		this.filter = filter;
	}
	
}
