package com.dmma.fxjai.web.gwt.poc.client;


import java.util.ArrayList;

import com.dmma.fxjai.web.gwt.poc.client.services.PocGWTService;
import com.dmma.fxjai.web.gwt.poc.client.services.PocGWTServiceAsync;
import com.dmma.fxjai.web.gwt.poc.shared.entities.PocDTO;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;


public class PocEP implements EntryPoint{
	public static final String MAINCONTENT = "maincontent";
	public PocGWTServiceAsync pocService = GWT.create(PocGWTService.class);
		
	private FlexTable table;
	
	public void onModuleLoad() {
		RootPanel a = RootPanel.get(MAINCONTENT);
		table = new FlexTable(); 
		a.add(table);
		requestPocs();
		
	}


	private void requestPocs() {
		pocService.findLastPocs(new AsyncCallback<ArrayList<PocDTO>>() {
			@Override
			public void onSuccess(ArrayList<PocDTO> result) {
				table.removeAllRows();
				for(PocDTO p : result){
					int row = table.getRowCount();
					table.setHTML(row, 0, p.getId()+"");
					table.setHTML(row, 1, p.getText());
					table.setHTML(row, 2, ""+p.getCreated());
				}
			}
			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}

	
	
	
	
	
}
