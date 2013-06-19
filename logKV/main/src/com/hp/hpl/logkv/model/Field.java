package com.hp.hpl.logkv.model;

import java.io.Serializable;

public class Field implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1662545137439476931L;
	private String fieldName = null;
	private FieldType fieldType = null;
	
	
	public Field(String fieldName, FieldType fieldType) {
		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
	}


	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public FieldType getFieldType() {
		return fieldType;
	}


	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}
	
}
