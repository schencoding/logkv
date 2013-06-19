package com.hp.hpl.logkv.query.parser;

import java.io.StringReader;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

public class LogKVQueryMain {
	
	public static void main(String[] args) throws Exception {
		
		StringReader sr = new StringReader("select a.a1, a.a2 as t2 from table_a as a, table_b as b where a1 = 1 or (a2 = 2 and a3=\"3\") within 60");
	
		                                       
		ANTLRReaderStream input = new ANTLRReaderStream(sr);
		LogKVQueryLexer lexer = new LogKVQueryLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LogKVQueryParser parser = new LogKVQueryParser(tokens);
				
		LogKVQueryParser.query_return r = parser.query();
	
		CommonTree root = (CommonTree)r.getTree();
		
		if (parser.getNumberOfSyntaxErrors() == 0) {
			boolean afterWhere = false;
			for (int i = 0; i < root.getChildCount(); i++) {

				CommonTree subTree = (CommonTree) root.getChild(i);

				if (!afterWhere) {

					System.out.println(subTree.getText());
					if ("where".equalsIgnoreCase(subTree.getText())) {
						afterWhere = true;
					}

				} else {

					if (subTree.getChildCount() == 0) {
						System.out.println(subTree.getText());
					} else {
						System.out.println(subTree.getText() + "\t" + subTree.getChildren());
					}

				}
			}
		}
		
		System.out.println(root.getChildren());
		System.out.println(((CommonTree)r.getTree()).toStringTree());
	
		System.out.println("parser.getNumberOfSyntaxErrors() = " + parser.getNumberOfSyntaxErrors());
		//System.out.println("parser.expressionList = " + parser.expressionList);
		
		
		System.out.println("===========================");
		for (int i = 0; i < tokens.getTokens().size(); i++) {
			CommonToken ct = (CommonToken) tokens.getTokens().get(i);
			String tokenType = null;
			
			if (ct.getType() != -1) {
				tokenType = LogKVQueryParser.tokenNames[ct.getType()];
			} else {
				tokenType = "<EOF>";
			}
			
			System.out.println(tokenType + "\t" + ct.getText());
		}
		
		System.out.println(((CommonTree)r.getTree()).toStringTree());
	}
}