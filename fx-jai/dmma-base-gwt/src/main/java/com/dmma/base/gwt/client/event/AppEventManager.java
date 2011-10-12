package com.dmma.base.gwt.client.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.dmma.base.gwt.client.utils.BaseStringUtils;
import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

//XXX review the code
public class AppEventManager implements ValueChangeHandler<String> {	
	/*All events identifier*/
	public static final String ALL_EVENTS = "{all}";	
	
	private static AppEventManager INSTANCE;
	private HashMap<String, ArrayList<AppEventListener>> listeners;
	
	private AppEvent currentHistoryEvent;
	
	/**
	 * Singleton class
	 */
	private AppEventManager() {
		listeners = new HashMap<String, ArrayList<AppEventListener>>();
		History.addValueChangeHandler(this);
		currentHistoryEvent = new AppEvent(History.getToken(), BaseStringUtils.getClassShortName(AppEventManager.class.getName()));
	}
	
	
	
	
	/**
	 *  Get EventManager
	 * @return EventManager
	 */
	public static AppEventManager get() {
		if (INSTANCE == null)
			INSTANCE = new AppEventManager();
		return INSTANCE;
	}
	
	/**
	 * Adds specified listener to specified event listener list.
	 * If specified listener is already in list, it's ignored. 
	 * @param listener listener to be notified
	 * @param eventKey event identifier
	 */
	public void addEventListener(String eventKey, AppEventListener listener) {
		ArrayList<AppEventListener> listenerList = listeners.get(eventKey);
		if (listenerList == null) {
			listenerList = new ArrayList<AppEventListener>();
			listeners.put(eventKey, listenerList);
		}
	
		
		//remove previous same class listener
		Iterator<AppEventListener> iter = listenerList.iterator();
		while (iter.hasNext()) {
			AppEventListener tmpListener = iter.next();
			//if (tmpListener.getClass().equals(listener.getClass()))
			if (tmpListener.getClass().equals(listener.getClass()))
				iter.remove();
		}
		
		//add listener
		if (!listenerList.contains(listener))
			listenerList.add(listener);
	}
	
	
	/**
	 * Unregister listener for specified event 
	 * @param listener listener to remove
	 * @param eventKey listener event key
	 */
	public void removeEventListener(AppEventListener listener, String eventKey) {
		ArrayList<AppEventListener> listenerList = listeners.get(eventKey);
		if (listenerList.contains(listener))
			listenerList.remove(listener);
	}
	
	/**
	 * Unregister listener for all events 
	 * @param listener listener to remove
	 * @param eventKey listener event key
	 */
	public void removeEventListener(AppEventListener listener) {
		Iterator<String> listenerListKeyIter = listeners.keySet().iterator();
		while (listenerListKeyIter.hasNext()) {
			String key = listenerListKeyIter.next();
			List<AppEventListener> listenerList = listeners.get(key);
			while (listenerList.remove(listener));
		}
	}
	
	/**
	 * Informs all interested listeners about event 
	 * @param event	 
	 */
	public void fireEvent(AppEvent event) {
		System.out.println("Streljaem");
		ArrayList<AppEventListener> allListeners = new ArrayList<AppEventListener>();
		
		ArrayList<AppEventListener> allEventListeners = listeners.get(ALL_EVENTS);
		if (allEventListeners != null)
			allListeners.addAll(allEventListeners);
	
		ArrayList<AppEventListener> specifiedEventListenerList = listeners.get(event.getKey());
		if (specifiedEventListenerList != null)
			allListeners.addAll(specifiedEventListenerList);
		
		if (allListeners.size() > 0) {
			Iterator<AppEventListener> iter = allListeners.iterator();
			while (iter.hasNext()) {
				try {
					AppEventListener listener = ((AppEventListener) iter.next());
					System.out.println(Duration.currentTimeMillis() 
							+ ": " + event.getKey().toString()
							+ " > "
							+ listener.getClass().getName());
					listener.onEvent(event);
					
				} catch (Exception ex) {
					UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
					if (handler != null)
						handler.onUncaughtException(ex);
				}
			}
		}
	}
		
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		if (event != null) {
			currentHistoryEvent = URLParser.parse(event.getValue());
			AppEvent eventt = getCurrentHistoryEvent();
			fireEvent(eventt);
		}
	}
	
	public void onValueChange(String  event) {
		if (event != null) {
			currentHistoryEvent = URLParser.parse(event);
			AppEvent eventt = getCurrentHistoryEvent();
			fireEvent(eventt);
		}
	}
	
	/**
	 * Changes current history token
	 * @param newEvent New event
	 */
	public void changeHistory(AppEvent newEvent) {
		History.newItem(URLParser.toHistoryToken(newEvent));
	}
	
	/**
	 * Changes current history token
	 * @param newEvent New event
	 */
	public void changeHistory(AppEvent newEvent, boolean issueEvent) {
		History.newItem(URLParser.toHistoryToken(newEvent),issueEvent);
	}


	public void changeURL(String address){
		StringBuilder destination = new StringBuilder();
		destination.append(Window.Location.getProtocol());    // http:
		destination.append("//");							  // //
		destination.append(Window.Location.getHost());        // 127.0.0.1:8888
		destination.append("/");
		destination.append(address);                          // home.do
		destination.append(Window.Location.getQueryString()); // ?gwt.codesvr=127.0.0.1:9997
		Window.Location.assign(destination.toString());
	}
	
	
	/**
	 * Get current history event
	 * @return current history event 
	 */
	public AppEvent getCurrentHistoryEvent() {
		return currentHistoryEvent.clone();
	}

	
}