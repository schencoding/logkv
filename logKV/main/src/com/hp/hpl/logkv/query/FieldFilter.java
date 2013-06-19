package com.hp.hpl.logkv.query;

import java.io.Serializable;

public class FieldFilter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2798331805960540924L;
	private int fieldId;
	private int operator;
	private Object value;

	public FieldFilter(int fieldId, int operator, Object value) {
		super();
		this.fieldId = fieldId;
		this.operator = operator;
		this.value = value;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
