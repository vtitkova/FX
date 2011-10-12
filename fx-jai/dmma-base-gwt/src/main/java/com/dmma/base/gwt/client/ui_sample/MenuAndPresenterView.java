package com.dmma.base.gwt.client.ui_sample;

import java.util.ArrayList;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.event.URLParser;
import com.dmma.base.gwt.client.presenter.AbstractPresenterMainController;
import com.dmma.base.gwt.client.ui.menu.MenuEntry;
import com.dmma.base.gwt.client.ui.menu.MenuItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class MenuAndPresenterView extends AbstractPresenterMainController{
	interface MyUiBinder extends UiBinder<Widget, MenuAndPresenterView> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	private Integer menuHeightDecreaser;
	
	@UiField
	StackLayoutPanel menuPanel;
	
	@UiField
	FlowPanel workAreaPanel;
	
	
	public MenuAndPresenterView(RootPanel rootPanel, Integer menuHeightDecreaser) {
		super();
		this.menuHeightDecreaser = menuHeightDecreaser;
		rootPanel.clear();
		rootPanel.add(uiBinder.createAndBindUi(this));
		workAreaPanel.add(getPresenterHolderPanel());
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				resizeMenuPanel();
			}
		});
		resizeMenuPanel();
	}
	
	private void resizeMenuPanel(){
		if(menuPanel!=null){
			int height = Window.getClientHeight();
			menuPanel.setHeight(height-menuHeightDecreaser + "px");
		}
	}
	
	protected void addMenuEntry(MenuEntry menuEntry) {
		Widget header = createHeaderWidget(menuEntry.getTitle(), menuEntry.getImageResource());
		Widget widget = createItems(menuEntry.getItems());
		menuPanel.add(widget, header, 30);
	}
	
	private Widget createHeaderWidget(String text, ImageResource image) {
	    // Add the image and text to a horizontal panel
	    HorizontalPanel hPanel = new HorizontalPanel();
	    hPanel.setHeight("100%");
	    hPanel.setSpacing(0);
	    hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	    if(image != null){
	    	hPanel.add(new Image(image));
	    }
	    HTML headerText = new HTML(text);
	    headerText.setStyleName("cw-StackPanelHeader");
	    hPanel.add(headerText);
	    return new SimplePanel(hPanel);
	  }

	private Widget createItems(ArrayList<MenuItem> items) {
		FlowPanel fp = new FlowPanel();
		VerticalPanel panel = new VerticalPanel();
		panel.setWidth("100%");
		panel.setSpacing(4);
		if(items!=null){
			for(MenuItem item : items){
				panel.add(createHyperlinkToUserPanel(item.getTitle(), item.getEvent()));
			}
		}
		fp.add(panel);
		return fp;
	}
	
	private Widget createHyperlinkToUserPanel(String title, AppEvent event) {
		
		Hyperlink hp = new Hyperlink(title, URLParser.toHistoryToken(event));
		return hp;
	}
	
	
	
	@Override
	protected void onPresenterChanged(String key) {
		System.out.println("HIGHLIGHT"+key);
	}
	
	
	
}
