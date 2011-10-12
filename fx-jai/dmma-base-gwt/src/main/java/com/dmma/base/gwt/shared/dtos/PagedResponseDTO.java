package com.dmma.base.gwt.shared.dtos;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PagedResponseDTO<T extends IsSerializable> implements IsSerializable{
	private Integer page;
	private Integer itemsOnPage;
	private Long total;
	private ArrayList<T> entities;
	
	public PagedResponseDTO() {
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getItemsOnPage() {
		return itemsOnPage;
	}

	public void setItemsOnPage(Integer itemsOnPage) {
		this.itemsOnPage = itemsOnPage;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public ArrayList<T> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<T> entities) {
		this.entities = entities;
	}
	
}
