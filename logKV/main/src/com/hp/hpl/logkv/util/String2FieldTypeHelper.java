package com.hp.hpl.logkv.util;

import com.hp.hpl.logkv.model.FieldType;

public class String2FieldTypeHelper {

	public static FieldType toFieldType(String fieldTypeStr) {
		if (fieldTypeStr.compareToIgnoreCase("String") == 0) {
			return FieldType.STRING;
		} else if (fieldTypeStr.compareToIgnoreCase("Int") == 0) {
			return FieldType.INT;
		} else if (fieldTypeStr.compareToIgnoreCase("Boolean") == 0) {
			return FieldType.BOOLEAN;
		} else if (fieldTypeStr.compareToIgnoreCase("Byte") == 0) {
			return FieldType.BYTE;
		} else if (fieldTypeStr.compareToIgnoreCase("Char") == 0) {
			return FieldType.CHAR;
		} else if (fieldTypeStr.compareToIgnoreCase("Double") == 0) {
			return FieldType.DOUBLE;
		} else if (fieldTypeStr.compareToIgnoreCase("Float") == 0) {
			return FieldType.FLOAT;
		} else if (fieldTypeStr.compareToIgnoreCase("Long") == 0) {
			return FieldType.LONG;
		}

		return null;
	}

}
