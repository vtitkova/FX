package com.dmma.base.gwt.client.resources.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * This is common css styles for DST project to make them available, in your ui.xml provide "with" element:<br>
 * 	<i> < ui:with field='cssRes' type='ec.ep.dst.gwt.core.client.css.DstCoreCssResource' / > </i><br>
 * 
 * you can access style in ui.xml, using:<br>
 * 	<i>< tr class="{cssRes.style.tableZebraRow}" ></i><br>
 * 
 * for programmatic access to inline Styles, provide UiField:<br>
 * UiField<br>
 * DstCoreCssResource cssRes;<br>
 *
 * <b>!!! NOTE !!!! IMPORTANT !!! ISSUE !!!</b><br>
 * whenever you using this resource, you need to be sure, that CSS was injected !!!!<br> 
 * do next: <br>
 *  <i>initWidget(uiBinder.createAndBindUi(this));</i><br>
 *  <i>cssRes.style().ensureInjected();</i><br>
 * 
 * **/
public interface BaseCssResource extends ClientBundle {
	public static final BaseCssResource  RES = (BaseCssResource)     GWT.create(BaseCssResource.class);

	
	public interface BaseCss extends CssResource{
		public String dialogImage();
		public String dialogVPanel();

		public String baseLoadingPopup();
		public String baseLoadingImage();
		public String baseLoadingCaption();
		public String baseLoadingMsg();
		
		public String autoMargin();
		
		public String tableHeaderRow();
		public String tableZebraRow();
		public String tableHooverRow();
		public String hasBorderBottom();		
		public String hasBorderLeft();		
		public String borderColor();		
		public String drawTableBorders();
		public String noBorderTopRow();
		
		public String actionWrap();

	}
	
	
	@Source("BaseCss.css")
	BaseCss style();

}
