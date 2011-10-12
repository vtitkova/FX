/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.code.gwteyecandy;

import static com.google.code.gwteyecandy.Button.IconAlignment.LEFT;
import static com.google.code.gwteyecandy.Button.IconAlignment.RIGHT;
import static com.google.gwt.dom.client.Style.Unit.PX;

import com.google.code.gwteyecandy.impl.ButtonImpl;
import com.google.code.gwteyecandy.resources.ButtonResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.AbstractImagePrototype.ImagePrototypeElement;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

public class Button extends FocusWidget implements HasHTML {

	public static enum IconAlignment {
		LEFT, RIGHT
	}
	
	public static enum GroupPosition {
		NONE, LEFT, RIGHT, MIDDLE
	}
	
	private static ButtonUiBinder uiBinder = GWT.create(ButtonUiBinder.class);

	interface ButtonUiBinder extends UiBinder<Widget, Button> {
	}
	
	@UiField(provided=true) final ButtonResources res = GWT.create(ButtonResources.class);
	@UiField Element content;
	@UiField Element textElement;
	@UiField Element gradient;
	
	private ImagePrototypeElement iconElement;
	private IconAlignment align = LEFT;
	
	private static ButtonImpl impl = GWT.create(ButtonImpl.class);
	
	private boolean enabled = true;

	public Button() {
		res.style().ensureInjected();
		setElement(uiBinder.createAndBindUi(this).getElement());
		sinkEvents(impl.getEventsToSink());
		impl.makeFocusable(getElement());
	}
	
	public Button(String html) {
		this();
		this.textElement.setInnerHTML(html);
	}
	
	public Button(String html, ClickHandler handler) {
		this(html);
		addClickHandler(handler);
	}
	
	public Button(SafeHtml html) {
		this(html.asString());
	}
	
	public Button(SafeHtml html, ClickHandler handler) {
		this(html.asString(), handler);
	}

	public Button(String html, AbstractImagePrototype prototype, IconAlignment align) {
		this(html);
		setIcon(prototype, align);
	}
	
	public Button(String html, AbstractImagePrototype prototype, IconAlignment align, ClickHandler handler) {
		this(html, prototype, align);
		addClickHandler(handler);
	}
	
	public Button(String html, AbstractImagePrototype prototype) {
		this(html, prototype, LEFT);
	}
	
	public Button(String html, AbstractImagePrototype prototype, ClickHandler handler) {
		this(html, prototype);
		addClickHandler(handler);
	}
	
	public Button(SafeHtml html, AbstractImagePrototype prototype, IconAlignment align) {
		this(html.asString(), prototype, align);
	}
	
	public Button(SafeHtml html, AbstractImagePrototype prototype, IconAlignment align, ClickHandler handler) {
		this(html.asString(), prototype, align, handler);
	}
	
	public Button(SafeHtml html, AbstractImagePrototype prototype) {
		this(html.asString(), prototype);
	}
	
	public Button(SafeHtml html, AbstractImagePrototype prototype, ClickHandler handler) {
		this(html.asString(), prototype, handler);
	}
	
	public void setIconAlign(IconAlignment align) {
		if(this.align == align) {
			return;
		}
		this.align = align;
		if(iconElement != null) {
			placeIcon();
		}
	}
	
	public void setIcon(ImageResource icon) {
		setIcon(AbstractImagePrototype.create(icon), align);
	}
	
	public void setIcon(AbstractImagePrototype prototype, IconAlignment align) {
		this.align = align;
		if(iconElement != null) {
			iconElement.removeFromParent();
		}
		iconElement = prototype.createElement();
		iconElement.addClassName(res.style().icon());
		placeIcon();
	}

	private void placeIcon() {
		Style style = iconElement.getStyle();
		if(align == RIGHT) {
			style.setMarginLeft(3, PX);
			style.clearMarginRight();
			content.insertAfter(iconElement, textElement);
		} else {
			style.setMarginRight(3, PX);
			style.clearMarginLeft();
			content.insertBefore(iconElement, textElement);
		}
		impl.setEnabled(iconElement, enabled);
	}
	
	@Override
	public void onBrowserEvent(Event event) {
		if(!enabled) {
			return;
		}
		super.onBrowserEvent(event);
		switch(DOM.eventGetType(event)) {
		case Event.ONMOUSEDOWN:
			impl.setPressed(getElement());
			break;
		case Event.ONMOUSEUP:
			impl.setUppressed(getElement());
			setFocus(false);
			break;
		case Event.ONMOUSEOVER:
			impl.setHover(getElement());
			break;
		case Event.ONMOUSEOUT:
			impl.setUnhover(getElement());
			break;
		}
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		impl.fixGradient(gradient);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if(enabled) {
			getElement().addClassName(res.style().enabled());
		} else {
			getElement().removeClassName(res.style().enabled());
		}
		if(iconElement != null) {
			impl.setEnabled(iconElement, enabled);
		}
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getText() {
		return textElement.getInnerText();
	}

	@Override
	public void setText(String text) {
		textElement.setInnerText(text);
	}

	@Override
	public String getHTML() {
		return textElement.getInnerHTML();
	}

	@Override
	public void setHTML(String html) {
		textElement.setInnerHTML(html);
	}
	
	public void setGroupPosition(GroupPosition position) {
		switch(position) {
		case NONE:
			removeStyleName(res.style().noleft());
			removeStyleName(res.style().noright());
			break;
		case LEFT:
			removeStyleName(res.style().noleft());
			addStyleName(res.style().noright());
			break;
		case RIGHT:
			addStyleName(res.style().noleft());
			removeStyleName(res.style().noright());
			break;
		case MIDDLE:
			addStyleName(res.style().noleft());
			addStyleName(res.style().noright());
			break;
		}
	}
}
