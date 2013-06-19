package com.hp.hpl.logkv.util;

import com.hp.hpl.logkv.model.FieldType;

public class CompareHelper {
	public static int compare(FieldType fieldType, Object obj1, Object obj2){
		if(obj1 == null  || obj2 == null){
			return -1;
		}
		int result = -1;
		switch(fieldType){
		case BOOLEAN:
			
			break;
		case BYTE:
			
			break;
		case CHAR:
			//???;
			break;
		case INT:
			if(((Integer)obj1).intValue() == ((Integer)obj2).intValue()){
				return 0;
			}
			break;
		case LONG:
			
			break;
		case FLOAT:
			
			break;
		case DOUBLE:
			
			break;
		case STRING:
			result = ((String)obj1).compareToIgnoreCase((String) obj2);
			break;
		}
		
		return result;
		
	}
}
