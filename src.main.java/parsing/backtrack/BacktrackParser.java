package parsing.backtrack;

/******************************
 *   date: 2013-6-20 下午04:01:51
 * author: qb.qian
 *****************************************/
public class BacktrackParser extends Parser{

    public BacktrackParser(Lexer input){
        super(input);
    }
    /** stat: list EOF | assign EOF ; */
    public void stat() throws RecognitionException{
        //尝试解析选项1: list EOF
        if( speculate_stat_alt1() ){
            list();
            match(Lexer.EOF_TYPE);
        }
        else if( speculate_state_alt2() ){
            assign(); match(Lexer.EOF_TYPE);
        }
        else throw new NoViableAltException("expecting stat found " + LT(1) );
    }
    
    public boolean speculate_stat_alt1(){
        boolean success = true;
        mark();
        try{
            list();
            match(Lexer.EOF_TYPE);
        }catch(RecognitionException e){
            success = false;
        }
        return success;
    }
    
    public boolean speculate_state_alt2(){
        boolean success = true;
        mark();
        try{
            assign();
            match(Lexer.EOF_TYPE);
        }catch(RecognitionException e){
            success = false;
        }
        return success;
    }
    
    /** assign : list '=' list; //parallel assignment */
    public void assign() throws RecognitionException{
        list();
        match(BacktrackLexer.EQUALS);
        list();
    }
    
    public void list() throws RecognitionException{
        match(BacktrackLexer.LBRACK);
        elements();
        match(BacktrackLexer.RBRACK);
    }
    
    void elements() throws RecognitionException{
        element();
        while ( LA(1) == BacktrackLexer.COMMA){
            match(BacktrackLexer.COMMA);
        }
        element();
    }
    
    void element() throws RecognitionException{
        if ( LA(1) == BacktrackLexer.NAME && LA(2) == BacktrackLexer.EQUALS){
            match(BacktrackLexer.NAME);
            match(BacktrackLexer.EQUALS);
            match(BacktrackLexer.NAME);
        }
        else if(LA(1) == BacktrackLexer.NAME )
            match(BacktrackLexer.NAME);
        else if( LA(1) == BacktrackLexer.LBRACK )
            list();
        else throw new NoViableAltException("expectiong element found " + LT(1));
    }
}
