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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HasValue;

public class ToggleButton extends Button implements HasValue<Boolean> {
	
	private boolean down;
	
	
	
	public ToggleButton() {
		super();
	}

	public ToggleButton(SafeHtml html, AbstractImagePrototype prototype,
			ValueChangeHandler<Boolean> handler) {
		this(html, prototype);
		addValueChangeHandler(handler);
	}

	public ToggleButton(SafeHtml html, AbstractImagePrototype prototype,
			IconAlignment align, ValueChangeHandler<Boolean> handler) {
		this(html, prototype, align);
		addValueChangeHandler(handler);
	}

	public ToggleButton(SafeHtml html, AbstractImagePrototype prototype,
			IconAlignment align) {
		super(html, prototype, align);
	}

	public ToggleButton(SafeHtml html, AbstractImagePrototype prototype) {
		super(html, prototype);
	}

	public ToggleButton(SafeHtml html, ValueChangeHandler<Boolean> handler) {
		this(html);
		addValueChangeHandler(handler);
	}

	public ToggleButton(SafeHtml html) {
		super(html);
	}

	public ToggleButton(String html, AbstractImagePrototype prototype,
			ValueChangeHandler<Boolean> handler) {
		this(html, prototype);
		addValueChangeHandler(handler);
	}

	public ToggleButton(String html, AbstractImagePrototype prototype,
			IconAlignment align, ValueChangeHandler<Boolean> handler) {
		this(html, prototype, align);
		addValueChangeHandler(handler);
	}

	public ToggleButton(String html, AbstractImagePrototype prototype,
			IconAlignment align) {
		super(html, prototype, align);
	}

	public ToggleButton(String html, AbstractImagePrototype prototype) {
		super(html, prototype);
	}

	public ToggleButton(String html, ValueChangeHandler<Boolean> handler) {
		this(html);
		addValueChangeHandler(handler);
	}

	public ToggleButton(String html) {
		super(html);
	}
	
	@Override
	public void onBrowserEvent(Event event) {
		if(!isEnabled()) {
			return;
		}
		super.onBrowserEvent(event);
		if(event.getTypeInt() == Event.ONCLICK) {
			setValue(!down, true);
		}
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
		if(down) {
			getElement().addClassName(res.style().pressed());
		} else {
			getElement().removeClassName(res.style().pressed());
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public Boolean getValue() {
		return isDown();
	}

	@Override
	public void setValue(Boolean value) {
		setDown(value);
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		setValue(value);
		if(fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}

}
