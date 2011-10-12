package com.dmma.base.app.o2xml.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is Annotation for each field (variables) 
 *  */


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface O2XAttribute {
	public String nameInXML()  default "";
	public boolean skip() default false;
	
	/**
	 * this annotation is useful if you have list of 
	 * primitive elements, like List< Sting > someStrings.
	 * */
	public String listChildNameInXML()  default "";
}
