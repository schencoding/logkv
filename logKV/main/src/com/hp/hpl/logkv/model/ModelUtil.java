package com.hp.hpl.logkv.model;

import com.hp.hpl.logkv.conf.Parameter;

public class ModelUtil {
	
	public static long getTRUId(long timestamp){
		return timestamp / Parameter.TRU_SIZE;
	}

}
