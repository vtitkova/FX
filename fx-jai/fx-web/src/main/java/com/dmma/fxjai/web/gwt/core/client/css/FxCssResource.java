package com.dmma.fxjai.web.gwt.core.client.css;

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
public interface FxCssResource extends ClientBundle {
	public static final FxCssResource  RES = (FxCssResource)     GWT.create(FxCssResource.class);

	
	public interface FxCss extends CssResource{
		public String canvas();
		public String bar();
		public String barUp();
		public String barDown();
	}
	
	
	@Source("FxCss.css")
	FxCss style();

}
