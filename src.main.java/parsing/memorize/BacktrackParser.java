package parsing.memorize;

import java.util.HashMap;
import java.util.Map;

/******************************
 *   date: 2013-6-24 上午09:43:21
 * author: qb.qian
 *****************************************/
public class BacktrackParser extends Parser{
    
    Map<Integer,Integer> list_memo = new HashMap<Integer,Integer>();
    
    public BacktrackParser(Lexer input) {
        super(input);
    }
    
    public void clearMemo(){
        list_memo.clear();
    }
    
    public void stat() throws RecognitionException{
        if( speculate_stat_alt1()){
            list(); match(Lexer.EOF_TYPE);
        }else if( speculate_stat_alt2()){
            assign();   match(Lexer.EOF_TYPE);
        }
        else throw new NoViableAltException("expecting stat found "+LT(1));
    }
    
    public boolean speculate_stat_alt1() {
        System.out.println("attempt alternative 1");
        boolean success = true;
        mark(); // mark this spot in input so we can rewind
        try { list(); match(Lexer.EOF_TYPE); }
        catch (RecognitionException e) { success = false; }
        release(); // either way, rewind to where we were
        return success;
    }
    
    public boolean speculate_stat_alt2() {
        System.out.println("attempt alternative 2");
        boolean success = true;
        mark(); // mark this spot in input so we can rewind
        try { assign(); match(Lexer.EOF_TYPE); }
        catch (RecognitionException e) { success = false; }
        release(); // either way, rewind to where we were
        return success;
    }
    
    public void assign() throws RecognitionException {
        list(); match(BacktrackLexer.EQUALS); list();
    }

    public void _list() throws RecognitionException{
        System.out.println("parser list rule at index()" + index() );
        match(BacktrackLexer.LBRACK);
        elements();
        match(BacktrackLexer.RBRACK);
    }
    
    public void list() throws RecognitionException{
        boolean failed = false;
        int startTokenIndex = index(); //获取当前词法单元位置
        if( isSpeculating() && alreadyParsedRule(list_memo))
            return;
        //之前没有在TokenIndex解析过，现在解析
        try{
            _list();
        }catch(RecognitionException e){
            failed = true;
            throw e;
        }finally{
            //回溯时，不管解析是否成功，都必须记录解析结果
            if ( isSpeculating())
                memorize(list_memo,startTokenIndex,failed);
        }
        
    }

    
    /** elements : element (',' element)* ; // match comma-separated list */
    void elements() throws RecognitionException {
        element(); while ( LA(1)== BacktrackLexer.COMMA ) { match(BacktrackLexer.COMMA); element(); }
    }

    /** element : name '=' NAME | NAME | list ; // assignment, name or list */
    void element() throws RecognitionException {
        if ( LA(1)==BacktrackLexer.NAME && LA(2)==BacktrackLexer.EQUALS) {
            match(BacktrackLexer.NAME);
            match(BacktrackLexer.EQUALS);
            match(BacktrackLexer.NAME);
        }
        else if ( LA(1)==BacktrackLexer.NAME ) match(BacktrackLexer.NAME);
        else if ( LA(1)==BacktrackLexer.LBRACK ) list();
        else throw new NoViableAltException("expecting element found "+LT(1));
    }
}
