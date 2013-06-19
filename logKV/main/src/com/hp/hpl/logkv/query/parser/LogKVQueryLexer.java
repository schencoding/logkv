// $ANTLR 3.4 E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g 2012-12-18 19:57:25
package com.hp.hpl.logkv.query.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class LogKVQueryLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public LogKVQueryLexer() {} 
    public LogKVQueryLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public LogKVQueryLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g"; }

    // $ANTLR start "SELECT_KEYWORD_T"
    public final void mSELECT_KEYWORD_T() throws RecognitionException {
        try {
            int _type = SELECT_KEYWORD_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:134:18: ( S E L E C T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:134:20: S E L E C T
            {
            mS(); 


            mE(); 


            mL(); 


            mE(); 


            mC(); 


            mT(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SELECT_KEYWORD_T"

    // $ANTLR start "FROM_KEYWORD_T"
    public final void mFROM_KEYWORD_T() throws RecognitionException {
        try {
            int _type = FROM_KEYWORD_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:135:17: ( F R O M )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:135:19: F R O M
            {
            mF(); 


            mR(); 


            mO(); 


            mM(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FROM_KEYWORD_T"

    // $ANTLR start "WHERE_KEYWORD_T"
    public final void mWHERE_KEYWORD_T() throws RecognitionException {
        try {
            int _type = WHERE_KEYWORD_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:136:18: ( W H E R E )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:136:20: W H E R E
            {
            mW(); 


            mH(); 


            mE(); 


            mR(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHERE_KEYWORD_T"

    // $ANTLR start "WITHIN_KEYWORD_T"
    public final void mWITHIN_KEYWORD_T() throws RecognitionException {
        try {
            int _type = WITHIN_KEYWORD_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:137:18: ( W I T H I N )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:137:20: W I T H I N
            {
            mW(); 


            mI(); 


            mT(); 


            mH(); 


            mI(); 


            mN(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WITHIN_KEYWORD_T"

    // $ANTLR start "AS_KEYWORD_T"
    public final void mAS_KEYWORD_T() throws RecognitionException {
        try {
            int _type = AS_KEYWORD_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:138:15: ( A S )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:138:17: A S
            {
            mA(); 


            mS(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AS_KEYWORD_T"

    // $ANTLR start "AND_OPERATOR_T"
    public final void mAND_OPERATOR_T() throws RecognitionException {
        try {
            int _type = AND_OPERATOR_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:139:17: ( A N D )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:139:19: A N D
            {
            mA(); 


            mN(); 


            mD(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND_OPERATOR_T"

    // $ANTLR start "OR_OPERATOR_T"
    public final void mOR_OPERATOR_T() throws RecognitionException {
        try {
            int _type = OR_OPERATOR_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:140:16: ( O R )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:140:18: O R
            {
            mO(); 


            mR(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OR_OPERATOR_T"

    // $ANTLR start "NOT_OPERATOR_T"
    public final void mNOT_OPERATOR_T() throws RecognitionException {
        try {
            int _type = NOT_OPERATOR_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:141:17: ( N O T )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:141:19: N O T
            {
            mN(); 


            mO(); 


            mT(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT_OPERATOR_T"

    // $ANTLR start "BETWEEN_OPERATOR_T"
    public final void mBETWEEN_OPERATOR_T() throws RecognitionException {
        try {
            int _type = BETWEEN_OPERATOR_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:145:20: ( B E T W E E N )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:145:22: B E T W E E N
            {
            mB(); 


            mE(); 


            mT(); 


            mW(); 


            mE(); 


            mE(); 


            mN(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BETWEEN_OPERATOR_T"

    // $ANTLR start "IN_OPERATOR_T"
    public final void mIN_OPERATOR_T() throws RecognitionException {
        try {
            int _type = IN_OPERATOR_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:144:16: ( I N )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:144:18: I N
            {
            mI(); 


            mN(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IN_OPERATOR_T"

    // $ANTLR start "IS_OPERATOR_T"
    public final void mIS_OPERATOR_T() throws RecognitionException {
        try {
            int _type = IS_OPERATOR_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:146:16: ( I S )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:146:18: I S
            {
            mI(); 


            mS(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IS_OPERATOR_T"

    // $ANTLR start "NULL_OPERATOR_T"
    public final void mNULL_OPERATOR_T() throws RecognitionException {
        try {
            int _type = NULL_OPERATOR_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:147:18: ( N U L L )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:147:20: N U L L
            {
            mN(); 


            mU(); 


            mL(); 


            mL(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NULL_OPERATOR_T"

    // $ANTLR start "BINARY_OPERATOR_T"
    public final void mBINARY_OPERATOR_T() throws RecognitionException {
        try {
            int _type = BINARY_OPERATOR_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:149:19: ( '=' | '!=' | '>' | '>=' | '<' | '<=' | S T A R T S W I T H | E N D S W I T H | C O N T A I N S )
            int alt1=9;
            switch ( input.LA(1) ) {
            case '=':
                {
                alt1=1;
                }
                break;
            case '!':
                {
                alt1=2;
                }
                break;
            case '>':
                {
                int LA1_3 = input.LA(2);

                if ( (LA1_3=='=') ) {
                    alt1=4;
                }
                else {
                    alt1=3;
                }
                }
                break;
            case '<':
                {
                int LA1_4 = input.LA(2);

                if ( (LA1_4=='=') ) {
                    alt1=6;
                }
                else {
                    alt1=5;
                }
                }
                break;
            case 'S':
            case 's':
                {
                alt1=7;
                }
                break;
            case 'E':
            case 'e':
                {
                alt1=8;
                }
                break;
            case 'C':
            case 'c':
                {
                alt1=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }

            switch (alt1) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:149:21: '='
                    {
                    match('='); 

                    }
                    break;
                case 2 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:149:25: '!='
                    {
                    match("!="); 



                    }
                    break;
                case 3 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:149:30: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 4 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:149:34: '>='
                    {
                    match(">="); 



                    }
                    break;
                case 5 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:149:39: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 6 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:149:43: '<='
                    {
                    match("<="); 



                    }
                    break;
                case 7 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:149:48: S T A R T S W I T H
                    {
                    mS(); 


                    mT(); 


                    mA(); 


                    mR(); 


                    mT(); 


                    mS(); 


                    mW(); 


                    mI(); 


                    mT(); 


                    mH(); 


                    }
                    break;
                case 8 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:149:68: E N D S W I T H
                    {
                    mE(); 


                    mN(); 


                    mD(); 


                    mS(); 


                    mW(); 


                    mI(); 


                    mT(); 


                    mH(); 


                    }
                    break;
                case 9 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:149:84: C O N T A I N S
                    {
                    mC(); 


                    mO(); 


                    mN(); 


                    mT(); 


                    mA(); 


                    mI(); 


                    mN(); 


                    mS(); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BINARY_OPERATOR_T"

    // $ANTLR start "LEFT_BRACE"
    public final void mLEFT_BRACE() throws RecognitionException {
        try {
            int _type = LEFT_BRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:151:13: ( '(' )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:151:15: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LEFT_BRACE"

    // $ANTLR start "RIGHT_BRACE"
    public final void mRIGHT_BRACE() throws RecognitionException {
        try {
            int _type = RIGHT_BRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:152:14: ( ')' )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:152:16: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RIGHT_BRACE"

    // $ANTLR start "FILED_T"
    public final void mFILED_T() throws RecognitionException {
        try {
            int _type = FILED_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:153:11: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ ( '\\.' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:153:13: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ ( '\\.' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:153:13: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:153:46: ( '\\.' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='.') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:153:47: '\\.'
                    {
                    match('.'); 

                    }
                    break;

            }


            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:153:53: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '0' && LA4_0 <= '9')||(LA4_0 >= 'A' && LA4_0 <= 'Z')||LA4_0=='_'||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FILED_T"

    // $ANTLR start "VALUE_T"
    public final void mVALUE_T() throws RecognitionException {
        try {
            int _type = VALUE_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:154:11: ( '\"' (~ ( '\"' ) )* '\"' | ( '-' | '\\.' | '0' .. '9' )+ )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\"') ) {
                alt7=1;
            }
            else if ( ((LA7_0 >= '-' && LA7_0 <= '.')||(LA7_0 >= '0' && LA7_0 <= '9')) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:154:13: '\"' (~ ( '\"' ) )* '\"'
                    {
                    match('\"'); 

                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:154:16: (~ ( '\"' ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0 >= '\u0000' && LA5_0 <= '!')||(LA5_0 >= '#' && LA5_0 <= '\uFFFF')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    match('\"'); 

                    }
                    break;
                case 2 :
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:154:29: ( '-' | '\\.' | '0' .. '9' )+
                    {
                    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:154:29: ( '-' | '\\.' | '0' .. '9' )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0 >= '-' && LA6_0 <= '.')||(LA6_0 >= '0' && LA6_0 <= '9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
                    	    {
                    	    if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VALUE_T"

    // $ANTLR start "COMMA_T"
    public final void mCOMMA_T() throws RecognitionException {
        try {
            int _type = COMMA_T;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:155:11: ( ',' )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:155:13: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA_T"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:159:6: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:159:8: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:159:8: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= '\t' && LA8_0 <= '\n')||LA8_0=='\r'||LA8_0==' ') ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "A"
    public final void mA() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:161:11: ( ( 'a' | 'A' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "A"

    // $ANTLR start "B"
    public final void mB() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:162:11: ( ( 'b' | 'B' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "B"

    // $ANTLR start "C"
    public final void mC() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:163:11: ( ( 'c' | 'C' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "C"

    // $ANTLR start "D"
    public final void mD() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:164:11: ( ( 'd' | 'D' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "D"

    // $ANTLR start "E"
    public final void mE() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:165:11: ( ( 'e' | 'E' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "E"

    // $ANTLR start "F"
    public final void mF() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:166:11: ( ( 'f' | 'F' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "F"

    // $ANTLR start "G"
    public final void mG() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:167:11: ( ( 'g' | 'G' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "G"

    // $ANTLR start "H"
    public final void mH() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:168:11: ( ( 'h' | 'H' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "H"

    // $ANTLR start "I"
    public final void mI() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:169:11: ( ( 'i' | 'I' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "I"

    // $ANTLR start "J"
    public final void mJ() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:170:11: ( ( 'j' | 'J' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "J"

    // $ANTLR start "K"
    public final void mK() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:171:11: ( ( 'k' | 'K' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "K"

    // $ANTLR start "L"
    public final void mL() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:172:11: ( ( 'l' | 'L' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "L"

    // $ANTLR start "M"
    public final void mM() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:173:11: ( ( 'm' | 'M' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "M"

    // $ANTLR start "N"
    public final void mN() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:174:11: ( ( 'n' | 'N' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "N"

    // $ANTLR start "O"
    public final void mO() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:175:11: ( ( 'o' | 'O' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "O"

    // $ANTLR start "P"
    public final void mP() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:176:11: ( ( 'p' | 'P' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "P"

    // $ANTLR start "Q"
    public final void mQ() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:177:11: ( ( 'q' | 'Q' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Q"

    // $ANTLR start "R"
    public final void mR() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:178:11: ( ( 'r' | 'R' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "R"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:179:11: ( ( 's' | 'S' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "T"
    public final void mT() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:180:11: ( ( 't' | 'T' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T"

    // $ANTLR start "U"
    public final void mU() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:181:11: ( ( 'u' | 'U' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "U"

    // $ANTLR start "V"
    public final void mV() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:182:11: ( ( 'v' | 'V' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "V"

    // $ANTLR start "W"
    public final void mW() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:183:11: ( ( 'w' | 'W' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "W"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:184:11: ( ( 'x' | 'X' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "X"

    // $ANTLR start "Y"
    public final void mY() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:185:11: ( ( 'y' | 'Y' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Y"

    // $ANTLR start "Z"
    public final void mZ() throws RecognitionException {
        try {
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:186:11: ( ( 'z' | 'Z' ) )
            // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:
            {
            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Z"

    public void mTokens() throws RecognitionException {
        // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:8: ( SELECT_KEYWORD_T | FROM_KEYWORD_T | WHERE_KEYWORD_T | WITHIN_KEYWORD_T | AS_KEYWORD_T | AND_OPERATOR_T | OR_OPERATOR_T | NOT_OPERATOR_T | BETWEEN_OPERATOR_T | IN_OPERATOR_T | IS_OPERATOR_T | NULL_OPERATOR_T | BINARY_OPERATOR_T | LEFT_BRACE | RIGHT_BRACE | FILED_T | VALUE_T | COMMA_T | WS )
        int alt9=19;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:10: SELECT_KEYWORD_T
                {
                mSELECT_KEYWORD_T(); 


                }
                break;
            case 2 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:27: FROM_KEYWORD_T
                {
                mFROM_KEYWORD_T(); 


                }
                break;
            case 3 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:42: WHERE_KEYWORD_T
                {
                mWHERE_KEYWORD_T(); 


                }
                break;
            case 4 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:58: WITHIN_KEYWORD_T
                {
                mWITHIN_KEYWORD_T(); 


                }
                break;
            case 5 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:75: AS_KEYWORD_T
                {
                mAS_KEYWORD_T(); 


                }
                break;
            case 6 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:88: AND_OPERATOR_T
                {
                mAND_OPERATOR_T(); 


                }
                break;
            case 7 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:103: OR_OPERATOR_T
                {
                mOR_OPERATOR_T(); 


                }
                break;
            case 8 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:117: NOT_OPERATOR_T
                {
                mNOT_OPERATOR_T(); 


                }
                break;
            case 9 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:132: BETWEEN_OPERATOR_T
                {
                mBETWEEN_OPERATOR_T(); 


                }
                break;
            case 10 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:151: IN_OPERATOR_T
                {
                mIN_OPERATOR_T(); 


                }
                break;
            case 11 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:165: IS_OPERATOR_T
                {
                mIS_OPERATOR_T(); 


                }
                break;
            case 12 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:179: NULL_OPERATOR_T
                {
                mNULL_OPERATOR_T(); 


                }
                break;
            case 13 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:195: BINARY_OPERATOR_T
                {
                mBINARY_OPERATOR_T(); 


                }
                break;
            case 14 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:213: LEFT_BRACE
                {
                mLEFT_BRACE(); 


                }
                break;
            case 15 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:224: RIGHT_BRACE
                {
                mRIGHT_BRACE(); 


                }
                break;
            case 16 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:236: FILED_T
                {
                mFILED_T(); 


                }
                break;
            case 17 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:244: VALUE_T
                {
                mVALUE_T(); 


                }
                break;
            case 18 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:252: COMMA_T
                {
                mCOMMA_T(); 


                }
                break;
            case 19 :
                // E:\\project_sourcecode\\Antlr\\grammar\\LogKVQuery.g:1:260: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA9_eotS =
        "\1\uffff\10\20\1\uffff\2\20\2\uffff\1\20\4\uffff\5\20\1\51\1\20"+
        "\1\53\3\20\1\57\1\60\11\20\1\uffff\1\71\1\uffff\1\72\2\20\2\uffff"+
        "\5\20\1\101\2\20\2\uffff\1\104\5\20\1\uffff\1\112\1\20\1\uffff\3"+
        "\20\1\117\1\20\1\uffff\1\121\3\20\1\uffff\1\20\1\uffff\1\126\3\20"+
        "\1\uffff\2\11\1\20\1\11";
    static final String DFA9_eofS =
        "\133\uffff";
    static final String DFA9_minS =
        "\1\11\1\105\1\122\1\110\1\116\1\122\1\117\1\105\1\116\1\uffff\1"+
        "\116\1\117\2\uffff\1\55\4\uffff\1\114\1\101\1\117\1\105\1\124\1"+
        "\56\1\104\1\56\1\124\1\114\1\124\2\56\1\104\1\116\2\55\1\105\1\122"+
        "\1\115\1\122\1\110\1\uffff\1\56\1\uffff\1\56\1\114\1\127\2\uffff"+
        "\1\123\1\124\1\55\1\103\1\124\1\56\1\105\1\111\2\uffff\1\56\1\105"+
        "\1\127\1\101\1\124\1\123\1\uffff\1\56\1\116\1\uffff\1\105\2\111"+
        "\1\56\1\127\1\uffff\1\56\1\116\1\124\1\116\1\uffff\1\111\1\uffff"+
        "\1\56\1\110\1\123\1\124\1\uffff\2\56\1\110\1\56";
    static final String DFA9_maxS =
        "\1\172\1\164\1\162\1\151\1\163\1\162\1\165\1\145\1\163\1\uffff\1"+
        "\156\1\157\2\uffff\1\71\4\uffff\1\154\1\141\1\157\1\145\1\164\1"+
        "\172\1\144\1\172\1\164\1\154\1\164\2\172\1\144\1\156\2\71\1\145"+
        "\1\162\1\155\1\162\1\150\1\uffff\1\172\1\uffff\1\172\1\154\1\167"+
        "\2\uffff\1\163\1\164\1\71\1\143\1\164\1\172\1\145\1\151\2\uffff"+
        "\1\172\1\145\1\167\1\141\1\164\1\163\1\uffff\1\172\1\156\1\uffff"+
        "\1\145\2\151\1\172\1\167\1\uffff\1\172\1\156\1\164\1\156\1\uffff"+
        "\1\151\1\uffff\1\172\1\150\1\163\1\164\1\uffff\2\172\1\150\1\172";
    static final String DFA9_acceptS =
        "\11\uffff\1\15\2\uffff\1\16\1\17\1\uffff\1\21\1\20\1\22\1\23\26"+
        "\uffff\1\5\1\uffff\1\7\3\uffff\1\12\1\13\10\uffff\1\6\1\10\6\uffff"+
        "\1\2\2\uffff\1\14\5\uffff\1\3\4\uffff\1\1\1\uffff\1\4\4\uffff\1"+
        "\11\4\uffff";
    static final String DFA9_specialS =
        "\133\uffff}>";
    static final String[] DFA9_transitionS = {
            "\2\22\2\uffff\1\22\22\uffff\1\22\1\11\1\17\5\uffff\1\14\1\15"+
            "\2\uffff\1\21\2\17\1\uffff\12\16\2\uffff\3\11\2\uffff\1\4\1"+
            "\7\1\13\1\20\1\12\1\2\2\20\1\10\4\20\1\6\1\5\3\20\1\1\3\20\1"+
            "\3\3\20\4\uffff\1\20\1\uffff\1\4\1\7\1\13\1\20\1\12\1\2\2\20"+
            "\1\10\4\20\1\6\1\5\3\20\1\1\3\20\1\3\3\20",
            "\1\23\16\uffff\1\24\20\uffff\1\23\16\uffff\1\24",
            "\1\25\37\uffff\1\25",
            "\1\26\1\27\36\uffff\1\26\1\27",
            "\1\31\4\uffff\1\30\32\uffff\1\31\4\uffff\1\30",
            "\1\32\37\uffff\1\32",
            "\1\33\5\uffff\1\34\31\uffff\1\33\5\uffff\1\34",
            "\1\35\37\uffff\1\35",
            "\1\36\4\uffff\1\37\32\uffff\1\36\4\uffff\1\37",
            "",
            "\1\40\37\uffff\1\40",
            "\1\41\37\uffff\1\41",
            "",
            "",
            "\1\17\1\42\1\uffff\12\43",
            "",
            "",
            "",
            "",
            "\1\44\37\uffff\1\44",
            "\1\45\37\uffff\1\45",
            "\1\46\37\uffff\1\46",
            "\1\47\37\uffff\1\47",
            "\1\50\37\uffff\1\50",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\52\37\uffff\1\52",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\54\37\uffff\1\54",
            "\1\55\37\uffff\1\55",
            "\1\56\37\uffff\1\56",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\61\37\uffff\1\61",
            "\1\62\37\uffff\1\62",
            "\2\17\1\uffff\12\63",
            "\1\17\1\42\1\uffff\12\43",
            "\1\64\37\uffff\1\64",
            "\1\65\37\uffff\1\65",
            "\1\66\37\uffff\1\66",
            "\1\67\37\uffff\1\67",
            "\1\70\37\uffff\1\70",
            "",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\73\37\uffff\1\73",
            "\1\74\37\uffff\1\74",
            "",
            "",
            "\1\75\37\uffff\1\75",
            "\1\76\37\uffff\1\76",
            "\2\17\1\uffff\12\63",
            "\1\77\37\uffff\1\77",
            "\1\100\37\uffff\1\100",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\102\37\uffff\1\102",
            "\1\103\37\uffff\1\103",
            "",
            "",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\105\37\uffff\1\105",
            "\1\106\37\uffff\1\106",
            "\1\107\37\uffff\1\107",
            "\1\110\37\uffff\1\110",
            "\1\111\37\uffff\1\111",
            "",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\113\37\uffff\1\113",
            "",
            "\1\114\37\uffff\1\114",
            "\1\115\37\uffff\1\115",
            "\1\116\37\uffff\1\116",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\120\37\uffff\1\120",
            "",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\122\37\uffff\1\122",
            "\1\123\37\uffff\1\123",
            "\1\124\37\uffff\1\124",
            "",
            "\1\125\37\uffff\1\125",
            "",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\127\37\uffff\1\127",
            "\1\130\37\uffff\1\130",
            "\1\131\37\uffff\1\131",
            "",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\132\37\uffff\1\132",
            "\1\20\1\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20"
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
            return "1:1: Tokens : ( SELECT_KEYWORD_T | FROM_KEYWORD_T | WHERE_KEYWORD_T | WITHIN_KEYWORD_T | AS_KEYWORD_T | AND_OPERATOR_T | OR_OPERATOR_T | NOT_OPERATOR_T | BETWEEN_OPERATOR_T | IN_OPERATOR_T | IS_OPERATOR_T | NULL_OPERATOR_T | BINARY_OPERATOR_T | LEFT_BRACE | RIGHT_BRACE | FILED_T | VALUE_T | COMMA_T | WS );";
        }
    }
 

}