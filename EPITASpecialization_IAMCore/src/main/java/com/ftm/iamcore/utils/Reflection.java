/**
 * Reflection.java
 */
package com.ftm.iamcore.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code Reflection} class has methods to invoke <i>getters</i> and <i>setters </i> from a given field of an object. 
 * @author Favio
 *
 */
public class Reflection {

	/**
	 * Invoke a <i>getter</i> method from an <i>Object's field </i>
	 * @param object
	 * @param name of the field
	 * @return execution of the getter
	 */
	public static Object invokeGetter(Object obj, String variableName){
	    Object variableValue = null;  
		try {
	        /**
	         * Get object of PropertyDescriptor using variable name and class
	         * Note: To use PropertyDescriptor on any field/variable, the field must have 
	         * both `Setter` and `Getter` method.
	         */
	         PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
	        /**
	         * Get field/variable value using getReadMethod()
	         * variableValue is Object because value can be an Object, Integer, String, etc...
	         */
	         variableValue = objPropertyDescriptor.getReadMethod().invoke(obj);	        
	      } catch (Exception e){
	    	  e.printStackTrace();
	      }
		return variableValue;
	   }
	
	/**
	 * Invoke a <i>setter</i> method from an <i>Object's field </i>
	 * @param object
	 * @param name of the field to set
	 * @param value of the field to set	 
	 */
	public static void invokeSetter(Object obj, String variableName, Object variableValue){
	      /* variableValue is Object because value can be an Object, Integer, String, etc... */
	      try {
	        /**
	         * Get object of PropertyDescriptor using variable name and class
	         * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
	         */
	         PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
	         /* Set field/variable value using getWriteMethod() */
	         objPropertyDescriptor.getWriteMethod().invoke(obj, variableValue);
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	   }
	
	/**
	 * Return list of fields given an {@link Object}
	 * @param object
	 * @return list of field's names.
	 */
	public static List<Field> getFields(Object object){
		List<Field> fields = new ArrayList<Field>(Arrays.asList(object.getClass().getDeclaredFields()));		
		return fields;		
	}	
		
	/**
	 * Return list of fields given an {@link Class}
	 * @param object
	 * @return list of field's names.
	 */
	public static List<Field> getFields(Class<?> type){
		List<Field> fields = new ArrayList<Field>(Arrays.asList(type.getDeclaredFields()));		
		return fields;		
	}
}
