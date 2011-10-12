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

package com.google.code.gwteyecandy.impl;

import com.google.code.gwteyecandy.resources.ButtonResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;

public class ButtonImpl {
	
	protected static final ButtonResources res = GWT.create(ButtonResources.class);

	public int getEventsToSink() {
		return Event.ONCLICK | Event.ONMOUSEUP | Event.FOCUSEVENTS;
	}
	
	public void makeFocusable(Element element) {
		element.setAttribute("href", "javascript:void(0)");
	}
	
	public void setPressed(Element element) {
	}

	public void setUppressed(Element element) {
	}

	public void setHover(Element element) {
	}

	public void setUnhover(Element element) {
	}
	
	public void fixGradient(Element element) {
	}
	
	public void setEnabled(Element element, boolean enabled) {
		if(enabled) {
			element.removeClassName(res.style().disabled());
		} else {
			element.addClassName(res.style().disabled());
		}
	}
}
