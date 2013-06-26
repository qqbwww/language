package parsing.multi;

import parsing.lexer.Token;

/******************************
 *   date: 2013-6-20 下午02:32:27
 * author: qb.qian
 *****************************************/
public class LookaheadLexer extends Lexer{
    public static int NAME = 2;
    public static int COMMA = 3;
    public static int LBRACK = 4;
    public static int RBRACK = 5;
    public static int EQUALS = 6;
    public static String[] tokenNames = {
        "n/a","<EOF>","NAME",",","[","]","="
    };
    

    /**
     * @param input
     */
    public LookaheadLexer(String input) {
        super(input);
    }

    @Override
    void WS() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getTokenName(int x) {
        return LookaheadLexer.tokenNames[x];
    }

    @Override
    public Token nextToken() {
        while ( c != EOF ){
            switch ( c ){
            case ' ': case '\t': case '\n': case '\r': WS(); continue;
            case ',': consume(); return new Token(COMMA,  ",");
            case '[': consume(); return new Token(LBRACK, "[");
            case ']': consume(); return new Token(RBRACK, "]");
            case '=': consume(); return new Token(EQUALS, "=");
            default:
                if ( isLETTER() ) return name();
                throw new Error("invalid character: " +c );
            }
        }
        return new Token(EOF_TYPE,"<EOF>");
    }
    
    Token name(){
        StringBuilder buf = new StringBuilder();
        do{
            buf.append(c);
            LETTER();
        }while(isLETTER());
        return new Token(NAME, buf.toString());
    }
    
    void LETTER(){
        if( isLETTER() )
            consume();
        else throw new Error("expecting LETTER; found " + c );
    }
    
    
    boolean isLETTER(){
        return c >= 'a' && c <='z' || c >='A' && c <= 'Z';  
    }
}
