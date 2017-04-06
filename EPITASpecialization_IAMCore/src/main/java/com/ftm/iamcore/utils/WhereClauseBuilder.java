/**
 * WhereClauseBuilder.java
 */
package com.ftm.iamcore.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that manages the creation of where clauses given the a class using reflection
 * It uses the Builder Pattern to create the criteria, for example
 * setCriteria("FileoftheField", "value");
 * @author Favio
 *
 */
public abstract class WhereClauseBuilder extends Reflection {

	/**
	 * Method that create a where clause given an entity with filled fields, using the 'or' criteria.
	 * For example: Identity("test", "test@test.com", "01/01/2000")
	 * should return: 
	 * where displayName='test' or email='test@test.com' or birthdate='01/01/2000' 
	 * @param entity
	 * @return
	 */
	public static String getClause(Object entity) {
		//filling the map with field name and values
		List<Field> listFields = getFields(entity.getClass());
		Map<String, Object>fields = new HashMap<>();
		for (Field value : listFields) {
			fillMap(entity, fields, value);
		}
		StringBuilder query = new StringBuilder();
		boolean flag = false;
		query.append(" where ");
		for(Map.Entry<String, Object> couple : fields.entrySet()) {
			if(couple.getValue() != null ) {
				query.append(flag? " or " : "");
				query.append(couple.getKey());
				query.append(" = ");				
				query.append((couple.getValue() instanceof String)? "'" : "");
				query.append(couple.getValue());
				query.append((couple.getValue() instanceof String)? "'" : "");
				flag = true;
			}
		}
		return query.toString();			
	}

	/**
	 * This method is invoke as many times as number of fields entity has
	 * @param entity object
	 * @param map containing the fields to be searched
	 * @param value of the element (couple K,V) in the map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void fillMap(Object entity, Map<String, Object> fields, Field value) {
		if(value.getType().isPrimitive()) {				
			switch (value.getType().getName()) {
			case "byte":
				if ((byte) invokeGetter(entity, value.getName()) == 0)
					fields.put(value.getName(), null);
				else
					fields.put(value.getName(), invokeGetter(entity, value.getName()));
				break;
			case "short":
				if ((short) invokeGetter(entity, value.getName()) == 0)
					fields.put(value.getName(), null);
				else
					fields.put(value.getName(), invokeGetter(entity, value.getName()));
				break;
			case "int":
				if ((int) invokeGetter(entity, value.getName()) == 0)
					fields.put(value.getName(), null);
				else
					fields.put(value.getName(), invokeGetter(entity, value.getName()));
				break;
			case "long":
				if ((long) invokeGetter(entity, value.getName()) == 0)
					fields.put(value.getName(), null);
				else
					fields.put(value.getName(), invokeGetter(entity, value.getName()));
				break;
			case "char":
				if ((char) invokeGetter(entity, value.getName()) == '\0')
					fields.put(value.getName(), null);
				else
					fields.put(value.getName(), invokeGetter(entity, value.getName()));
				break;
			case "float":
				if ((float) invokeGetter(entity, value.getName()) == 0.0)
					fields.put(value.getName(), null);
				else
					fields.put(value.getName(), invokeGetter(entity, value.getName()));
				break;
			case "double":
				if ((double) invokeGetter(entity, value.getName()) == 0.0)
					fields.put(value.getName(), null);
				else
					fields.put(value.getName(), invokeGetter(entity, value.getName()));
				break;					
			}
		} 	
		else if (value.getType().isInterface() ){
			switch (value.getType().getName()) {
			case "java.util.Map":
				if ( ((Map) invokeGetter(entity, value.getName())).size() >0 )
					fields.putAll((Map) invokeGetter(entity, value.getName()));
				break;
			}
		} else if (value.getType().getName().equals("java.util.Date") && invokeGetter(entity, value.getName()) != null) {
			//set the Date in format (dd/MM/yyyy) in String format
			fields.put(value.getName(), new SimpleDateFormat("dd/MM/yyyy").format(invokeGetter(entity, value.getName()))) ;
		} else
			fields.put(value.getName(), invokeGetter(entity, value.getName()));
	}
}