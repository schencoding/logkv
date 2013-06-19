package com.hp.hpl.logkv.qp;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.query.Query;
import com.hp.hpl.logkv.queryprocess.QueryProxy;
import com.hp.hpl.logkv.queryprocess.ResultSet.Record;
import com.hp.hpl.logkv.util.Util;

public class QueryProcessTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Query query = new Query("SELECT Field2, Field3, Field5  from logKV WHERE Field3=\"Black\" and Field5=\"Duncical\" within 20");
		//Query query = new Query("SELECT  Field1, Field3, Field5  from logKV WHERE logKV.field5=logKV.field5 and Field5=\"Nucleus\" within 2");
		//Query query = new Query("SELECT  t1.Field1, t1.Field3, t2.Field5  from logKV as t1, logKV as t2 WHERE t1.Field5 = t2.Field5 and  Field5=\"Merchant\" ");
		//Query query = new Query("SELECT  t1.Field1, t1.Field3, t2.Field5  from logKV as t1");
		Query query = new Query("SELECT  t1.Field1, t1.Field2, t2.Field3, t2.Field5 from LogKV as t1, LogKV as t2 Where t1.Field3=t2.Field5 ");
		query.startTime = 1355894651L * 1000;
		query.endTime = query.startTime + 1 * Parameter.TRU_SIZE;
		//query.truId = Util.getTruId(query.startTime);
		System.out.println("Start TRUId: " + Util.getTruId(query.startTime));
		System.out.println("End TRUId: " + Util.getTruId(query.endTime));
		// query.columnIds.add(1);
		//query.within = 2;
		//query.joinLeftAttribute = 2;
		//query.bSelfJoin = false;
		QueryProxy qp = new QueryProxy(query);
		Record[] results = qp.execute();
		int length = results.length>100? 100: results.length;
		for (int i = 0; i < length; i++) {
			System.out.println(results[i].toString());
		}
		System.out.println(qp.getQueryProcessingStatistics());
	}

}






//SELECT t1.Field1, t1.Field3, t2.Field5  from logKV as t1, logKV as t2 WHERE t1.Field3 = t2.Field3 and  Field5=\"Nucleus\" 