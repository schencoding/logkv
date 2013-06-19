package com.hp.hpl.logkv.ui;

import java.util.UUID;

import javax.jms.DeliveryMode;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.demo.jms.JMSSender;

public class QueryRequestTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JMSSender sender = new JMSSender("LogKV.request", DeliveryMode.PERSISTENT, 0);
		//String querStr = "SELECT  t1.Field1, t1.Field3, t2.Field5  from logKV as t1, logKV as t2 WHERE t1.Field5 = t2.Field5 and  Field5=\"Merchant\"";
		String querStr = "SELECT  t1.Field1, t1.Field3, t2.Field5  from logKV as t1, logKV as t2 WHERE   Field5=\"Odyl\"";

		QueryRequest request = new QueryRequest(querStr, 0 * Parameter.TRU_SIZE + 1, 1 * Parameter.TRU_SIZE );

		String requestClientID = UUID.randomUUID().toString();
		sender.send(requestClientID, request);
		
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sender.release();

	}

}
