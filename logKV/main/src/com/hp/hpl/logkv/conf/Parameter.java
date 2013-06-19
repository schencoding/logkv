package com.hp.hpl.logkv.conf;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

import com.hp.hpl.logkv.model.Field;
import com.hp.hpl.logkv.model.FieldType;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.util.String2FieldTypeHelper;;

public class Parameter {
	public final static String LOCAL_TRU_DIR = "data/localtru/";
	public final static String GLOBAL_TRU_DIR = "data/globaltru/";
	public final static String RECEIVED_INGEST_KV_REPLICA_DIR = "data/replica/";
	public final static int INGEST_KV_REPLICA_FACTOR = 1;
	
	public static final int INGEST_RECEIVER_PORT = 12351;
	public static int NUM_OF_INGEST_RECEIVER = 1;
	
	public final static double FIELD_FILL_RATIO = 0.5;
	
	public static long TRU_SIZE = 5 * 1000;
	public final static int INGEST_KV_REPLICA_INTERVAL = 1000;
	public final static int SHUFFLE_SENDER_PORT = 12346;
	public final static int INGEST_KV_REPLICA_RECEVIER_PORT = 12347;
	
	public static int COORDINATOR_PORT = 12345;
	public static String[] COORDINATOR_IPs = null;
	public static int BUFFER_SIZE_FOR_INGEST_KV_REPLICA_RECEIVER = 8*1024;
	public static final int BUFFER_SIZE_FOR_SHUFFLE_RECEIVER = 8*1024;
	
	
	public static final int NUMBER_OF_CONCURRENT_SHUFF_THRES = 1;
	public static final int NUMBER_OF_KV_CONVERTERS = 4;
	public static final boolean IF_INSERT_TO_KV_STORE = true;
	
	
	public static final int KV_STORE_PORT = 9160;
	public static final String KV_STORE_SPACENAME = "logKV";
	public static final String KV_STORE_COLUMN_FAMILY_PREFIX = "KV_CF_";
	public static final String KV_STORE_VALUE_COLUMN_NAME = "value";
	public static final String KV_STORE_INDEX_COLUMN_NAME = "index";
	public static final int KV_STORE_REPLICA_FACTOR = 3;
	
	public static String INITIAL_KV_ACCESS = null;
	public static final int NUMBER_OF_QP_THREADS = 5;
	public static final int TIMERANGE_KV_QP_PORT = 12348;
	public static final int INGEST_KV_QP_PORT = 12349;
	
	public static final int LOG_SOURCE_PORT = 12350;
	public static final int LOG_SOURCE_RUNNER_PORT = 12352;
	public static int TENTATIVE_TIMERANGE = 6;
	public static String KV_STORE_DB_DIR = null;
	public static String[] WORKER_NODES = null;	
	
	public static String SSH_USER_NAME = null;
	public static String SSH_PASSWORD = null;
	public static int NUM_OF_THRES_FOR_UI_REQUEST = 8;
	
	public static Schema schema = new Schema();
	

	static{
			Properties props = new Properties();
			try{
				props.load(new FileInputStream("conf/config.properties"));
				COORDINATOR_IPs = props.getProperty("coordinator_ip").split(",");
				String workerNodesStr = props.getProperty("worker_nodes");
				WORKER_NODES = workerNodesStr.split(",");
				System.out.println("Worker nodes: " + Arrays.toString(WORKER_NODES));
				NUM_OF_INGEST_RECEIVER = WORKER_NODES.length;
				TRU_SIZE = Long.parseLong(props.getProperty("tru_size"));
				INITIAL_KV_ACCESS = props.getProperty("kv_store_accesspoint");
				KV_STORE_DB_DIR = props.getProperty("kv_store_db_dir");
				SSH_USER_NAME = props.getProperty("ssh_user_name");
				SSH_PASSWORD = props.getProperty("ssh_password");
				TENTATIVE_TIMERANGE = Integer.parseInt(props.getProperty("tentative_timerange"));
				NUM_OF_THRES_FOR_UI_REQUEST = Integer.parseInt(props.getProperty("number_of_threads_for_ui_request"));
				String schemaStr = props.getProperty("schema");
				String[] fieldStrs = schemaStr.split(",");
				for(int i = 0; i < fieldStrs.length; i++){
					String fieldStr = fieldStrs[i];
					String[] strs = fieldStr.split(":");
					FieldType fieldType = String2FieldTypeHelper.toFieldType(strs[1]);
					schema.addField(i, new Field(strs[0], fieldType));
				}
			}catch(Exception e){
				System.err.print("Load configuration exception.");
				e.printStackTrace();
			}
	}
}
