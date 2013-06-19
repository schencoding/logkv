package com.hp.hpl.logkv.ui;

import java.util.Arrays;

import com.hp.hpl.logkv.queryprocess.QueryProcessingStatistics;
import com.hp.hpl.logkv.queryprocess.ResultSet.Record;

public class QueryResponse extends Response {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8259518690975522347L;
	
	private String[] columnNames;
	private int[] columnIds;
	private Record[] resultSet;
	private QueryProcessingStatistics runningStatistics;	
	
	
	public QueryResponse(long timestamp, Record[] resultSet, String[] columnNames, int[] columnIds, QueryProcessingStatistics runningStatistics) {
		super(timestamp);
		this.resultSet = resultSet;
		this.columnNames = columnNames;
		this.runningStatistics = runningStatistics;
		this.columnIds = columnIds;
	}

	public Record[] getResultSet() {
		return resultSet;
	}

	public void setResultSet(Record[] resultSet) {
		this.resultSet = resultSet;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	

	public int[] getColumnIds() {
		return columnIds;
	}

	public void setColumnIds(int[] columnIds) {
		this.columnIds = columnIds;
	}

	public QueryProcessingStatistics getRunningStatistics() {
		return runningStatistics;
	}

	public void setRunningStatistics(QueryProcessingStatistics runningStatistics) {
		this.runningStatistics = runningStatistics;
	}

	@Override
	public String toString() {
		return "QueryResponse [columnNames=" + Arrays.toString(columnNames) + ", \n resultSet=\n" + resultSet + "]";
	}


	
	

}
