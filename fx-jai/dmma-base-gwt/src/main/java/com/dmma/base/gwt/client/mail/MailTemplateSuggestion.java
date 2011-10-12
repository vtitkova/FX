package com.dmma.base.gwt.client.mail;

public class MailTemplateSuggestion {
	private final String   name;
	private final String   title;
	private final String[] tags;
	
	public MailTemplateSuggestion(String name, String title, String[] tags){
		this.name = name;
		this.title = title;
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public String[] getTags() {
		return tags;
	}

}
