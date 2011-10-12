package com.dmma.base.gwt.client.presenter;


import java.util.HashMap;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.event.URLParser;
import com.dmma.base.gwt.client.ui.dialog.AppDialog;
import com.dmma.base.gwt.client.ui_sample.PresenterView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;


public class AbstractPresenterMainController implements ValueChangeHandler<String> {
	private final PresenterView presenterHolderPanel;
	private final HashMap<String, IPresenter>  registeredPresenters;
	private IPresenter       currentPresenter;
	

	public AbstractPresenterMainController() {
		this.registeredPresenters = new HashMap<String, IPresenter>();
		this.presenterHolderPanel = new PresenterView();
		this.currentPresenter = null;
		History.addValueChangeHandler(this);
		
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		AppEvent e = URLParser.parse(token);
		String key = e.getKey();
		if(key == null || key.isEmpty())
			return;
		IPresenter requested = registeredPresenters.get(key);
		if(requested == null)
			return;
		if(currentPresenter == null || !currentPresenter.getPresenterId().equals(key)){
			currentPresenter = requested;
			presenterHolderPanel.repaint(currentPresenter.getPresenterDisplay());
			onPresenterChanged(key);
		}
		currentPresenter.applyNewParams(e);
		
	}
	
	protected void onPresenterChanged(String key) {
	}

	public void addPresenter(IPresenter presenter){
		IPresenter p = registeredPresenters.get(presenter.getPresenterId());
		if(p!=null){
			AppDialog.show("Application initialization error - probably you have two or more presenters with same ID", AppDialog.ERROR_MESSAGE);
			return;
		}
		registeredPresenters.put(presenter.getPresenterId(), presenter);
	}
	
	
	public PresenterView getPresenterHolderPanel(){
		return presenterHolderPanel;
	}
	/** 
	 * After all presenters was adder and registered, we need to process current history */
	public void fireCurrentHistoryState(){
		History.fireCurrentHistoryState();
	}

}
