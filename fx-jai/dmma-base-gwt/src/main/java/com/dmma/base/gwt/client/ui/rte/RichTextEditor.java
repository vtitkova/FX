package com.dmma.base.gwt.client.ui.rte;

import com.dmma.base.gwt.client.ui.rte.css.RichTextEditorCss;
import com.dmma.base.gwt.client.ui.rte.i18n.RichTextEditorMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;


public class RichTextEditor extends Composite{
	interface MyUiBinder extends UiBinder<Widget, RichTextEditor> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	private EventHandler handler;  
	private RichTextArea.Formatter formatter;
	
	@UiField
	protected RichTextEditorCss style;
	
	@UiField
	protected ToggleButton boldBT;
	@UiField
	protected  ToggleButton italicBT;
	@UiField
	protected  ToggleButton underlineBT;
	@UiField
	protected  ToggleButton subscriptBT;
	@UiField
	protected  ToggleButton superscriptBT;
	@UiField
	protected  ToggleButton strikethroughBT;
	@UiField
	protected PushButton justifyLeftBT;
	@UiField
	protected PushButton justifyCenterBT;
	@UiField
	protected PushButton justifyRightBT;
	@UiField
	protected  PushButton indentBT;
	@UiField
	protected  PushButton outdentBT;
	@UiField
	protected  PushButton hrBT;
	@UiField
	protected  PushButton olBT;
	@UiField
	protected  PushButton ulBT;
	@UiField
	protected  PushButton insertImageBT;
	@UiField
	protected  PushButton createLinkBT;
	@UiField
	protected  PushButton removeLinkBT;
	@UiField
	protected  PushButton removeFormatBT;

	@UiField
	protected ListBox backColorsLB;
	@UiField
	protected ListBox foreColorsLB;

	@UiField
	protected ListBox fontsLB;

	@UiField
	protected ListBox fontSizesLB;


	@UiField
	protected RichTextArea richTextArea;


	public RichTextEditor() {
		initWidget(uiBinder.createAndBindUi(this));
		if (!GWT.isScript())
			this.setTitle(this.getClass().getName());
		
		formatter = richTextArea.getFormatter();
		handler = new EventHandler();
		addValuesToLists();
		bindHandlers();
	}

	private void addValuesToLists() {
		backColorsLB.addItem(RichTextEditorMessages.MSG.background());
		backColorsLB.addItem(RichTextEditorMessages.MSG.white(),  "white");
		backColorsLB.addItem(RichTextEditorMessages.MSG.black(),  "black");
		backColorsLB.addItem(RichTextEditorMessages.MSG.red(),    "red");
		backColorsLB.addItem(RichTextEditorMessages.MSG.green(),  "green");
		backColorsLB.addItem(RichTextEditorMessages.MSG.yellow(), "yellow");
		backColorsLB.addItem(RichTextEditorMessages.MSG.blue(),   "blue");

		foreColorsLB.addItem(RichTextEditorMessages.MSG.foreground());
		foreColorsLB.addItem(RichTextEditorMessages.MSG.white(),  "white");
		foreColorsLB.addItem(RichTextEditorMessages.MSG.black(),  "black");
		foreColorsLB.addItem(RichTextEditorMessages.MSG.red(),    "red");
		foreColorsLB.addItem(RichTextEditorMessages.MSG.green(),  "green");
		foreColorsLB.addItem(RichTextEditorMessages.MSG.yellow(), "yellow");
		foreColorsLB.addItem(RichTextEditorMessages.MSG.blue(),   "blue");

		fontsLB.addItem(RichTextEditorMessages.MSG.font(), "");
		fontsLB.addItem(RichTextEditorMessages.MSG.normal(), "");
		fontsLB.addItem("Times New Roman", "Times New Roman");
		fontsLB.addItem("Arial", "Arial");
		fontsLB.addItem("Courier New", "Courier New");
		fontsLB.addItem("Georgia", "Georgia");
		fontsLB.addItem("Trebuchet", "Trebuchet");
		fontsLB.addItem("Verdana", "Verdana");

		fontSizesLB.addItem(RichTextEditorMessages.MSG.size());
		fontSizesLB.addItem(RichTextEditorMessages.MSG.xxsmall());
		fontSizesLB.addItem(RichTextEditorMessages.MSG.xsmall());
		fontSizesLB.addItem(RichTextEditorMessages.MSG.small());
		fontSizesLB.addItem(RichTextEditorMessages.MSG.medium());
		fontSizesLB.addItem(RichTextEditorMessages.MSG.large());
		fontSizesLB.addItem(RichTextEditorMessages.MSG.xlarge());
		fontSizesLB.addItem(RichTextEditorMessages.MSG.xxlarge());

	}

