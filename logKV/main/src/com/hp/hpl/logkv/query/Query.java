package com.hp.hpl.logkv.query;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.query.parser.LogKVQueryLexer;
import com.hp.hpl.logkv.query.parser.LogKVQueryParser;
import com.hp.hpl.logkv.util.String2ObjectHelper;
import com.hp.hpl.logkv.util.Util;

public class Query implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5248924378004896945L;
	private static CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();
	private transient String queryStr = null;
	public transient long startTime = -1L;
	public transient long endTime = -1L;
	public long within = -1L;
	
	public List<Integer> leftColumnIds = new ArrayList<Integer>(5);
	public List<Integer> rightColumnIds = new ArrayList<Integer>(5);
	public List<Integer> filterColumnIds = new ArrayList<Integer>(5);
	public boolean bSelfJoin = false;
	public int joinLeftAttribute = -1;
	public int joinRightAttribute = -1;
	
	public long subQueryTruId = -1L;
	
	public List<String> columnNamesInSelect = new ArrayList<String>(5);
	public List<String> tableNamesInSelect = new ArrayList<String>(5);

	public List<String> realTableNames = new ArrayList<String>(2);
	public List<String> aliasTableNames = new ArrayList<String>(2);
	public List<FieldFilter> filters = new ArrayList<FieldFilter>(5);
	Schema schema = null;
	
	public Query(String queryStr) {
		this.queryStr = queryStr;
		try {
			buildQuery();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param query
	 * Build a new query instance from an existing query
	 * This is mainly used to generate a sub-query from a query
	 */
	public Query(Query query){
		this.queryStr = query.queryStr;
		this.startTime = query.startTime;
		this.endTime = query.endTime;
		this.within = query.within;
		this.leftColumnIds = query.leftColumnIds;
		this.rightColumnIds = query.rightColumnIds;
		this.filterColumnIds = query.filterColumnIds;
		this.bSelfJoin = query.bSelfJoin;
		this.joinLeftAttribute = query.joinLeftAttribute;
		this.joinRightAttribute = query.joinRightAttribute;
		this.columnNamesInSelect = query.columnNamesInSelect;
		this.tableNamesInSelect = query.tableNamesInSelect;

		this.realTableNames = query.realTableNames;
		this.aliasTableNames = query.aliasTableNames;
		
		this.filters = query.filters;
		this.schema = query.schema;
	}


	public void buildQuery() throws IOException, RecognitionException {
		schema = coordinatorAccessor.getSchema();
		StringReader sr = new StringReader(queryStr);
		ANTLRReaderStream input = new ANTLRReaderStream(sr);
		LogKVQueryLexer lexer = new LogKVQueryLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LogKVQueryParser parser = new LogKVQueryParser(tokens);

		LogKVQueryParser.query_return r = parser.query();

		CommonTree root = (CommonTree) r.getTree();
		if (root.getChildCount() < 1) {
			System.err.println("SELECT clause is required.");
			return;
		}
		CommonTree selectSubTree = null;
		CommonTree fromSubTree = null;
		CommonTree whereSubTree = null;
		CommonTree withinSubTree = null;

		if (parser.getNumberOfSyntaxErrors() == 0) {

		} else {
			System.out.println("Error syntax...");
		}

		for (int i = 0; i < root.getChildCount(); i++) {
			CommonTree subTree = (CommonTree) root.getChild(i);
			if (subTree.getText().compareToIgnoreCase("SELECT") == 0) {
				selectSubTree = subTree;
				for (int j = 0; j < selectSubTree.getChildCount(); j++) {
					CommonTree attributeSubTree = (CommonTree) selectSubTree.getChild(j);
					String attributeStr = attributeSubTree.getText();
					if (attributeStr.compareToIgnoreCase("AS") == 0) {
						String columnName = attributeSubTree.getChild(0).getText().split("\\.")[1];
						String aliasColumnName = attributeSubTree.getChild(1).getText();
						this.tableNamesInSelect.add(attributeSubTree.getChild(0).getText().split("\\.")[0]);
						this.columnNamesInSelect.add(aliasColumnName);
						this.leftColumnIds.add(getFieldId(schema, columnName));
					} else {
						if (attributeStr.contains(".")) {
							String columnName = attributeStr.split("\\.")[1];
							this.tableNamesInSelect.add(attributeStr.split("\\.")[0]);
							this.columnNamesInSelect.add(columnName);
							this.leftColumnIds.add(getFieldId(schema, columnName));
						} else {
							String columnName = attributeStr;
							this.columnNamesInSelect.add(columnName);
							this.leftColumnIds.add(getFieldId(schema, columnName));
						}
					}
				}
			} else if (subTree.getText().compareToIgnoreCase("FROM") == 0) {
				fromSubTree = subTree;
				for (int j = 0; j < fromSubTree.getChildCount(); j++) {
					CommonTree tableSubTree = (CommonTree) fromSubTree.getChild(j);
					String tableStr = tableSubTree.getText();
					if (tableStr.compareToIgnoreCase("AS") == 0) {
						CommonTree leftSubTree = (CommonTree) tableSubTree.getChild(0);
						CommonTree rightSubTree = (CommonTree) tableSubTree.getChild(1);
						realTableNames.add(leftSubTree.getText());
						aliasTableNames.add(rightSubTree.getText());
					} else {
						realTableNames.add(tableSubTree.getText());
					}
				}
			} else if (subTree.getText().compareToIgnoreCase("WHERE") == 0) {
				whereSubTree = subTree;
				getFilters(whereSubTree, filters);
			} else if (subTree.getText().compareToIgnoreCase("WITHIN") == 0) {
				withinSubTree = subTree;
				within = Long.parseLong(withinSubTree.getChild(0).getText());
				bSelfJoin = true;
			} else {
				System.err.println("Error clause.");
			}
		}
		
		if(bSelfJoin){
			String leftTable = aliasTableNames.get(0);
			String rightTable = aliasTableNames.get(1);
			for(int i = 0; i < tableNamesInSelect.size(); i++){
				String str = tableNamesInSelect.get(i);
				if(rightTable.compareToIgnoreCase(str) == 0){
					this.tableNamesInSelect.remove(i);
					int x = this.leftColumnIds.remove(i);
					this.rightColumnIds.add(x);
					System.out.println(x + " is added to the right columnIds");
					i--;
				}
			}
		}
	}

	public void getFilters(CommonTree conditionSubTree, List<FieldFilter> list) {
		int childCount = conditionSubTree.getChildCount();
		for (int i = 0; i < childCount; i++) {
			CommonTree subTree = (CommonTree) conditionSubTree.getChild(i);
			if (subTree.getText().compareToIgnoreCase("AND") == 0) {
				getFilters(subTree, list);
			} else {
				int fieldId = -1;
				String filterString = subTree.getChild(0).getText();
				if (filterString.contains(".")) {
					fieldId = this.getFieldId(schema, filterString.split("\\.")[1]);
				} else {
					fieldId = this.getFieldId(schema, filterString);
				}

				String valueStr = subTree.getChild(1).getText();

				if (valueStr.contains(".")) {
					String joinStr = valueStr;
					int joinFieldId = this.getFieldId(schema, joinStr.split("\\.")[1]);
					joinRightAttribute = joinFieldId;
					joinLeftAttribute = fieldId;
					
					this.bSelfJoin = true;

					if (!leftColumnIds.contains(fieldId)) {
						filterColumnIds.add(fieldId);
					}

					if (!leftColumnIds.contains(joinFieldId)) {
						if (!this.filterColumnIds.contains(joinFieldId)) {
							filterColumnIds.add(joinFieldId);
						}
					}
				} else {
					if (valueStr.startsWith("\"")) {
						valueStr = valueStr.substring(1);
					}
					if (valueStr.endsWith("\"")) {
						valueStr = valueStr.substring(0, valueStr.length() - 1);
					}
					Object value = String2ObjectHelper.getObject(schema.getField(fieldId).getFieldType(), valueStr);
					FieldFilter ff = new FieldFilter(fieldId, 0, value);
					list.add(ff);
					if (!leftColumnIds.contains(fieldId)) {
						filterColumnIds.add(fieldId);
					}
				}
			}

		}
	}

	private int getFieldId(Schema schema, String fieldName) {
		for (int i = 0; i < schema.getNumOfField(); i++) {
			if (schema.getField(i).getFieldName().compareToIgnoreCase(fieldName) == 0) {
				return i;
			}
		}
		return -1;
	}

	public List<FieldFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<FieldFilter> filters) {
		this.filters = filters;
	}

	public boolean check(Tree tree, Record record) {
		int childCount = tree.getChildCount();
		boolean bSatisfy = true;

		/**
		 * case 1: this is a leaf node
		 */
		if (childCount == 0) {
			// TODO //check();
			return bSatisfy;
		}

		/**
		 * case 2: this is a non-leaf node
		 */
		LogicOperator logicOp = null;
		;
		String logicOpStr = tree.getText();

		if (logicOpStr.compareToIgnoreCase(LogicOperator.AND.name()) == 0) {
			bSatisfy = true;
			logicOp = LogicOperator.AND;
		} else if (logicOpStr.compareToIgnoreCase(LogicOperator.OR.name()) == 0) {
			bSatisfy = false;
			logicOp = LogicOperator.OR;
		} else {
			Util.err("Unknown logic operator " + logicOpStr, this.getClass());
		}

		for (int i = 0; i < childCount; i++) {
			Tree subTree = tree.getChild(i);
			boolean bi = check(subTree, record);

			if (logicOp == LogicOperator.AND) {
				if (bi == false) {
					bSatisfy = false;
					return bSatisfy;
				}
			} else if (logicOp == LogicOperator.OR) {
				if (bi == true) {
					bSatisfy = true;
					return bSatisfy;
				}
			} else {
				Util.err("Unknown logic operator " + logicOpStr, this.getClass());
			}

		}

		return bSatisfy;
	}

	public boolean check(Expression exp, Record record) {

		return true;
	}

	public static class Record {

	}

	public static class Expression {

	}

	public int[] getColumnIds() {
		int leftSize = leftColumnIds.size();
		int rightSize = rightColumnIds.size();
		
		int[] ids = new int[leftSize + rightSize];
		int i = 0;
		for ( ; i < leftSize; i++) {
			ids[i] = leftColumnIds.get(i);
		}
		
		for (; i < leftSize + rightSize; i++) {
			ids[i] = rightColumnIds.get(i - leftSize);
		}
		return ids;
	}

	public int[] getColumnIdsToRetrieve() {
		int leftSize = leftColumnIds.size();
		int rightSize = rightColumnIds.size();
		int size2 = this.filterColumnIds.size();
		int[] ids = new int[leftSize + rightSize + size2];
		int i = 0;
		for (i = 0; i < leftSize; i++) {
			ids[i] = leftColumnIds.get(i);
		}
		
		for (; i < leftSize + rightSize; i++) {
			ids[i] = rightColumnIds.get(i - leftSize);
		}
		
		for (; i < (leftSize + rightSize + size2); i++) {
			ids[i] = filterColumnIds.get(i - (leftSize + rightSize));
		}
		
		return ids;
	}

}
