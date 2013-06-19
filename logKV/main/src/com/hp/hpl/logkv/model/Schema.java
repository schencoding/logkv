package com.hp.hpl.logkv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Schema implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6241245560667806293L;

	public static int DEFAULT_FIELD_NUM = 10;
	
	private String schemaName = null;
	private List<Field> fields = new ArrayList<Field>(DEFAULT_FIELD_NUM);
	
	public Schema(){
		
	}
	
	public Schema(String name){
		this.schemaName = name;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public void addField(int index, Field field){
		fields.add(index, field);
	}
	
	public void addField(Field field){
		fields.add(field);
	}
	
	public Field getField(int i){
		return fields.get(i);
	}
	
	public int getNumOfField(){
		return fields.size();
	}

}
