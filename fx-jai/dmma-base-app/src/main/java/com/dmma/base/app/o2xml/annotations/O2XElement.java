package com.dmma.base.app.o2xml.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * This is Annotation for entire class
 * for class variables, use  <code>O2XAttribute</code> annotations
 *  */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface O2XElement {
	String nameInXml() default "";
}
