package com.dmma.base.gwt.client.ui.dialog;

import com.dmma.base.gwt.client.resources.css.BaseCssResource;
import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.resources.img.BaseImages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class AppDialog extends DialogBox {
	interface MyUiBinder extends UiBinder<Widget, AppDialog> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField 
	protected BaseCssResource cssRes;
	
	/** 
	 * Type meaning Look and Feel should not supply any options -- only
	 * use the options from the <code>JOptionPane</code>.
	 */
	public static final int         CLOSE_OPTION = -1;
	public static final int         YES_NO_OPTION = 0;
	public static final int         YES_NO_CANCEL_OPTION = 1;
	public static final int         OK_CANCEL_OPTION = 2;

	//
	// Return values.
	//
	/** Return value form class method if CLOSE is chosen. */
	public static final int         CLOSE_RETURN = -1;
	/** Return value from class method if YES is chosen. */
	public static final int         YES_RETURN = 0;
	/** Return value from class method if NO is chosen. */
	public static final int         NO_RETURN = 1;
	/** Return value from class method if CANCEL is chosen. */
	public static final int         CANCEL_RETURN = 2;
	/** Return value form class method if OK is chosen. */
	public static final int         OK_RETURN = 0;

	//
	// Message types. Used by the UI to determine what icon to display,
	// and possibly what behavior to give based on the type.
	//
	/** Used for error messages. */
	public static final int  NONE_MESSAGE = -1;
	/** Used for error messages. */
	public static final int  ERROR_MESSAGE = 0;
	/** Used for information messages. */
	public static final int  INFORMATION_MESSAGE = 1;
	/** Used for warning messages. */
	public static final int  WARNING_MESSAGE = 2;
	/** Used for questions. */
	public static final int  QUESTION_MESSAGE = 3;

	private FlowPanel imageHolder;
	private HTML dialogTextLabel;
	private AppDialogChoiseListener choiseListener;

	private AppDialog(String message, String title, int messageType, int selectionType, AppDialogChoiseListener choiseListener) {
		super();
		uiBinder.createAndBindUi(this);
		cssRes.style().ensureInjected();
		this.choiseListener = choiseListener;
		init(message, title, messageType, selectionType);
	}

	private void init(String message, String title, int messageType, int selectionType) {
		this.setText(title);
		this.setAnimationEnabled(true);
		FlexTable contentTab = new FlexTable();
		contentTab.addStyleName(cssRes.style().dialogVPanel());
		this.setWidget(contentTab);
		
		int x = 0;
		if(messageType!=NONE_MESSAGE){
			contentTab.setWidget(0, 0, createImageHolder(messageType));
			contentTab.getFlexCellFormatter().setRowSpan(0, 0, 2);
			x=1;
		}
		dialogTextLabel = new HTML(message);
		contentTab.setWidget(0, x,dialogTextLabel);
		contentTab.setWidget(1, 0, createControlHolder(selectionType));
		contentTab.getFlexCellFormatter().setHorizontalAlignment(1,0, HasHorizontalAlignment.ALIGN_RIGHT);
	}

	
	private Widget createControlHolder(int selectionType) {
		FlexTable controllHolder = new FlexTable();
		if(selectionType==YES_NO_OPTION){
			controllHolder.setWidget(0, 0, createButton(BaseMessages.MSG.yes(),     "70px", YES_RETURN));
			controllHolder.setWidget(0, 1, createButton(BaseMessages.MSG.no(),      "70px", NO_RETURN));
		}else if(selectionType==YES_NO_CANCEL_OPTION){
			controllHolder.setWidget(0, 0, createButton(BaseMessages.MSG.yes(),     "70px", YES_RETURN));
			controllHolder.setWidget(0, 1, createButton(BaseMessages.MSG.no(),      "70px", NO_RETURN));
			controllHolder.setWidget(0, 2, createButton(BaseMessages.MSG.cancel(),  "100px", CANCEL_RETURN));
		}else if(selectionType==OK_CANCEL_OPTION){
			controllHolder.setWidget(0, 0, createButton(BaseMessages.MSG.ok(),      "70px", OK_RETURN));
			controllHolder.setWidget(0, 1, createButton(BaseMessages.MSG.cancel(),  "100px", CANCEL_RETURN));
		}else{ 
			controllHolder.setWidget(0, 0, createButton(BaseMessages.MSG.close(),   "100px", CLOSE_RETURN));
		}
		return controllHolder;
	}


	private Button createButton(String tille, String width, int intToReturn) {
		Button bt = new Button();
		bt.setText(tille);
		bt.setWidth(width);
		bt.addClickHandler(new ReturnClickHandler(intToReturn));
		return bt;
	}
	
	
	
	private Widget createImageHolder(int messageType) {
		imageHolder = new FlowPanel();
		Image image;
		if(messageType == ERROR_MESSAGE){
			image = new Image(BaseImages.IMG.errorBig());
		}else if(messageType == WARNING_MESSAGE){
			image = new Image(BaseImages.IMG.warningBig());
		}else if(messageType == QUESTION_MESSAGE){
			image = new Image(BaseImages.IMG.questionBig());
		}else{
			image = new Image(BaseImages.IMG.infoBig());
		}
		
		image.addStyleName(cssRes.style().dialogImage());
		imageHolder.add(image);
		return imageHolder;
	}

	private class ReturnClickHandler implements ClickHandler{
		int intToReturn;
		public ReturnClickHandler(int intToReturn) {
			this.intToReturn = intToReturn;
		}
		
		@Override
		public void onClick(ClickEvent arg0) {
			AppDialog.this.hide();
			if(choiseListener!=null)
				choiseListener.onDialogClose(intToReturn);
		}
	}
	
		
	
	public static void show(String message, int messageType) {
		show(message, messageType, null );
	}
	
	public static void show(String message, int messageType, AppDialogChoiseListener choiseListener) {
		String title = BaseMessages.MSG.info();
		if(messageType==ERROR_MESSAGE)
			title = BaseMessages.MSG.error();
		else if(messageType==WARNING_MESSAGE)
			title = BaseMessages.MSG.warning();
		else if(messageType==QUESTION_MESSAGE)
			title = BaseMessages.MSG.confirmation();
		show(message, title, messageType, CLOSE_OPTION, choiseListener);
	}
	
	
	// Full constructor without listener
	public static void show(String message, String title, int messageType, int selectionType) {
		show(message, title, messageType, selectionType, null);
	}


	// Full constructor
	public static void show(String message, String title, int messageType, int selectionType, AppDialogChoiseListener choiseListener ) {
		AppDialog d = new AppDialog(message, title, messageType, selectionType, choiseListener);
		d.center();
	}

		
	

}