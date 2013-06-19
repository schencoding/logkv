// $ANTLR 3.4 E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g 2012-12-18 19:57:20
package com.hp.hpl.logkv.query.parser;
import java.util.ArrayList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class LogKVQueryParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AND_OPERATOR_T", "AS_KEYWORD_T", "B", "BETWEEN_OPERATOR_T", "BINARY_OPERATOR_T", "C", "COMMA_T", "D", "E", "F", "FILED_T", "FROM_KEYWORD_T", "G", "H", "I", "IN_OPERATOR_T", "IS_OPERATOR_T", "J", "K", "L", "LEFT_BRACE", "M", "N", "NOT_OPERATOR_T", "NULL_OPERATOR_T", "O", "OR_OPERATOR_T", "P", "Q", "R", "RIGHT_BRACE", "S", "SELECT_KEYWORD_T", "T", "U", "V", "VALUE_T", "W", "WHERE_KEYWORD_T", "WITHIN_KEYWORD_T", "WS", "X", "Y", "Z"
    };

    public static final int EOF=-1;
    public static final int A=4;
    public static final int AND_OPERATOR_T=5;
    public static final int AS_KEYWORD_T=6;
    public static final int B=7;
    public static final int BETWEEN_OPERATOR_T=8;
    public static final int BINARY_OPERATOR_T=9;
    public static final int C=10;
    public static final int COMMA_T=11;
    public static final int D=12;
    public static final int E=13;
    public static final int F=14;
    public static final int FILED_T=15;
    public static final int FROM_KEYWORD_T=16;
    public static final int G=17;
    public static final int H=18;
    public static final int I=19;
    public static final int IN_OPERATOR_T=20;
    public static final int IS_OPERATOR_T=21;
    public static final int J=22;
    public static final int K=23;
    public static final int L=24;
    public static final int LEFT_BRACE=25;
    public static final int M=26;
    public static final int N=27;
    public static final int NOT_OPERATOR_T=28;
    public static final int NULL_OPERATOR_T=29;
    public static final int O=30;
    public static final int OR_OPERATOR_T=31;
    public static final int P=32;
    public static final int Q=33;
    public static final int R=34;
    public static final int RIGHT_BRACE=35;
    public static final int S=36;
    public static final int SELECT_KEYWORD_T=37;
    public static final int T=38;
    public static final int U=39;
    public static final int V=40;
    public static final int VALUE_T=41;
    public static final int W=42;
    public static final int WHERE_KEYWORD_T=43;
    public static final int WITHIN_KEYWORD_T=44;
    public static final int WS=45;
    public static final int X=46;
    public static final int Y=47;
    public static final int Z=48;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public LogKVQueryParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public LogKVQueryParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return LogKVQueryParser.tokenNames; }
    public String getGrammarFileName() { return "E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g"; }


    List expressionList = new ArrayList();


    public static class query_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "query"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:11:1: query : select_statement from_statement ( where_statement )? ( within_statement )? ;
    public final LogKVQueryParser.query_return query() throws RecognitionException {
        LogKVQueryParser.query_return retval = new LogKVQueryParser.query_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.select_statement_return select_statement1 =null;

        LogKVQueryParser.from_statement_return from_statement2 =null;

        LogKVQueryParser.where_statement_return where_statement3 =null;

        LogKVQueryParser.within_statement_return within_statement4 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:11:8: ( select_statement from_statement ( where_statement )? ( within_statement )? )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:12:4: select_statement from_statement ( where_statement )? ( within_statement )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_select_statement_in_query51);
            select_statement1=select_statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, select_statement1.getTree());

            pushFollow(FOLLOW_from_statement_in_query57);
            from_statement2=from_statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, from_statement2.getTree());

            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:14:4: ( where_statement )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==WHERE_KEYWORD_T) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:14:4: where_statement
                    {
                    pushFollow(FOLLOW_where_statement_in_query62);
                    where_statement3=where_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, where_statement3.getTree());

                    }
                    break;

            }


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:15:4: ( within_statement )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==WITHIN_KEYWORD_T) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:15:4: within_statement
                    {
                    pushFollow(FOLLOW_within_statement_in_query70);
                    within_statement4=within_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, within_statement4.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "query"


    public static class select_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "select_statement"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:18:1: select_statement : select_keyword ^ ( ( field | alias_statement ) comma_t !)* ( field | alias_statement ) ;
    public final LogKVQueryParser.select_statement_return select_statement() throws RecognitionException {
        LogKVQueryParser.select_statement_return retval = new LogKVQueryParser.select_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.select_keyword_return select_keyword5 =null;

        LogKVQueryParser.field_return field6 =null;

        LogKVQueryParser.alias_statement_return alias_statement7 =null;

        LogKVQueryParser.comma_t_return comma_t8 =null;

        LogKVQueryParser.field_return field9 =null;

        LogKVQueryParser.alias_statement_return alias_statement10 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:19:3: ( select_keyword ^ ( ( field | alias_statement ) comma_t !)* ( field | alias_statement ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:20:4: select_keyword ^ ( ( field | alias_statement ) comma_t !)* ( field | alias_statement )
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_select_keyword_in_select_statement88);
            select_keyword5=select_keyword();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(select_keyword5.getTree(), root_0);

            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:20:20: ( ( field | alias_statement ) comma_t !)*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==FILED_T) ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1==AS_KEYWORD_T) ) {
                        int LA4_3 = input.LA(3);

                        if ( (LA4_3==FILED_T) ) {
                            int LA4_5 = input.LA(4);

                            if ( (LA4_5==COMMA_T) ) {
                                alt4=1;
                            }


                        }


                    }
                    else if ( (LA4_1==COMMA_T) ) {
                        alt4=1;
                    }


                }


                switch (alt4) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:20:21: ( field | alias_statement ) comma_t !
            	    {
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:20:21: ( field | alias_statement )
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==FILED_T) ) {
            	        int LA3_1 = input.LA(2);

            	        if ( (LA3_1==COMMA_T) ) {
            	            alt3=1;
            	        }
            	        else if ( (LA3_1==AS_KEYWORD_T) ) {
            	            alt3=2;
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 3, 1, input);

            	            throw nvae;

            	        }
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 3, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:20:22: field
            	            {
            	            pushFollow(FOLLOW_field_in_select_statement93);
            	            field6=field();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, field6.getTree());

            	            }
            	            break;
            	        case 2 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:20:28: alias_statement
            	            {
            	            pushFollow(FOLLOW_alias_statement_in_select_statement95);
            	            alias_statement7=alias_statement();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, alias_statement7.getTree());

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_comma_t_in_select_statement98);
            	    comma_t8=comma_t();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:20:56: ( field | alias_statement )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==FILED_T) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==FROM_KEYWORD_T) ) {
                    alt5=1;
                }
                else if ( (LA5_1==AS_KEYWORD_T) ) {
                    alt5=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:20:57: field
                    {
                    pushFollow(FOLLOW_field_in_select_statement104);
                    field9=field();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, field9.getTree());

                    }
                    break;
                case 2 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:20:63: alias_statement
                    {
                    pushFollow(FOLLOW_alias_statement_in_select_statement106);
                    alias_statement10=alias_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, alias_statement10.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "select_statement"


    public static class from_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "from_statement"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:23:1: from_statement : from_keyword ^ ( ( field | alias_statement ) comma_t !)* ( field | alias_statement ) ;
    public final LogKVQueryParser.from_statement_return from_statement() throws RecognitionException {
        LogKVQueryParser.from_statement_return retval = new LogKVQueryParser.from_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.from_keyword_return from_keyword11 =null;

        LogKVQueryParser.field_return field12 =null;

        LogKVQueryParser.alias_statement_return alias_statement13 =null;

        LogKVQueryParser.comma_t_return comma_t14 =null;

        LogKVQueryParser.field_return field15 =null;

        LogKVQueryParser.alias_statement_return alias_statement16 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:24:3: ( from_keyword ^ ( ( field | alias_statement ) comma_t !)* ( field | alias_statement ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:25:4: from_keyword ^ ( ( field | alias_statement ) comma_t !)* ( field | alias_statement )
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_from_keyword_in_from_statement124);
            from_keyword11=from_keyword();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(from_keyword11.getTree(), root_0);

            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:25:18: ( ( field | alias_statement ) comma_t !)*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==FILED_T) ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1==AS_KEYWORD_T) ) {
                        int LA7_3 = input.LA(3);

                        if ( (LA7_3==FILED_T) ) {
                            int LA7_5 = input.LA(4);

                            if ( (LA7_5==COMMA_T) ) {
                                alt7=1;
                            }


                        }


                    }
                    else if ( (LA7_1==COMMA_T) ) {
                        alt7=1;
                    }


                }


                switch (alt7) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:25:19: ( field | alias_statement ) comma_t !
            	    {
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:25:19: ( field | alias_statement )
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==FILED_T) ) {
            	        int LA6_1 = input.LA(2);

            	        if ( (LA6_1==COMMA_T) ) {
            	            alt6=1;
            	        }
            	        else if ( (LA6_1==AS_KEYWORD_T) ) {
            	            alt6=2;
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 6, 1, input);

            	            throw nvae;

            	        }
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 6, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:25:20: field
            	            {
            	            pushFollow(FOLLOW_field_in_from_statement129);
            	            field12=field();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, field12.getTree());

            	            }
            	            break;
            	        case 2 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:25:26: alias_statement
            	            {
            	            pushFollow(FOLLOW_alias_statement_in_from_statement131);
            	            alias_statement13=alias_statement();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, alias_statement13.getTree());

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_comma_t_in_from_statement134);
            	    comma_t14=comma_t();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:25:54: ( field | alias_statement )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==FILED_T) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==EOF||(LA8_1 >= WHERE_KEYWORD_T && LA8_1 <= WITHIN_KEYWORD_T)) ) {
                    alt8=1;
                }
                else if ( (LA8_1==AS_KEYWORD_T) ) {
                    alt8=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:25:55: field
                    {
                    pushFollow(FOLLOW_field_in_from_statement140);
                    field15=field();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, field15.getTree());

                    }
                    break;
                case 2 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:25:61: alias_statement
                    {
                    pushFollow(FOLLOW_alias_statement_in_from_statement142);
                    alias_statement16=alias_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, alias_statement16.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "from_statement"


    public static class alias_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "alias_statement"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:28:1: alias_statement : field as_keyword ^ field ;
    public final LogKVQueryParser.alias_statement_return alias_statement() throws RecognitionException {
        LogKVQueryParser.alias_statement_return retval = new LogKVQueryParser.alias_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.field_return field17 =null;

        LogKVQueryParser.as_keyword_return as_keyword18 =null;

        LogKVQueryParser.field_return field19 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:29:3: ( field as_keyword ^ field )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:30:4: field as_keyword ^ field
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_field_in_alias_statement161);
            field17=field();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, field17.getTree());

            pushFollow(FOLLOW_as_keyword_in_alias_statement163);
            as_keyword18=as_keyword();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(as_keyword18.getTree(), root_0);

            pushFollow(FOLLOW_field_in_alias_statement166);
            field19=field();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, field19.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "alias_statement"


    public static class where_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "where_statement"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:33:1: where_statement : where_keyword ^ expression_list ;
    public final LogKVQueryParser.where_statement_return where_statement() throws RecognitionException {
        LogKVQueryParser.where_statement_return retval = new LogKVQueryParser.where_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.where_keyword_return where_keyword20 =null;

        LogKVQueryParser.expression_list_return expression_list21 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:34:3: ( where_keyword ^ expression_list )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:35:4: where_keyword ^ expression_list
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_where_keyword_in_where_statement182);
            where_keyword20=where_keyword();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(where_keyword20.getTree(), root_0);

            pushFollow(FOLLOW_expression_list_in_where_statement185);
            expression_list21=expression_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression_list21.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "where_statement"


    public static class expression_list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression_list"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:38:1: expression_list : ( not_final_compound_withlevel_expression | final_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_withlevel_expression | not_compound_withlevel_expression | compound_withlevel_expression | multi_compound_expression | relation ^)* ( not_final_compound_withlevel_expression | final_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_withlevel_expression | not_compound_withlevel_expression | compound_withlevel_expression | multi_compound_expression ) ;
    public final LogKVQueryParser.expression_list_return expression_list() throws RecognitionException {
        LogKVQueryParser.expression_list_return retval = new LogKVQueryParser.expression_list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.not_final_compound_withlevel_expression_return not_final_compound_withlevel_expression22 =null;

        LogKVQueryParser.final_compound_withlevel_expression_return final_compound_withlevel_expression23 =null;

        LogKVQueryParser.not_multi_compound_withlevel_expression_return not_multi_compound_withlevel_expression24 =null;

        LogKVQueryParser.multi_compound_withlevel_expression_return multi_compound_withlevel_expression25 =null;

        LogKVQueryParser.not_compound_withlevel_expression_return not_compound_withlevel_expression26 =null;

        LogKVQueryParser.compound_withlevel_expression_return compound_withlevel_expression27 =null;

        LogKVQueryParser.multi_compound_expression_return multi_compound_expression28 =null;

        LogKVQueryParser.relation_return relation29 =null;

        LogKVQueryParser.not_final_compound_withlevel_expression_return not_final_compound_withlevel_expression30 =null;

        LogKVQueryParser.final_compound_withlevel_expression_return final_compound_withlevel_expression31 =null;

        LogKVQueryParser.not_multi_compound_withlevel_expression_return not_multi_compound_withlevel_expression32 =null;

        LogKVQueryParser.multi_compound_withlevel_expression_return multi_compound_withlevel_expression33 =null;

        LogKVQueryParser.not_compound_withlevel_expression_return not_compound_withlevel_expression34 =null;

        LogKVQueryParser.compound_withlevel_expression_return compound_withlevel_expression35 =null;

        LogKVQueryParser.multi_compound_expression_return multi_compound_expression36 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:39:3: ( ( not_final_compound_withlevel_expression | final_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_withlevel_expression | not_compound_withlevel_expression | compound_withlevel_expression | multi_compound_expression | relation ^)* ( not_final_compound_withlevel_expression | final_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_withlevel_expression | not_compound_withlevel_expression | compound_withlevel_expression | multi_compound_expression ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:4: ( not_final_compound_withlevel_expression | final_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_withlevel_expression | not_compound_withlevel_expression | compound_withlevel_expression | multi_compound_expression | relation ^)* ( not_final_compound_withlevel_expression | final_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_withlevel_expression | not_compound_withlevel_expression | compound_withlevel_expression | multi_compound_expression )
            {
            root_0 = (CommonTree)adaptor.nil();


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:4: ( not_final_compound_withlevel_expression | final_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_withlevel_expression | not_compound_withlevel_expression | compound_withlevel_expression | multi_compound_expression | relation ^)*
            loop9:
            do {
                int alt9=9;
                alt9 = dfa9.predict(input);
                switch (alt9) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:5: not_final_compound_withlevel_expression
            	    {
            	    pushFollow(FOLLOW_not_final_compound_withlevel_expression_in_expression_list203);
            	    not_final_compound_withlevel_expression22=not_final_compound_withlevel_expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_final_compound_withlevel_expression22.getTree());

            	    }
            	    break;
            	case 2 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:45: final_compound_withlevel_expression
            	    {
            	    pushFollow(FOLLOW_final_compound_withlevel_expression_in_expression_list205);
            	    final_compound_withlevel_expression23=final_compound_withlevel_expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, final_compound_withlevel_expression23.getTree());

            	    }
            	    break;
            	case 3 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:81: not_multi_compound_withlevel_expression
            	    {
            	    pushFollow(FOLLOW_not_multi_compound_withlevel_expression_in_expression_list207);
            	    not_multi_compound_withlevel_expression24=not_multi_compound_withlevel_expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_multi_compound_withlevel_expression24.getTree());

            	    }
            	    break;
            	case 4 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:121: multi_compound_withlevel_expression
            	    {
            	    pushFollow(FOLLOW_multi_compound_withlevel_expression_in_expression_list209);
            	    multi_compound_withlevel_expression25=multi_compound_withlevel_expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_withlevel_expression25.getTree());

            	    }
            	    break;
            	case 5 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:157: not_compound_withlevel_expression
            	    {
            	    pushFollow(FOLLOW_not_compound_withlevel_expression_in_expression_list211);
            	    not_compound_withlevel_expression26=not_compound_withlevel_expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_compound_withlevel_expression26.getTree());

            	    }
            	    break;
            	case 6 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:191: compound_withlevel_expression
            	    {
            	    pushFollow(FOLLOW_compound_withlevel_expression_in_expression_list213);
            	    compound_withlevel_expression27=compound_withlevel_expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_withlevel_expression27.getTree());

            	    }
            	    break;
            	case 7 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:221: multi_compound_expression
            	    {
            	    pushFollow(FOLLOW_multi_compound_expression_in_expression_list215);
            	    multi_compound_expression28=multi_compound_expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_expression28.getTree());

            	    }
            	    break;
            	case 8 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:248: relation ^
            	    {
            	    pushFollow(FOLLOW_relation_in_expression_list218);
            	    relation29=relation();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(relation29.getTree(), root_0);

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:4: ( not_final_compound_withlevel_expression | final_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_withlevel_expression | not_compound_withlevel_expression | compound_withlevel_expression | multi_compound_expression )
            int alt10=7;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:5: not_final_compound_withlevel_expression
                    {
                    pushFollow(FOLLOW_not_final_compound_withlevel_expression_in_expression_list227);
                    not_final_compound_withlevel_expression30=not_final_compound_withlevel_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_final_compound_withlevel_expression30.getTree());

                    }
                    break;
                case 2 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:45: final_compound_withlevel_expression
                    {
                    pushFollow(FOLLOW_final_compound_withlevel_expression_in_expression_list229);
                    final_compound_withlevel_expression31=final_compound_withlevel_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, final_compound_withlevel_expression31.getTree());

                    }
                    break;
                case 3 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:81: not_multi_compound_withlevel_expression
                    {
                    pushFollow(FOLLOW_not_multi_compound_withlevel_expression_in_expression_list231);
                    not_multi_compound_withlevel_expression32=not_multi_compound_withlevel_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_multi_compound_withlevel_expression32.getTree());

                    }
                    break;
                case 4 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:121: multi_compound_withlevel_expression
                    {
                    pushFollow(FOLLOW_multi_compound_withlevel_expression_in_expression_list233);
                    multi_compound_withlevel_expression33=multi_compound_withlevel_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_withlevel_expression33.getTree());

                    }
                    break;
                case 5 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:157: not_compound_withlevel_expression
                    {
                    pushFollow(FOLLOW_not_compound_withlevel_expression_in_expression_list235);
                    not_compound_withlevel_expression34=not_compound_withlevel_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_compound_withlevel_expression34.getTree());

                    }
                    break;
                case 6 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:191: compound_withlevel_expression
                    {
                    pushFollow(FOLLOW_compound_withlevel_expression_in_expression_list237);
                    compound_withlevel_expression35=compound_withlevel_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_withlevel_expression35.getTree());

                    }
                    break;
                case 7 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:221: multi_compound_expression
                    {
                    pushFollow(FOLLOW_multi_compound_expression_in_expression_list239);
                    multi_compound_expression36=multi_compound_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_expression36.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression_list"


    public static class within_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "within_statement"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:45:1: within_statement : within_keyword ^ value ;
    public final LogKVQueryParser.within_statement_return within_statement() throws RecognitionException {
        LogKVQueryParser.within_statement_return retval = new LogKVQueryParser.within_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.within_keyword_return within_keyword37 =null;

        LogKVQueryParser.value_return value38 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:46:3: ( within_keyword ^ value )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:47:4: within_keyword ^ value
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_within_keyword_in_within_statement260);
            within_keyword37=within_keyword();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(within_keyword37.getTree(), root_0);

            pushFollow(FOLLOW_value_in_within_statement263);
            value38=value();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, value38.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "within_statement"


    public static class not_final_compound_withlevel_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "not_final_compound_withlevel_expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:50:1: not_final_compound_withlevel_expression : not_operator ^ final_compound_withlevel_expression ;
    public final LogKVQueryParser.not_final_compound_withlevel_expression_return not_final_compound_withlevel_expression() throws RecognitionException {
        LogKVQueryParser.not_final_compound_withlevel_expression_return retval = new LogKVQueryParser.not_final_compound_withlevel_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.not_operator_return not_operator39 =null;

        LogKVQueryParser.final_compound_withlevel_expression_return final_compound_withlevel_expression40 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:51:3: ( not_operator ^ final_compound_withlevel_expression )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:52:4: not_operator ^ final_compound_withlevel_expression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_not_operator_in_not_final_compound_withlevel_expression279);
            not_operator39=not_operator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(not_operator39.getTree(), root_0);

            pushFollow(FOLLOW_final_compound_withlevel_expression_in_not_final_compound_withlevel_expression282);
            final_compound_withlevel_expression40=final_compound_withlevel_expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, final_compound_withlevel_expression40.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "not_final_compound_withlevel_expression"


    public static class final_compound_withlevel_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "final_compound_withlevel_expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:55:1: final_compound_withlevel_expression : LEFT_BRACE ! ( ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression |) relation ^)* ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression ) RIGHT_BRACE !;
    public final LogKVQueryParser.final_compound_withlevel_expression_return final_compound_withlevel_expression() throws RecognitionException {
        LogKVQueryParser.final_compound_withlevel_expression_return retval = new LogKVQueryParser.final_compound_withlevel_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token LEFT_BRACE41=null;
        Token RIGHT_BRACE49=null;
        LogKVQueryParser.multi_compound_withlevel_expression_return multi_compound_withlevel_expression42 =null;

        LogKVQueryParser.not_multi_compound_withlevel_expression_return not_multi_compound_withlevel_expression43 =null;

        LogKVQueryParser.multi_compound_expression_return multi_compound_expression44 =null;

        LogKVQueryParser.relation_return relation45 =null;

        LogKVQueryParser.multi_compound_withlevel_expression_return multi_compound_withlevel_expression46 =null;

        LogKVQueryParser.not_multi_compound_withlevel_expression_return not_multi_compound_withlevel_expression47 =null;

        LogKVQueryParser.multi_compound_expression_return multi_compound_expression48 =null;


        CommonTree LEFT_BRACE41_tree=null;
        CommonTree RIGHT_BRACE49_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:56:3: ( LEFT_BRACE ! ( ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression |) relation ^)* ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression ) RIGHT_BRACE !)
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:57:4: LEFT_BRACE ! ( ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression |) relation ^)* ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression ) RIGHT_BRACE !
            {
            root_0 = (CommonTree)adaptor.nil();


            LEFT_BRACE41=(Token)match(input,LEFT_BRACE,FOLLOW_LEFT_BRACE_in_final_compound_withlevel_expression298); if (state.failed) return retval;

            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:4: ( ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression |) relation ^)*
            loop12:
            do {
                int alt12=2;
                alt12 = dfa12.predict(input);
                switch (alt12) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:5: ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression |) relation ^
            	    {
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:5: ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression |)
            	    int alt11=4;
            	    switch ( input.LA(1) ) {
            	    case LEFT_BRACE:
            	        {
            	        alt11=1;
            	        }
            	        break;
            	    case NOT_OPERATOR_T:
            	        {
            	        alt11=2;
            	        }
            	        break;
            	    case FILED_T:
            	        {
            	        alt11=3;
            	        }
            	        break;
            	    case AND_OPERATOR_T:
            	    case OR_OPERATOR_T:
            	        {
            	        alt11=4;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 11, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt11) {
            	        case 1 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:6: multi_compound_withlevel_expression
            	            {
            	            pushFollow(FOLLOW_multi_compound_withlevel_expression_in_final_compound_withlevel_expression306);
            	            multi_compound_withlevel_expression42=multi_compound_withlevel_expression();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_withlevel_expression42.getTree());

            	            }
            	            break;
            	        case 2 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:42: not_multi_compound_withlevel_expression
            	            {
            	            pushFollow(FOLLOW_not_multi_compound_withlevel_expression_in_final_compound_withlevel_expression308);
            	            not_multi_compound_withlevel_expression43=not_multi_compound_withlevel_expression();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, not_multi_compound_withlevel_expression43.getTree());

            	            }
            	            break;
            	        case 3 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:82: multi_compound_expression
            	            {
            	            pushFollow(FOLLOW_multi_compound_expression_in_final_compound_withlevel_expression310);
            	            multi_compound_expression44=multi_compound_expression();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_expression44.getTree());

            	            }
            	            break;
            	        case 4 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:108: 
            	            {
            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_relation_in_final_compound_withlevel_expression314);
            	    relation45=relation();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(relation45.getTree(), root_0);

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:59:4: ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression )
            int alt13=3;
            switch ( input.LA(1) ) {
            case LEFT_BRACE:
                {
                alt13=1;
                }
                break;
            case NOT_OPERATOR_T:
                {
                alt13=2;
                }
                break;
            case FILED_T:
                {
                alt13=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:59:5: multi_compound_withlevel_expression
                    {
                    pushFollow(FOLLOW_multi_compound_withlevel_expression_in_final_compound_withlevel_expression323);
                    multi_compound_withlevel_expression46=multi_compound_withlevel_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_withlevel_expression46.getTree());

                    }
                    break;
                case 2 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:59:41: not_multi_compound_withlevel_expression
                    {
                    pushFollow(FOLLOW_not_multi_compound_withlevel_expression_in_final_compound_withlevel_expression325);
                    not_multi_compound_withlevel_expression47=not_multi_compound_withlevel_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_multi_compound_withlevel_expression47.getTree());

                    }
                    break;
                case 3 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:59:81: multi_compound_expression
                    {
                    pushFollow(FOLLOW_multi_compound_expression_in_final_compound_withlevel_expression327);
                    multi_compound_expression48=multi_compound_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_expression48.getTree());

                    }
                    break;

            }


            RIGHT_BRACE49=(Token)match(input,RIGHT_BRACE,FOLLOW_RIGHT_BRACE_in_final_compound_withlevel_expression333); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "final_compound_withlevel_expression"


    public static class not_multi_compound_withlevel_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "not_multi_compound_withlevel_expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:63:1: not_multi_compound_withlevel_expression : not_operator ^ multi_compound_withlevel_expression ;
    public final LogKVQueryParser.not_multi_compound_withlevel_expression_return not_multi_compound_withlevel_expression() throws RecognitionException {
        LogKVQueryParser.not_multi_compound_withlevel_expression_return retval = new LogKVQueryParser.not_multi_compound_withlevel_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.not_operator_return not_operator50 =null;

        LogKVQueryParser.multi_compound_withlevel_expression_return multi_compound_withlevel_expression51 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:64:3: ( not_operator ^ multi_compound_withlevel_expression )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:65:4: not_operator ^ multi_compound_withlevel_expression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_not_operator_in_not_multi_compound_withlevel_expression351);
            not_operator50=not_operator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(not_operator50.getTree(), root_0);

            pushFollow(FOLLOW_multi_compound_withlevel_expression_in_not_multi_compound_withlevel_expression354);
            multi_compound_withlevel_expression51=multi_compound_withlevel_expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_withlevel_expression51.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "not_multi_compound_withlevel_expression"


    public static class multi_compound_withlevel_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multi_compound_withlevel_expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:68:1: multi_compound_withlevel_expression : LEFT_BRACE ! ( ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) relation ^)* ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) RIGHT_BRACE !;
    public final LogKVQueryParser.multi_compound_withlevel_expression_return multi_compound_withlevel_expression() throws RecognitionException {
        LogKVQueryParser.multi_compound_withlevel_expression_return retval = new LogKVQueryParser.multi_compound_withlevel_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token LEFT_BRACE52=null;
        Token RIGHT_BRACE60=null;
        LogKVQueryParser.compound_withlevel_expression_return compound_withlevel_expression53 =null;

        LogKVQueryParser.multi_compound_expression_return multi_compound_expression54 =null;

        LogKVQueryParser.not_compound_withlevel_expression_return not_compound_withlevel_expression55 =null;

        LogKVQueryParser.relation_return relation56 =null;

        LogKVQueryParser.compound_withlevel_expression_return compound_withlevel_expression57 =null;

        LogKVQueryParser.multi_compound_expression_return multi_compound_expression58 =null;

        LogKVQueryParser.not_compound_withlevel_expression_return not_compound_withlevel_expression59 =null;


        CommonTree LEFT_BRACE52_tree=null;
        CommonTree RIGHT_BRACE60_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:69:3: ( LEFT_BRACE ! ( ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) relation ^)* ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) RIGHT_BRACE !)
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:4: LEFT_BRACE ! ( ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) relation ^)* ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) RIGHT_BRACE !
            {
            root_0 = (CommonTree)adaptor.nil();


            LEFT_BRACE52=(Token)match(input,LEFT_BRACE,FOLLOW_LEFT_BRACE_in_multi_compound_withlevel_expression371); if (state.failed) return retval;

            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:16: ( ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) relation ^)*
            loop15:
            do {
                int alt15=2;
                alt15 = dfa15.predict(input);
                switch (alt15) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:17: ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) relation ^
            	    {
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:17: ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression )
            	    int alt14=3;
            	    switch ( input.LA(1) ) {
            	    case LEFT_BRACE:
            	        {
            	        alt14=1;
            	        }
            	        break;
            	    case FILED_T:
            	        {
            	        alt14=2;
            	        }
            	        break;
            	    case NOT_OPERATOR_T:
            	        {
            	        alt14=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 14, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt14) {
            	        case 1 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:18: compound_withlevel_expression
            	            {
            	            pushFollow(FOLLOW_compound_withlevel_expression_in_multi_compound_withlevel_expression376);
            	            compound_withlevel_expression53=compound_withlevel_expression();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_withlevel_expression53.getTree());

            	            }
            	            break;
            	        case 2 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:48: multi_compound_expression
            	            {
            	            pushFollow(FOLLOW_multi_compound_expression_in_multi_compound_withlevel_expression378);
            	            multi_compound_expression54=multi_compound_expression();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_expression54.getTree());

            	            }
            	            break;
            	        case 3 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:74: not_compound_withlevel_expression
            	            {
            	            pushFollow(FOLLOW_not_compound_withlevel_expression_in_multi_compound_withlevel_expression380);
            	            not_compound_withlevel_expression55=not_compound_withlevel_expression();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, not_compound_withlevel_expression55.getTree());

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_relation_in_multi_compound_withlevel_expression383);
            	    relation56=relation();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(relation56.getTree(), root_0);

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:71:4: ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression )
            int alt16=3;
            switch ( input.LA(1) ) {
            case LEFT_BRACE:
                {
                alt16=1;
                }
                break;
            case FILED_T:
                {
                alt16=2;
                }
                break;
            case NOT_OPERATOR_T:
                {
                alt16=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }

            switch (alt16) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:71:5: compound_withlevel_expression
                    {
                    pushFollow(FOLLOW_compound_withlevel_expression_in_multi_compound_withlevel_expression393);
                    compound_withlevel_expression57=compound_withlevel_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_withlevel_expression57.getTree());

                    }
                    break;
                case 2 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:71:35: multi_compound_expression
                    {
                    pushFollow(FOLLOW_multi_compound_expression_in_multi_compound_withlevel_expression395);
                    multi_compound_expression58=multi_compound_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_expression58.getTree());

                    }
                    break;
                case 3 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:71:61: not_compound_withlevel_expression
                    {
                    pushFollow(FOLLOW_not_compound_withlevel_expression_in_multi_compound_withlevel_expression397);
                    not_compound_withlevel_expression59=not_compound_withlevel_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_compound_withlevel_expression59.getTree());

                    }
                    break;

            }


            RIGHT_BRACE60=(Token)match(input,RIGHT_BRACE,FOLLOW_RIGHT_BRACE_in_multi_compound_withlevel_expression400); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multi_compound_withlevel_expression"


    public static class not_compound_withlevel_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "not_compound_withlevel_expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:74:1: not_compound_withlevel_expression : not_operator ^ compound_withlevel_expression ;
    public final LogKVQueryParser.not_compound_withlevel_expression_return not_compound_withlevel_expression() throws RecognitionException {
        LogKVQueryParser.not_compound_withlevel_expression_return retval = new LogKVQueryParser.not_compound_withlevel_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.not_operator_return not_operator61 =null;

        LogKVQueryParser.compound_withlevel_expression_return compound_withlevel_expression62 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:75:3: ( not_operator ^ compound_withlevel_expression )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:75:5: not_operator ^ compound_withlevel_expression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_not_operator_in_not_compound_withlevel_expression415);
            not_operator61=not_operator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(not_operator61.getTree(), root_0);

            pushFollow(FOLLOW_compound_withlevel_expression_in_not_compound_withlevel_expression418);
            compound_withlevel_expression62=compound_withlevel_expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_withlevel_expression62.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "not_compound_withlevel_expression"


    public static class compound_withlevel_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "compound_withlevel_expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:78:1: compound_withlevel_expression : LEFT_BRACE ! multi_compound_expression relation ^ multi_compound_expression RIGHT_BRACE !;
    public final LogKVQueryParser.compound_withlevel_expression_return compound_withlevel_expression() throws RecognitionException {
        LogKVQueryParser.compound_withlevel_expression_return retval = new LogKVQueryParser.compound_withlevel_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token LEFT_BRACE63=null;
        Token RIGHT_BRACE67=null;
        LogKVQueryParser.multi_compound_expression_return multi_compound_expression64 =null;

        LogKVQueryParser.relation_return relation65 =null;

        LogKVQueryParser.multi_compound_expression_return multi_compound_expression66 =null;


        CommonTree LEFT_BRACE63_tree=null;
        CommonTree RIGHT_BRACE67_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:79:3: ( LEFT_BRACE ! multi_compound_expression relation ^ multi_compound_expression RIGHT_BRACE !)
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:79:5: LEFT_BRACE ! multi_compound_expression relation ^ multi_compound_expression RIGHT_BRACE !
            {
            root_0 = (CommonTree)adaptor.nil();


            LEFT_BRACE63=(Token)match(input,LEFT_BRACE,FOLLOW_LEFT_BRACE_in_compound_withlevel_expression432); if (state.failed) return retval;

            pushFollow(FOLLOW_multi_compound_expression_in_compound_withlevel_expression435);
            multi_compound_expression64=multi_compound_expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_expression64.getTree());

            pushFollow(FOLLOW_relation_in_compound_withlevel_expression437);
            relation65=relation();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(relation65.getTree(), root_0);

            pushFollow(FOLLOW_multi_compound_expression_in_compound_withlevel_expression440);
            multi_compound_expression66=multi_compound_expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, multi_compound_expression66.getTree());

            RIGHT_BRACE67=(Token)match(input,RIGHT_BRACE,FOLLOW_RIGHT_BRACE_in_compound_withlevel_expression442); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "compound_withlevel_expression"


    public static class multi_compound_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multi_compound_expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:83:1: multi_compound_expression : ( ( expression | compound_expression ) relation ^)* ( expression | compound_expression ) ;
    public final LogKVQueryParser.multi_compound_expression_return multi_compound_expression() throws RecognitionException {
        LogKVQueryParser.multi_compound_expression_return retval = new LogKVQueryParser.multi_compound_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.expression_return expression68 =null;

        LogKVQueryParser.compound_expression_return compound_expression69 =null;

        LogKVQueryParser.relation_return relation70 =null;

        LogKVQueryParser.expression_return expression71 =null;

        LogKVQueryParser.compound_expression_return compound_expression72 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:84:3: ( ( ( expression | compound_expression ) relation ^)* ( expression | compound_expression ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:4: ( ( expression | compound_expression ) relation ^)* ( expression | compound_expression )
            {
            root_0 = (CommonTree)adaptor.nil();


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:4: ( ( expression | compound_expression ) relation ^)*
            loop18:
            do {
                int alt18=2;
                alt18 = dfa18.predict(input);
                switch (alt18) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:5: ( expression | compound_expression ) relation ^
            	    {
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:5: ( expression | compound_expression )
            	    int alt17=2;
            	    alt17 = dfa17.predict(input);
            	    switch (alt17) {
            	        case 1 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:6: expression
            	            {
            	            pushFollow(FOLLOW_expression_in_multi_compound_expression463);
            	            expression68=expression();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression68.getTree());

            	            }
            	            break;
            	        case 2 :
            	            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:17: compound_expression
            	            {
            	            pushFollow(FOLLOW_compound_expression_in_multi_compound_expression465);
            	            compound_expression69=compound_expression();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_expression69.getTree());

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_relation_in_multi_compound_expression468);
            	    relation70=relation();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(relation70.getTree(), root_0);

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:50: ( expression | compound_expression )
            int alt19=2;
            alt19 = dfa19.predict(input);
            switch (alt19) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:51: expression
                    {
                    pushFollow(FOLLOW_expression_in_multi_compound_expression474);
                    expression71=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression71.getTree());

                    }
                    break;
                case 2 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:62: compound_expression
                    {
                    pushFollow(FOLLOW_compound_expression_in_multi_compound_expression476);
                    compound_expression72=compound_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_expression72.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multi_compound_expression"


    public static class compound_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "compound_expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:88:1: compound_expression : expression relation expression -> ^( relation ( expression )+ ) ;
    public final LogKVQueryParser.compound_expression_return compound_expression() throws RecognitionException {
        LogKVQueryParser.compound_expression_return retval = new LogKVQueryParser.compound_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.expression_return expression73 =null;

        LogKVQueryParser.relation_return relation74 =null;

        LogKVQueryParser.expression_return expression75 =null;


        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_relation=new RewriteRuleSubtreeStream(adaptor,"rule relation");
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:89:3: ( expression relation expression -> ^( relation ( expression )+ ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:90:4: expression relation expression
            {
            pushFollow(FOLLOW_expression_in_compound_expression495);
            expression73=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression73.getTree());

            pushFollow(FOLLOW_relation_in_compound_expression497);
            relation74=relation();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_relation.add(relation74.getTree());

            pushFollow(FOLLOW_expression_in_compound_expression499);
            expression75=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression75.getTree());

            // AST REWRITE
            // elements: expression, relation
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 91:4: -> ^( relation ( expression )+ )
            {
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:91:7: ^( relation ( expression )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_relation.nextNode(), root_1);

                if ( !(stream_expression.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_expression.hasNext() ) {
                    adaptor.addChild(root_1, stream_expression.nextTree());

                }
                stream_expression.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "compound_expression"


    public static class relation_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "relation"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:94:1: relation : ( and_operator | or_operator );
    public final LogKVQueryParser.relation_return relation() throws RecognitionException {
        LogKVQueryParser.relation_return retval = new LogKVQueryParser.relation_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        LogKVQueryParser.and_operator_return and_operator76 =null;

        LogKVQueryParser.or_operator_return or_operator77 =null;



        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:94:10: ( and_operator | or_operator )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==AND_OPERATOR_T) ) {
                alt20=1;
            }
            else if ( (LA20_0==OR_OPERATOR_T) ) {
                alt20=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:94:12: and_operator
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_and_operator_in_relation522);
                    and_operator76=and_operator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, and_operator76.getTree());

                    }
                    break;
                case 2 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:94:25: or_operator
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_or_operator_in_relation524);
                    or_operator77=or_operator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, or_operator77.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "relation"


    public static class expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:96:1: expression : ( field binary_operator value -> ^( binary_operator field value ) |x1= FILED_T binary_operator x2= FILED_T -> ^( binary_operator $x1 $x2) | field between_operator value and_operator value -> ^( between_operator field between_operator ( value )+ ) | in_expression | not_in_expression );
    public final LogKVQueryParser.expression_return expression() throws RecognitionException {
        LogKVQueryParser.expression_return retval = new LogKVQueryParser.expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token x1=null;
        Token x2=null;
        LogKVQueryParser.field_return field78 =null;

        LogKVQueryParser.binary_operator_return binary_operator79 =null;

        LogKVQueryParser.value_return value80 =null;

        LogKVQueryParser.binary_operator_return binary_operator81 =null;

        LogKVQueryParser.field_return field82 =null;

        LogKVQueryParser.between_operator_return between_operator83 =null;

        LogKVQueryParser.value_return value84 =null;

        LogKVQueryParser.and_operator_return and_operator85 =null;

        LogKVQueryParser.value_return value86 =null;

        LogKVQueryParser.in_expression_return in_expression87 =null;

        LogKVQueryParser.not_in_expression_return not_in_expression88 =null;


        CommonTree x1_tree=null;
        CommonTree x2_tree=null;
        RewriteRuleTokenStream stream_FILED_T=new RewriteRuleTokenStream(adaptor,"token FILED_T");
        RewriteRuleSubtreeStream stream_field=new RewriteRuleSubtreeStream(adaptor,"rule field");
        RewriteRuleSubtreeStream stream_between_operator=new RewriteRuleSubtreeStream(adaptor,"rule between_operator");
        RewriteRuleSubtreeStream stream_and_operator=new RewriteRuleSubtreeStream(adaptor,"rule and_operator");
        RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");
        RewriteRuleSubtreeStream stream_binary_operator=new RewriteRuleSubtreeStream(adaptor,"rule binary_operator");
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:96:12: ( field binary_operator value -> ^( binary_operator field value ) |x1= FILED_T binary_operator x2= FILED_T -> ^( binary_operator $x1 $x2) | field between_operator value and_operator value -> ^( between_operator field between_operator ( value )+ ) | in_expression | not_in_expression )
            int alt21=5;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==FILED_T) ) {
                switch ( input.LA(2) ) {
                case BINARY_OPERATOR_T:
                    {
                    int LA21_2 = input.LA(3);

                    if ( (LA21_2==VALUE_T) ) {
                        alt21=1;
                    }
                    else if ( (LA21_2==FILED_T) ) {
                        alt21=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 21, 2, input);

                        throw nvae;

                    }
                    }
                    break;
                case BETWEEN_OPERATOR_T:
                    {
                    alt21=3;
                    }
                    break;
                case IN_OPERATOR_T:
                    {
                    alt21=4;
                    }
                    break;
                case NOT_OPERATOR_T:
                    {
                    alt21=5;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:96:14: field binary_operator value
                    {
                    pushFollow(FOLLOW_field_in_expression532);
                    field78=field();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_field.add(field78.getTree());

                    pushFollow(FOLLOW_binary_operator_in_expression534);
                    binary_operator79=binary_operator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binary_operator.add(binary_operator79.getTree());

                    pushFollow(FOLLOW_value_in_expression536);
                    value80=value();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_value.add(value80.getTree());

                    // AST REWRITE
                    // elements: field, value, binary_operator
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 97:4: -> ^( binary_operator field value )
                    {
                        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:97:7: ^( binary_operator field value )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_binary_operator.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_field.nextTree());

                        adaptor.addChild(root_1, stream_value.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:99:4: x1= FILED_T binary_operator x2= FILED_T
                    {
                    x1=(Token)match(input,FILED_T,FOLLOW_FILED_T_in_expression560); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_FILED_T.add(x1);


                    pushFollow(FOLLOW_binary_operator_in_expression562);
                    binary_operator81=binary_operator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binary_operator.add(binary_operator81.getTree());

                    x2=(Token)match(input,FILED_T,FOLLOW_FILED_T_in_expression566); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_FILED_T.add(x2);


                    // AST REWRITE
                    // elements: binary_operator, x2, x1
                    // token labels: x2, x1
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_x2=new RewriteRuleTokenStream(adaptor,"token x2",x2);
                    RewriteRuleTokenStream stream_x1=new RewriteRuleTokenStream(adaptor,"token x1",x1);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 100:4: -> ^( binary_operator $x1 $x2)
                    {
                        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:100:7: ^( binary_operator $x1 $x2)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_binary_operator.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_x1.nextNode());

                        adaptor.addChild(root_1, stream_x2.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:102:4: field between_operator value and_operator value
                    {
                    pushFollow(FOLLOW_field_in_expression590);
                    field82=field();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_field.add(field82.getTree());

                    pushFollow(FOLLOW_between_operator_in_expression592);
                    between_operator83=between_operator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_between_operator.add(between_operator83.getTree());

                    pushFollow(FOLLOW_value_in_expression594);
                    value84=value();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_value.add(value84.getTree());

                    pushFollow(FOLLOW_and_operator_in_expression596);
                    and_operator85=and_operator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_and_operator.add(and_operator85.getTree());

                    pushFollow(FOLLOW_value_in_expression598);
                    value86=value();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_value.add(value86.getTree());

                    // AST REWRITE
                    // elements: value, between_operator, field, between_operator
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 103:4: -> ^( between_operator field between_operator ( value )+ )
                    {
                        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:103:7: ^( between_operator field between_operator ( value )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_between_operator.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_field.nextTree());

                        adaptor.addChild(root_1, stream_between_operator.nextTree());

                        if ( !(stream_value.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_value.hasNext() ) {
                            adaptor.addChild(root_1, stream_value.nextTree());

                        }
                        stream_value.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:105:4: in_expression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_in_expression_in_expression623);
                    in_expression87=in_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, in_expression87.getTree());

                    }
                    break;
                case 5 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:107:4: not_in_expression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_not_in_expression_in_expression632);
                    not_in_expression88=not_in_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_in_expression88.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression"


    public static class not_in_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "not_in_expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:110:1: not_in_expression : field not_operator in_operator LEFT_BRACE ( value comma_t )* value RIGHT_BRACE -> ^( not_operator ^( in_operator field ( value )+ ) ) ;
    public final LogKVQueryParser.not_in_expression_return not_in_expression() throws RecognitionException {
        LogKVQueryParser.not_in_expression_return retval = new LogKVQueryParser.not_in_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token LEFT_BRACE92=null;
        Token RIGHT_BRACE96=null;
        LogKVQueryParser.field_return field89 =null;

        LogKVQueryParser.not_operator_return not_operator90 =null;

        LogKVQueryParser.in_operator_return in_operator91 =null;

        LogKVQueryParser.value_return value93 =null;

        LogKVQueryParser.comma_t_return comma_t94 =null;

        LogKVQueryParser.value_return value95 =null;


        CommonTree LEFT_BRACE92_tree=null;
        CommonTree RIGHT_BRACE96_tree=null;
        RewriteRuleTokenStream stream_RIGHT_BRACE=new RewriteRuleTokenStream(adaptor,"token RIGHT_BRACE");
        RewriteRuleTokenStream stream_LEFT_BRACE=new RewriteRuleTokenStream(adaptor,"token LEFT_BRACE");
        RewriteRuleSubtreeStream stream_field=new RewriteRuleSubtreeStream(adaptor,"rule field");
        RewriteRuleSubtreeStream stream_not_operator=new RewriteRuleSubtreeStream(adaptor,"rule not_operator");
        RewriteRuleSubtreeStream stream_comma_t=new RewriteRuleSubtreeStream(adaptor,"rule comma_t");
        RewriteRuleSubtreeStream stream_in_operator=new RewriteRuleSubtreeStream(adaptor,"rule in_operator");
        RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:111:3: ( field not_operator in_operator LEFT_BRACE ( value comma_t )* value RIGHT_BRACE -> ^( not_operator ^( in_operator field ( value )+ ) ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:111:5: field not_operator in_operator LEFT_BRACE ( value comma_t )* value RIGHT_BRACE
            {
            pushFollow(FOLLOW_field_in_not_in_expression646);
            field89=field();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_field.add(field89.getTree());

            pushFollow(FOLLOW_not_operator_in_not_in_expression648);
            not_operator90=not_operator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_not_operator.add(not_operator90.getTree());

            pushFollow(FOLLOW_in_operator_in_not_in_expression650);
            in_operator91=in_operator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_in_operator.add(in_operator91.getTree());

            LEFT_BRACE92=(Token)match(input,LEFT_BRACE,FOLLOW_LEFT_BRACE_in_not_in_expression652); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LEFT_BRACE.add(LEFT_BRACE92);


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:111:47: ( value comma_t )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==VALUE_T) ) {
                    int LA22_1 = input.LA(2);

                    if ( (LA22_1==COMMA_T) ) {
                        alt22=1;
                    }


                }


                switch (alt22) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:111:48: value comma_t
            	    {
            	    pushFollow(FOLLOW_value_in_not_in_expression655);
            	    value93=value();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_value.add(value93.getTree());

            	    pushFollow(FOLLOW_comma_t_in_not_in_expression657);
            	    comma_t94=comma_t();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_comma_t.add(comma_t94.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            pushFollow(FOLLOW_value_in_not_in_expression661);
            value95=value();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_value.add(value95.getTree());

            RIGHT_BRACE96=(Token)match(input,RIGHT_BRACE,FOLLOW_RIGHT_BRACE_in_not_in_expression663); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RIGHT_BRACE.add(RIGHT_BRACE96);


            // AST REWRITE
            // elements: in_operator, not_operator, field, value
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 112:4: -> ^( not_operator ^( in_operator field ( value )+ ) )
            {
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:112:7: ^( not_operator ^( in_operator field ( value )+ ) )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_not_operator.nextNode(), root_1);

                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:112:22: ^( in_operator field ( value )+ )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(stream_in_operator.nextNode(), root_2);

                adaptor.addChild(root_2, stream_field.nextTree());

                if ( !(stream_value.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_value.hasNext() ) {
                    adaptor.addChild(root_2, stream_value.nextTree());

                }
                stream_value.reset();

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "not_in_expression"


    public static class in_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "in_expression"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:114:1: in_expression : field in_operator LEFT_BRACE ( value comma_t )* value RIGHT_BRACE -> ^( in_operator field ( value )+ ) ;
    public final LogKVQueryParser.in_expression_return in_expression() throws RecognitionException {
        LogKVQueryParser.in_expression_return retval = new LogKVQueryParser.in_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token LEFT_BRACE99=null;
        Token RIGHT_BRACE103=null;
        LogKVQueryParser.field_return field97 =null;

        LogKVQueryParser.in_operator_return in_operator98 =null;

        LogKVQueryParser.value_return value100 =null;

        LogKVQueryParser.comma_t_return comma_t101 =null;

        LogKVQueryParser.value_return value102 =null;


        CommonTree LEFT_BRACE99_tree=null;
        CommonTree RIGHT_BRACE103_tree=null;
        RewriteRuleTokenStream stream_RIGHT_BRACE=new RewriteRuleTokenStream(adaptor,"token RIGHT_BRACE");
        RewriteRuleTokenStream stream_LEFT_BRACE=new RewriteRuleTokenStream(adaptor,"token LEFT_BRACE");
        RewriteRuleSubtreeStream stream_field=new RewriteRuleSubtreeStream(adaptor,"rule field");
        RewriteRuleSubtreeStream stream_comma_t=new RewriteRuleSubtreeStream(adaptor,"rule comma_t");
        RewriteRuleSubtreeStream stream_in_operator=new RewriteRuleSubtreeStream(adaptor,"rule in_operator");
        RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:114:15: ( field in_operator LEFT_BRACE ( value comma_t )* value RIGHT_BRACE -> ^( in_operator field ( value )+ ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:114:17: field in_operator LEFT_BRACE ( value comma_t )* value RIGHT_BRACE
            {
            pushFollow(FOLLOW_field_in_in_expression691);
            field97=field();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_field.add(field97.getTree());

            pushFollow(FOLLOW_in_operator_in_in_expression693);
            in_operator98=in_operator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_in_operator.add(in_operator98.getTree());

            LEFT_BRACE99=(Token)match(input,LEFT_BRACE,FOLLOW_LEFT_BRACE_in_in_expression695); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LEFT_BRACE.add(LEFT_BRACE99);


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:114:46: ( value comma_t )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==VALUE_T) ) {
                    int LA23_1 = input.LA(2);

                    if ( (LA23_1==COMMA_T) ) {
                        alt23=1;
                    }


                }


                switch (alt23) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:114:47: value comma_t
            	    {
            	    pushFollow(FOLLOW_value_in_in_expression698);
            	    value100=value();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_value.add(value100.getTree());

            	    pushFollow(FOLLOW_comma_t_in_in_expression700);
            	    comma_t101=comma_t();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_comma_t.add(comma_t101.getTree());

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            pushFollow(FOLLOW_value_in_in_expression704);
            value102=value();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_value.add(value102.getTree());

            RIGHT_BRACE103=(Token)match(input,RIGHT_BRACE,FOLLOW_RIGHT_BRACE_in_in_expression706); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RIGHT_BRACE.add(RIGHT_BRACE103);


            // AST REWRITE
            // elements: value, in_operator, field
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 115:4: -> ^( in_operator field ( value )+ )
            {
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:115:7: ^( in_operator field ( value )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_in_operator.nextNode(), root_1);

                adaptor.addChild(root_1, stream_field.nextTree());

                if ( !(stream_value.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_value.hasNext() ) {
                    adaptor.addChild(root_1, stream_value.nextTree());

                }
                stream_value.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "in_expression"


    public static class select_keyword_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "select_keyword"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:118:1: select_keyword : SELECT_KEYWORD_T ;
    public final LogKVQueryParser.select_keyword_return select_keyword() throws RecognitionException {
        LogKVQueryParser.select_keyword_return retval = new LogKVQueryParser.select_keyword_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token SELECT_KEYWORD_T104=null;

        CommonTree SELECT_KEYWORD_T104_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:118:16: ( SELECT_KEYWORD_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:118:18: SELECT_KEYWORD_T
            {
            root_0 = (CommonTree)adaptor.nil();


            SELECT_KEYWORD_T104=(Token)match(input,SELECT_KEYWORD_T,FOLLOW_SELECT_KEYWORD_T_in_select_keyword734); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SELECT_KEYWORD_T104_tree = 
            (CommonTree)adaptor.create(SELECT_KEYWORD_T104)
            ;
            adaptor.addChild(root_0, SELECT_KEYWORD_T104_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "select_keyword"


    public static class from_keyword_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "from_keyword"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:119:1: from_keyword : FROM_KEYWORD_T ;
    public final LogKVQueryParser.from_keyword_return from_keyword() throws RecognitionException {
        LogKVQueryParser.from_keyword_return retval = new LogKVQueryParser.from_keyword_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token FROM_KEYWORD_T105=null;

        CommonTree FROM_KEYWORD_T105_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:119:14: ( FROM_KEYWORD_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:119:16: FROM_KEYWORD_T
            {
            root_0 = (CommonTree)adaptor.nil();


            FROM_KEYWORD_T105=(Token)match(input,FROM_KEYWORD_T,FOLLOW_FROM_KEYWORD_T_in_from_keyword741); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FROM_KEYWORD_T105_tree = 
            (CommonTree)adaptor.create(FROM_KEYWORD_T105)
            ;
            adaptor.addChild(root_0, FROM_KEYWORD_T105_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "from_keyword"


    public static class where_keyword_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "where_keyword"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:120:1: where_keyword : WHERE_KEYWORD_T ;
    public final LogKVQueryParser.where_keyword_return where_keyword() throws RecognitionException {
        LogKVQueryParser.where_keyword_return retval = new LogKVQueryParser.where_keyword_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token WHERE_KEYWORD_T106=null;

        CommonTree WHERE_KEYWORD_T106_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:120:15: ( WHERE_KEYWORD_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:120:17: WHERE_KEYWORD_T
            {
            root_0 = (CommonTree)adaptor.nil();


            WHERE_KEYWORD_T106=(Token)match(input,WHERE_KEYWORD_T,FOLLOW_WHERE_KEYWORD_T_in_where_keyword748); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            WHERE_KEYWORD_T106_tree = 
            (CommonTree)adaptor.create(WHERE_KEYWORD_T106)
            ;
            adaptor.addChild(root_0, WHERE_KEYWORD_T106_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "where_keyword"


    public static class within_keyword_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "within_keyword"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:121:1: within_keyword : WITHIN_KEYWORD_T ;
    public final LogKVQueryParser.within_keyword_return within_keyword() throws RecognitionException {
        LogKVQueryParser.within_keyword_return retval = new LogKVQueryParser.within_keyword_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token WITHIN_KEYWORD_T107=null;

        CommonTree WITHIN_KEYWORD_T107_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:121:16: ( WITHIN_KEYWORD_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:121:18: WITHIN_KEYWORD_T
            {
            root_0 = (CommonTree)adaptor.nil();


            WITHIN_KEYWORD_T107=(Token)match(input,WITHIN_KEYWORD_T,FOLLOW_WITHIN_KEYWORD_T_in_within_keyword755); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            WITHIN_KEYWORD_T107_tree = 
            (CommonTree)adaptor.create(WITHIN_KEYWORD_T107)
            ;
            adaptor.addChild(root_0, WITHIN_KEYWORD_T107_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "within_keyword"


    public static class as_keyword_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "as_keyword"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:122:1: as_keyword : AS_KEYWORD_T ;
    public final LogKVQueryParser.as_keyword_return as_keyword() throws RecognitionException {
        LogKVQueryParser.as_keyword_return retval = new LogKVQueryParser.as_keyword_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token AS_KEYWORD_T108=null;

        CommonTree AS_KEYWORD_T108_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:122:12: ( AS_KEYWORD_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:122:14: AS_KEYWORD_T
            {
            root_0 = (CommonTree)adaptor.nil();


            AS_KEYWORD_T108=(Token)match(input,AS_KEYWORD_T,FOLLOW_AS_KEYWORD_T_in_as_keyword762); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            AS_KEYWORD_T108_tree = 
            (CommonTree)adaptor.create(AS_KEYWORD_T108)
            ;
            adaptor.addChild(root_0, AS_KEYWORD_T108_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "as_keyword"


    public static class field_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "field"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:123:1: field : FILED_T ;
    public final LogKVQueryParser.field_return field() throws RecognitionException {
        LogKVQueryParser.field_return retval = new LogKVQueryParser.field_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token FILED_T109=null;

        CommonTree FILED_T109_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:123:8: ( FILED_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:123:10: FILED_T
            {
            root_0 = (CommonTree)adaptor.nil();


            FILED_T109=(Token)match(input,FILED_T,FOLLOW_FILED_T_in_field770); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FILED_T109_tree = 
            (CommonTree)adaptor.create(FILED_T109)
            ;
            adaptor.addChild(root_0, FILED_T109_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "field"


    public static class value_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "value"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:124:1: value : VALUE_T ;
    public final LogKVQueryParser.value_return value() throws RecognitionException {
        LogKVQueryParser.value_return retval = new LogKVQueryParser.value_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token VALUE_T110=null;

        CommonTree VALUE_T110_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:124:8: ( VALUE_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:124:10: VALUE_T
            {
            root_0 = (CommonTree)adaptor.nil();


            VALUE_T110=(Token)match(input,VALUE_T,FOLLOW_VALUE_T_in_value778); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            VALUE_T110_tree = 
            (CommonTree)adaptor.create(VALUE_T110)
            ;
            adaptor.addChild(root_0, VALUE_T110_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "value"


    public static class comma_t_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comma_t"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:125:1: comma_t : COMMA_T ;
    public final LogKVQueryParser.comma_t_return comma_t() throws RecognitionException {
        LogKVQueryParser.comma_t_return retval = new LogKVQueryParser.comma_t_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token COMMA_T111=null;

        CommonTree COMMA_T111_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:125:10: ( COMMA_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:125:12: COMMA_T
            {
            root_0 = (CommonTree)adaptor.nil();


            COMMA_T111=(Token)match(input,COMMA_T,FOLLOW_COMMA_T_in_comma_t786); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA_T111_tree = 
            (CommonTree)adaptor.create(COMMA_T111)
            ;
            adaptor.addChild(root_0, COMMA_T111_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "comma_t"


    public static class binary_operator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "binary_operator"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:126:1: binary_operator : BINARY_OPERATOR_T ;
    public final LogKVQueryParser.binary_operator_return binary_operator() throws RecognitionException {
        LogKVQueryParser.binary_operator_return retval = new LogKVQueryParser.binary_operator_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token BINARY_OPERATOR_T112=null;

        CommonTree BINARY_OPERATOR_T112_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:126:17: ( BINARY_OPERATOR_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:126:19: BINARY_OPERATOR_T
            {
            root_0 = (CommonTree)adaptor.nil();


            BINARY_OPERATOR_T112=(Token)match(input,BINARY_OPERATOR_T,FOLLOW_BINARY_OPERATOR_T_in_binary_operator793); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BINARY_OPERATOR_T112_tree = 
            (CommonTree)adaptor.create(BINARY_OPERATOR_T112)
            ;
            adaptor.addChild(root_0, BINARY_OPERATOR_T112_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "binary_operator"


    public static class and_operator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "and_operator"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:127:1: and_operator : AND_OPERATOR_T ;
    public final LogKVQueryParser.and_operator_return and_operator() throws RecognitionException {
        LogKVQueryParser.and_operator_return retval = new LogKVQueryParser.and_operator_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token AND_OPERATOR_T113=null;

        CommonTree AND_OPERATOR_T113_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:127:14: ( AND_OPERATOR_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:127:16: AND_OPERATOR_T
            {
            root_0 = (CommonTree)adaptor.nil();


            AND_OPERATOR_T113=(Token)match(input,AND_OPERATOR_T,FOLLOW_AND_OPERATOR_T_in_and_operator800); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            AND_OPERATOR_T113_tree = 
            (CommonTree)adaptor.create(AND_OPERATOR_T113)
            ;
            adaptor.addChild(root_0, AND_OPERATOR_T113_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "and_operator"


    public static class or_operator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "or_operator"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:128:1: or_operator : OR_OPERATOR_T ;
    public final LogKVQueryParser.or_operator_return or_operator() throws RecognitionException {
        LogKVQueryParser.or_operator_return retval = new LogKVQueryParser.or_operator_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token OR_OPERATOR_T114=null;

        CommonTree OR_OPERATOR_T114_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:128:13: ( OR_OPERATOR_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:128:15: OR_OPERATOR_T
            {
            root_0 = (CommonTree)adaptor.nil();


            OR_OPERATOR_T114=(Token)match(input,OR_OPERATOR_T,FOLLOW_OR_OPERATOR_T_in_or_operator807); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            OR_OPERATOR_T114_tree = 
            (CommonTree)adaptor.create(OR_OPERATOR_T114)
            ;
            adaptor.addChild(root_0, OR_OPERATOR_T114_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "or_operator"


    public static class not_operator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "not_operator"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:129:1: not_operator : NOT_OPERATOR_T ;
    public final LogKVQueryParser.not_operator_return not_operator() throws RecognitionException {
        LogKVQueryParser.not_operator_return retval = new LogKVQueryParser.not_operator_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NOT_OPERATOR_T115=null;

        CommonTree NOT_OPERATOR_T115_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:129:14: ( NOT_OPERATOR_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:129:16: NOT_OPERATOR_T
            {
            root_0 = (CommonTree)adaptor.nil();


            NOT_OPERATOR_T115=(Token)match(input,NOT_OPERATOR_T,FOLLOW_NOT_OPERATOR_T_in_not_operator814); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NOT_OPERATOR_T115_tree = 
            (CommonTree)adaptor.create(NOT_OPERATOR_T115)
            ;
            adaptor.addChild(root_0, NOT_OPERATOR_T115_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "not_operator"


    public static class between_operator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "between_operator"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:130:1: between_operator : BETWEEN_OPERATOR_T ;
    public final LogKVQueryParser.between_operator_return between_operator() throws RecognitionException {
        LogKVQueryParser.between_operator_return retval = new LogKVQueryParser.between_operator_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token BETWEEN_OPERATOR_T116=null;

        CommonTree BETWEEN_OPERATOR_T116_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:130:17: ( BETWEEN_OPERATOR_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:130:19: BETWEEN_OPERATOR_T
            {
            root_0 = (CommonTree)adaptor.nil();


            BETWEEN_OPERATOR_T116=(Token)match(input,BETWEEN_OPERATOR_T,FOLLOW_BETWEEN_OPERATOR_T_in_between_operator820); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BETWEEN_OPERATOR_T116_tree = 
            (CommonTree)adaptor.create(BETWEEN_OPERATOR_T116)
            ;
            adaptor.addChild(root_0, BETWEEN_OPERATOR_T116_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "between_operator"


    public static class in_operator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "in_operator"
    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:131:1: in_operator : IN_OPERATOR_T ;
    public final LogKVQueryParser.in_operator_return in_operator() throws RecognitionException {
        LogKVQueryParser.in_operator_return retval = new LogKVQueryParser.in_operator_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IN_OPERATOR_T117=null;

        CommonTree IN_OPERATOR_T117_tree=null;

        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:131:13: ( IN_OPERATOR_T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:131:15: IN_OPERATOR_T
            {
            root_0 = (CommonTree)adaptor.nil();


            IN_OPERATOR_T117=(Token)match(input,IN_OPERATOR_T,FOLLOW_IN_OPERATOR_T_in_in_operator827); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN_OPERATOR_T117_tree = 
            (CommonTree)adaptor.create(IN_OPERATOR_T117)
            ;
            adaptor.addChild(root_0, IN_OPERATOR_T117_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "in_operator"

    // $ANTLR start synpred9_LogKVQuery
    public final void synpred9_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:5: ( not_final_compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:5: not_final_compound_withlevel_expression
        {
        pushFollow(FOLLOW_not_final_compound_withlevel_expression_in_synpred9_LogKVQuery203);
        not_final_compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred9_LogKVQuery

    // $ANTLR start synpred10_LogKVQuery
    public final void synpred10_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:45: ( final_compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:45: final_compound_withlevel_expression
        {
        pushFollow(FOLLOW_final_compound_withlevel_expression_in_synpred10_LogKVQuery205);
        final_compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred10_LogKVQuery

    // $ANTLR start synpred11_LogKVQuery
    public final void synpred11_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:81: ( not_multi_compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:81: not_multi_compound_withlevel_expression
        {
        pushFollow(FOLLOW_not_multi_compound_withlevel_expression_in_synpred11_LogKVQuery207);
        not_multi_compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred11_LogKVQuery

    // $ANTLR start synpred12_LogKVQuery
    public final void synpred12_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:121: ( multi_compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:121: multi_compound_withlevel_expression
        {
        pushFollow(FOLLOW_multi_compound_withlevel_expression_in_synpred12_LogKVQuery209);
        multi_compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred12_LogKVQuery

    // $ANTLR start synpred13_LogKVQuery
    public final void synpred13_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:157: ( not_compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:157: not_compound_withlevel_expression
        {
        pushFollow(FOLLOW_not_compound_withlevel_expression_in_synpred13_LogKVQuery211);
        not_compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred13_LogKVQuery

    // $ANTLR start synpred14_LogKVQuery
    public final void synpred14_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:191: ( compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:191: compound_withlevel_expression
        {
        pushFollow(FOLLOW_compound_withlevel_expression_in_synpred14_LogKVQuery213);
        compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred14_LogKVQuery

    // $ANTLR start synpred15_LogKVQuery
    public final void synpred15_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:221: ( multi_compound_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:40:221: multi_compound_expression
        {
        pushFollow(FOLLOW_multi_compound_expression_in_synpred15_LogKVQuery215);
        multi_compound_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred15_LogKVQuery

    // $ANTLR start synpred17_LogKVQuery
    public final void synpred17_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:5: ( not_final_compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:5: not_final_compound_withlevel_expression
        {
        pushFollow(FOLLOW_not_final_compound_withlevel_expression_in_synpred17_LogKVQuery227);
        not_final_compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred17_LogKVQuery

    // $ANTLR start synpred18_LogKVQuery
    public final void synpred18_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:45: ( final_compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:45: final_compound_withlevel_expression
        {
        pushFollow(FOLLOW_final_compound_withlevel_expression_in_synpred18_LogKVQuery229);
        final_compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred18_LogKVQuery

    // $ANTLR start synpred19_LogKVQuery
    public final void synpred19_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:81: ( not_multi_compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:81: not_multi_compound_withlevel_expression
        {
        pushFollow(FOLLOW_not_multi_compound_withlevel_expression_in_synpred19_LogKVQuery231);
        not_multi_compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_LogKVQuery

    // $ANTLR start synpred20_LogKVQuery
    public final void synpred20_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:121: ( multi_compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:121: multi_compound_withlevel_expression
        {
        pushFollow(FOLLOW_multi_compound_withlevel_expression_in_synpred20_LogKVQuery233);
        multi_compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred20_LogKVQuery

    // $ANTLR start synpred21_LogKVQuery
    public final void synpred21_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:157: ( not_compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:157: not_compound_withlevel_expression
        {
        pushFollow(FOLLOW_not_compound_withlevel_expression_in_synpred21_LogKVQuery235);
        not_compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred21_LogKVQuery

    // $ANTLR start synpred22_LogKVQuery
    public final void synpred22_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:191: ( compound_withlevel_expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:41:191: compound_withlevel_expression
        {
        pushFollow(FOLLOW_compound_withlevel_expression_in_synpred22_LogKVQuery237);
        compound_withlevel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred22_LogKVQuery

    // $ANTLR start synpred26_LogKVQuery
    public final void synpred26_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:5: ( ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression |) relation )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:5: ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression |) relation
        {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:5: ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression |)
        int alt26=4;
        switch ( input.LA(1) ) {
        case LEFT_BRACE:
            {
            alt26=1;
            }
            break;
        case NOT_OPERATOR_T:
            {
            alt26=2;
            }
            break;
        case FILED_T:
            {
            alt26=3;
            }
            break;
        case AND_OPERATOR_T:
        case OR_OPERATOR_T:
            {
            alt26=4;
            }
            break;
        default:
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 26, 0, input);

            throw nvae;

        }

        switch (alt26) {
            case 1 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:6: multi_compound_withlevel_expression
                {
                pushFollow(FOLLOW_multi_compound_withlevel_expression_in_synpred26_LogKVQuery306);
                multi_compound_withlevel_expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:42: not_multi_compound_withlevel_expression
                {
                pushFollow(FOLLOW_not_multi_compound_withlevel_expression_in_synpred26_LogKVQuery308);
                not_multi_compound_withlevel_expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 3 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:82: multi_compound_expression
                {
                pushFollow(FOLLOW_multi_compound_expression_in_synpred26_LogKVQuery310);
                multi_compound_expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 4 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:58:108: 
                {
                }
                break;

        }


        pushFollow(FOLLOW_relation_in_synpred26_LogKVQuery314);
        relation();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred26_LogKVQuery

    // $ANTLR start synpred31_LogKVQuery
    public final void synpred31_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:17: ( ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) relation )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:17: ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) relation
        {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:17: ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression )
        int alt27=3;
        switch ( input.LA(1) ) {
        case LEFT_BRACE:
            {
            alt27=1;
            }
            break;
        case FILED_T:
            {
            alt27=2;
            }
            break;
        case NOT_OPERATOR_T:
            {
            alt27=3;
            }
            break;
        default:
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 27, 0, input);

            throw nvae;

        }

        switch (alt27) {
            case 1 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:18: compound_withlevel_expression
                {
                pushFollow(FOLLOW_compound_withlevel_expression_in_synpred31_LogKVQuery376);
                compound_withlevel_expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:48: multi_compound_expression
                {
                pushFollow(FOLLOW_multi_compound_expression_in_synpred31_LogKVQuery378);
                multi_compound_expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 3 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:70:74: not_compound_withlevel_expression
                {
                pushFollow(FOLLOW_not_compound_withlevel_expression_in_synpred31_LogKVQuery380);
                not_compound_withlevel_expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        pushFollow(FOLLOW_relation_in_synpred31_LogKVQuery383);
        relation();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred31_LogKVQuery

    // $ANTLR start synpred34_LogKVQuery
    public final void synpred34_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:6: ( expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:6: expression
        {
        pushFollow(FOLLOW_expression_in_synpred34_LogKVQuery463);
        expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred34_LogKVQuery

    // $ANTLR start synpred35_LogKVQuery
    public final void synpred35_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:5: ( ( expression | compound_expression ) relation )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:5: ( expression | compound_expression ) relation
        {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:5: ( expression | compound_expression )
        int alt28=2;
        alt28 = dfa28.predict(input);
        switch (alt28) {
            case 1 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:6: expression
                {
                pushFollow(FOLLOW_expression_in_synpred35_LogKVQuery463);
                expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:17: compound_expression
                {
                pushFollow(FOLLOW_compound_expression_in_synpred35_LogKVQuery465);
                compound_expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        pushFollow(FOLLOW_relation_in_synpred35_LogKVQuery468);
        relation();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred35_LogKVQuery

    // $ANTLR start synpred36_LogKVQuery
    public final void synpred36_LogKVQuery_fragment() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:51: ( expression )
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:85:51: expression
        {
        pushFollow(FOLLOW_expression_in_synpred36_LogKVQuery474);
        expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred36_LogKVQuery

    // Delegated rules

    public final boolean synpred34_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred36_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred36_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred19_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred19_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred22_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred22_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred31_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred31_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred18_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred26_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred26_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred17_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_LogKVQuery() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_LogKVQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA9 dfa9 = new DFA9(this);
    protected DFA10 dfa10 = new DFA10(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA15 dfa15 = new DFA15(this);
    protected DFA18 dfa18 = new DFA18(this);
    protected DFA17 dfa17 = new DFA17(this);
    protected DFA19 dfa19 = new DFA19(this);
    protected DFA28 dfa28 = new DFA28(this);
    static final String DFA9_eotS =
        "\u04fe\uffff";
    static final String DFA9_eofS =
        "\37\uffff\2\105\121\uffff\1\105\15\uffff\1\105\1\uffff\1\105\23"+
        "\uffff\1\105\70\uffff\1\105\10\uffff\1\105\53\uffff\1\105\u00f5"+
        "\uffff\1\105\65\uffff\1\105\u02cd\uffff";
    static final String DFA9_minS =
        "\1\5\1\31\1\5\1\10\1\uffff\1\5\1\17\1\31\1\10\2\5\1\17\1\51\1\31"+
        "\1\24\1\17\1\31\1\10\2\5\1\17\1\10\1\31\2\17\1\51\1\31\1\24\1\17"+
        "\1\31\1\10\3\5\1\51\1\31\1\17\1\10\1\31\2\17\1\51\1\31\1\24\1\17"+
        "\1\31\2\10\1\17\1\51\1\31\1\24\2\17\1\10\1\31\3\5\1\51\1\31\1\10"+
        "\2\17\1\51\1\31\1\24\2\0\2\uffff\2\0\1\51\1\13\1\51\1\10\1\17\1"+
        "\51\1\31\1\24\2\17\1\10\1\31\3\5\1\51\1\31\1\10\2\17\1\51\1\31\1"+
        "\24\1\17\1\51\1\31\1\24\3\5\1\51\1\31\2\10\1\17\1\51\1\31\1\24\1"+
        "\17\2\5\1\54\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1\10\3\5\1\51\1"+
        "\31\1\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24\3\5\1\51\1\31\2\10\1\17"+
        "\1\51\1\31\1\24\1\17\2\5\1\54\1\51\1\13\1\51\1\17\1\51\1\31\1\24"+
        "\1\10\3\5\1\51\1\31\3\5\1\51\1\31\2\17\1\5\1\51\1\13\1\51\1\17\1"+
        "\51\1\31\1\24\1\17\1\51\1\31\1\24\3\5\1\51\1\31\2\10\2\uffff\1\5"+
        "\1\51\1\5\1\13\3\5\1\51\1\31\1\17\1\51\1\31\1\24\3\5\1\51\1\13\1"+
        "\51\4\0\1\51\4\5\1\51\1\31\2\17\1\5\1\51\1\13\1\51\1\17\1\51\1\31"+
        "\1\24\1\17\1\51\1\31\1\24\3\5\1\51\1\31\2\10\2\uffff\1\5\1\51\1"+
        "\5\1\13\3\5\1\51\1\31\1\17\1\51\1\31\1\24\3\5\1\51\1\13\1\51\2\17"+
        "\1\51\1\13\1\51\1\10\1\5\1\51\1\5\1\13\3\5\1\51\1\31\3\5\1\51\1"+
        "\31\2\17\1\5\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1\17\1\51\1\31\1"+
        "\24\1\51\1\5\2\17\1\51\1\13\1\51\3\5\1\51\1\31\1\10\1\5\1\51\1\5"+
        "\1\13\2\0\2\17\1\51\1\13\1\51\1\10\1\5\1\51\1\5\1\13\3\5\1\51\1"+
        "\31\3\5\1\51\1\31\2\17\1\5\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1"+
        "\17\1\51\1\31\1\24\1\51\1\5\2\17\1\51\1\13\1\51\3\5\1\51\1\31\1"+
        "\10\1\5\1\51\1\5\1\13\1\10\1\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24"+
        "\1\51\1\5\2\17\1\51\1\13\1\51\2\17\1\51\1\13\1\51\1\10\1\5\1\51"+
        "\1\5\1\13\3\5\1\51\1\31\3\5\1\51\1\31\1\10\1\5\1\51\1\5\1\13\2\17"+
        "\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1\51\1\5\1\10\1\5\1\51\1\5\1"+
        "\13\1\17\1\51\1\31\1\24\1\51\1\5\2\17\1\51\1\13\1\51\2\17\1\51\1"+
        "\13\1\51\1\10\1\5\1\51\1\5\1\13\3\5\1\51\1\31\3\5\1\51\1\31\1\10"+
        "\1\5\1\51\1\5\1\13\2\17\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1\51"+
        "\1\5\1\17\1\51\1\31\1\24\1\51\4\5\1\51\1\31\1\10\1\5\1\51\1\5\1"+
        "\13\1\10\1\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24\1\51\1\5\2\17\1\51"+
        "\1\13\1\51\2\5\1\54\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1\51\1\5"+
        "\1\10\1\5\1\51\1\5\1\13\3\5\1\51\1\31\1\17\1\51\1\31\1\24\1\51\4"+
        "\5\1\51\1\31\1\10\1\5\1\51\1\5\1\13\1\10\1\5\1\51\1\5\1\13\1\17"+
        "\1\51\1\31\1\24\1\51\1\5\2\17\1\51\1\13\1\51\2\5\1\54\1\51\1\13"+
        "\1\51\1\17\1\51\1\31\1\24\1\51\1\5\1\10\1\5\1\51\1\5\1\13\3\5\1"+
        "\51\1\31\3\5\1\51\1\31\2\17\1\5\1\51\1\13\1\51\1\17\1\51\1\31\1"+
        "\24\1\51\1\5\1\17\1\51\1\31\1\24\1\51\4\5\1\51\1\31\1\10\1\5\1\51"+
        "\1\5\1\13\1\10\1\uffff\1\5\1\51\1\5\1\13\3\5\1\51\1\31\1\17\1\51"+
        "\1\31\1\24\1\51\1\5\1\51\1\13\1\51\3\5\1\51\1\31\2\17\1\5\1\51\1"+
        "\13\1\51\1\17\1\51\1\31\1\24\1\51\1\5\1\17\1\51\1\31\1\24\1\51\4"+
        "\5\1\51\1\31\1\10\1\5\1\51\1\5\1\13\1\10\1\uffff\1\5\1\51\1\5\1"+
        "\13\3\5\1\51\1\31\1\17\1\51\1\31\1\24\1\51\1\5\1\51\1\13\1\51\2"+
        "\17\1\5\1\51\1\13\1\51\1\10\3\5\1\51\1\5\1\13\3\5\1\51\1\31\3\5"+
        "\1\51\1\31\2\17\1\5\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1\51\1\5"+
        "\1\17\1\51\1\31\1\24\1\51\1\5\1\51\1\13\1\51\3\5\1\51\1\31\1\5\1"+
        "\51\1\5\1\13\2\17\1\5\1\51\1\13\1\51\1\10\3\5\1\51\1\5\1\13\3\5"+
        "\1\51\1\31\3\5\1\51\1\31\2\17\1\5\1\51\1\13\1\51\1\17\1\51\1\31"+
        "\1\24\1\51\1\5\1\17\1\51\1\31\1\24\1\51\1\5\1\51\1\13\1\51\3\5\1"+
        "\51\1\31\1\5\1\51\1\5\1\13\1\10\2\17\1\5\1\51\1\5\1\13\1\17\1\51"+
        "\1\31\1\24\1\10\1\51\1\5\2\17\1\5\1\51\1\13\1\51\2\17\1\5\1\51\1"+
        "\13\1\51\1\10\1\5\1\51\1\5\1\13\3\5\1\51\1\31\3\5\1\51\1\31\1\5"+
        "\1\51\1\5\1\13\1\51\1\13\2\51\1\5\1\10\2\17\1\5\1\51\1\5\1\13\1"+
        "\17\1\51\1\31\1\24\1\10\1\51\1\5\2\17\1\5\1\51\1\13\1\51\2\17\1"+
        "\5\1\51\1\13\1\51\1\10\1\5\1\51\1\5\1\13\3\5\1\51\1\31\3\5\1\51"+
        "\1\31\1\5\1\51\1\5\1\13\1\51\1\13\2\51\1\5\1\17\1\51\1\31\1\24\1"+
        "\51\4\5\1\51\1\31\1\17\1\51\1\31\1\24\1\10\1\5\1\51\1\5\1\13\1\10"+
        "\2\17\1\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24\1\51\1\5\2\17\1\5\1"+
        "\51\1\13\2\51\1\13\2\51\2\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24\1"+
        "\51\4\5\1\51\1\31\1\17\1\51\1\31\1\24\1\10\1\5\1\51\1\5\1\13\1\10"+
        "\2\17\1\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24\1\51\1\5\2\17\1\5\1"+
        "\51\1\13\2\51\1\13\2\51\2\5\1\51\1\5\1\13\3\5\1\51\1\31\1\51\1\13"+
        "\1\51\3\5\1\51\1\31\1\17\1\51\1\31\1\24\1\51\1\5\1\17\1\51\1\31"+
        "\1\24\1\51\4\5\1\51\1\31\1\10\1\5\1\51\1\5\1\13\1\5\1\51\1\5\1\13"+
        "\1\51\4\5\1\51\1\31\1\51\1\13\1\51\3\5\1\51\1\31\1\17\1\51\1\31"+
        "\1\24\1\51\1\5\1\17\1\51\1\31\1\24\1\51\4\5\1\51\1\31\1\10\1\5\1"+
        "\51\1\5\1\13\1\5\1\51\1\5\1\13\1\51\1\5\1\51\1\13\1\51\1\5\1\51"+
        "\1\5\1\13\2\5\1\51\1\13\1\51\3\5\1\51\1\31\3\5\1\51\1\31\1\51\1"+
        "\13\1\51\1\17\1\51\1\31\1\24\1\51\1\5\1\51\1\5\1\51\1\13\1\51\1"+
        "\5\1\51\1\5\1\13\2\5\1\51\1\13\1\51\3\5\1\51\1\31\3\5\1\51\1\31"+
        "\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1\51\1\5\1\51\2\5\1\51\1\5\1"+
        "\13\1\51\1\5\1\10\1\5\1\51\1\5\1\13\1\51\1\13\2\51\1\13\1\51\1\5"+
        "\1\51\1\5\1\13\3\5\1\51\1\31\1\5\1\51\1\5\1\13\1\51\1\5\1\10\1\5"+
        "\1\51\1\5\1\13\1\51\1\13\2\51\1\13\1\51\1\5\1\51\1\5\1\13\3\5\1"+
        "\51\1\31\1\51\1\5\1\17\1\51\1\31\1\24\1\51\2\5\1\51\1\5\1\13\1\5"+
        "\1\51\1\5\1\13\1\51\1\5\1\51\1\13\2\51\1\5\1\17\1\51\1\31\1\24\1"+
        "\51\2\5\1\51\1\5\1\13\1\5\1\51\1\5\1\13\1\51\1\5\1\51\1\13\1\51"+
        "\3\5\1\51\1\31\1\51\1\5\1\51\2\5\1\51\1\5\1\13\3\5\1\51\1\31\1\51"+
        "\1\5\1\51\2\5\1\51\1\5\1\13\1\51\1\13\2\51\1\5\1\51\1\13\2\51\2"+
        "\5\1\51\1\5\1\13\1\5\1\51\1\5\1\13\1\51\1\5\1\51\1\5";
    static final String DFA9_maxS =
        "\1\37\1\31\1\37\1\34\1\uffff\1\37\1\34\1\31\1\34\2\37\2\51\1\31"+
        "\1\24\1\34\1\31\1\34\2\37\1\17\1\34\1\31\1\34\2\51\1\31\1\24\1\34"+
        "\1\31\1\34\2\54\1\5\1\51\1\31\1\17\1\34\1\31\1\34\2\51\1\31\1\24"+
        "\1\34\1\31\2\34\2\51\1\31\1\24\2\17\1\34\1\31\2\43\1\5\1\51\1\31"+
        "\2\34\2\51\1\31\1\24\2\0\2\uffff\2\0\1\51\1\43\1\51\1\34\2\51\1"+
        "\31\1\24\2\17\1\34\1\31\2\43\1\5\1\51\1\31\2\34\2\51\1\31\1\24\2"+
        "\51\1\31\1\24\2\43\1\5\1\51\1\31\2\34\2\51\1\31\1\24\1\17\2\37\1"+
        "\54\1\51\1\43\3\51\1\31\1\24\1\34\2\43\1\5\1\51\1\31\1\54\1\51\1"+
        "\54\1\43\2\51\1\31\1\24\2\43\1\5\1\51\1\31\2\34\2\51\1\31\1\24\1"+
        "\17\2\37\1\54\1\51\1\43\3\51\1\31\1\24\1\34\2\43\1\5\1\51\1\31\2"+
        "\37\1\5\1\51\1\31\2\34\1\43\1\51\1\43\3\51\1\31\1\24\2\51\1\31\1"+
        "\24\2\43\1\5\1\51\1\31\2\34\2\uffff\1\43\1\51\4\43\1\5\1\51\1\31"+
        "\2\51\1\31\1\24\2\37\1\54\1\51\1\43\1\51\4\0\1\51\1\54\2\37\1\5"+
        "\1\51\1\31\2\34\1\43\1\51\1\43\3\51\1\31\1\24\2\51\1\31\1\24\2\43"+
        "\1\5\1\51\1\31\2\34\2\uffff\1\43\1\51\4\43\1\5\1\51\1\31\2\51\1"+
        "\31\1\24\2\37\1\54\1\51\1\43\1\51\2\17\1\51\1\43\1\51\1\34\1\43"+
        "\1\51\2\43\2\37\1\5\1\51\1\31\2\37\1\5\1\51\1\31\2\34\1\43\1\51"+
        "\1\43\3\51\1\31\1\24\2\51\1\31\1\24\1\51\1\43\2\34\1\51\1\43\1\51"+
        "\2\43\1\5\1\51\1\31\1\34\1\43\1\51\2\43\2\0\2\17\1\51\1\43\1\51"+
        "\1\34\1\43\1\51\2\43\2\37\1\5\1\51\1\31\2\37\1\5\1\51\1\31\2\34"+
        "\1\43\1\51\1\43\3\51\1\31\1\24\2\51\1\31\1\24\1\51\1\43\2\34\1\51"+
        "\1\43\1\51\2\43\1\5\1\51\1\31\1\34\1\43\1\51\2\43\1\34\1\37\1\51"+
        "\1\37\1\43\2\51\1\31\1\24\1\51\1\43\2\17\1\51\1\43\1\51\2\17\1\51"+
        "\1\43\1\51\1\34\1\43\1\51\2\43\2\37\1\5\1\51\1\31\2\43\1\5\1\51"+
        "\1\31\1\34\1\43\1\51\2\43\2\34\1\51\1\43\3\51\1\31\1\24\1\51\1\43"+
        "\1\34\1\37\1\51\1\37\1\43\2\51\1\31\1\24\1\51\1\43\2\17\1\51\1\43"+
        "\1\51\2\17\1\51\1\43\1\51\1\34\1\43\1\51\2\43\2\37\1\5\1\51\1\31"+
        "\2\43\1\5\1\51\1\31\1\34\1\43\1\51\2\43\2\34\1\51\1\43\3\51\1\31"+
        "\1\24\1\51\1\43\2\51\1\31\1\24\1\51\1\37\2\43\1\5\1\51\1\31\1\34"+
        "\1\37\1\51\1\37\1\43\1\34\1\37\1\51\1\37\1\43\2\51\1\31\1\24\1\51"+
        "\1\43\2\17\1\51\1\43\1\51\2\37\1\54\1\51\1\43\3\51\1\31\1\24\1\51"+
        "\1\43\1\34\1\43\1\51\4\43\1\5\1\51\1\31\2\51\1\31\1\24\1\51\1\37"+
        "\2\43\1\5\1\51\1\31\1\34\1\37\1\51\1\37\1\43\1\34\1\37\1\51\1\37"+
        "\1\43\2\51\1\31\1\24\1\51\1\43\2\17\1\51\1\43\1\51\2\37\1\54\1\51"+
        "\1\43\3\51\1\31\1\24\1\51\1\43\1\34\1\43\1\51\4\43\1\5\1\51\1\31"+
        "\2\43\1\5\1\51\1\31\2\34\1\43\1\51\1\43\3\51\1\31\1\24\1\51\1\37"+
        "\2\51\1\31\1\24\1\51\1\37\2\43\1\5\1\51\1\31\1\34\1\37\1\51\1\37"+
        "\1\43\1\34\1\uffff\1\43\1\51\4\43\1\5\1\51\1\31\2\51\1\31\1\24\1"+
        "\51\1\43\1\51\1\43\1\51\2\43\1\5\1\51\1\31\2\34\1\43\1\51\1\43\3"+
        "\51\1\31\1\24\1\51\1\37\2\51\1\31\1\24\1\51\1\37\2\43\1\5\1\51\1"+
        "\31\1\34\1\37\1\51\1\37\1\43\1\34\1\uffff\1\43\1\51\4\43\1\5\1\51"+
        "\1\31\2\51\1\31\1\24\1\51\1\43\1\51\1\43\1\51\2\17\1\43\1\51\1\43"+
        "\1\51\1\34\2\37\1\43\1\51\4\43\1\5\1\51\1\31\2\43\1\5\1\51\1\31"+
        "\2\34\1\43\1\51\1\43\3\51\1\31\1\24\1\51\1\37\2\51\1\31\1\24\1\51"+
        "\1\43\1\51\1\43\1\51\2\43\1\5\1\51\1\31\1\43\1\51\2\43\2\17\1\43"+
        "\1\51\1\43\1\51\1\34\2\37\1\43\1\51\4\43\1\5\1\51\1\31\2\43\1\5"+
        "\1\51\1\31\2\34\1\43\1\51\1\43\3\51\1\31\1\24\1\51\1\37\2\51\1\31"+
        "\1\24\1\51\1\43\1\51\1\43\1\51\2\43\1\5\1\51\1\31\1\43\1\51\2\43"+
        "\3\34\1\43\1\51\2\43\2\51\1\31\1\24\1\34\1\51\1\43\2\17\1\43\1\51"+
        "\1\43\1\51\2\17\1\43\1\51\1\43\1\51\1\34\1\43\1\51\4\43\1\5\1\51"+
        "\1\31\2\43\1\5\1\51\1\31\1\43\1\51\2\43\1\51\1\43\2\51\1\43\3\34"+
        "\1\43\1\51\2\43\2\51\1\31\1\24\1\34\1\51\1\43\2\17\1\43\1\51\1\43"+
        "\1\51\2\17\1\43\1\51\1\43\1\51\1\34\1\43\1\51\4\43\1\5\1\51\1\31"+
        "\2\43\1\5\1\51\1\31\1\43\1\51\2\43\1\51\1\43\2\51\1\43\2\51\1\31"+
        "\1\24\1\51\3\43\1\5\1\51\1\31\2\51\1\31\1\24\1\34\1\43\1\51\2\43"+
        "\3\34\1\43\1\51\2\43\2\51\1\31\1\24\1\51\1\43\2\17\1\43\1\51\1\43"+
        "\2\51\1\43\2\51\2\43\1\51\2\43\2\51\1\31\1\24\1\51\3\43\1\5\1\51"+
        "\1\31\2\51\1\31\1\24\1\34\1\43\1\51\2\43\3\34\1\43\1\51\2\43\2\51"+
        "\1\31\1\24\1\51\1\43\2\17\1\43\1\51\1\43\2\51\1\43\2\51\2\43\1\51"+
        "\4\43\1\5\1\51\1\31\1\51\1\43\1\51\2\43\1\5\1\51\1\31\2\51\1\31"+
        "\1\24\1\51\1\43\2\51\1\31\1\24\1\51\3\43\1\5\1\51\1\31\1\34\1\43"+
        "\1\51\3\43\1\51\2\43\1\51\3\43\1\5\1\51\1\31\1\51\1\43\1\51\2\43"+
        "\1\5\1\51\1\31\2\51\1\31\1\24\1\51\1\43\2\51\1\31\1\24\1\51\3\43"+
        "\1\5\1\51\1\31\1\34\1\43\1\51\3\43\1\51\2\43\1\51\1\43\1\51\1\43"+
        "\1\51\1\43\1\51\2\43\2\37\1\51\1\43\1\51\2\43\1\5\1\51\1\31\2\43"+
        "\1\5\1\51\1\31\1\51\1\43\3\51\1\31\1\24\1\51\1\43\1\51\1\43\1\51"+
        "\1\43\1\51\1\43\1\51\2\43\2\37\1\51\1\43\1\51\2\43\1\5\1\51\1\31"+
        "\2\43\1\5\1\51\1\31\1\51\1\43\3\51\1\31\1\24\1\51\1\43\1\51\2\43"+
        "\1\51\2\43\1\51\1\43\1\34\1\43\1\51\2\43\1\51\1\43\2\51\1\43\1\51"+
        "\1\43\1\51\4\43\1\5\1\51\1\31\1\43\1\51\2\43\1\51\1\43\1\34\1\43"+
        "\1\51\2\43\1\51\1\43\2\51\1\43\1\51\1\43\1\51\4\43\1\5\1\51\1\31"+
        "\1\51\1\43\2\51\1\31\1\24\1\51\2\43\1\51\3\43\1\51\2\43\1\51\1\43"+
        "\1\51\1\43\2\51\1\43\2\51\1\31\1\24\1\51\2\43\1\51\3\43\1\51\2\43"+
        "\1\51\1\43\1\51\1\43\1\51\2\43\1\5\1\51\1\31\1\51\1\43\1\51\2\43"+
        "\1\51\4\43\1\5\1\51\1\31\1\51\1\43\1\51\2\43\1\51\2\43\1\51\1\43"+
        "\2\51\1\43\1\51\1\43\2\51\2\43\1\51\3\43\1\51\2\43\1\51\1\43\1\51"+
        "\1\43";
    static final String DFA9_acceptS =
        "\4\uffff\1\10\100\uffff\1\11\1\7\167\uffff\1\2\1\4\63\uffff\1\1"+
        "\1\3\u0171\uffff\1\6\64\uffff\1\5\u0262\uffff";
    static final String DFA9_specialS =
        "\103\uffff\1\6\1\13\2\uffff\1\5\1\12\51\uffff\1\1\43\uffff\1\14"+
        "\74\uffff\1\4\1\11\1\3\1\10\144\uffff\1\2\1\7\u00bd\uffff\1\15\65"+
        "\uffff\1\0\u02cd\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\4\11\uffff\1\3\11\uffff\1\2\2\uffff\1\1\2\uffff\1\4",
            "\1\5",
            "\1\11\11\uffff\1\10\11\uffff\1\6\2\uffff\1\7\2\uffff\1\12",
            "\1\14\1\13\12\uffff\1\15\7\uffff\1\16",
            "",
            "\1\22\11\uffff\1\21\11\uffff\1\17\2\uffff\1\20\2\uffff\1\23",
            "\1\25\11\uffff\1\24\2\uffff\1\26",
            "\1\27",
            "\1\31\1\30\12\uffff\1\32\7\uffff\1\33",
            "\1\11\11\uffff\1\36\11\uffff\1\34\2\uffff\1\35\2\uffff\1\12",
            "\1\11\11\uffff\1\36\11\uffff\1\34\2\uffff\1\35\2\uffff\1\12",
            "\1\40\31\uffff\1\37",
            "\1\41",
            "\1\42",
            "\1\43",
            "\1\45\11\uffff\1\44\2\uffff\1\46",
            "\1\47",
            "\1\51\1\50\12\uffff\1\52\7\uffff\1\53",
            "\1\22\11\uffff\1\56\11\uffff\1\54\2\uffff\1\55\2\uffff\1\23",
            "\1\22\11\uffff\1\56\11\uffff\1\54\2\uffff\1\55\2\uffff\1\23",
            "\1\57",
            "\1\61\1\60\12\uffff\1\62\7\uffff\1\63",
            "\1\64",
            "\1\66\11\uffff\1\65\2\uffff\1\67",
            "\1\71\31\uffff\1\70",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75\11\uffff\1\24\2\uffff\1\26",
            "\1\76",
            "\1\100\1\77\12\uffff\1\101\7\uffff\1\102",
            "\1\103\11\uffff\1\106\11\uffff\1\106\2\uffff\1\106\2\uffff"+
            "\1\104\14\uffff\1\105",
            "\1\107\11\uffff\1\106\11\uffff\1\106\2\uffff\1\106\2\uffff"+
            "\1\110\14\uffff\1\105",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\116\1\115\12\uffff\1\117\7\uffff\1\120",
            "\1\121",
            "\1\123\11\uffff\1\122\2\uffff\1\124",
            "\1\126\31\uffff\1\125",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132\11\uffff\1\44\2\uffff\1\46",
            "\1\133",
            "\1\135\1\134\12\uffff\1\136\7\uffff\1\137",
            "\1\141\1\140\12\uffff\1\142\7\uffff\1\143",
            "\1\145\31\uffff\1\144",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\154\1\153\12\uffff\1\155\7\uffff\1\156",
            "\1\157",
            "\1\160\31\uffff\1\161\3\uffff\1\162",
            "\1\160\31\uffff\1\161\3\uffff\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\167\1\166\12\uffff\1\170\7\uffff\1\171",
            "\1\172\11\uffff\1\65\2\uffff\1\67",
            "\1\174\31\uffff\1\173",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\u0080",
            "\1\u0081\27\uffff\1\u0082",
            "\1\u0083",
            "\1\u0085\1\u0084\12\uffff\1\u0086\7\uffff\1\u0087",
            "\1\u0089\31\uffff\1\u0088",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u0090\1\u008f\12\uffff\1\u0091\7\uffff\1\u0092",
            "\1\u0093",
            "\1\u0094\31\uffff\1\u0095\3\uffff\1\u0096",
            "\1\u0094\31\uffff\1\u0095\3\uffff\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009b\1\u009a\12\uffff\1\u009c\7\uffff\1\u009d",
            "\1\u009e\11\uffff\1\122\2\uffff\1\124",
            "\1\u00a0\31\uffff\1\u009f",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a5\31\uffff\1\u00a4",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9\31\uffff\1\u00aa\3\uffff\1\u00ab",
            "\1\u00a9\31\uffff\1\u00aa\3\uffff\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00b0\1\u00af\12\uffff\1\u00b1\7\uffff\1\u00b2",
            "\1\u00b4\1\u00b3\12\uffff\1\u00b5\7\uffff\1\u00b6",
            "\1\u00b8\31\uffff\1\u00b7",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\11\11\uffff\1\u00bd\11\uffff\1\6\2\uffff\1\7\2\uffff\1\12",
            "\1\11\11\uffff\1\u00bd\11\uffff\1\6\2\uffff\1\7\2\uffff\1\12",
            "\1\105",
            "\1\u00c0",
            "\1\u00c1\27\uffff\1\u00c2",
            "\1\u00c3",
            "\1\u00c5\31\uffff\1\u00c4",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00ca\1\u00c9\12\uffff\1\u00cb\7\uffff\1\u00cc",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3\11\uffff\1\106\11\uffff\1\106\2\uffff\1\106\2\uffff"+
            "\1\u00d4\14\uffff\1\105",
            "\1\112",
            "\1\u00d5\11\uffff\1\106\11\uffff\1\106\2\uffff\1\106\2\uffff"+
            "\1\u00d6\14\uffff\1\105",
            "\1\u00d7\27\uffff\1\u00d8",
            "\1\u00da\31\uffff\1\u00d9",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e5\1\u00e4\12\uffff\1\u00e6\7\uffff\1\u00e7",
            "\1\u00e9\1\u00e8\12\uffff\1\u00ea\7\uffff\1\u00eb",
            "\1\u00ed\31\uffff\1\u00ec",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\22\11\uffff\1\u00f2\11\uffff\1\17\2\uffff\1\20\2\uffff\1"+
            "\23",
            "\1\22\11\uffff\1\u00f2\11\uffff\1\17\2\uffff\1\20\2\uffff\1"+
            "\23",
            "\1\105",
            "\1\u00f5",
            "\1\u00f6\27\uffff\1\u00f7",
            "\1\u00f8",
            "\1\u00fa\31\uffff\1\u00f9",
            "\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "\1\u00ff\1\u00fe\12\uffff\1\u0100\7\uffff\1\u0101",
            "\1\u0102\31\uffff\1\u0103\3\uffff\1\u0104",
            "\1\u0102\31\uffff\1\u0103\3\uffff\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\1\u0108\31\uffff\1\u0109",
            "\1\u0108\31\uffff\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d\11\uffff\1\24\2\uffff\1\26",
            "\1\u010d\11\uffff\1\24\2\uffff\1\26",
            "\1\11\31\uffff\1\12\3\uffff\1\u00cf",
            "\1\u010e",
            "\1\u010f\27\uffff\1\u0110",
            "\1\u0111",
            "\1\u0113\31\uffff\1\u0112",
            "\1\u0114",
            "\1\u0115",
            "\1\u0116",
            "\1\u0118\31\uffff\1\u0117",
            "\1\u0119",
            "\1\u011a",
            "\1\u011b",
            "\1\u011c\31\uffff\1\u011d\3\uffff\1\u011e",
            "\1\u011c\31\uffff\1\u011d\3\uffff\1\u011e",
            "\1\u011f",
            "\1\u0120",
            "\1\u0121",
            "\1\u0123\1\u0122\12\uffff\1\u0124\7\uffff\1\u0125",
            "\1\u0127\1\u0126\12\uffff\1\u0128\7\uffff\1\u0129",
            "",
            "",
            "\1\160\31\uffff\1\161\3\uffff\1\162",
            "\1\164",
            "\1\160\31\uffff\1\161\3\uffff\1\162",
            "\1\u012a\27\uffff\1\u012b",
            "\1\u012c\31\uffff\1\u012d\3\uffff\1\u00ab",
            "\1\u012c\31\uffff\1\u012d\3\uffff\1\u00ab",
            "\1\u012e",
            "\1\u012f",
            "\1\u0130",
            "\1\u0132\31\uffff\1\u0131",
            "\1\u0133",
            "\1\u0134",
            "\1\u0135",
            "\1\11\11\uffff\1\u0136\11\uffff\1\34\2\uffff\1\35\2\uffff\1"+
            "\12",
            "\1\11\11\uffff\1\u0136\11\uffff\1\34\2\uffff\1\35\2\uffff\1"+
            "\12",
            "\1\u00be\11\uffff\1\u00be\11\uffff\1\u00be\2\uffff\1\u00be"+
            "\2\uffff\1\u00be\14\uffff\1\105",
            "\1\u0137",
            "\1\u0138\27\uffff\1\u0139",
            "\1\u013a",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\u0083",
            "\1\u013b\11\uffff\1\106\11\uffff\1\106\2\uffff\1\106\2\uffff"+
            "\1\u013c\14\uffff\1\105",
            "\1\u013d\31\uffff\1\u013e",
            "\1\u013d\31\uffff\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "\1\u0142\11\uffff\1\44\2\uffff\1\46",
            "\1\u0142\11\uffff\1\44\2\uffff\1\46",
            "\1\22\31\uffff\1\23\3\uffff\1\u0104",
            "\1\u0143",
            "\1\u0144\27\uffff\1\u0145",
            "\1\u0146",
            "\1\u0148\31\uffff\1\u0147",
            "\1\u0149",
            "\1\u014a",
            "\1\u014b",
            "\1\u014d\31\uffff\1\u014c",
            "\1\u014e",
            "\1\u014f",
            "\1\u0150",
            "\1\u0151\31\uffff\1\u0152\3\uffff\1\u0153",
            "\1\u0151\31\uffff\1\u0152\3\uffff\1\u0153",
            "\1\u0154",
            "\1\u0155",
            "\1\u0156",
            "\1\u0158\1\u0157\12\uffff\1\u0159\7\uffff\1\u015a",
            "\1\u015c\1\u015b\12\uffff\1\u015d\7\uffff\1\u015e",
            "",
            "",
            "\1\u0094\31\uffff\1\u0095\3\uffff\1\u0096",
            "\1\u0098",
            "\1\u0094\31\uffff\1\u0095\3\uffff\1\u0096",
            "\1\u015f\27\uffff\1\u0160",
            "\1\u0161\31\uffff\1\u0162\3\uffff\1\u00e0",
            "\1\u0161\31\uffff\1\u0162\3\uffff\1\u00e0",
            "\1\u0163",
            "\1\u0164",
            "\1\u0165",
            "\1\u0167\31\uffff\1\u0166",
            "\1\u0168",
            "\1\u0169",
            "\1\u016a",
            "\1\22\11\uffff\1\u016b\11\uffff\1\54\2\uffff\1\55\2\uffff\1"+
            "\23",
            "\1\22\11\uffff\1\u016b\11\uffff\1\54\2\uffff\1\55\2\uffff\1"+
            "\23",
            "\1\u00f3\11\uffff\1\u00f3\11\uffff\1\u00f3\2\uffff\1\u00f3"+
            "\2\uffff\1\u00f3\14\uffff\1\105",
            "\1\u016c",
            "\1\u016d\27\uffff\1\u016e",
            "\1\u016f",
            "\1\u0170",
            "\1\u0170",
            "\1\u0171",
            "\1\u0172\27\uffff\1\u0173",
            "\1\u0174",
            "\1\u0176\1\u0175\12\uffff\1\u0177\7\uffff\1\u0178",
            "\1\u00a9\31\uffff\1\u00aa\3\uffff\1\u00ab",
            "\1\u00ad",
            "\1\u00a9\31\uffff\1\u00aa\3\uffff\1\u00ab",
            "\1\u0179\27\uffff\1\u017a",
            "\1\u017b\31\uffff\1\u017c",
            "\1\u017b\31\uffff\1\u017c",
            "\1\u017d",
            "\1\u017e",
            "\1\u017f",
            "\1\u0180\31\uffff\1\u0181",
            "\1\u0180\31\uffff\1\u0181",
            "\1\u0182",
            "\1\u0183",
            "\1\u0184",
            "\1\u0185\11\uffff\1\65\2\uffff\1\67",
            "\1\u0185\11\uffff\1\65\2\uffff\1\67",
            "\1\11\31\uffff\1\12\3\uffff\1\u00cf",
            "\1\u0186",
            "\1\u0187\27\uffff\1\u0188",
            "\1\u0189",
            "\1\u018b\31\uffff\1\u018a",
            "\1\u018c",
            "\1\u018d",
            "\1\u018e",
            "\1\u0190\31\uffff\1\u018f",
            "\1\u0191",
            "\1\u0192",
            "\1\u0193",
            "\1\u00c3",
            "\1\160\31\uffff\1\161\3\uffff\1\162",
            "\1\u0194\11\uffff\1\24\2\uffff\1\26",
            "\1\u0194\11\uffff\1\24\2\uffff\1\26",
            "\1\u0195",
            "\1\u0196\27\uffff\1\u0197",
            "\1\u0198",
            "\1\u0199\31\uffff\1\u019a\3\uffff\1\u011e",
            "\1\u0199\31\uffff\1\u019a\3\uffff\1\u011e",
            "\1\u019b",
            "\1\u019c",
            "\1\u019d",
            "\1\u019f\1\u019e\12\uffff\1\u01a0\7\uffff\1\u01a1",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u00d1",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u01a2\27\uffff\1\u01a3",
            "\1\uffff",
            "\1\uffff",
            "\1\u01a4",
            "\1\u01a4",
            "\1\u01a5",
            "\1\u01a6\27\uffff\1\u01a7",
            "\1\u01a8",
            "\1\u01aa\1\u01a9\12\uffff\1\u01ab\7\uffff\1\u01ac",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u00e2",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u01ad\27\uffff\1\u01ae",
            "\1\u01af\31\uffff\1\u01b0",
            "\1\u01af\31\uffff\1\u01b0",
            "\1\u01b1",
            "\1\u01b2",
            "\1\u01b3",
            "\1\u01b4\31\uffff\1\u01b5",
            "\1\u01b4\31\uffff\1\u01b5",
            "\1\u01b6",
            "\1\u01b7",
            "\1\u01b8",
            "\1\u01b9\11\uffff\1\122\2\uffff\1\124",
            "\1\u01b9\11\uffff\1\122\2\uffff\1\124",
            "\1\22\31\uffff\1\23\3\uffff\1\u0104",
            "\1\u01ba",
            "\1\u01bb\27\uffff\1\u01bc",
            "\1\u01bd",
            "\1\u01bf\31\uffff\1\u01be",
            "\1\u01c0",
            "\1\u01c1",
            "\1\u01c2",
            "\1\u01c4\31\uffff\1\u01c3",
            "\1\u01c5",
            "\1\u01c6",
            "\1\u01c7",
            "\1\u00f8",
            "\1\u0094\31\uffff\1\u0095\3\uffff\1\u0096",
            "\1\u01c8\11\uffff\1\44\2\uffff\1\46",
            "\1\u01c8\11\uffff\1\44\2\uffff\1\46",
            "\1\u01c9",
            "\1\u01ca\27\uffff\1\u01cb",
            "\1\u01cc",
            "\1\u01cd\31\uffff\1\u01ce\3\uffff\1\u0153",
            "\1\u01cd\31\uffff\1\u01ce\3\uffff\1\u0153",
            "\1\u01cf",
            "\1\u01d0",
            "\1\u01d1",
            "\1\u01d3\1\u01d2\12\uffff\1\u01d4\7\uffff\1\u01d5",
            "\1\u0102\31\uffff\1\u0103\3\uffff\1\u0104",
            "\1\u0106",
            "\1\u0102\31\uffff\1\u0103\3\uffff\1\u0104",
            "\1\u01d6\27\uffff\1\u01d7",
            "\1\u01d9\1\u01d8\12\uffff\1\u01da\7\uffff\1\u01db",
            "\1\u0108\31\uffff\1\u0109",
            "\1\u010b",
            "\1\u0108\31\uffff\1\u0109",
            "\1\u01dc\27\uffff\1\u01dd",
            "\1\u01df\31\uffff\1\u01de",
            "\1\u01e0",
            "\1\u01e1",
            "\1\u01e2",
            "\1\u0111",
            "\1\u00a9\31\uffff\1\u00aa\3\uffff\1\u00ab",
            "\1\u01e3",
            "\1\u01e3",
            "\1\u01e4",
            "\1\u01e5\27\uffff\1\u01e6",
            "\1\u01e7",
            "\1\u01e8",
            "\1\u01e8",
            "\1\u01e9",
            "\1\u01ea\27\uffff\1\u01eb",
            "\1\u01ec",
            "\1\u01ee\1\u01ed\12\uffff\1\u01ef\7\uffff\1\u01f0",
            "\1\u011c\31\uffff\1\u011d\3\uffff\1\u011e",
            "\1\u0120",
            "\1\u011c\31\uffff\1\u011d\3\uffff\1\u011e",
            "\1\u01f1\27\uffff\1\u01f2",
            "\1\u01f3\31\uffff\1\u01f4",
            "\1\u01f3\31\uffff\1\u01f4",
            "\1\u01f5",
            "\1\u01f6",
            "\1\u01f7",
            "\1\u01f8\31\uffff\1\u01f9\3\uffff\1\u01fa",
            "\1\u01f8\31\uffff\1\u01f9\3\uffff\1\u01fa",
            "\1\u01fb",
            "\1\u01fc",
            "\1\u01fd",
            "\1\u01ff\1\u01fe\12\uffff\1\u0200\7\uffff\1\u0201",
            "\1\u012c\31\uffff\1\u012d\3\uffff\1\u00ab",
            "\1\u012f",
            "\1\u012c\31\uffff\1\u012d\3\uffff\1\u00ab",
            "\1\u0202\27\uffff\1\u0203",
            "\1\u0204\11\uffff\1\65\2\uffff\1\67",
            "\1\u0204\11\uffff\1\65\2\uffff\1\67",
            "\1\u0205",
            "\1\u0206\27\uffff\1\u0207",
            "\1\u0208",
            "\1\u020a\31\uffff\1\u0209",
            "\1\u020b",
            "\1\u020c",
            "\1\u020d",
            "\1\u013a",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u020f\1\u020e\12\uffff\1\u0210\7\uffff\1\u0211",
            "\1\u013d\31\uffff\1\u013e",
            "\1\u0140",
            "\1\u013d\31\uffff\1\u013e",
            "\1\u0212\27\uffff\1\u0213",
            "\1\u0215\31\uffff\1\u0214",
            "\1\u0216",
            "\1\u0217",
            "\1\u0218",
            "\1\u0146",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u0219",
            "\1\u0219",
            "\1\u021a",
            "\1\u021b\27\uffff\1\u021c",
            "\1\u021d",
            "\1\u021e",
            "\1\u021e",
            "\1\u021f",
            "\1\u0220\27\uffff\1\u0221",
            "\1\u0222",
            "\1\u0224\1\u0223\12\uffff\1\u0225\7\uffff\1\u0226",
            "\1\u0151\31\uffff\1\u0152\3\uffff\1\u0153",
            "\1\u0155",
            "\1\u0151\31\uffff\1\u0152\3\uffff\1\u0153",
            "\1\u0227\27\uffff\1\u0228",
            "\1\u0229\31\uffff\1\u022a",
            "\1\u0229\31\uffff\1\u022a",
            "\1\u022b",
            "\1\u022c",
            "\1\u022d",
            "\1\u022e\31\uffff\1\u022f\3\uffff\1\u0230",
            "\1\u022e\31\uffff\1\u022f\3\uffff\1\u0230",
            "\1\u0231",
            "\1\u0232",
            "\1\u0233",
            "\1\u0235\1\u0234\12\uffff\1\u0236\7\uffff\1\u0237",
            "\1\u0161\31\uffff\1\u0162\3\uffff\1\u00e0",
            "\1\u0164",
            "\1\u0161\31\uffff\1\u0162\3\uffff\1\u00e0",
            "\1\u0238\27\uffff\1\u0239",
            "\1\u023a\11\uffff\1\122\2\uffff\1\124",
            "\1\u023a\11\uffff\1\122\2\uffff\1\124",
            "\1\u023b",
            "\1\u023c\27\uffff\1\u023d",
            "\1\u023e",
            "\1\u0240\31\uffff\1\u023f",
            "\1\u0241",
            "\1\u0242",
            "\1\u0243",
            "\1\u016f",
            "\1\u0102\31\uffff\1\u0103\3\uffff\1\u0104",
            "\1\u0245\31\uffff\1\u0244",
            "\1\u0246",
            "\1\u0247",
            "\1\u0248",
            "\1\u0174",
            "\1\u0108\31\uffff\1\u0109",
            "\1\u0249\31\uffff\1\u024a\3\uffff\1\u024b",
            "\1\u0249\31\uffff\1\u024a\3\uffff\1\u024b",
            "\1\u024c",
            "\1\u024d",
            "\1\u024e",
            "\1\u0250\1\u024f\12\uffff\1\u0251\7\uffff\1\u0252",
            "\1\u017b\31\uffff\1\u017c",
            "\1\u017e",
            "\1\u017b\31\uffff\1\u017c",
            "\1\u0253\27\uffff\1\u0254",
            "\1\u0256\1\u0255\12\uffff\1\u0257\7\uffff\1\u0258",
            "\1\u0180\31\uffff\1\u0181",
            "\1\u0183",
            "\1\u0180\31\uffff\1\u0181",
            "\1\u0259\27\uffff\1\u025a",
            "\1\u025c\31\uffff\1\u025b",
            "\1\u025d",
            "\1\u025e",
            "\1\u025f",
            "\1\u0189",
            "\1\u011c\31\uffff\1\u011d\3\uffff\1\u011e",
            "\1\u0260",
            "\1\u0260",
            "\1\u0261",
            "\1\u0262\27\uffff\1\u0263",
            "\1\u0264",
            "\1\11\11\uffff\1\u0265\11\uffff\1\6\2\uffff\1\7\2\uffff\1\12",
            "\1\11\11\uffff\1\u0265\11\uffff\1\6\2\uffff\1\7\2\uffff\1\12",
            "\1\105",
            "\1\u0267",
            "\1\u0268\27\uffff\1\u0269",
            "\1\u026a",
            "\1\u026c\31\uffff\1\u026b",
            "\1\u026d",
            "\1\u026e",
            "\1\u026f",
            "\1\u0198",
            "\1\u012c\31\uffff\1\u012d\3\uffff\1\u00ab",
            "\1\u0271\1\u0270\12\uffff\1\u0272\7\uffff\1\u0273",
            "\1\u0199\31\uffff\1\u019a\3\uffff\1\u011e",
            "\1\u019c",
            "\1\u0199\31\uffff\1\u019a\3\uffff\1\u011e",
            "\1\u0274\27\uffff\1\u0275",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u0276",
            "\1\u0277",
            "\1\u0278",
            "\1\u027a\31\uffff\1\u0279",
            "\1\u027b",
            "\1\u027c",
            "\1\u027d",
            "\1\u01a8",
            "\1\u013d\31\uffff\1\u013e",
            "\1\u027e\31\uffff\1\u027f\3\uffff\1\u0280",
            "\1\u027e\31\uffff\1\u027f\3\uffff\1\u0280",
            "\1\u0281",
            "\1\u0282",
            "\1\u0283",
            "\1\u0285\1\u0284\12\uffff\1\u0286\7\uffff\1\u0287",
            "\1\u01af\31\uffff\1\u01b0",
            "\1\u01b2",
            "\1\u01af\31\uffff\1\u01b0",
            "\1\u0288\27\uffff\1\u0289",
            "\1\u028b\1\u028a\12\uffff\1\u028c\7\uffff\1\u028d",
            "\1\u01b4\31\uffff\1\u01b5",
            "\1\u01b7",
            "\1\u01b4\31\uffff\1\u01b5",
            "\1\u028e\27\uffff\1\u028f",
            "\1\u0291\31\uffff\1\u0290",
            "\1\u0292",
            "\1\u0293",
            "\1\u0294",
            "\1\u01bd",
            "\1\u0151\31\uffff\1\u0152\3\uffff\1\u0153",
            "\1\u0295",
            "\1\u0295",
            "\1\u0296",
            "\1\u0297\27\uffff\1\u0298",
            "\1\u0299",
            "\1\22\11\uffff\1\u029a\11\uffff\1\17\2\uffff\1\20\2\uffff\1"+
            "\23",
            "\1\22\11\uffff\1\u029a\11\uffff\1\17\2\uffff\1\20\2\uffff\1"+
            "\23",
            "\1\105",
            "\1\u029c",
            "\1\u029d\27\uffff\1\u029e",
            "\1\u029f",
            "\1\u02a1\31\uffff\1\u02a0",
            "\1\u02a2",
            "\1\u02a3",
            "\1\u02a4",
            "\1\u01cc",
            "\1\u0161\31\uffff\1\u0162\3\uffff\1\u00e0",
            "\1\u02a6\1\u02a5\12\uffff\1\u02a7\7\uffff\1\u02a8",
            "\1\u01cd\31\uffff\1\u01ce\3\uffff\1\u0153",
            "\1\u01d0",
            "\1\u01cd\31\uffff\1\u01ce\3\uffff\1\u0153",
            "\1\u02a9\27\uffff\1\u02aa",
            "\1\u0102\31\uffff\1\u0103\3\uffff\1\u0104",
            "\1\u0102\31\uffff\1\u0103\3\uffff\1\u0104",
            "\1\u02ab",
            "\1\u02ac",
            "\1\u02ad",
            "\1\u02ae\31\uffff\1\u02af\3\uffff\1\u02b0",
            "\1\u02ae\31\uffff\1\u02af\3\uffff\1\u02b0",
            "\1\u02b1",
            "\1\u02b2",
            "\1\u02b3",
            "\1\u02b4\11\uffff\1\24\2\uffff\1\26",
            "\1\u02b4\11\uffff\1\24\2\uffff\1\26",
            "\1\u02b5\31\uffff\1\u02b6\3\uffff\1\162",
            "\1\u02b7",
            "\1\u02b8\27\uffff\1\u02b9",
            "\1\u02ba",
            "\1\u02bc\31\uffff\1\u02bb",
            "\1\u02bd",
            "\1\u02be",
            "\1\u02bf",
            "\1\u01e7",
            "\1\u017b\31\uffff\1\u017c",
            "\1\u02c1\31\uffff\1\u02c0",
            "\1\u02c2",
            "\1\u02c3",
            "\1\u02c4",
            "\1\u01ec",
            "\1\u0180\31\uffff\1\u0181",
            "\1\u02c5\31\uffff\1\u02c6\3\uffff\1\u02c7",
            "\1\u02c5\31\uffff\1\u02c6\3\uffff\1\u02c7",
            "\1\u02c8",
            "\1\u02c9",
            "\1\u02ca",
            "\1\u02cc\1\u02cb\12\uffff\1\u02cd\7\uffff\1\u02ce",
            "\1\u01f3\31\uffff\1\u01f4",
            "\1\u01f6",
            "\1\u01f3\31\uffff\1\u01f4",
            "\1\u02cf\27\uffff\1\u02d0",
            "\1\u02d2\1\u02d1\12\uffff\1\u02d3\7\uffff\1\u02d4",
            "",
            "\1\u01f8\31\uffff\1\u01f9\3\uffff\1\u01fa",
            "\1\u01fc",
            "\1\u01f8\31\uffff\1\u01f9\3\uffff\1\u01fa",
            "\1\u02d5\27\uffff\1\u02d6",
            "\1\u012c\31\uffff\1\u012d\3\uffff\1\u00ab",
            "\1\u012c\31\uffff\1\u012d\3\uffff\1\u00ab",
            "\1\u02d7",
            "\1\u02d8",
            "\1\u02d9",
            "\1\u02db\31\uffff\1\u02da",
            "\1\u02dc",
            "\1\u02dd",
            "\1\u02de",
            "\1\u0208",
            "\1\u0199\31\uffff\1\u019a\3\uffff\1\u011e",
            "\1\u02df",
            "\1\u02e0\27\uffff\1\u02e1",
            "\1\u02e2",
            "\1\u02e3\31\uffff\1\u02e4\3\uffff\1\u02e5",
            "\1\u02e3\31\uffff\1\u02e4\3\uffff\1\u02e5",
            "\1\u02e6",
            "\1\u02e7",
            "\1\u02e8",
            "\1\u02e9\11\uffff\1\44\2\uffff\1\46",
            "\1\u02e9\11\uffff\1\44\2\uffff\1\46",
            "\1\u02ea\31\uffff\1\u02eb\3\uffff\1\u0096",
            "\1\u02ec",
            "\1\u02ed\27\uffff\1\u02ee",
            "\1\u02ef",
            "\1\u02f1\31\uffff\1\u02f0",
            "\1\u02f2",
            "\1\u02f3",
            "\1\u02f4",
            "\1\u021d",
            "\1\u01af\31\uffff\1\u01b0",
            "\1\u02f6\31\uffff\1\u02f5",
            "\1\u02f7",
            "\1\u02f8",
            "\1\u02f9",
            "\1\u0222",
            "\1\u01b4\31\uffff\1\u01b5",
            "\1\u02fa\31\uffff\1\u02fb\3\uffff\1\u02fc",
            "\1\u02fa\31\uffff\1\u02fb\3\uffff\1\u02fc",
            "\1\u02fd",
            "\1\u02fe",
            "\1\u02ff",
            "\1\u0301\1\u0300\12\uffff\1\u0302\7\uffff\1\u0303",
            "\1\u0229\31\uffff\1\u022a",
            "\1\u022c",
            "\1\u0229\31\uffff\1\u022a",
            "\1\u0304\27\uffff\1\u0305",
            "\1\u0307\1\u0306\12\uffff\1\u0308\7\uffff\1\u0309",
            "",
            "\1\u022e\31\uffff\1\u022f\3\uffff\1\u0230",
            "\1\u0232",
            "\1\u022e\31\uffff\1\u022f\3\uffff\1\u0230",
            "\1\u030a\27\uffff\1\u030b",
            "\1\u0161\31\uffff\1\u0162\3\uffff\1\u00e0",
            "\1\u0161\31\uffff\1\u0162\3\uffff\1\u00e0",
            "\1\u030c",
            "\1\u030d",
            "\1\u030e",
            "\1\u0310\31\uffff\1\u030f",
            "\1\u0311",
            "\1\u0312",
            "\1\u0313",
            "\1\u023e",
            "\1\u01cd\31\uffff\1\u01ce\3\uffff\1\u0153",
            "\1\u0314",
            "\1\u0315\27\uffff\1\u0316",
            "\1\u0317",
            "\1\u0318",
            "\1\u0318",
            "\1\u0319\31\uffff\1\u031a\3\uffff\1\u00ab",
            "\1\u031b",
            "\1\u031c\27\uffff\1\u031d",
            "\1\u031e",
            "\1\u0320\1\u031f\12\uffff\1\u0321\7\uffff\1\u0322",
            "\1\11\11\uffff\1\u0323\11\uffff\1\6\2\uffff\1\7\2\uffff\1\12",
            "\1\11\11\uffff\1\u0323\11\uffff\1\6\2\uffff\1\7\2\uffff\1\12",
            "\1\u0249\31\uffff\1\u024a\3\uffff\1\u024b",
            "\1\u024d",
            "\1\u0249\31\uffff\1\u024a\3\uffff\1\u024b",
            "\1\u0324\27\uffff\1\u0325",
            "\1\u0326\31\uffff\1\u0327\3\uffff\1\u0328",
            "\1\u0326\31\uffff\1\u0327\3\uffff\1\u0328",
            "\1\u0329",
            "\1\u032a",
            "\1\u032b",
            "\1\u032c\31\uffff\1\u032d\3\uffff\1\u032e",
            "\1\u032c\31\uffff\1\u032d\3\uffff\1\u032e",
            "\1\u032f",
            "\1\u0330",
            "\1\u0331",
            "\1\u0332\11\uffff\1\65\2\uffff\1\67",
            "\1\u0332\11\uffff\1\65\2\uffff\1\67",
            "\1\u02b5\31\uffff\1\u02b6\3\uffff\1\162",
            "\1\u0333",
            "\1\u0334\27\uffff\1\u0335",
            "\1\u0336",
            "\1\u0338\31\uffff\1\u0337",
            "\1\u0339",
            "\1\u033a",
            "\1\u033b",
            "\1\u0264",
            "\1\u01f3\31\uffff\1\u01f4",
            "\1\u033d\31\uffff\1\u033c",
            "\1\u033e",
            "\1\u033f",
            "\1\u0340",
            "\1\u026a",
            "\1\u01f8\31\uffff\1\u01f9\3\uffff\1\u01fa",
            "\1\u0341",
            "\1\u0342\27\uffff\1\u0343",
            "\1\u0344",
            "\1\u0199\31\uffff\1\u019a\3\uffff\1\u011e",
            "\1\u0199\31\uffff\1\u019a\3\uffff\1\u011e",
            "\1\u0345",
            "\1\u0346",
            "\1\u0347",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u0277",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u0348\27\uffff\1\u0349",
            "\1\u034a",
            "\1\u034a",
            "\1\u034b\31\uffff\1\u034c\3\uffff\1\u00e0",
            "\1\u034d",
            "\1\u034e\27\uffff\1\u034f",
            "\1\u0350",
            "\1\u0352\1\u0351\12\uffff\1\u0353\7\uffff\1\u0354",
            "\1\22\11\uffff\1\u0355\11\uffff\1\17\2\uffff\1\20\2\uffff\1"+
            "\23",
            "\1\22\11\uffff\1\u0355\11\uffff\1\17\2\uffff\1\20\2\uffff\1"+
            "\23",
            "\1\u027e\31\uffff\1\u027f\3\uffff\1\u0280",
            "\1\u0282",
            "\1\u027e\31\uffff\1\u027f\3\uffff\1\u0280",
            "\1\u0356\27\uffff\1\u0357",
            "\1\u0358\31\uffff\1\u0359\3\uffff\1\u035a",
            "\1\u0358\31\uffff\1\u0359\3\uffff\1\u035a",
            "\1\u035b",
            "\1\u035c",
            "\1\u035d",
            "\1\u035e\31\uffff\1\u035f\3\uffff\1\u0360",
            "\1\u035e\31\uffff\1\u035f\3\uffff\1\u0360",
            "\1\u0361",
            "\1\u0362",
            "\1\u0363",
            "\1\u0364\11\uffff\1\122\2\uffff\1\124",
            "\1\u0364\11\uffff\1\122\2\uffff\1\124",
            "\1\u02ea\31\uffff\1\u02eb\3\uffff\1\u0096",
            "\1\u0365",
            "\1\u0366\27\uffff\1\u0367",
            "\1\u0368",
            "\1\u036a\31\uffff\1\u0369",
            "\1\u036b",
            "\1\u036c",
            "\1\u036d",
            "\1\u0299",
            "\1\u0229\31\uffff\1\u022a",
            "\1\u036f\31\uffff\1\u036e",
            "\1\u0370",
            "\1\u0371",
            "\1\u0372",
            "\1\u029f",
            "\1\u022e\31\uffff\1\u022f\3\uffff\1\u0230",
            "\1\u0373",
            "\1\u0374\27\uffff\1\u0375",
            "\1\u0376",
            "\1\u01cd\31\uffff\1\u01ce\3\uffff\1\u0153",
            "\1\u01cd\31\uffff\1\u01ce\3\uffff\1\u0153",
            "\1\u0377",
            "\1\u0378",
            "\1\u0379",
            "\1\u0102\31\uffff\1\u0103\3\uffff\1\u0104",
            "\1\u02ac",
            "\1\u0102\31\uffff\1\u0103\3\uffff\1\u0104",
            "\1\u037a\27\uffff\1\u037b",
            "\1\u037d\1\u037c\12\uffff\1\u037e\7\uffff\1\u037f",
            "\1\75\11\uffff\1\24\2\uffff\1\26",
            "\1\75\11\uffff\1\24\2\uffff\1\26",
            "\1\u02ae\31\uffff\1\u02af\3\uffff\1\u02b0",
            "\1\u02b2",
            "\1\u02ae\31\uffff\1\u02af\3\uffff\1\u02b0",
            "\1\u0380\27\uffff\1\u0381",
            "\1\u0383\31\uffff\1\u0382",
            "\1\u0384",
            "\1\u0385",
            "\1\u0386",
            "\1\u0388\1\u0387\12\uffff\1\u0389\7\uffff\1\u038a",
            "\1\u02ba",
            "\1\u0249\31\uffff\1\u024a\3\uffff\1\u024b",
            "\1\u038b",
            "\1\u038b",
            "\1\u0319\31\uffff\1\u031a\3\uffff\1\u00ab",
            "\1\u038c",
            "\1\u038d\27\uffff\1\u038e",
            "\1\u038f",
            "\1\u0390",
            "\1\u0390",
            "\1\u0391\31\uffff\1\u0392\3\uffff\1\u011e",
            "\1\u0393",
            "\1\u0394\27\uffff\1\u0395",
            "\1\u0396",
            "\1\u0398\1\u0397\12\uffff\1\u0399\7\uffff\1\u039a",
            "\1\u02c5\31\uffff\1\u02c6\3\uffff\1\u02c7",
            "\1\u02c9",
            "\1\u02c5\31\uffff\1\u02c6\3\uffff\1\u02c7",
            "\1\u039b\27\uffff\1\u039c",
            "\1\u039d\31\uffff\1\u039e\3\uffff\1\u039f",
            "\1\u039d\31\uffff\1\u039e\3\uffff\1\u039f",
            "\1\u03a0",
            "\1\u03a1",
            "\1\u03a2",
            "\1\u01f8\31\uffff\1\u01f9\3\uffff\1\u01fa",
            "\1\u01f8\31\uffff\1\u01f9\3\uffff\1\u01fa",
            "\1\u03a3",
            "\1\u03a4",
            "\1\u03a5",
            "\1\u012c\31\uffff\1\u012d\3\uffff\1\u00ab",
            "\1\u02d8",
            "\1\u012c\31\uffff\1\u012d\3\uffff\1\u00ab",
            "\1\u03a6\27\uffff\1\u03a7",
            "\1\u03a8",
            "\1\u03a9\27\uffff\1\u03aa",
            "\1\u03ab",
            "\1\u02e2",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u03ad\1\u03ac\12\uffff\1\u03ae\7\uffff\1\u03af",
            "\1\132\11\uffff\1\44\2\uffff\1\46",
            "\1\132\11\uffff\1\44\2\uffff\1\46",
            "\1\u02e3\31\uffff\1\u02e4\3\uffff\1\u02e5",
            "\1\u02e7",
            "\1\u02e3\31\uffff\1\u02e4\3\uffff\1\u02e5",
            "\1\u03b0\27\uffff\1\u03b1",
            "\1\u03b3\31\uffff\1\u03b2",
            "\1\u03b4",
            "\1\u03b5",
            "\1\u03b6",
            "\1\u03b8\1\u03b7\12\uffff\1\u03b9\7\uffff\1\u03ba",
            "\1\u02ef",
            "\1\u027e\31\uffff\1\u027f\3\uffff\1\u0280",
            "\1\u03bb",
            "\1\u03bb",
            "\1\u034b\31\uffff\1\u034c\3\uffff\1\u00e0",
            "\1\u03bc",
            "\1\u03bd\27\uffff\1\u03be",
            "\1\u03bf",
            "\1\u03c0",
            "\1\u03c0",
            "\1\u03c1\31\uffff\1\u03c2\3\uffff\1\u0153",
            "\1\u03c3",
            "\1\u03c4\27\uffff\1\u03c5",
            "\1\u03c6",
            "\1\u03c8\1\u03c7\12\uffff\1\u03c9\7\uffff\1\u03ca",
            "\1\u02fa\31\uffff\1\u02fb\3\uffff\1\u02fc",
            "\1\u02fe",
            "\1\u02fa\31\uffff\1\u02fb\3\uffff\1\u02fc",
            "\1\u03cb\27\uffff\1\u03cc",
            "\1\u03cd\31\uffff\1\u03ce\3\uffff\1\u03cf",
            "\1\u03cd\31\uffff\1\u03ce\3\uffff\1\u03cf",
            "\1\u03d0",
            "\1\u03d1",
            "\1\u03d2",
            "\1\u022e\31\uffff\1\u022f\3\uffff\1\u0230",
            "\1\u022e\31\uffff\1\u022f\3\uffff\1\u0230",
            "\1\u03d3",
            "\1\u03d4",
            "\1\u03d5",
            "\1\u0161\31\uffff\1\u0162\3\uffff\1\u00e0",
            "\1\u030d",
            "\1\u0161\31\uffff\1\u0162\3\uffff\1\u00e0",
            "\1\u03d6\27\uffff\1\u03d7",
            "\1\u03d8",
            "\1\u03d9\27\uffff\1\u03da",
            "\1\u03db",
            "\1\u0317",
            "\1\u0102\31\uffff\1\u0103\3\uffff\1\u0104",
            "\1\u03dd\31\uffff\1\u03dc",
            "\1\u03de",
            "\1\u03df",
            "\1\u03e0",
            "\1\u031e",
            "\1\u02ae\31\uffff\1\u02af\3\uffff\1\u02b0",
            "\1\u0249\31\uffff\1\u024a\3\uffff\1\u024b",
            "\1\u0249\31\uffff\1\u024a\3\uffff\1\u024b",
            "\1\u03e1",
            "\1\u03e2",
            "\1\u03e3",
            "\1\u03e5\31\uffff\1\u03e4",
            "\1\u03e6",
            "\1\u03e7",
            "\1\u03e8",
            "\1\u03ea\1\u03e9\12\uffff\1\u03eb\7\uffff\1\u03ec",
            "\1\u0326\31\uffff\1\u0327\3\uffff\1\u0328",
            "\1\u032a",
            "\1\u0326\31\uffff\1\u0327\3\uffff\1\u0328",
            "\1\u03ed\27\uffff\1\u03ee",
            "\1\u03f0\1\u03ef\12\uffff\1\u03f1\7\uffff\1\u03f2",
            "\1\172\11\uffff\1\65\2\uffff\1\67",
            "\1\172\11\uffff\1\65\2\uffff\1\67",
            "\1\u032c\31\uffff\1\u032d\3\uffff\1\u032e",
            "\1\u0330",
            "\1\u032c\31\uffff\1\u032d\3\uffff\1\u032e",
            "\1\u03f3\27\uffff\1\u03f4",
            "\1\u03f6\31\uffff\1\u03f5",
            "\1\u03f7",
            "\1\u03f8",
            "\1\u03f9",
            "\1\u0336",
            "\1\u02c5\31\uffff\1\u02c6\3\uffff\1\u02c7",
            "\1\u03fa",
            "\1\u03fa",
            "\1\u0391\31\uffff\1\u0392\3\uffff\1\u011e",
            "\1\u03fb",
            "\1\u03fc\27\uffff\1\u03fd",
            "\1\u03fe",
            "\1\u03ff",
            "\1\u0400\27\uffff\1\u0401",
            "\1\u0402",
            "\1\u0344",
            "\1\u012c\31\uffff\1\u012d\3\uffff\1\u00ab",
            "\1\u0199\31\uffff\1\u019a\3\uffff\1\u011e",
            "\1\u0346",
            "\1\u0199\31\uffff\1\u019a\3\uffff\1\u011e",
            "\1\u0403\27\uffff\1\u0404",
            "\1\u0406\31\uffff\1\u0405",
            "\1\u0407",
            "\1\u0408",
            "\1\u0409",
            "\1\u0350",
            "\1\u02e3\31\uffff\1\u02e4\3\uffff\1\u02e5",
            "\1\u027e\31\uffff\1\u027f\3\uffff\1\u0280",
            "\1\u027e\31\uffff\1\u027f\3\uffff\1\u0280",
            "\1\u040a",
            "\1\u040b",
            "\1\u040c",
            "\1\u040e\31\uffff\1\u040d",
            "\1\u040f",
            "\1\u0410",
            "\1\u0411",
            "\1\u0413\1\u0412\12\uffff\1\u0414\7\uffff\1\u0415",
            "\1\u0358\31\uffff\1\u0359\3\uffff\1\u035a",
            "\1\u035c",
            "\1\u0358\31\uffff\1\u0359\3\uffff\1\u035a",
            "\1\u0416\27\uffff\1\u0417",
            "\1\u0419\1\u0418\12\uffff\1\u041a\7\uffff\1\u041b",
            "\1\u009e\11\uffff\1\122\2\uffff\1\124",
            "\1\u009e\11\uffff\1\122\2\uffff\1\124",
            "\1\u035e\31\uffff\1\u035f\3\uffff\1\u0360",
            "\1\u0362",
            "\1\u035e\31\uffff\1\u035f\3\uffff\1\u0360",
            "\1\u041c\27\uffff\1\u041d",
            "\1\u041f\31\uffff\1\u041e",
            "\1\u0420",
            "\1\u0421",
            "\1\u0422",
            "\1\u0368",
            "\1\u02fa\31\uffff\1\u02fb\3\uffff\1\u02fc",
            "\1\u0423",
            "\1\u0423",
            "\1\u03c1\31\uffff\1\u03c2\3\uffff\1\u0153",
            "\1\u0424",
            "\1\u0425\27\uffff\1\u0426",
            "\1\u0427",
            "\1\u0428",
            "\1\u0429\27\uffff\1\u042a",
            "\1\u042b",
            "\1\u0376",
            "\1\u0161\31\uffff\1\u0162\3\uffff\1\u00e0",
            "\1\u01cd\31\uffff\1\u01ce\3\uffff\1\u0153",
            "\1\u0378",
            "\1\u01cd\31\uffff\1\u01ce\3\uffff\1\u0153",
            "\1\u042c\27\uffff\1\u042d",
            "\1\u02ae\31\uffff\1\u02af\3\uffff\1\u02b0",
            "\1\u02ae\31\uffff\1\u02af\3\uffff\1\u02b0",
            "\1\u042e",
            "\1\u042f",
            "\1\u0430",
            "\1\u0431",
            "\1\u0432\27\uffff\1\u0433",
            "\1\u0434",
            "\1\u0435\31\uffff\1\u0436\3\uffff\1\162",
            "\1\u0435\31\uffff\1\u0436\3\uffff\1\162",
            "\1\u0437",
            "\1\u0438",
            "\1\u0439",
            "\1\u043b\31\uffff\1\u043a",
            "\1\u043c",
            "\1\u043d",
            "\1\u043e",
            "\1\u038f",
            "\1\u0326\31\uffff\1\u0327\3\uffff\1\u0328",
            "\1\u0440\31\uffff\1\u043f",
            "\1\u0441",
            "\1\u0442",
            "\1\u0443",
            "\1\u0396",
            "\1\u032c\31\uffff\1\u032d\3\uffff\1\u032e",
            "\1\u02c5\31\uffff\1\u02c6\3\uffff\1\u02c7",
            "\1\u02c5\31\uffff\1\u02c6\3\uffff\1\u02c7",
            "\1\u0444",
            "\1\u0445",
            "\1\u0446",
            "\1\u0448\1\u0447\12\uffff\1\u0449\7\uffff\1\u044a",
            "\1\u039d\31\uffff\1\u039e\3\uffff\1\u039f",
            "\1\u03a1",
            "\1\u039d\31\uffff\1\u039e\3\uffff\1\u039f",
            "\1\u044b\27\uffff\1\u044c",
            "\1\u01f8\31\uffff\1\u01f9\3\uffff\1\u01fa",
            "\1\u03a4",
            "\1\u01f8\31\uffff\1\u01f9\3\uffff\1\u01fa",
            "\1\u044d\27\uffff\1\u044e",
            "\1\u03ab",
            "\1\u0199\31\uffff\1\u019a\3\uffff\1\u011e",
            "\1\u02e3\31\uffff\1\u02e4\3\uffff\1\u02e5",
            "\1\u02e3\31\uffff\1\u02e4\3\uffff\1\u02e5",
            "\1\u044f",
            "\1\u0450",
            "\1\u0451",
            "\1\u0452",
            "\1\u0453\27\uffff\1\u0454",
            "\1\u0455",
            "\1\u0456\31\uffff\1\u0457\3\uffff\1\u0096",
            "\1\u0456\31\uffff\1\u0457\3\uffff\1\u0096",
            "\1\u0458",
            "\1\u0459",
            "\1\u045a",
            "\1\u045c\31\uffff\1\u045b",
            "\1\u045d",
            "\1\u045e",
            "\1\u045f",
            "\1\u03bf",
            "\1\u0358\31\uffff\1\u0359\3\uffff\1\u035a",
            "\1\u0461\31\uffff\1\u0460",
            "\1\u0462",
            "\1\u0463",
            "\1\u0464",
            "\1\u03c6",
            "\1\u035e\31\uffff\1\u035f\3\uffff\1\u0360",
            "\1\u02fa\31\uffff\1\u02fb\3\uffff\1\u02fc",
            "\1\u02fa\31\uffff\1\u02fb\3\uffff\1\u02fc",
            "\1\u0465",
            "\1\u0466",
            "\1\u0467",
            "\1\u0469\1\u0468\12\uffff\1\u046a\7\uffff\1\u046b",
            "\1\u03cd\31\uffff\1\u03ce\3\uffff\1\u03cf",
            "\1\u03d1",
            "\1\u03cd\31\uffff\1\u03ce\3\uffff\1\u03cf",
            "\1\u046c\27\uffff\1\u046d",
            "\1\u022e\31\uffff\1\u022f\3\uffff\1\u0230",
            "\1\u03d4",
            "\1\u022e\31\uffff\1\u022f\3\uffff\1\u0230",
            "\1\u046e\27\uffff\1\u046f",
            "\1\u03db",
            "\1\u01cd\31\uffff\1\u01ce\3\uffff\1\u0153",
            "\1\u0470",
            "\1\u0471\27\uffff\1\u0472",
            "\1\u0473",
            "\1\u0249\31\uffff\1\u024a\3\uffff\1\u024b",
            "\1\u03e2",
            "\1\u0249\31\uffff\1\u024a\3\uffff\1\u024b",
            "\1\u0474\27\uffff\1\u0475",
            "\1\11\11\uffff\1\u0476\11\uffff\1\6\2\uffff\1\7\2\uffff\1\12",
            "\1\11\11\uffff\1\u0476\11\uffff\1\6\2\uffff\1\7\2\uffff\1\12",
            "\1\u0477",
            "\1\u0478\27\uffff\1\u0479",
            "\1\u047a",
            "\1\u0326\31\uffff\1\u0327\3\uffff\1\u0328",
            "\1\u0326\31\uffff\1\u0327\3\uffff\1\u0328",
            "\1\u047b",
            "\1\u047c",
            "\1\u047d",
            "\1\u032c\31\uffff\1\u032d\3\uffff\1\u032e",
            "\1\u032c\31\uffff\1\u032d\3\uffff\1\u032e",
            "\1\u047e",
            "\1\u047f",
            "\1\u0480",
            "\1\u0481",
            "\1\u0482\27\uffff\1\u0483",
            "\1\u0484",
            "\1\u0486\31\uffff\1\u0485",
            "\1\u0487",
            "\1\u0488",
            "\1\u0489",
            "\1\u03fe",
            "\1\u039d\31\uffff\1\u039e\3\uffff\1\u039f",
            "\1\u0402",
            "\1\u01f8\31\uffff\1\u01f9\3\uffff\1\u01fa",
            "\1\u048a",
            "\1\u048b\27\uffff\1\u048c",
            "\1\u048d",
            "\1\u027e\31\uffff\1\u027f\3\uffff\1\u0280",
            "\1\u040b",
            "\1\u027e\31\uffff\1\u027f\3\uffff\1\u0280",
            "\1\u048e\27\uffff\1\u048f",
            "\1\22\11\uffff\1\u0490\11\uffff\1\17\2\uffff\1\20\2\uffff\1"+
            "\23",
            "\1\22\11\uffff\1\u0490\11\uffff\1\17\2\uffff\1\20\2\uffff\1"+
            "\23",
            "\1\u0491",
            "\1\u0492\27\uffff\1\u0493",
            "\1\u0494",
            "\1\u0358\31\uffff\1\u0359\3\uffff\1\u035a",
            "\1\u0358\31\uffff\1\u0359\3\uffff\1\u035a",
            "\1\u0495",
            "\1\u0496",
            "\1\u0497",
            "\1\u035e\31\uffff\1\u035f\3\uffff\1\u0360",
            "\1\u035e\31\uffff\1\u035f\3\uffff\1\u0360",
            "\1\u0498",
            "\1\u0499",
            "\1\u049a",
            "\1\u049b",
            "\1\u049c\27\uffff\1\u049d",
            "\1\u049e",
            "\1\u04a0\31\uffff\1\u049f",
            "\1\u04a1",
            "\1\u04a2",
            "\1\u04a3",
            "\1\u0427",
            "\1\u03cd\31\uffff\1\u03ce\3\uffff\1\u03cf",
            "\1\u042b",
            "\1\u022e\31\uffff\1\u022f\3\uffff\1\u0230",
            "\1\u02ae\31\uffff\1\u02af\3\uffff\1\u02b0",
            "\1\u042f",
            "\1\u02ae\31\uffff\1\u02af\3\uffff\1\u02b0",
            "\1\u04a4\27\uffff\1\u04a5",
            "\1\u0434",
            "\1\u0249\31\uffff\1\u024a\3\uffff\1\u024b",
            "\1\u04a7\1\u04a6\12\uffff\1\u04a8\7\uffff\1\u04a9",
            "\1\u0435\31\uffff\1\u0436\3\uffff\1\162",
            "\1\u0438",
            "\1\u0435\31\uffff\1\u0436\3\uffff\1\162",
            "\1\u04aa\27\uffff\1\u04ab",
            "\1\u04ac",
            "\1\u04ad\27\uffff\1\u04ae",
            "\1\u04af",
            "\1\u04b0",
            "\1\u04b1\27\uffff\1\u04b2",
            "\1\u04b3",
            "\1\u02c5\31\uffff\1\u02c6\3\uffff\1\u02c7",
            "\1\u0445",
            "\1\u02c5\31\uffff\1\u02c6\3\uffff\1\u02c7",
            "\1\u04b4\27\uffff\1\u04b5",
            "\1\u039d\31\uffff\1\u039e\3\uffff\1\u039f",
            "\1\u039d\31\uffff\1\u039e\3\uffff\1\u039f",
            "\1\u04b6",
            "\1\u04b7",
            "\1\u04b8",
            "\1\u02e3\31\uffff\1\u02e4\3\uffff\1\u02e5",
            "\1\u0450",
            "\1\u02e3\31\uffff\1\u02e4\3\uffff\1\u02e5",
            "\1\u04b9\27\uffff\1\u04ba",
            "\1\u0455",
            "\1\u027e\31\uffff\1\u027f\3\uffff\1\u0280",
            "\1\u04bc\1\u04bb\12\uffff\1\u04bd\7\uffff\1\u04be",
            "\1\u0456\31\uffff\1\u0457\3\uffff\1\u0096",
            "\1\u0459",
            "\1\u0456\31\uffff\1\u0457\3\uffff\1\u0096",
            "\1\u04bf\27\uffff\1\u04c0",
            "\1\u04c1",
            "\1\u04c2\27\uffff\1\u04c3",
            "\1\u04c4",
            "\1\u04c5",
            "\1\u04c6\27\uffff\1\u04c7",
            "\1\u04c8",
            "\1\u02fa\31\uffff\1\u02fb\3\uffff\1\u02fc",
            "\1\u0466",
            "\1\u02fa\31\uffff\1\u02fb\3\uffff\1\u02fc",
            "\1\u04c9\27\uffff\1\u04ca",
            "\1\u03cd\31\uffff\1\u03ce\3\uffff\1\u03cf",
            "\1\u03cd\31\uffff\1\u03ce\3\uffff\1\u03cf",
            "\1\u04cb",
            "\1\u04cc",
            "\1\u04cd",
            "\1\u0473",
            "\1\u02ae\31\uffff\1\u02af\3\uffff\1\u02b0",
            "\1\u04cf\31\uffff\1\u04ce",
            "\1\u04d0",
            "\1\u04d1",
            "\1\u04d2",
            "\1\u047a",
            "\1\u0435\31\uffff\1\u0436\3\uffff\1\162",
            "\1\u0326\31\uffff\1\u0327\3\uffff\1\u0328",
            "\1\u047c",
            "\1\u0326\31\uffff\1\u0327\3\uffff\1\u0328",
            "\1\u04d3\27\uffff\1\u04d4",
            "\1\u032c\31\uffff\1\u032d\3\uffff\1\u032e",
            "\1\u047f",
            "\1\u032c\31\uffff\1\u032d\3\uffff\1\u032e",
            "\1\u04d5\27\uffff\1\u04d6",
            "\1\u0484",
            "\1\u02c5\31\uffff\1\u02c6\3\uffff\1\u02c7",
            "\1\u04d7",
            "\1\u04d8\27\uffff\1\u04d9",
            "\1\u04da",
            "\1\u048d",
            "\1\u02e3\31\uffff\1\u02e4\3\uffff\1\u02e5",
            "\1\u04dc\31\uffff\1\u04db",
            "\1\u04dd",
            "\1\u04de",
            "\1\u04df",
            "\1\u0494",
            "\1\u0456\31\uffff\1\u0457\3\uffff\1\u0096",
            "\1\u0358\31\uffff\1\u0359\3\uffff\1\u035a",
            "\1\u0496",
            "\1\u0358\31\uffff\1\u0359\3\uffff\1\u035a",
            "\1\u04e0\27\uffff\1\u04e1",
            "\1\u035e\31\uffff\1\u035f\3\uffff\1\u0360",
            "\1\u0499",
            "\1\u035e\31\uffff\1\u035f\3\uffff\1\u0360",
            "\1\u04e2\27\uffff\1\u04e3",
            "\1\u049e",
            "\1\u02fa\31\uffff\1\u02fb\3\uffff\1\u02fc",
            "\1\u04e4",
            "\1\u04e5\27\uffff\1\u04e6",
            "\1\u04e7",
            "\1\u0435\31\uffff\1\u0436\3\uffff\1\162",
            "\1\u0435\31\uffff\1\u0436\3\uffff\1\162",
            "\1\u04e8",
            "\1\u04e9",
            "\1\u04ea",
            "\1\u04af",
            "\1\u0326\31\uffff\1\u0327\3\uffff\1\u0328",
            "\1\u04b3",
            "\1\u032c\31\uffff\1\u032d\3\uffff\1\u032e",
            "\1\u039d\31\uffff\1\u039e\3\uffff\1\u039f",
            "\1\u04b7",
            "\1\u039d\31\uffff\1\u039e\3\uffff\1\u039f",
            "\1\u04eb\27\uffff\1\u04ec",
            "\1\u0456\31\uffff\1\u0457\3\uffff\1\u0096",
            "\1\u0456\31\uffff\1\u0457\3\uffff\1\u0096",
            "\1\u04ed",
            "\1\u04ee",
            "\1\u04ef",
            "\1\u04c4",
            "\1\u0358\31\uffff\1\u0359\3\uffff\1\u035a",
            "\1\u04c8",
            "\1\u035e\31\uffff\1\u035f\3\uffff\1\u0360",
            "\1\u03cd\31\uffff\1\u03ce\3\uffff\1\u03cf",
            "\1\u04cc",
            "\1\u03cd\31\uffff\1\u03ce\3\uffff\1\u03cf",
            "\1\u04f0\27\uffff\1\u04f1",
            "\1\u04f2",
            "\1\u04f3\27\uffff\1\u04f4",
            "\1\u04f5",
            "\1\u04da",
            "\1\u039d\31\uffff\1\u039e\3\uffff\1\u039f",
            "\1\u04f6",
            "\1\u04f7\27\uffff\1\u04f8",
            "\1\u04f9",
            "\1\u04e7",
            "\1\u03cd\31\uffff\1\u03ce\3\uffff\1\u03cf",
            "\1\u0435\31\uffff\1\u0436\3\uffff\1\162",
            "\1\u04e9",
            "\1\u0435\31\uffff\1\u0436\3\uffff\1\162",
            "\1\u04fa\27\uffff\1\u04fb",
            "\1\u0456\31\uffff\1\u0457\3\uffff\1\u0096",
            "\1\u04ee",
            "\1\u0456\31\uffff\1\u0457\3\uffff\1\u0096",
            "\1\u04fc\27\uffff\1\u04fd",
            "\1\u04f5",
            "\1\u0435\31\uffff\1\u0436\3\uffff\1\162",
            "\1\u04f9",
            "\1\u0456\31\uffff\1\u0457\3\uffff\1\u0096"
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "()* loopback of 40:4: ( not_final_compound_withlevel_expression | final_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_withlevel_expression | not_compound_withlevel_expression | compound_withlevel_expression | multi_compound_expression | relation ^)*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA9_560 = input.LA(1);

                         
                        int index9_560 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA9_560==EOF||LA9_560==WITHIN_KEYWORD_T) ) {s = 69;}

                        else if ( (synpred9_LogKVQuery()) ) {s = 243;}

                        else if ( (synpred11_LogKVQuery()) ) {s = 244;}

                        else if ( (synpred13_LogKVQuery()) ) {s = 667;}

                         
                        input.seek(index9_560);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA9_114 = input.LA(1);

                         
                        int index9_114 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA9_114==EOF||LA9_114==WITHIN_KEYWORD_T) ) {s = 69;}

                        else if ( (synpred10_LogKVQuery()) ) {s = 190;}

                        else if ( (synpred12_LogKVQuery()) ) {s = 191;}

                         
                        input.seek(index9_114);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA9_315 = input.LA(1);

                         
                        int index9_315 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred15_LogKVQuery()) ) {s = 70;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index9_315);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA9_213 = input.LA(1);

                         
                        int index9_213 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred15_LogKVQuery()) ) {s = 70;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index9_213);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA9_211 = input.LA(1);

                         
                        int index9_211 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred15_LogKVQuery()) ) {s = 70;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index9_211);

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA9_71 = input.LA(1);

                         
                        int index9_71 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred15_LogKVQuery()) ) {s = 70;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index9_71);

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA9_67 = input.LA(1);

                         
                        int index9_67 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred15_LogKVQuery()) ) {s = 70;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index9_67);

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA9_316 = input.LA(1);

                         
                        int index9_316 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred15_LogKVQuery()) ) {s = 70;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index9_316);

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA9_214 = input.LA(1);

                         
                        int index9_214 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred15_LogKVQuery()) ) {s = 70;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index9_214);

                        if ( s>=0 ) return s;
                        break;

                    case 9 : 
                        int LA9_212 = input.LA(1);

                         
                        int index9_212 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred15_LogKVQuery()) ) {s = 70;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index9_212);

                        if ( s>=0 ) return s;
                        break;

                    case 10 : 
                        int LA9_72 = input.LA(1);

                         
                        int index9_72 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred15_LogKVQuery()) ) {s = 70;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index9_72);

                        if ( s>=0 ) return s;
                        break;

                    case 11 : 
                        int LA9_68 = input.LA(1);

                         
                        int index9_68 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred15_LogKVQuery()) ) {s = 70;}

                        else if ( (true) ) {s = 69;}

                         
                        input.seek(index9_68);

                        if ( s>=0 ) return s;
                        break;

                    case 12 : 
                        int LA9_150 = input.LA(1);

                         
                        int index9_150 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA9_150==EOF||LA9_150==WITHIN_KEYWORD_T) ) {s = 69;}

                        else if ( (synpred9_LogKVQuery()) ) {s = 243;}

                        else if ( (synpred11_LogKVQuery()) ) {s = 244;}

                         
                        input.seek(index9_150);

                        if ( s>=0 ) return s;
                        break;

                    case 13 : 
                        int LA9_506 = input.LA(1);

                         
                        int index9_506 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA9_506==EOF||LA9_506==WITHIN_KEYWORD_T) ) {s = 69;}

                        else if ( (synpred10_LogKVQuery()) ) {s = 190;}

                        else if ( (synpred12_LogKVQuery()) ) {s = 191;}

                        else if ( (synpred14_LogKVQuery()) ) {s = 614;}

                         
                        input.seek(index9_506);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 9, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA10_eotS =
        "\u01db\uffff";
    static final String DFA10_eofS =
        "\u01db\uffff";
    static final String DFA10_minS =
        "\1\17\1\31\1\5\1\uffff\1\5\1\17\1\31\1\10\1\uffff\1\17\1\31\1\10"+
        "\1\uffff\1\10\2\17\1\51\1\31\1\24\1\10\2\17\1\51\1\31\1\24\1\17"+
        "\1\51\1\31\1\24\1\10\3\5\1\51\1\31\1\17\1\51\1\31\1\24\1\10\3\5"+
        "\1\51\1\31\3\5\1\51\1\31\1\17\1\51\1\31\1\24\2\5\1\0\1\51\1\13\1"+
        "\51\3\5\1\51\1\31\1\17\1\51\1\31\1\24\2\5\1\0\1\51\1\13\1\51\2\17"+
        "\1\51\1\13\1\51\3\5\1\51\1\31\1\10\1\uffff\1\5\1\51\1\5\1\13\2\17"+
        "\1\51\1\13\1\51\3\5\1\51\1\31\1\10\1\uffff\1\5\1\51\1\5\1\13\1\10"+
        "\1\5\1\51\1\5\1\13\2\17\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1\51"+
        "\1\5\1\10\1\5\1\51\1\5\1\13\2\17\1\51\1\13\1\51\1\17\1\51\1\31\1"+
        "\24\1\51\1\5\1\17\1\51\1\31\1\24\1\51\1\5\1\10\1\5\1\51\1\5\1\13"+
        "\3\5\1\51\1\31\1\17\1\51\1\31\1\24\1\51\1\5\1\10\1\5\1\51\1\5\1"+
        "\13\3\5\1\51\1\31\3\5\1\51\1\31\1\17\1\51\1\31\1\24\1\51\3\5\1\0"+
        "\1\51\1\13\1\51\3\5\1\51\1\31\1\17\1\51\1\31\1\24\1\51\3\5\1\0\1"+
        "\51\1\13\1\51\2\17\1\5\1\51\1\13\1\51\3\5\1\51\1\31\1\10\1\uffff"+
        "\1\5\1\51\1\5\1\13\2\17\1\5\1\51\1\13\1\51\3\5\1\51\1\31\1\10\1"+
        "\uffff\1\5\1\51\1\5\1\13\1\10\3\5\1\51\1\5\1\13\2\17\1\5\1\51\1"+
        "\13\1\51\1\17\1\51\1\31\1\24\1\51\1\5\1\10\3\5\1\51\1\5\1\13\2\17"+
        "\1\5\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1\51\1\5\1\17\1\51\1\31"+
        "\1\24\1\10\1\51\1\5\1\10\1\5\1\51\1\5\1\13\3\5\1\51\1\31\1\17\1"+
        "\51\1\31\1\24\1\10\1\51\1\5\1\10\1\5\1\51\1\5\1\13\3\5\1\51\1\31"+
        "\3\5\1\51\1\31\1\17\1\51\1\31\1\24\1\17\1\51\1\31\1\24\1\51\1\5"+
        "\1\51\1\13\1\51\3\5\1\51\1\31\1\17\1\51\1\31\1\24\1\17\1\51\1\31"+
        "\1\24\1\51\1\5\1\51\1\13\2\51\1\13\1\51\3\5\1\51\1\31\3\5\1\51\1"+
        "\31\1\5\1\51\1\5\1\13\1\51\1\13\1\51\3\5\1\51\1\31\3\5\1\51\1\31"+
        "\1\5\1\51\1\5\1\13\1\5\1\51\1\5\1\13\2\5\1\51\1\13\2\51\1\13\2\51"+
        "\2\5\1\51\1\5\1\13\2\5\1\51\1\13\2\51\1\13\2\51\1\5\1\51\1\5\1\10"+
        "\1\5\1\51\1\5\1\13\1\5\1\51\1\5\1\13\1\51\1\5\1\10\1\5\1\51\1\5"+
        "\1\13\1\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24\1\51\1\5\1\51\1\5\1"+
        "\17\1\51\1\31\1\24\1\51\1\5\1\51\4\5\1\51\1\31\3\5\1\51\1\31\1\51"+
        "\1\13\2\51\1\13\1\51\1\5\1\51\1\5\1\13\1\5\1\51\1\5\1\13\1\51\1"+
        "\5\1\51\1\5";
    static final String DFA10_maxS =
        "\1\34\1\31\1\37\1\uffff\1\37\1\34\1\31\1\34\1\uffff\1\34\1\31\1"+
        "\34\1\uffff\2\34\2\51\1\31\1\24\2\34\2\51\1\31\1\24\2\51\1\31\1"+
        "\24\1\34\2\43\1\5\1\51\1\31\2\51\1\31\1\24\1\34\2\43\1\5\1\51\1"+
        "\31\2\43\1\5\1\51\1\31\2\51\1\31\1\24\2\37\1\0\1\51\1\43\1\51\2"+
        "\43\1\5\1\51\1\31\2\51\1\31\1\24\2\37\1\0\1\51\1\43\1\51\2\34\1"+
        "\51\1\43\1\51\2\43\1\5\1\51\1\31\1\34\1\uffff\1\43\1\51\2\43\2\34"+
        "\1\51\1\43\1\51\2\43\1\5\1\51\1\31\1\34\1\uffff\1\43\1\51\2\43\1"+
        "\34\1\43\1\51\2\43\2\34\1\51\1\43\3\51\1\31\1\24\1\51\1\43\1\34"+
        "\1\43\1\51\2\43\2\34\1\51\1\43\3\51\1\31\1\24\1\51\1\43\2\51\1\31"+
        "\1\24\1\51\1\43\1\34\1\43\1\51\4\43\1\5\1\51\1\31\2\51\1\31\1\24"+
        "\1\51\1\43\1\34\1\43\1\51\4\43\1\5\1\51\1\31\2\43\1\5\1\51\1\31"+
        "\2\51\1\31\1\24\1\51\1\43\2\37\1\0\1\51\1\43\1\51\2\43\1\5\1\51"+
        "\1\31\2\51\1\31\1\24\1\51\1\43\2\37\1\0\1\51\1\43\1\51\2\34\1\43"+
        "\1\51\1\43\1\51\2\43\1\5\1\51\1\31\1\34\1\uffff\1\43\1\51\2\43\2"+
        "\34\1\43\1\51\1\43\1\51\2\43\1\5\1\51\1\31\1\34\1\uffff\1\43\1\51"+
        "\2\43\1\34\2\37\1\43\1\51\2\43\2\34\1\43\1\51\1\43\3\51\1\31\1\24"+
        "\1\51\1\43\1\34\2\37\1\43\1\51\2\43\2\34\1\43\1\51\1\43\3\51\1\31"+
        "\1\24\1\51\1\43\2\51\1\31\1\24\1\34\1\51\1\43\1\34\1\43\1\51\4\43"+
        "\1\5\1\51\1\31\2\51\1\31\1\24\1\34\1\51\1\43\1\34\1\43\1\51\4\43"+
        "\1\5\1\51\1\31\2\43\1\5\1\51\1\31\2\51\1\31\1\24\2\51\1\31\1\24"+
        "\1\51\1\43\1\51\1\43\1\51\2\43\1\5\1\51\1\31\2\51\1\31\1\24\2\51"+
        "\1\31\1\24\1\51\1\43\1\51\1\43\2\51\1\43\1\51\2\43\1\5\1\51\1\31"+
        "\2\43\1\5\1\51\1\31\1\43\1\51\2\43\1\51\1\43\1\51\2\43\1\5\1\51"+
        "\1\31\2\43\1\5\1\51\1\31\1\43\1\51\3\43\1\51\2\43\2\37\1\51\1\43"+
        "\2\51\1\43\2\51\2\43\1\51\2\43\2\37\1\51\1\43\2\51\1\43\2\51\1\43"+
        "\1\51\1\43\1\34\1\43\1\51\3\43\1\51\2\43\1\51\1\43\1\34\1\43\1\51"+
        "\3\43\1\51\2\43\2\51\1\31\1\24\1\51\1\43\1\51\1\43\2\51\1\31\1\24"+
        "\1\51\1\43\1\51\3\43\1\5\1\51\1\31\2\43\1\5\1\51\1\31\1\51\1\43"+
        "\2\51\1\43\1\51\1\43\1\51\3\43\1\51\2\43\1\51\1\43\1\51\1\43";
    static final String DFA10_acceptS =
        "\3\uffff\1\7\4\uffff\1\2\3\uffff\1\1\111\uffff\1\4\17\uffff\1\3"+
        "\162\uffff\1\6\20\uffff\1\5\u00f0\uffff";
    static final String DFA10_specialS =
        "\70\uffff\1\0\16\uffff\1\2\160\uffff\1\1\20\uffff\1\3\u0111\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\3\11\uffff\1\2\2\uffff\1\1",
            "\1\4",
            "\1\10\11\uffff\1\7\11\uffff\1\5\2\uffff\1\6\2\uffff\1\10",
            "",
            "\1\14\11\uffff\1\13\11\uffff\1\11\2\uffff\1\12\2\uffff\1\14",
            "\1\15\11\uffff\1\10\2\uffff\1\10",
            "\1\16",
            "\1\20\1\17\12\uffff\1\21\7\uffff\1\22",
            "",
            "\1\23\11\uffff\1\14\2\uffff\1\14",
            "\1\24",
            "\1\26\1\25\12\uffff\1\27\7\uffff\1\30",
            "",
            "\1\32\1\31\12\uffff\1\33\7\uffff\1\34",
            "\1\35\11\uffff\1\10\2\uffff\1\10",
            "\1\37\31\uffff\1\36",
            "\1\40",
            "\1\41",
            "\1\42",
            "\1\44\1\43\12\uffff\1\45\7\uffff\1\46",
            "\1\47\11\uffff\1\14\2\uffff\1\14",
            "\1\51\31\uffff\1\50",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\56\31\uffff\1\55",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\63\1\62\12\uffff\1\64\7\uffff\1\65",
            "\1\66\31\uffff\1\67\3\uffff\1\70",
            "\1\66\31\uffff\1\67\3\uffff\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\75\31\uffff\1\74",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\102\1\101\12\uffff\1\103\7\uffff\1\104",
            "\1\105\31\uffff\1\106\3\uffff\1\107",
            "\1\105\31\uffff\1\106\3\uffff\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113\31\uffff\1\114\3\uffff\1\10",
            "\1\113\31\uffff\1\114\3\uffff\1\10",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\121\31\uffff\1\120",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\10\11\uffff\1\125\11\uffff\1\5\2\uffff\1\6\2\uffff\1\10",
            "\1\10\11\uffff\1\125\11\uffff\1\5\2\uffff\1\6\2\uffff\1\10",
            "\1\uffff",
            "\1\127",
            "\1\130\27\uffff\1\131",
            "\1\132",
            "\1\133\31\uffff\1\134\3\uffff\1\14",
            "\1\133\31\uffff\1\134\3\uffff\1\14",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\141\31\uffff\1\140",
            "\1\142",
            "\1\143",
            "\1\144",
            "\1\14\11\uffff\1\145\11\uffff\1\11\2\uffff\1\12\2\uffff\1\14",
            "\1\14\11\uffff\1\145\11\uffff\1\11\2\uffff\1\12\2\uffff\1\14",
            "\1\uffff",
            "\1\147",
            "\1\150\27\uffff\1\151",
            "\1\152",
            "\1\153\11\uffff\1\10\2\uffff\1\10",
            "\1\153\11\uffff\1\10\2\uffff\1\10",
            "\1\154",
            "\1\155\27\uffff\1\156",
            "\1\157",
            "\1\160\31\uffff\1\161\3\uffff\1\10",
            "\1\160\31\uffff\1\161\3\uffff\1\10",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\166\1\165\12\uffff\1\167\7\uffff\1\170",
            "",
            "\1\66\31\uffff\1\67\3\uffff\1\70",
            "\1\72",
            "\1\66\31\uffff\1\67\3\uffff\1\70",
            "\1\171\27\uffff\1\172",
            "\1\173\11\uffff\1\14\2\uffff\1\14",
            "\1\173\11\uffff\1\14\2\uffff\1\14",
            "\1\174",
            "\1\175\27\uffff\1\176",
            "\1\177",
            "\1\u0080\31\uffff\1\u0081\3\uffff\1\14",
            "\1\u0080\31\uffff\1\u0081\3\uffff\1\14",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0086\1\u0085\12\uffff\1\u0087\7\uffff\1\u0088",
            "",
            "\1\105\31\uffff\1\106\3\uffff\1\107",
            "\1\111",
            "\1\105\31\uffff\1\106\3\uffff\1\107",
            "\1\u0089\27\uffff\1\u008a",
            "\1\u008c\1\u008b\12\uffff\1\u008d\7\uffff\1\u008e",
            "\1\113\31\uffff\1\114\3\uffff\1\10",
            "\1\116",
            "\1\113\31\uffff\1\114\3\uffff\1\10",
            "\1\u008f\27\uffff\1\u0090",
            "\1\u0091\11\uffff\1\10\2\uffff\1\10",
            "\1\u0091\11\uffff\1\10\2\uffff\1\10",
            "\1\u0092",
            "\1\u0093\27\uffff\1\u0094",
            "\1\u0095",
            "\1\u0097\31\uffff\1\u0096",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\132",
            "\1\66\31\uffff\1\67\3\uffff\1\70",
            "\1\u009c\1\u009b\12\uffff\1\u009d\7\uffff\1\u009e",
            "\1\133\31\uffff\1\134\3\uffff\1\14",
            "\1\136",
            "\1\133\31\uffff\1\134\3\uffff\1\14",
            "\1\u009f\27\uffff\1\u00a0",
            "\1\u00a1\11\uffff\1\14\2\uffff\1\14",
            "\1\u00a1\11\uffff\1\14\2\uffff\1\14",
            "\1\u00a2",
            "\1\u00a3\27\uffff\1\u00a4",
            "\1\u00a5",
            "\1\u00a7\31\uffff\1\u00a6",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\152",
            "\1\105\31\uffff\1\106\3\uffff\1\107",
            "\1\u00ac\31\uffff\1\u00ab",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\157",
            "\1\113\31\uffff\1\114\3\uffff\1\10",
            "\1\u00b1\1\u00b0\12\uffff\1\u00b2\7\uffff\1\u00b3",
            "\1\160\31\uffff\1\161\3\uffff\1\10",
            "\1\163",
            "\1\160\31\uffff\1\161\3\uffff\1\10",
            "\1\u00b4\27\uffff\1\u00b5",
            "\1\u00b6\31\uffff\1\u00b7\3\uffff\1\u00b8",
            "\1\u00b6\31\uffff\1\u00b7\3\uffff\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bd\31\uffff\1\u00bc",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\177",
            "\1\133\31\uffff\1\134\3\uffff\1\14",
            "\1\u00c2\1\u00c1\12\uffff\1\u00c3\7\uffff\1\u00c4",
            "\1\u0080\31\uffff\1\u0081\3\uffff\1\14",
            "\1\u0083",
            "\1\u0080\31\uffff\1\u0081\3\uffff\1\14",
            "\1\u00c5\27\uffff\1\u00c6",
            "\1\u00c7\31\uffff\1\u00c8\3\uffff\1\u00c9",
            "\1\u00c7\31\uffff\1\u00c8\3\uffff\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d4\31\uffff\1\u00d3",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u0095",
            "\1\160\31\uffff\1\161\3\uffff\1\10",
            "\1\10\11\uffff\1\u00d8\11\uffff\1\5\2\uffff\1\6\2\uffff\1\10",
            "\1\10\11\uffff\1\u00d8\11\uffff\1\5\2\uffff\1\6\2\uffff\1\10",
            "\1\uffff",
            "\1\u00da",
            "\1\u00db\27\uffff\1\u00dc",
            "\1\u00dd",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e5\31\uffff\1\u00e4",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00a5",
            "\1\u0080\31\uffff\1\u0081\3\uffff\1\14",
            "\1\14\11\uffff\1\u00e9\11\uffff\1\11\2\uffff\1\12\2\uffff\1"+
            "\14",
            "\1\14\11\uffff\1\u00e9\11\uffff\1\11\2\uffff\1\12\2\uffff\1"+
            "\14",
            "\1\uffff",
            "\1\u00eb",
            "\1\u00ec\27\uffff\1\u00ed",
            "\1\u00ee",
            "\1\u00ef\11\uffff\1\10\2\uffff\1\10",
            "\1\u00ef\11\uffff\1\10\2\uffff\1\10",
            "\1\u00f0\31\uffff\1\u00f1\3\uffff\1\70",
            "\1\u00f2",
            "\1\u00f3\27\uffff\1\u00f4",
            "\1\u00f5",
            "\1\u00f6\31\uffff\1\u00f7\3\uffff\1\u00f8",
            "\1\u00f6\31\uffff\1\u00f7\3\uffff\1\u00f8",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "\1\u00fd\1\u00fc\12\uffff\1\u00fe\7\uffff\1\u00ff",
            "",
            "\1\u00b6\31\uffff\1\u00b7\3\uffff\1\u00b8",
            "\1\u00ba",
            "\1\u00b6\31\uffff\1\u00b7\3\uffff\1\u00b8",
            "\1\u0100\27\uffff\1\u0101",
            "\1\u0102\11\uffff\1\14\2\uffff\1\14",
            "\1\u0102\11\uffff\1\14\2\uffff\1\14",
            "\1\u0103\31\uffff\1\u0104\3\uffff\1\107",
            "\1\u0105",
            "\1\u0106\27\uffff\1\u0107",
            "\1\u0108",
            "\1\u0109\31\uffff\1\u010a\3\uffff\1\u010b",
            "\1\u0109\31\uffff\1\u010a\3\uffff\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\1\u0110\1\u010f\12\uffff\1\u0111\7\uffff\1\u0112",
            "",
            "\1\u00c7\31\uffff\1\u00c8\3\uffff\1\u00c9",
            "\1\u00cb",
            "\1\u00c7\31\uffff\1\u00c8\3\uffff\1\u00c9",
            "\1\u0113\27\uffff\1\u0114",
            "\1\u0116\1\u0115\12\uffff\1\u0117\7\uffff\1\u0118",
            "\1\10\11\uffff\1\u0119\11\uffff\1\5\2\uffff\1\6\2\uffff\1\10",
            "\1\10\11\uffff\1\u0119\11\uffff\1\5\2\uffff\1\6\2\uffff\1\10",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u00d1",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u011a\27\uffff\1\u011b",
            "\1\u011c\11\uffff\1\10\2\uffff\1\10",
            "\1\u011c\11\uffff\1\10\2\uffff\1\10",
            "\1\u00f0\31\uffff\1\u00f1\3\uffff\1\70",
            "\1\u011d",
            "\1\u011e\27\uffff\1\u011f",
            "\1\u0120",
            "\1\u0122\31\uffff\1\u0121",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\1\u00dd",
            "\1\u00b6\31\uffff\1\u00b7\3\uffff\1\u00b8",
            "\1\u0127\1\u0126\12\uffff\1\u0128\7\uffff\1\u0129",
            "\1\14\11\uffff\1\u012a\11\uffff\1\11\2\uffff\1\12\2\uffff\1"+
            "\14",
            "\1\14\11\uffff\1\u012a\11\uffff\1\11\2\uffff\1\12\2\uffff\1"+
            "\14",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u00e2",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u012b\27\uffff\1\u012c",
            "\1\u012d\11\uffff\1\14\2\uffff\1\14",
            "\1\u012d\11\uffff\1\14\2\uffff\1\14",
            "\1\u0103\31\uffff\1\u0104\3\uffff\1\107",
            "\1\u012e",
            "\1\u012f\27\uffff\1\u0130",
            "\1\u0131",
            "\1\u0133\31\uffff\1\u0132",
            "\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "\1\u00ee",
            "\1\u00c7\31\uffff\1\u00c8\3\uffff\1\u00c9",
            "\1\u0138\31\uffff\1\u0137",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b",
            "\1\u013d\1\u013c\12\uffff\1\u013e\7\uffff\1\u013f",
            "\1\u00f5",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u0141\1\u0140\12\uffff\1\u0142\7\uffff\1\u0143",
            "\1\u00f6\31\uffff\1\u00f7\3\uffff\1\u00f8",
            "\1\u00fa",
            "\1\u00f6\31\uffff\1\u00f7\3\uffff\1\u00f8",
            "\1\u0144\27\uffff\1\u0145",
            "\1\u00b6\31\uffff\1\u00b7\3\uffff\1\u00b8",
            "\1\u00b6\31\uffff\1\u00b7\3\uffff\1\u00b8",
            "\1\u0146",
            "\1\u0147",
            "\1\u0148",
            "\1\u014a\31\uffff\1\u0149",
            "\1\u014b",
            "\1\u014c",
            "\1\u014d",
            "\1\u014f\1\u014e\12\uffff\1\u0150\7\uffff\1\u0151",
            "\1\u0108",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u0153\1\u0152\12\uffff\1\u0154\7\uffff\1\u0155",
            "\1\u0109\31\uffff\1\u010a\3\uffff\1\u010b",
            "\1\u010d",
            "\1\u0109\31\uffff\1\u010a\3\uffff\1\u010b",
            "\1\u0156\27\uffff\1\u0157",
            "\1\u00c7\31\uffff\1\u00c8\3\uffff\1\u00c9",
            "\1\u00c7\31\uffff\1\u00c8\3\uffff\1\u00c9",
            "\1\u0158",
            "\1\u0159",
            "\1\u015a",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u015b",
            "\1\u015c",
            "\1\u015d",
            "\1\u015f\31\uffff\1\u015e",
            "\1\u0160",
            "\1\u0161",
            "\1\u0162",
            "\1\u0164\31\uffff\1\u0163",
            "\1\u0165",
            "\1\u0166",
            "\1\u0167",
            "\1\u0120",
            "\1\u00f6\31\uffff\1\u00f7\3\uffff\1\u00f8",
            "\1\u0168",
            "\1\u0169\27\uffff\1\u016a",
            "\1\u016b",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u016c",
            "\1\u016d",
            "\1\u016e",
            "\1\u0170\31\uffff\1\u016f",
            "\1\u0171",
            "\1\u0172",
            "\1\u0173",
            "\1\u0175\31\uffff\1\u0174",
            "\1\u0176",
            "\1\u0177",
            "\1\u0178",
            "\1\u0131",
            "\1\u0109\31\uffff\1\u010a\3\uffff\1\u010b",
            "\1\u0179",
            "\1\u017a\27\uffff\1\u017b",
            "\1\u017c",
            "\1\u017d",
            "\1\u017e\27\uffff\1\u017f",
            "\1\u0180",
            "\1\u0181\31\uffff\1\u0182\3\uffff\1\70",
            "\1\u0181\31\uffff\1\u0182\3\uffff\1\70",
            "\1\u0183",
            "\1\u0184",
            "\1\u0185",
            "\1\u00f6\31\uffff\1\u00f7\3\uffff\1\u00f8",
            "\1\u00f6\31\uffff\1\u00f7\3\uffff\1\u00f8",
            "\1\u0186",
            "\1\u0187",
            "\1\u0188",
            "\1\u00b6\31\uffff\1\u00b7\3\uffff\1\u00b8",
            "\1\u0147",
            "\1\u00b6\31\uffff\1\u00b7\3\uffff\1\u00b8",
            "\1\u0189\27\uffff\1\u018a",
            "\1\u018b",
            "\1\u018c\27\uffff\1\u018d",
            "\1\u018e",
            "\1\u018f\31\uffff\1\u0190\3\uffff\1\107",
            "\1\u018f\31\uffff\1\u0190\3\uffff\1\107",
            "\1\u0191",
            "\1\u0192",
            "\1\u0193",
            "\1\u0109\31\uffff\1\u010a\3\uffff\1\u010b",
            "\1\u0109\31\uffff\1\u010a\3\uffff\1\u010b",
            "\1\u0194",
            "\1\u0195",
            "\1\u0196",
            "\1\u00c7\31\uffff\1\u00c8\3\uffff\1\u00c9",
            "\1\u0159",
            "\1\u00c7\31\uffff\1\u00c8\3\uffff\1\u00c9",
            "\1\u0197\27\uffff\1\u0198",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u015c",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u0199\27\uffff\1\u019a",
            "\1\10\11\uffff\1\u019b\11\uffff\1\5\2\uffff\1\6\2\uffff\1\10",
            "\1\10\11\uffff\1\u019b\11\uffff\1\5\2\uffff\1\6\2\uffff\1\10",
            "\1\u019c",
            "\1\u019d\27\uffff\1\u019e",
            "\1\u019f",
            "\1\u01a0",
            "\1\u01a1\27\uffff\1\u01a2",
            "\1\u01a3",
            "\1\u016b",
            "\1\u00b6\31\uffff\1\u00b7\3\uffff\1\u00b8",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u016d",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u01a4\27\uffff\1\u01a5",
            "\1\14\11\uffff\1\u01a6\11\uffff\1\11\2\uffff\1\12\2\uffff\1"+
            "\14",
            "\1\14\11\uffff\1\u01a6\11\uffff\1\11\2\uffff\1\12\2\uffff\1"+
            "\14",
            "\1\u01a7",
            "\1\u01a8\27\uffff\1\u01a9",
            "\1\u01aa",
            "\1\u01ab",
            "\1\u01ac\27\uffff\1\u01ad",
            "\1\u01ae",
            "\1\u017c",
            "\1\u00c7\31\uffff\1\u00c8\3\uffff\1\u00c9",
            "\1\u0180",
            "\1\u00cd\31\uffff\1\u00ce\3\uffff\1\u00cf",
            "\1\u01b0\1\u01af\12\uffff\1\u01b1\7\uffff\1\u01b2",
            "\1\u0181\31\uffff\1\u0182\3\uffff\1\70",
            "\1\u0184",
            "\1\u0181\31\uffff\1\u0182\3\uffff\1\70",
            "\1\u01b3\27\uffff\1\u01b4",
            "\1\u00f6\31\uffff\1\u00f7\3\uffff\1\u00f8",
            "\1\u0187",
            "\1\u00f6\31\uffff\1\u00f7\3\uffff\1\u00f8",
            "\1\u01b5\27\uffff\1\u01b6",
            "\1\u018e",
            "\1\u00de\31\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u01b8\1\u01b7\12\uffff\1\u01b9\7\uffff\1\u01ba",
            "\1\u018f\31\uffff\1\u0190\3\uffff\1\107",
            "\1\u0192",
            "\1\u018f\31\uffff\1\u0190\3\uffff\1\107",
            "\1\u01bb\27\uffff\1\u01bc",
            "\1\u0109\31\uffff\1\u010a\3\uffff\1\u010b",
            "\1\u0195",
            "\1\u0109\31\uffff\1\u010a\3\uffff\1\u010b",
            "\1\u01bd\27\uffff\1\u01be",
            "\1\u01c0\31\uffff\1\u01bf",
            "\1\u01c1",
            "\1\u01c2",
            "\1\u01c3",
            "\1\u019f",
            "\1\u0181\31\uffff\1\u0182\3\uffff\1\70",
            "\1\u01a3",
            "\1\u00f6\31\uffff\1\u00f7\3\uffff\1\u00f8",
            "\1\u01c5\31\uffff\1\u01c4",
            "\1\u01c6",
            "\1\u01c7",
            "\1\u01c8",
            "\1\u01aa",
            "\1\u018f\31\uffff\1\u0190\3\uffff\1\107",
            "\1\u01ae",
            "\1\u0109\31\uffff\1\u010a\3\uffff\1\u010b",
            "\1\u0181\31\uffff\1\u0182\3\uffff\1\70",
            "\1\u0181\31\uffff\1\u0182\3\uffff\1\70",
            "\1\u01c9",
            "\1\u01ca",
            "\1\u01cb",
            "\1\u018f\31\uffff\1\u0190\3\uffff\1\107",
            "\1\u018f\31\uffff\1\u0190\3\uffff\1\107",
            "\1\u01cc",
            "\1\u01cd",
            "\1\u01ce",
            "\1\u01cf",
            "\1\u01d0\27\uffff\1\u01d1",
            "\1\u01d2",
            "\1\u01d3",
            "\1\u01d4\27\uffff\1\u01d5",
            "\1\u01d6",
            "\1\u0181\31\uffff\1\u0182\3\uffff\1\70",
            "\1\u01ca",
            "\1\u0181\31\uffff\1\u0182\3\uffff\1\70",
            "\1\u01d7\27\uffff\1\u01d8",
            "\1\u018f\31\uffff\1\u0190\3\uffff\1\107",
            "\1\u01cd",
            "\1\u018f\31\uffff\1\u0190\3\uffff\1\107",
            "\1\u01d9\27\uffff\1\u01da",
            "\1\u01d2",
            "\1\u0181\31\uffff\1\u0182\3\uffff\1\70",
            "\1\u01d6",
            "\1\u018f\31\uffff\1\u0190\3\uffff\1\107"
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "41:4: ( not_final_compound_withlevel_expression | final_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_withlevel_expression | not_compound_withlevel_expression | compound_withlevel_expression | multi_compound_expression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA10_56 = input.LA(1);

                         
                        int index10_56 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred18_LogKVQuery()) ) {s = 8;}

                        else if ( (synpred20_LogKVQuery()) ) {s = 86;}

                         
                        input.seek(index10_56);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA10_184 = input.LA(1);

                         
                        int index10_184 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred18_LogKVQuery()) ) {s = 8;}

                        else if ( (synpred20_LogKVQuery()) ) {s = 86;}

                        else if ( (synpred22_LogKVQuery()) ) {s = 217;}

                         
                        input.seek(index10_184);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA10_71 = input.LA(1);

                         
                        int index10_71 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred17_LogKVQuery()) ) {s = 12;}

                        else if ( (synpred19_LogKVQuery()) ) {s = 102;}

                         
                        input.seek(index10_71);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA10_201 = input.LA(1);

                         
                        int index10_201 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred17_LogKVQuery()) ) {s = 12;}

                        else if ( (synpred19_LogKVQuery()) ) {s = 102;}

                        else if ( (synpred21_LogKVQuery()) ) {s = 234;}

                         
                        input.seek(index10_201);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 10, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA12_eotS =
        "\u0177\uffff";
    static final String DFA12_eofS =
        "\u0177\uffff";
    static final String DFA12_minS =
        "\1\5\1\17\1\31\1\10\1\uffff\1\17\1\10\1\31\2\17\1\51\1\31\1\24\1"+
        "\10\1\17\1\51\1\31\1\24\2\17\1\10\1\31\3\5\1\51\1\31\1\17\1\51\1"+
        "\31\1\24\3\5\1\51\1\31\2\10\1\17\1\51\1\31\1\24\1\17\2\0\1\uffff"+
        "\2\0\1\51\1\13\1\51\3\5\1\51\1\31\2\17\1\5\1\51\1\13\1\51\1\17\1"+
        "\51\1\31\1\24\1\17\1\51\1\31\1\24\3\5\1\51\1\31\1\10\1\5\1\51\1"+
        "\5\1\13\2\17\1\51\1\13\1\51\1\10\1\5\1\51\1\5\1\13\3\5\1\51\1\31"+
        "\3\5\1\51\1\31\2\17\1\5\1\51\1\13\1\51\1\17\1\51\1\31\1\24\4\0\1"+
        "\51\1\5\1\10\1\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24\1\51\1\5\2\17"+
        "\1\51\1\13\1\51\2\17\1\51\1\13\1\51\1\10\1\5\1\51\1\5\1\13\3\5\1"+
        "\51\1\31\2\0\1\17\1\51\1\31\1\24\1\51\4\5\1\51\1\31\1\10\1\5\1\51"+
        "\1\5\1\13\1\10\1\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24\1\51\1\5\2"+
        "\17\1\51\1\13\1\51\3\5\1\51\1\31\1\51\1\13\1\51\1\17\1\51\1\31\1"+
        "\24\1\51\1\5\1\17\1\51\1\31\1\24\1\51\4\5\1\51\1\31\1\10\1\5\1\51"+
        "\1\5\1\13\2\17\1\5\1\51\1\13\1\51\1\5\1\51\1\5\1\13\3\5\1\51\1\31"+
        "\3\5\1\51\1\31\1\51\1\13\1\51\1\17\1\51\1\31\1\24\1\51\1\5\1\10"+
        "\2\17\1\5\1\51\1\5\1\13\1\51\1\5\2\17\1\5\1\51\1\13\1\51\2\17\1"+
        "\5\1\51\1\13\1\51\1\5\1\51\1\5\1\13\3\5\1\51\1\31\1\17\1\51\1\31"+
        "\1\24\1\51\1\5\1\10\1\5\1\51\1\5\1\13\1\10\2\17\1\5\1\51\1\5\1\13"+
        "\1\51\1\5\2\17\1\5\1\51\1\13\1\51\3\5\1\51\1\31\1\17\1\51\1\31\1"+
        "\24\1\51\1\5\1\17\1\51\1\31\1\24\1\51\1\5\1\10\1\5\1\51\1\5\1\13"+
        "\1\51\1\13\1\51\3\5\1\51\1\31\3\5\1\51\1\31\1\17\1\51\1\31\1\24"+
        "\1\51\2\5\1\51\1\5\1\13\1\51\1\13\2\51\1\13\1\51\3\5\1\51\1\31\1"+
        "\51\2\5\1\51\1\5\1\13\1\5\1\51\1\5\1\13\1\51\1\13\2\51\1\5\1\51"+
        "\2\5\1\51\1\5\1\13\1\51\1\5";
    static final String DFA12_maxS =
        "\1\37\1\34\1\31\1\34\1\uffff\1\17\1\34\1\31\1\34\2\51\1\31\1\24"+
        "\1\34\2\51\1\31\1\24\2\17\1\34\1\31\2\43\1\5\1\51\1\31\2\51\1\31"+
        "\1\24\2\43\1\5\1\51\1\31\2\34\2\51\1\31\1\24\1\17\2\0\1\uffff\2"+
        "\0\1\51\1\43\1\51\2\37\1\5\1\51\1\31\2\34\1\43\1\51\1\43\3\51\1"+
        "\31\1\24\2\51\1\31\1\24\2\43\1\5\1\51\1\31\1\34\1\43\1\51\2\43\2"+
        "\17\1\51\1\43\1\51\1\34\1\43\1\51\2\43\2\37\1\5\1\51\1\31\2\37\1"+
        "\5\1\51\1\31\2\34\1\43\1\51\1\43\3\51\1\31\1\24\4\0\1\51\1\43\1"+
        "\34\1\37\1\51\1\37\1\43\2\51\1\31\1\24\1\51\1\43\2\17\1\51\1\43"+
        "\1\51\2\17\1\51\1\43\1\51\1\34\1\43\1\51\2\43\2\37\1\5\1\51\1\31"+
        "\2\0\2\51\1\31\1\24\1\51\1\37\2\43\1\5\1\51\1\31\1\34\1\37\1\51"+
        "\1\37\1\43\1\34\1\37\1\51\1\37\1\43\2\51\1\31\1\24\1\51\1\43\2\17"+
        "\1\51\1\43\1\51\2\43\1\5\1\51\1\31\1\51\1\43\3\51\1\31\1\24\1\51"+
        "\1\37\2\51\1\31\1\24\1\51\1\37\2\43\1\5\1\51\1\31\1\34\1\37\1\51"+
        "\1\37\1\43\2\17\1\43\1\51\1\43\1\51\1\43\1\51\4\43\1\5\1\51\1\31"+
        "\2\43\1\5\1\51\1\31\1\51\1\43\3\51\1\31\1\24\1\51\1\37\3\34\1\43"+
        "\1\51\2\43\1\51\1\43\2\17\1\43\1\51\1\43\1\51\2\17\1\43\1\51\1\43"+
        "\1\51\1\43\1\51\4\43\1\5\1\51\1\31\2\51\1\31\1\24\1\51\1\43\1\34"+
        "\1\43\1\51\2\43\3\34\1\43\1\51\2\43\1\51\1\43\2\17\1\43\1\51\1\43"+
        "\1\51\2\43\1\5\1\51\1\31\2\51\1\31\1\24\1\51\1\43\2\51\1\31\1\24"+
        "\1\51\1\43\1\34\1\43\1\51\2\43\1\51\1\43\1\51\2\43\1\5\1\51\1\31"+
        "\2\43\1\5\1\51\1\31\2\51\1\31\1\24\1\51\2\43\1\51\2\43\1\51\1\43"+
        "\2\51\1\43\1\51\2\43\1\5\1\51\1\31\1\51\2\43\1\51\3\43\1\51\2\43"+
        "\1\51\1\43\2\51\1\43\1\51\2\43\1\51\2\43\1\51\1\43";
    static final String DFA12_acceptS =
        "\4\uffff\1\1\50\uffff\1\2\u0149\uffff";
    static final String DFA12_specialS =
        "\53\uffff\1\5\1\0\1\uffff\1\6\1\1\76\uffff\1\7\1\2\1\10\1\3\41\uffff"+
        "\1\11\1\4\u00e2\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\4\11\uffff\1\3\11\uffff\1\1\2\uffff\1\2\2\uffff\1\4",
            "\1\6\11\uffff\1\5\2\uffff\1\7",
            "\1\10",
            "\1\12\1\11\12\uffff\1\13\7\uffff\1\14",
            "",
            "\1\15",
            "\1\17\1\16\12\uffff\1\20\7\uffff\1\21",
            "\1\22",
            "\1\24\11\uffff\1\23\2\uffff\1\25",
            "\1\27\31\uffff\1\26",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\34\1\33\12\uffff\1\35\7\uffff\1\36",
            "\1\40\31\uffff\1\37",
            "\1\41",
            "\1\42",
            "\1\43",
            "\1\44",
            "\1\45",
            "\1\47\1\46\12\uffff\1\50\7\uffff\1\51",
            "\1\52",
            "\1\53\31\uffff\1\54\3\uffff\1\55",
            "\1\56\31\uffff\1\57\3\uffff\1\55",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\64\31\uffff\1\63",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70\31\uffff\1\71\3\uffff\1\72",
            "\1\70\31\uffff\1\71\3\uffff\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\77\1\76\12\uffff\1\100\7\uffff\1\101",
            "\1\103\1\102\12\uffff\1\104\7\uffff\1\105",
            "\1\107\31\uffff\1\106",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\114",
            "\1\115\27\uffff\1\116",
            "\1\117",
            "\1\120\31\uffff\1\121",
            "\1\120\31\uffff\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125\11\uffff\1\5\2\uffff\1\7",
            "\1\125\11\uffff\1\5\2\uffff\1\7",
            "\1\4\31\uffff\1\4\3\uffff\1\55",
            "\1\126",
            "\1\127\27\uffff\1\130",
            "\1\131",
            "\1\133\31\uffff\1\132",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\140\31\uffff\1\137",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144\31\uffff\1\145\3\uffff\1\146",
            "\1\144\31\uffff\1\145\3\uffff\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\153\1\152\12\uffff\1\154\7\uffff\1\155",
            "\1\156\31\uffff\1\157\3\uffff\1\55",
            "\1\61",
            "\1\160\31\uffff\1\161\3\uffff\1\55",
            "\1\162\27\uffff\1\163",
            "\1\164",
            "\1\164",
            "\1\165",
            "\1\166\27\uffff\1\167",
            "\1\170",
            "\1\172\1\171\12\uffff\1\173\7\uffff\1\174",
            "\1\70\31\uffff\1\71\3\uffff\1\72",
            "\1\74",
            "\1\70\31\uffff\1\71\3\uffff\1\72",
            "\1\175\27\uffff\1\176",
            "\1\177\31\uffff\1\u0080",
            "\1\177\31\uffff\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084\31\uffff\1\u0085",
            "\1\u0084\31\uffff\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089\11\uffff\1\23\2\uffff\1\25",
            "\1\u0089\11\uffff\1\23\2\uffff\1\25",
            "\1\4\31\uffff\1\4\3\uffff\1\55",
            "\1\u008a",
            "\1\u008b\27\uffff\1\u008c",
            "\1\u008d",
            "\1\u008f\31\uffff\1\u008e",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\117",
            "\1\u0093\31\uffff\1\u0094\3\uffff\1\55",
            "\1\u0096\1\u0095\12\uffff\1\u0097\7\uffff\1\u0098",
            "\1\120\31\uffff\1\121",
            "\1\123",
            "\1\120\31\uffff\1\121",
            "\1\u0099\27\uffff\1\u009a",
            "\1\u009c\31\uffff\1\u009b",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\131",
            "\1\70\31\uffff\1\71\3\uffff\1\72",
            "\1\u00a0",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2\27\uffff\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7\27\uffff\1\u00a8",
            "\1\u00a9",
            "\1\u00ab\1\u00aa\12\uffff\1\u00ac\7\uffff\1\u00ad",
            "\1\144\31\uffff\1\145\3\uffff\1\146",
            "\1\150",
            "\1\144\31\uffff\1\145\3\uffff\1\146",
            "\1\u00ae\27\uffff\1\u00af",
            "\1\u00b0\31\uffff\1\u00b1",
            "\1\u00b0\31\uffff\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\uffff",
            "\1\uffff",
            "\1\u00b6\31\uffff\1\u00b5",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\170",
            "\1\120\31\uffff\1\121",
            "\1\70\31\uffff\1\71\3\uffff\1\72",
            "\1\70\31\uffff\1\71\3\uffff\1\72",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00be\1\u00bd\12\uffff\1\u00bf\7\uffff\1\u00c0",
            "\1\177\31\uffff\1\u0080",
            "\1\u0082",
            "\1\177\31\uffff\1\u0080",
            "\1\u00c1\27\uffff\1\u00c2",
            "\1\u00c4\1\u00c3\12\uffff\1\u00c5\7\uffff\1\u00c6",
            "\1\u0084\31\uffff\1\u0085",
            "\1\u0087",
            "\1\u0084\31\uffff\1\u0085",
            "\1\u00c7\27\uffff\1\u00c8",
            "\1\u00ca\31\uffff\1\u00c9",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u008d",
            "\1\144\31\uffff\1\145\3\uffff\1\146",
            "\1\u00ce",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0\27\uffff\1\u00d1",
            "\1\u00d2",
            "\1\u00d3\31\uffff\1\u00d4\3\uffff\1\u00d5",
            "\1\u00d3\31\uffff\1\u00d4\3\uffff\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da\27\uffff\1\u00db",
            "\1\u00dc",
            "\1\u00de\31\uffff\1\u00dd",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00a4",
            "\1\177\31\uffff\1\u0080",
            "\1\u00e3\31\uffff\1\u00e2",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00a9",
            "\1\u0084\31\uffff\1\u0085",
            "\1\144\31\uffff\1\145\3\uffff\1\146",
            "\1\144\31\uffff\1\145\3\uffff\1\146",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00eb\1\u00ea\12\uffff\1\u00ec\7\uffff\1\u00ed",
            "\1\u00b0\31\uffff\1\u00b1",
            "\1\u00b3",
            "\1\u00b0\31\uffff\1\u00b1",
            "\1\u00ee\27\uffff\1\u00ef",
            "\1\u00f0",
            "\1\u00f0",
            "\1\u00f1\31\uffff\1\u00f2\3\uffff\1\72",
            "\1\u00f3",
            "\1\u00f4\27\uffff\1\u00f5",
            "\1\u00f6",
            "\1\70\31\uffff\1\71\3\uffff\1\72",
            "\1\u00bb",
            "\1\70\31\uffff\1\71\3\uffff\1\72",
            "\1\u00f7\27\uffff\1\u00f8",
            "\1\u00f9\31\uffff\1\u00fa\3\uffff\1\u00fb",
            "\1\u00f9\31\uffff\1\u00fa\3\uffff\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "\1\u00fe",
            "\1\u00ff\31\uffff\1\u0100\3\uffff\1\u0101",
            "\1\u00ff\31\uffff\1\u0100\3\uffff\1\u0101",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106\27\uffff\1\u0107",
            "\1\u0108",
            "\1\u010a\31\uffff\1\u0109",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\u00d2",
            "\1\u00b0\31\uffff\1\u00b1",
            "\1\u010f\1\u010e\12\uffff\1\u0110\7\uffff\1\u0111",
            "\1\6\11\uffff\1\5\2\uffff\1\7",
            "\1\6\11\uffff\1\5\2\uffff\1\7",
            "\1\u00d3\31\uffff\1\u00d4\3\uffff\1\u00d5",
            "\1\u00d7",
            "\1\u00d3\31\uffff\1\u00d4\3\uffff\1\u00d5",
            "\1\u0112\27\uffff\1\u0113",
            "\1\u00dc",
            "\1\70\31\uffff\1\71\3\uffff\1\72",
            "\1\u0114",
            "\1\u0114",
            "\1\u00f1\31\uffff\1\u00f2\3\uffff\1\72",
            "\1\u0115",
            "\1\u0116\27\uffff\1\u0117",
            "\1\u0118",
            "\1\u0119",
            "\1\u0119",
            "\1\u011a\31\uffff\1\u011b\3\uffff\1\146",
            "\1\u011c",
            "\1\u011d\27\uffff\1\u011e",
            "\1\u011f",
            "\1\144\31\uffff\1\145\3\uffff\1\146",
            "\1\u00e8",
            "\1\144\31\uffff\1\145\3\uffff\1\146",
            "\1\u0120\27\uffff\1\u0121",
            "\1\u0122\31\uffff\1\u0123\3\uffff\1\u0124",
            "\1\u0122\31\uffff\1\u0123\3\uffff\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127",
            "\1\u0129\31\uffff\1\u0128",
            "\1\u012a",
            "\1\u012b",
            "\1\u012c",
            "\1\u00f6",
            "\1\u00d3\31\uffff\1\u00d4\3\uffff\1\u00d5",
            "\1\u012e\1\u012d\12\uffff\1\u012f\7\uffff\1\u0130",
            "\1\u00f9\31\uffff\1\u00fa\3\uffff\1\u00fb",
            "\1\u00fd",
            "\1\u00f9\31\uffff\1\u00fa\3\uffff\1\u00fb",
            "\1\u0131\27\uffff\1\u0132",
            "\1\u0134\1\u0133\12\uffff\1\u0135\7\uffff\1\u0136",
            "\1\24\11\uffff\1\23\2\uffff\1\25",
            "\1\24\11\uffff\1\23\2\uffff\1\25",
            "\1\u00ff\31\uffff\1\u0100\3\uffff\1\u0101",
            "\1\u0103",
            "\1\u00ff\31\uffff\1\u0100\3\uffff\1\u0101",
            "\1\u0137\27\uffff\1\u0138",
            "\1\u0108",
            "\1\144\31\uffff\1\145\3\uffff\1\146",
            "\1\u0139",
            "\1\u0139",
            "\1\u011a\31\uffff\1\u011b\3\uffff\1\146",
            "\1\u013a",
            "\1\u013b\27\uffff\1\u013c",
            "\1\u013d",
            "\1\u00d3\31\uffff\1\u00d4\3\uffff\1\u00d5",
            "\1\u00d3\31\uffff\1\u00d4\3\uffff\1\u00d5",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0142\31\uffff\1\u0141",
            "\1\u0143",
            "\1\u0144",
            "\1\u0145",
            "\1\u0118",
            "\1\u00f9\31\uffff\1\u00fa\3\uffff\1\u00fb",
            "\1\u0147\31\uffff\1\u0146",
            "\1\u0148",
            "\1\u0149",
            "\1\u014a",
            "\1\u011f",
            "\1\u00ff\31\uffff\1\u0100\3\uffff\1\u0101",
            "\1\u014c\1\u014b\12\uffff\1\u014d\7\uffff\1\u014e",
            "\1\u0122\31\uffff\1\u0123\3\uffff\1\u0124",
            "\1\u0126",
            "\1\u0122\31\uffff\1\u0123\3\uffff\1\u0124",
            "\1\u014f\27\uffff\1\u0150",
            "\1\u0151",
            "\1\u0152\27\uffff\1\u0153",
            "\1\u0154",
            "\1\u00f9\31\uffff\1\u00fa\3\uffff\1\u00fb",
            "\1\u00f9\31\uffff\1\u00fa\3\uffff\1\u00fb",
            "\1\u0155",
            "\1\u0156",
            "\1\u0157",
            "\1\u00ff\31\uffff\1\u0100\3\uffff\1\u0101",
            "\1\u00ff\31\uffff\1\u0100\3\uffff\1\u0101",
            "\1\u0158",
            "\1\u0159",
            "\1\u015a",
            "\1\u015c\31\uffff\1\u015b",
            "\1\u015d",
            "\1\u015e",
            "\1\u015f",
            "\1\u013d",
            "\1\u0122\31\uffff\1\u0123\3\uffff\1\u0124",
            "\1\u00d3\31\uffff\1\u00d4\3\uffff\1\u00d5",
            "\1\u013f",
            "\1\u00d3\31\uffff\1\u00d4\3\uffff\1\u00d5",
            "\1\u0160\27\uffff\1\u0161",
            "\1\u0162",
            "\1\u0163\27\uffff\1\u0164",
            "\1\u0165",
            "\1\u0166",
            "\1\u0167\27\uffff\1\u0168",
            "\1\u0169",
            "\1\u0122\31\uffff\1\u0123\3\uffff\1\u0124",
            "\1\u0122\31\uffff\1\u0123\3\uffff\1\u0124",
            "\1\u016a",
            "\1\u016b",
            "\1\u016c",
            "\1\u0154",
            "\1\u00d3\31\uffff\1\u00d4\3\uffff\1\u00d5",
            "\1\u00f9\31\uffff\1\u00fa\3\uffff\1\u00fb",
            "\1\u0156",
            "\1\u00f9\31\uffff\1\u00fa\3\uffff\1\u00fb",
            "\1\u016d\27\uffff\1\u016e",
            "\1\u00ff\31\uffff\1\u0100\3\uffff\1\u0101",
            "\1\u0159",
            "\1\u00ff\31\uffff\1\u0100\3\uffff\1\u0101",
            "\1\u016f\27\uffff\1\u0170",
            "\1\u0171",
            "\1\u0172\27\uffff\1\u0173",
            "\1\u0174",
            "\1\u0165",
            "\1\u00f9\31\uffff\1\u00fa\3\uffff\1\u00fb",
            "\1\u0169",
            "\1\u00ff\31\uffff\1\u0100\3\uffff\1\u0101",
            "\1\u0122\31\uffff\1\u0123\3\uffff\1\u0124",
            "\1\u016b",
            "\1\u0122\31\uffff\1\u0123\3\uffff\1\u0124",
            "\1\u0175\27\uffff\1\u0176",
            "\1\u0174",
            "\1\u0122\31\uffff\1\u0123\3\uffff\1\u0124"
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "()* loopback of 58:4: ( ( multi_compound_withlevel_expression | not_multi_compound_withlevel_expression | multi_compound_expression |) relation ^)*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA12_44 = input.LA(1);

                         
                        int index12_44 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred26_LogKVQuery()) ) {s = 4;}

                        else if ( (true) ) {s = 45;}

                         
                        input.seek(index12_44);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA12_47 = input.LA(1);

                         
                        int index12_47 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred26_LogKVQuery()) ) {s = 4;}

                        else if ( (true) ) {s = 45;}

                         
                        input.seek(index12_47);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA12_111 = input.LA(1);

                         
                        int index12_111 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred26_LogKVQuery()) ) {s = 4;}

                        else if ( (true) ) {s = 45;}

                         
                        input.seek(index12_111);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA12_113 = input.LA(1);

                         
                        int index12_113 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred26_LogKVQuery()) ) {s = 4;}

                        else if ( (true) ) {s = 45;}

                         
                        input.seek(index12_113);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA12_148 = input.LA(1);

                         
                        int index12_148 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred26_LogKVQuery()) ) {s = 4;}

                        else if ( (true) ) {s = 45;}

                         
                        input.seek(index12_148);

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA12_43 = input.LA(1);

                         
                        int index12_43 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred26_LogKVQuery()) ) {s = 4;}

                        else if ( (true) ) {s = 45;}

                         
                        input.seek(index12_43);

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA12_46 = input.LA(1);

                         
                        int index12_46 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred26_LogKVQuery()) ) {s = 4;}

                        else if ( (true) ) {s = 45;}

                         
                        input.seek(index12_46);

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA12_110 = input.LA(1);

                         
                        int index12_110 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred26_LogKVQuery()) ) {s = 4;}

                        else if ( (true) ) {s = 45;}

                         
                        input.seek(index12_110);

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA12_112 = input.LA(1);

                         
                        int index12_112 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred26_LogKVQuery()) ) {s = 4;}

                        else if ( (true) ) {s = 45;}

                         
                        input.seek(index12_112);

                        if ( s>=0 ) return s;
                        break;

                    case 9 : 
                        int LA12_147 = input.LA(1);

                         
                        int index12_147 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred26_LogKVQuery()) ) {s = 4;}

                        else if ( (true) ) {s = 45;}

                         
                        input.seek(index12_147);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 12, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA15_eotS =
        "\u009f\uffff";
    static final String DFA15_eofS =
        "\u009f\uffff";
    static final String DFA15_minS =
        "\2\17\1\10\1\31\1\10\1\17\1\51\1\31\1\24\2\17\1\51\1\31\1\24\3\5"+
        "\1\51\1\31\1\10\3\5\1\51\1\31\2\0\1\uffff\2\0\1\51\1\13\1\51\1\17"+
        "\1\51\1\31\1\24\2\17\1\51\1\13\1\51\1\uffff\1\5\1\51\1\5\1\13\3"+
        "\5\1\51\1\31\1\10\1\5\1\51\1\5\1\13\4\0\1\51\1\5\2\17\1\51\1\13"+
        "\1\51\1\17\1\51\1\31\1\24\1\51\1\5\2\0\1\10\1\5\1\51\1\5\1\13\3"+
        "\5\1\51\1\31\1\17\1\51\1\31\1\24\1\51\1\5\2\17\1\5\1\51\1\13\1\51"+
        "\3\5\1\51\1\31\1\10\1\5\1\51\1\5\1\13\2\17\1\5\1\51\1\13\1\51\1"+
        "\17\1\51\1\31\1\24\1\51\1\5\1\10\1\5\1\51\1\5\1\13\3\5\1\51\1\31"+
        "\1\17\1\51\1\31\1\24\1\51\1\5\1\51\1\13\1\51\3\5\1\51\1\31\1\5\1"+
        "\51\1\5\1\13\1\51\1\13\2\51\2\5\1\51\1\5\1\13\1\51\1\5";
    static final String DFA15_maxS =
        "\1\34\1\17\1\34\1\31\1\34\2\51\1\31\1\24\1\17\2\51\1\31\1\24\2\43"+
        "\1\5\1\51\1\31\1\34\2\37\1\5\1\51\1\31\2\0\1\uffff\2\0\1\51\1\43"+
        "\3\51\1\31\1\24\2\17\1\51\1\43\1\51\1\uffff\1\43\1\51\2\43\2\37"+
        "\1\5\1\51\1\31\1\34\1\37\1\51\1\37\1\43\4\0\1\51\1\43\2\17\1\51"+
        "\1\43\3\51\1\31\1\24\1\51\1\37\2\0\1\34\1\37\1\51\1\37\3\43\1\5"+
        "\1\51\1\31\2\51\1\31\1\24\1\51\1\37\2\17\1\43\1\51\1\43\1\51\2\43"+
        "\1\5\1\51\1\31\1\34\1\43\1\51\2\43\2\17\1\43\1\51\1\43\3\51\1\31"+
        "\1\24\1\51\1\43\1\34\1\43\1\51\4\43\1\5\1\51\1\31\2\51\1\31\1\24"+
        "\1\51\1\43\1\51\1\43\1\51\2\43\1\5\1\51\1\31\1\43\1\51\2\43\1\51"+
        "\1\43\2\51\2\43\1\51\2\43\1\51\1\43";
    static final String DFA15_acceptS =
        "\33\uffff\1\2\16\uffff\1\1\164\uffff";
    static final String DFA15_specialS =
        "\31\uffff\1\6\1\1\1\uffff\1\5\1\0\33\uffff\1\7\1\2\1\10\1\3\15\uffff"+
        "\1\11\1\4\123\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\2\11\uffff\1\1\2\uffff\1\3",
            "\1\4",
            "\1\6\1\5\12\uffff\1\7\7\uffff\1\10",
            "\1\11",
            "\1\13\1\12\12\uffff\1\14\7\uffff\1\15",
            "\1\17\31\uffff\1\16",
            "\1\20",
            "\1\21",
            "\1\22",
            "\1\23",
            "\1\25\31\uffff\1\24",
            "\1\26",
            "\1\27",
            "\1\30",
            "\1\31\31\uffff\1\32\3\uffff\1\33",
            "\1\34\31\uffff\1\35\3\uffff\1\33",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\42\1\41\12\uffff\1\43\7\uffff\1\44",
            "\1\45\31\uffff\1\46",
            "\1\45\31\uffff\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\53",
            "\1\54\27\uffff\1\55",
            "\1\56",
            "\1\60\31\uffff\1\57",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\64",
            "\1\65",
            "\1\66\27\uffff\1\67",
            "\1\70",
            "",
            "\1\71\31\uffff\1\72\3\uffff\1\33",
            "\1\37",
            "\1\73\31\uffff\1\74\3\uffff\1\33",
            "\1\75\27\uffff\1\76",
            "\1\77\31\uffff\1\100",
            "\1\77\31\uffff\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\105\1\104\12\uffff\1\106\7\uffff\1\107",
            "\1\45\31\uffff\1\46",
            "\1\50",
            "\1\45\31\uffff\1\46",
            "\1\110\27\uffff\1\111",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\56",
            "\1\112\31\uffff\1\113\3\uffff\1\33",
            "\1\114",
            "\1\114",
            "\1\115",
            "\1\116\27\uffff\1\117",
            "\1\120",
            "\1\122\31\uffff\1\121",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\70",
            "\1\45\31\uffff\1\46",
            "\1\uffff",
            "\1\uffff",
            "\1\127\1\126\12\uffff\1\130\7\uffff\1\131",
            "\1\77\31\uffff\1\100",
            "\1\102",
            "\1\77\31\uffff\1\100",
            "\1\132\27\uffff\1\133",
            "\1\134\31\uffff\1\135\3\uffff\1\136",
            "\1\134\31\uffff\1\135\3\uffff\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\143\31\uffff\1\142",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\120",
            "\1\77\31\uffff\1\100",
            "\1\147",
            "\1\147",
            "\1\52\31\uffff\1\52\3\uffff\1\33",
            "\1\150",
            "\1\151\27\uffff\1\152",
            "\1\153",
            "\1\154\31\uffff\1\155\3\uffff\1\156",
            "\1\154\31\uffff\1\155\3\uffff\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\163\1\162\12\uffff\1\164\7\uffff\1\165",
            "\1\134\31\uffff\1\135\3\uffff\1\136",
            "\1\140",
            "\1\134\31\uffff\1\135\3\uffff\1\136",
            "\1\166\27\uffff\1\167",
            "\1\170",
            "\1\170",
            "\1\52\31\uffff\1\52\3\uffff\1\33",
            "\1\171",
            "\1\172\27\uffff\1\173",
            "\1\174",
            "\1\176\31\uffff\1\175",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\153",
            "\1\134\31\uffff\1\135\3\uffff\1\136",
            "\1\u0083\1\u0082\12\uffff\1\u0084\7\uffff\1\u0085",
            "\1\154\31\uffff\1\155\3\uffff\1\156",
            "\1\160",
            "\1\154\31\uffff\1\155\3\uffff\1\156",
            "\1\u0086\27\uffff\1\u0087",
            "\1\134\31\uffff\1\135\3\uffff\1\136",
            "\1\134\31\uffff\1\135\3\uffff\1\136",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008c\31\uffff\1\u008b",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\174",
            "\1\154\31\uffff\1\155\3\uffff\1\156",
            "\1\u0090",
            "\1\u0091\27\uffff\1\u0092",
            "\1\u0093",
            "\1\154\31\uffff\1\155\3\uffff\1\156",
            "\1\154\31\uffff\1\155\3\uffff\1\156",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\134\31\uffff\1\135\3\uffff\1\136",
            "\1\u0089",
            "\1\134\31\uffff\1\135\3\uffff\1\136",
            "\1\u0097\27\uffff\1\u0098",
            "\1\u0099",
            "\1\u009a\27\uffff\1\u009b",
            "\1\u009c",
            "\1\u0093",
            "\1\134\31\uffff\1\135\3\uffff\1\136",
            "\1\154\31\uffff\1\155\3\uffff\1\156",
            "\1\u0095",
            "\1\154\31\uffff\1\155\3\uffff\1\156",
            "\1\u009d\27\uffff\1\u009e",
            "\1\u009c",
            "\1\154\31\uffff\1\155\3\uffff\1\156"
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "()* loopback of 70:16: ( ( compound_withlevel_expression | multi_compound_expression | not_compound_withlevel_expression ) relation ^)*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA15_29 = input.LA(1);

                         
                        int index15_29 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred31_LogKVQuery()) ) {s = 42;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index15_29);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA15_26 = input.LA(1);

                         
                        int index15_26 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred31_LogKVQuery()) ) {s = 42;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index15_26);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA15_58 = input.LA(1);

                         
                        int index15_58 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred31_LogKVQuery()) ) {s = 42;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index15_58);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA15_60 = input.LA(1);

                         
                        int index15_60 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred31_LogKVQuery()) ) {s = 42;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index15_60);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA15_75 = input.LA(1);

                         
                        int index15_75 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred31_LogKVQuery()) ) {s = 42;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index15_75);

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA15_28 = input.LA(1);

                         
                        int index15_28 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred31_LogKVQuery()) ) {s = 42;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index15_28);

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA15_25 = input.LA(1);

                         
                        int index15_25 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred31_LogKVQuery()) ) {s = 42;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index15_25);

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA15_57 = input.LA(1);

                         
                        int index15_57 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred31_LogKVQuery()) ) {s = 42;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index15_57);

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA15_59 = input.LA(1);

                         
                        int index15_59 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred31_LogKVQuery()) ) {s = 42;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index15_59);

                        if ( s>=0 ) return s;
                        break;

                    case 9 : 
                        int LA15_74 = input.LA(1);

                         
                        int index15_74 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred31_LogKVQuery()) ) {s = 42;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index15_74);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 15, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA18_eotS =
        "\40\uffff";
    static final String DFA18_eofS =
        "\6\uffff\2\13\14\uffff\1\13\1\uffff\1\13\6\uffff\1\13\2\uffff";
    static final String DFA18_minS =
        "\1\17\1\10\1\17\1\51\1\31\1\24\3\5\1\51\1\31\1\uffff\4\0\1\51\1"+
        "\13\1\51\1\uffff\1\5\1\51\1\5\1\13\4\0\1\51\1\5\2\0";
    static final String DFA18_maxS =
        "\1\17\1\34\2\51\1\31\1\24\2\54\1\5\1\51\1\31\1\uffff\4\0\1\51\1"+
        "\43\1\51\1\uffff\1\54\1\51\1\54\1\43\4\0\1\51\1\54\2\0";
    static final String DFA18_acceptS =
        "\13\uffff\1\2\7\uffff\1\1\14\uffff";
    static final String DFA18_specialS =
        "\14\uffff\1\3\1\10\1\4\1\11\10\uffff\1\2\1\7\1\1\1\6\2\uffff\1\0"+
        "\1\5}>";
    static final String[] DFA18_transitionS = {
            "\1\1",
            "\1\3\1\2\12\uffff\1\4\7\uffff\1\5",
            "\1\7\31\uffff\1\6",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\14\11\uffff\1\13\11\uffff\1\13\2\uffff\1\13\2\uffff\1\15"+
            "\3\uffff\1\13\10\uffff\1\13",
            "\1\16\11\uffff\1\13\11\uffff\1\13\2\uffff\1\13\2\uffff\1\17"+
            "\3\uffff\1\13\10\uffff\1\13",
            "\1\20",
            "\1\21",
            "\1\22",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\24",
            "\1\25\27\uffff\1\26",
            "\1\27",
            "",
            "\1\30\11\uffff\1\13\11\uffff\1\13\2\uffff\1\13\2\uffff\1\31"+
            "\3\uffff\1\13\10\uffff\1\13",
            "\1\21",
            "\1\32\11\uffff\1\13\11\uffff\1\13\2\uffff\1\13\2\uffff\1\33"+
            "\3\uffff\1\13\10\uffff\1\13",
            "\1\34\27\uffff\1\35",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\27",
            "\1\36\11\uffff\1\13\11\uffff\1\13\2\uffff\1\13\2\uffff\1\37"+
            "\3\uffff\1\13\10\uffff\1\13",
            "\1\uffff",
            "\1\uffff"
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "()* loopback of 85:4: ( ( expression | compound_expression ) relation ^)*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA18_30 = input.LA(1);

                         
                        int index18_30 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred35_LogKVQuery()) ) {s = 19;}

                        else if ( (true) ) {s = 11;}

                         
                        input.seek(index18_30);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA18_26 = input.LA(1);

                         
                        int index18_26 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred35_LogKVQuery()) ) {s = 19;}

                        else if ( (true) ) {s = 11;}

                         
                        input.seek(index18_26);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA18_24 = input.LA(1);

                         
                        int index18_24 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred35_LogKVQuery()) ) {s = 19;}

                        else if ( (true) ) {s = 11;}

                         
                        input.seek(index18_24);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA18_12 = input.LA(1);

                         
                        int index18_12 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred35_LogKVQuery()) ) {s = 19;}

                        else if ( (true) ) {s = 11;}

                         
                        input.seek(index18_12);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA18_14 = input.LA(1);

                         
                        int index18_14 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred35_LogKVQuery()) ) {s = 19;}

                        else if ( (true) ) {s = 11;}

                         
                        input.seek(index18_14);

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA18_31 = input.LA(1);

                         
                        int index18_31 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred35_LogKVQuery()) ) {s = 19;}

                        else if ( (true) ) {s = 11;}

                         
                        input.seek(index18_31);

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA18_27 = input.LA(1);

                         
                        int index18_27 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred35_LogKVQuery()) ) {s = 19;}

                        else if ( (true) ) {s = 11;}

                         
                        input.seek(index18_27);

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA18_25 = input.LA(1);

                         
                        int index18_25 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred35_LogKVQuery()) ) {s = 19;}

                        else if ( (true) ) {s = 11;}

                         
                        input.seek(index18_25);

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA18_13 = input.LA(1);

                         
                        int index18_13 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred35_LogKVQuery()) ) {s = 19;}

                        else if ( (true) ) {s = 11;}

                         
                        input.seek(index18_13);

                        if ( s>=0 ) return s;
                        break;

                    case 9 : 
                        int LA18_15 = input.LA(1);

                         
                        int index18_15 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred35_LogKVQuery()) ) {s = 19;}

                        else if ( (true) ) {s = 11;}

                         
                        input.seek(index18_15);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 18, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA17_eotS =
        "\53\uffff";
    static final String DFA17_eofS =
        "\53\uffff";
    static final String DFA17_minS =
        "\1\17\1\10\1\17\1\51\1\31\1\24\3\5\1\51\1\31\2\17\1\51\1\13\1\51"+
        "\1\10\1\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24\1\51\1\5\2\0\1\5\1\51"+
        "\1\31\2\uffff\1\51\1\13\1\51\1\0\1\51\1\0\1\13\1\51\1\0";
    static final String DFA17_maxS =
        "\1\17\1\34\2\51\1\31\1\24\2\37\1\5\1\51\1\31\2\17\1\51\1\43\1\51"+
        "\1\34\1\37\1\51\1\37\1\43\2\51\1\31\1\24\1\51\1\37\2\0\1\5\1\51"+
        "\1\31\2\uffff\1\51\1\43\1\51\1\0\1\51\1\0\1\43\1\51\1\0";
    static final String DFA17_acceptS =
        "\40\uffff\1\1\1\2\11\uffff";
    static final String DFA17_specialS =
        "\33\uffff\1\4\1\1\10\uffff\1\3\1\uffff\1\0\2\uffff\1\2}>";
    static final String[] DFA17_transitionS = {
            "\1\1",
            "\1\3\1\2\12\uffff\1\4\7\uffff\1\5",
            "\1\7\31\uffff\1\6",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\13\31\uffff\1\14",
            "\1\13\31\uffff\1\14",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\20",
            "\1\20",
            "\1\21",
            "\1\22\27\uffff\1\23",
            "\1\24",
            "\1\26\1\25\12\uffff\1\27\7\uffff\1\30",
            "\1\13\31\uffff\1\14",
            "\1\16",
            "\1\13\31\uffff\1\14",
            "\1\31\27\uffff\1\32",
            "\1\34\31\uffff\1\33",
            "\1\35",
            "\1\36",
            "\1\37",
            "\1\24",
            "\1\13\31\uffff\1\14",
            "\1\uffff",
            "\1\uffff",
            "\1\42",
            "\1\43",
            "\1\44",
            "",
            "",
            "\1\45",
            "\1\46\27\uffff\1\47",
            "\1\50",
            "\1\uffff",
            "\1\43",
            "\1\uffff",
            "\1\51\27\uffff\1\52",
            "\1\50",
            "\1\uffff"
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "85:5: ( expression | compound_expression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA17_39 = input.LA(1);

                         
                        int index17_39 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred34_LogKVQuery()) ) {s = 32;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index17_39);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA17_28 = input.LA(1);

                         
                        int index17_28 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred34_LogKVQuery()) ) {s = 32;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index17_28);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA17_42 = input.LA(1);

                         
                        int index17_42 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred34_LogKVQuery()) ) {s = 32;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index17_42);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA17_37 = input.LA(1);

                         
                        int index17_37 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred34_LogKVQuery()) ) {s = 32;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index17_37);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA17_27 = input.LA(1);

                         
                        int index17_27 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred34_LogKVQuery()) ) {s = 32;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index17_27);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 17, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA19_eotS =
        "\53\uffff";
    static final String DFA19_eofS =
        "\6\uffff\2\13\4\uffff\2\13\4\uffff\1\13\1\uffff\1\13\6\uffff\1\13"+
        "\17\uffff";
    static final String DFA19_minS =
        "\1\17\1\10\1\17\1\51\1\31\1\24\3\5\1\51\1\31\1\uffff\2\5\1\51\1"+
        "\13\1\51\1\10\1\5\1\51\1\5\1\13\1\17\1\51\1\31\1\24\1\51\1\5\2\0"+
        "\1\5\1\51\1\31\1\uffff\1\51\1\13\1\51\1\0\1\51\1\0\1\13\1\51\1\0";
    static final String DFA19_maxS =
        "\1\17\1\34\2\51\1\31\1\24\2\54\1\5\1\51\1\31\1\uffff\2\37\1\51\1"+
        "\43\1\51\1\34\1\54\1\51\1\54\1\43\2\51\1\31\1\24\1\51\1\54\2\0\1"+
        "\5\1\51\1\31\1\uffff\1\51\1\43\1\51\1\0\1\51\1\0\1\43\1\51\1\0";
    static final String DFA19_acceptS =
        "\13\uffff\1\1\25\uffff\1\2\11\uffff";
    static final String DFA19_specialS =
        "\34\uffff\1\1\1\4\7\uffff\1\0\1\uffff\1\2\2\uffff\1\3}>";
    static final String[] DFA19_transitionS = {
            "\1\1",
            "\1\3\1\2\12\uffff\1\4\7\uffff\1\5",
            "\1\7\31\uffff\1\6",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\14\11\uffff\1\13\11\uffff\1\13\2\uffff\1\13\2\uffff\1\15"+
            "\3\uffff\1\13\10\uffff\1\13",
            "\1\14\11\uffff\1\13\11\uffff\1\13\2\uffff\1\13\2\uffff\1\15"+
            "\3\uffff\1\13\10\uffff\1\13",
            "\1\16",
            "\1\17",
            "\1\20",
            "",
            "\1\13\11\uffff\1\21\11\uffff\1\13\2\uffff\1\13\2\uffff\1\13",
            "\1\13\11\uffff\1\21\11\uffff\1\13\2\uffff\1\13\2\uffff\1\13",
            "\1\22",
            "\1\23\27\uffff\1\24",
            "\1\25",
            "\1\27\1\26\12\uffff\1\30\7\uffff\1\31",
            "\1\14\11\uffff\1\13\11\uffff\1\13\2\uffff\1\13\2\uffff\1\15"+
            "\3\uffff\1\13\10\uffff\1\13",
            "\1\17",
            "\1\14\11\uffff\1\13\11\uffff\1\13\2\uffff\1\13\2\uffff\1\15"+
            "\3\uffff\1\13\10\uffff\1\13",
            "\1\32\27\uffff\1\33",
            "\1\35\31\uffff\1\34",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\25",
            "\1\14\11\uffff\1\13\11\uffff\1\13\2\uffff\1\13\2\uffff\1\15"+
            "\3\uffff\1\13\10\uffff\1\13",
            "\1\uffff",
            "\1\uffff",
            "\1\42",
            "\1\43",
            "\1\44",
            "",
            "\1\45",
            "\1\46\27\uffff\1\47",
            "\1\50",
            "\1\uffff",
            "\1\43",
            "\1\uffff",
            "\1\51\27\uffff\1\52",
            "\1\50",
            "\1\uffff"
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "85:50: ( expression | compound_expression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA19_37 = input.LA(1);

                         
                        int index19_37 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred36_LogKVQuery()) ) {s = 11;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index19_37);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA19_28 = input.LA(1);

                         
                        int index19_28 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred36_LogKVQuery()) ) {s = 11;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index19_28);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA19_39 = input.LA(1);

                         
                        int index19_39 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred36_LogKVQuery()) ) {s = 11;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index19_39);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA19_42 = input.LA(1);

                         
                        int index19_42 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred36_LogKVQuery()) ) {s = 11;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index19_42);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA19_29 = input.LA(1);

                         
                        int index19_29 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred36_LogKVQuery()) ) {s = 11;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index19_29);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 19, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA28_eotS =
        "\30\uffff";
    static final String DFA28_eofS =
        "\13\uffff\2\20\13\uffff";
    static final String DFA28_minS =
        "\1\17\1\10\1\17\1\51\1\31\1\24\3\5\1\51\1\31\2\17\1\51\1\13\1\51"+
        "\2\uffff\1\5\1\51\1\5\1\13\1\51\1\5";
    static final String DFA28_maxS =
        "\1\17\1\34\2\51\1\31\1\24\2\37\1\5\1\51\1\31\2\17\1\51\1\43\1\51"+
        "\2\uffff\1\37\1\51\1\37\1\43\1\51\1\37";
    static final String DFA28_acceptS =
        "\20\uffff\1\1\1\2\6\uffff";
    static final String DFA28_specialS =
        "\30\uffff}>";
    static final String[] DFA28_transitionS = {
            "\1\1",
            "\1\3\1\2\12\uffff\1\4\7\uffff\1\5",
            "\1\7\31\uffff\1\6",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\13\31\uffff\1\14",
            "\1\13\31\uffff\1\14",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\21",
            "\1\21",
            "\1\22",
            "\1\23\27\uffff\1\24",
            "\1\25",
            "",
            "",
            "\1\13\31\uffff\1\14",
            "\1\16",
            "\1\13\31\uffff\1\14",
            "\1\26\27\uffff\1\27",
            "\1\25",
            "\1\13\31\uffff\1\14"
    };

    static final short[] DFA28_eot = DFA.unpackEncodedString(DFA28_eotS);
    static final short[] DFA28_eof = DFA.unpackEncodedString(DFA28_eofS);
    static final char[] DFA28_min = DFA.unpackEncodedStringToUnsignedChars(DFA28_minS);
    static final char[] DFA28_max = DFA.unpackEncodedStringToUnsignedChars(DFA28_maxS);
    static final short[] DFA28_accept = DFA.unpackEncodedString(DFA28_acceptS);
    static final short[] DFA28_special = DFA.unpackEncodedString(DFA28_specialS);
    static final short[][] DFA28_transition;

    static {
        int numStates = DFA28_transitionS.length;
        DFA28_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA28_transition[i] = DFA.unpackEncodedString(DFA28_transitionS[i]);
        }
    }

    class DFA28 extends DFA {

        public DFA28(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 28;
            this.eot = DFA28_eot;
            this.eof = DFA28_eof;
            this.min = DFA28_min;
            this.max = DFA28_max;
            this.accept = DFA28_accept;
            this.special = DFA28_special;
            this.transition = DFA28_transition;
        }
        public String getDescription() {
            return "85:5: ( expression | compound_expression )";
        }
    }
 

    public static final BitSet FOLLOW_select_statement_in_query51 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_from_statement_in_query57 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_where_statement_in_query62 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_within_statement_in_query70 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_keyword_in_select_statement88 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_field_in_select_statement93 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_alias_statement_in_select_statement95 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_comma_t_in_select_statement98 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_field_in_select_statement104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alias_statement_in_select_statement106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_from_keyword_in_from_statement124 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_field_in_from_statement129 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_alias_statement_in_from_statement131 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_comma_t_in_from_statement134 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_field_in_from_statement140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alias_statement_in_from_statement142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_in_alias_statement161 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_as_keyword_in_alias_statement163 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_field_in_alias_statement166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_where_keyword_in_where_statement182 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_expression_list_in_where_statement185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_final_compound_withlevel_expression_in_expression_list203 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_final_compound_withlevel_expression_in_expression_list205 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_not_multi_compound_withlevel_expression_in_expression_list207 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_multi_compound_withlevel_expression_in_expression_list209 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_not_compound_withlevel_expression_in_expression_list211 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_compound_withlevel_expression_in_expression_list213 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_multi_compound_expression_in_expression_list215 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_relation_in_expression_list218 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_not_final_compound_withlevel_expression_in_expression_list227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final_compound_withlevel_expression_in_expression_list229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_multi_compound_withlevel_expression_in_expression_list231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multi_compound_withlevel_expression_in_expression_list233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_compound_withlevel_expression_in_expression_list235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_withlevel_expression_in_expression_list237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multi_compound_expression_in_expression_list239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_within_keyword_in_within_statement260 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_value_in_within_statement263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_operator_in_not_final_compound_withlevel_expression279 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_final_compound_withlevel_expression_in_not_final_compound_withlevel_expression282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_BRACE_in_final_compound_withlevel_expression298 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_multi_compound_withlevel_expression_in_final_compound_withlevel_expression306 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_not_multi_compound_withlevel_expression_in_final_compound_withlevel_expression308 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_multi_compound_expression_in_final_compound_withlevel_expression310 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_relation_in_final_compound_withlevel_expression314 = new BitSet(new long[]{0x0000000092008020L});
    public static final BitSet FOLLOW_multi_compound_withlevel_expression_in_final_compound_withlevel_expression323 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_not_multi_compound_withlevel_expression_in_final_compound_withlevel_expression325 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_multi_compound_expression_in_final_compound_withlevel_expression327 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_BRACE_in_final_compound_withlevel_expression333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_operator_in_not_multi_compound_withlevel_expression351 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_multi_compound_withlevel_expression_in_not_multi_compound_withlevel_expression354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_BRACE_in_multi_compound_withlevel_expression371 = new BitSet(new long[]{0x0000000012008000L});
    public static final BitSet FOLLOW_compound_withlevel_expression_in_multi_compound_withlevel_expression376 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_multi_compound_expression_in_multi_compound_withlevel_expression378 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_not_compound_withlevel_expression_in_multi_compound_withlevel_expression380 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_relation_in_multi_compound_withlevel_expression383 = new BitSet(new long[]{0x0000000012008000L});
    public static final BitSet FOLLOW_compound_withlevel_expression_in_multi_compound_withlevel_expression393 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_multi_compound_expression_in_multi_compound_withlevel_expression395 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_not_compound_withlevel_expression_in_multi_compound_withlevel_expression397 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_BRACE_in_multi_compound_withlevel_expression400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_operator_in_not_compound_withlevel_expression415 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_compound_withlevel_expression_in_not_compound_withlevel_expression418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_BRACE_in_compound_withlevel_expression432 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_multi_compound_expression_in_compound_withlevel_expression435 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_relation_in_compound_withlevel_expression437 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_multi_compound_expression_in_compound_withlevel_expression440 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_BRACE_in_compound_withlevel_expression442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_multi_compound_expression463 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_compound_expression_in_multi_compound_expression465 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_relation_in_multi_compound_expression468 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_expression_in_multi_compound_expression474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_expression_in_multi_compound_expression476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_compound_expression495 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_relation_in_compound_expression497 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_expression_in_compound_expression499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_operator_in_relation522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_operator_in_relation524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_in_expression532 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_binary_operator_in_expression534 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_value_in_expression536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILED_T_in_expression560 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_binary_operator_in_expression562 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_FILED_T_in_expression566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_in_expression590 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_between_operator_in_expression592 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_value_in_expression594 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_and_operator_in_expression596 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_value_in_expression598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_expression_in_expression623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_in_expression_in_expression632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_in_not_in_expression646 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_not_operator_in_not_in_expression648 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_in_operator_in_not_in_expression650 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LEFT_BRACE_in_not_in_expression652 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_value_in_not_in_expression655 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_comma_t_in_not_in_expression657 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_value_in_not_in_expression661 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_BRACE_in_not_in_expression663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_in_in_expression691 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_in_operator_in_in_expression693 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LEFT_BRACE_in_in_expression695 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_value_in_in_expression698 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_comma_t_in_in_expression700 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_value_in_in_expression704 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_BRACE_in_in_expression706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECT_KEYWORD_T_in_select_keyword734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_KEYWORD_T_in_from_keyword741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHERE_KEYWORD_T_in_where_keyword748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WITHIN_KEYWORD_T_in_within_keyword755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AS_KEYWORD_T_in_as_keyword762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILED_T_in_field770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_T_in_value778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_T_in_comma_t786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BINARY_OPERATOR_T_in_binary_operator793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_OPERATOR_T_in_and_operator800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_OPERATOR_T_in_or_operator807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_OPERATOR_T_in_not_operator814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BETWEEN_OPERATOR_T_in_between_operator820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IN_OPERATOR_T_in_in_operator827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_final_compound_withlevel_expression_in_synpred9_LogKVQuery203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final_compound_withlevel_expression_in_synpred10_LogKVQuery205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_multi_compound_withlevel_expression_in_synpred11_LogKVQuery207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multi_compound_withlevel_expression_in_synpred12_LogKVQuery209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_compound_withlevel_expression_in_synpred13_LogKVQuery211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_withlevel_expression_in_synpred14_LogKVQuery213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multi_compound_expression_in_synpred15_LogKVQuery215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_final_compound_withlevel_expression_in_synpred17_LogKVQuery227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final_compound_withlevel_expression_in_synpred18_LogKVQuery229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_multi_compound_withlevel_expression_in_synpred19_LogKVQuery231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multi_compound_withlevel_expression_in_synpred20_LogKVQuery233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_compound_withlevel_expression_in_synpred21_LogKVQuery235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_withlevel_expression_in_synpred22_LogKVQuery237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multi_compound_withlevel_expression_in_synpred26_LogKVQuery306 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_not_multi_compound_withlevel_expression_in_synpred26_LogKVQuery308 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_multi_compound_expression_in_synpred26_LogKVQuery310 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_relation_in_synpred26_LogKVQuery314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_withlevel_expression_in_synpred31_LogKVQuery376 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_multi_compound_expression_in_synpred31_LogKVQuery378 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_not_compound_withlevel_expression_in_synpred31_LogKVQuery380 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_relation_in_synpred31_LogKVQuery383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_synpred34_LogKVQuery463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_synpred35_LogKVQuery463 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_compound_expression_in_synpred35_LogKVQuery465 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_relation_in_synpred35_LogKVQuery468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_synpred36_LogKVQuery474 = new BitSet(new long[]{0x0000000000000002L});

}