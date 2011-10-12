package com.dmma.base.gwt.client.ui.rte.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface RichTextEditorMessages  extends Constants{
	public static final RichTextEditorMessages  MSG = (RichTextEditorMessages)     GWT.create(RichTextEditorMessages.class);
	
	public String bold();         // Toggle Bold
	public String createLink();   // Create Link
	public String hr();           // Insert Horizontal Rule
	public String indent();       // Indent Right
	public String insertImage();  // Insert Image
	public String italic();       // Toggle Italic
	public String justifyCenter();// Center
	public String justifyLeft();  // Left Justify
	public String justifyRight(); // Right Justify
	public String ol();           // Insert Ordered List
	public String outdent();      // Indent Left
	public String removeFormat(); // Remove Formatting
	public String removeLink();   // Remove Link
	public String strikeThrough();// Toggle Strikethrough
	public String subscript();    // Toggle Subscript
	public String superscript();  // Toggle Superscript
	public String ul();           // Insert Unordered List
	public String underline();    // Toggle Underline
	public String color();        // Color
	public String black();        // Black
	public String white();        // White
	public String red();          // Red
	public String green();        // Green
	public String yellow();       // Yellow
	public String blue();         // Blue
	public String font();         // Font
	public String normal();       // Normal
	public String size();         // Size
	public String xxsmall();      // XX-Small
	public String xsmall();       // X-Small
	public String small();        // Small
	public String medium();       // Medium
	public String large();        // Large
	public String xlarge();       // X-Large
	public String xxlarge();      // XX-Large
	public String background();
	public String foreground();
	
}
