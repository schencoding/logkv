package com.hp.hpl.logkv.ingestkv;

import com.hp.hpl.logkv.model.GlobalTRU;
import com.hp.hpl.logkv.model.KVTRU;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.util.Util;

public class TimeRangeKVWriter implements Runnable{
	private GlobalTRU globalTru;
	private Schema schema;
	
	public TimeRangeKVWriter(GlobalTRU globalTru, Schema schema){
		this.globalTru = globalTru;
		this.schema = schema;
	}

	@Override
	public void run() {
		Util.log("Begin converting TRU " + globalTru.getTruId(), this.getClass());
		KVTRU kvTru = new KVTRU(globalTru.getTruId(), schema);
		kvTru.addEvents(globalTru);
		kvTru.write2KVStore();
		Util.log("Finished converting and writing TRU " + globalTru.getTruId(), this.getClass());
	}
	
	
}
