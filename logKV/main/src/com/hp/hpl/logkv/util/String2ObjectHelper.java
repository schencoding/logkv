package com.hp.hpl.logkv.util;

import com.hp.hpl.logkv.model.FieldType;

public class String2ObjectHelper {
	public static Object getObject(FieldType fieldType, String str){
		Object obj = null;
		switch(fieldType){
		case BOOLEAN:
			obj = Boolean.parseBoolean(str);
			break;
		case BYTE:
			obj = Byte.parseByte(str);
			break;
		case CHAR:
			//???;
			break;
		case INT:
			Integer.parseInt(str);
			break;
		case LONG:
			Long.parseLong(str);
			break;
		case FLOAT:
			Float.parseFloat(str);
			break;
		case DOUBLE:
			Double.parseDouble(str);
			break;
		case STRING:
			obj = str;
			break;
		}
		
		return obj;
	}
}