	private void bindHandlers() {
		richTextArea.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				updateStatus();
			}
		});
		richTextArea.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				updateStatus();
			}
		});

		boldBT.addClickHandler(handler);
		italicBT.addClickHandler(handler);
		underlineBT.addClickHandler(handler);
		subscriptBT.addClickHandler(handler);
		superscriptBT.addClickHandler(handler);
		strikethroughBT.addClickHandler(handler);
		justifyLeftBT.addClickHandler(handler);
		justifyCenterBT.addClickHandler(handler);
		justifyRightBT.addClickHandler(handler);
		indentBT.addClickHandler(handler);
		outdentBT.addClickHandler(handler);
		hrBT.addClickHandler(handler);
		olBT.addClickHandler(handler);
		ulBT.addClickHandler(handler);
		insertImageBT.addClickHandler(handler);
		createLinkBT.addClickHandler(handler);
		removeLinkBT.addClickHandler(handler);
		removeFormatBT.addClickHandler(handler);

		backColorsLB.addChangeHandler(handler);
		foreColorsLB.addChangeHandler(handler);
		fontsLB.addChangeHandler(handler);
		fontSizesLB.addChangeHandler(handler);
	}



	public String getHTML(){
		return richTextArea.getHTML();
	}

	public void setHTML(String html){
		richTextArea.setHTML(html);
	}


	private class EventHandler implements ClickHandler, ChangeHandler{
		@Override
		public void onClick(ClickEvent event) {
			Widget sender = (Widget) event.getSource();

			if (sender == boldBT) {
				formatter.toggleBold();
			} else if (sender == italicBT) {
				formatter.toggleItalic();
			} else if (sender == underlineBT) {
				formatter.toggleUnderline();
			} else if (sender == subscriptBT) {
				formatter.toggleSubscript();
			} else if (sender == superscriptBT) {
				formatter.toggleSuperscript();
			} else if (sender == strikethroughBT) {
				formatter.toggleStrikethrough();
			}else if (sender == indentBT) {
				formatter.rightIndent();
			} else if (sender == outdentBT) {
				formatter.leftIndent();
			} else if (sender == justifyLeftBT) {
				formatter.setJustification(RichTextArea.Justification.LEFT);
			} else if (sender == justifyCenterBT) {
				formatter.setJustification(RichTextArea.Justification.CENTER);
			} else if (sender == justifyRightBT) {
				formatter.setJustification(RichTextArea.Justification.RIGHT);
			} else if (sender == insertImageBT) {
				String url = Window.prompt("Enter an image URL:", "http://");
				if (url != null) {
					formatter.insertImage(url);
				}
			} else if (sender == createLinkBT) {
				String url = Window.prompt("Enter a link URL:", "http://");
				if (url != null) {
					formatter.createLink(url);
				}
			} else if (sender == removeLinkBT) {
				formatter.removeLink();
			} else if (sender == hrBT) {
				formatter.insertHorizontalRule();
			} else if (sender == olBT) {
				formatter.insertOrderedList();
			} else if (sender == ulBT) {
				formatter.insertUnorderedList();
			} else if (sender == removeFormatBT) {
				formatter.removeFormat();
			} 
		}

		@Override
		public void onChange(ChangeEvent event) {
			Widget sender = (Widget) event.getSource();
			if(sender == backColorsLB) {
				formatter.setBackColor(backColorsLB.getValue(backColorsLB.getSelectedIndex()));
				backColorsLB.setSelectedIndex(0);
			} else if (sender == foreColorsLB) {
				formatter.setForeColor(foreColorsLB.getValue(foreColorsLB.getSelectedIndex()));
				foreColorsLB.setSelectedIndex(0);
			} else if (sender == fontsLB) {
				formatter.setFontName(fontsLB.getValue(fontsLB.getSelectedIndex()));
				fontsLB.setSelectedIndex(0);
			} else if (sender == fontSizesLB) {
				formatter.setFontSize(fontSizesConstants[fontSizesLB.getSelectedIndex() - 1]);
				fontSizesLB.setSelectedIndex(0);
			}
		}
	}

	/**
	 * Updates the status of all the stateful buttons.
	 */
	private void updateStatus() {
		boldBT.setDown(formatter.isBold());
		italicBT.setDown(formatter.isItalic());
		underlineBT.setDown(formatter.isUnderlined());
		subscriptBT.setDown(formatter.isSubscript());
		superscriptBT.setDown(formatter.isSuperscript());
		strikethroughBT.setDown(formatter.isStrikethrough());
	}
	
	 private static final RichTextArea.FontSize[] fontSizesConstants = new RichTextArea.FontSize[] {
	      RichTextArea.FontSize.XX_SMALL, RichTextArea.FontSize.X_SMALL,
	      RichTextArea.FontSize.SMALL, RichTextArea.FontSize.MEDIUM,
	      RichTextArea.FontSize.LARGE, RichTextArea.FontSize.X_LARGE,
	      RichTextArea.FontSize.XX_LARGE};

}
