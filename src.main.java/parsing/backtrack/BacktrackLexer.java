package parsing.backtrack;

/******************************
 *   date: 2013-6-20 下午04:34:05
 * author: qb.qian
 *****************************************/
public class BacktrackLexer extends Lexer{
    public static int NAME = 2;
    public static int COMMA = 3;
    public static int LBRACK = 4;
    public static int RBRACK = 5;
    public static int EQUALS = 6;
    public static String[] tokenNames = {
        "n/a", "<EOF>","NAME", "," ,"[", "]", "="
    };
    
    public String getTokenName(int x){
        return BacktrackLexer.tokenNames[x];
    }
    
    public BacktrackLexer(String input){
        super(input);
    }
    boolean isLETTER(){
        return c > 'a' && c < 'z' || c > 'A' && c < 'Z';
    }
    
    public Token nextToken(){
        while( c != EOF ){
            switch( c ){
                case ' ':case '\t': case '\n': case '\r': WS(); continue;
                case ',':consume(); return new Token(COMMA,",");
                case '[':consume(); return new Token(LBRACK,"[");
                case ']':consume(); return new Token(RBRACK,"]");
                case '=':consume(); return new Token(EQUALS,"=");
                default:
                    if ( isLETTER() )
                        return name();
                    throw new Error("invalid character: " + c);
            }
        }
        return new Token(EOF_TYPE,"<EOF");
    }
    
    Token name(){
        StringBuilder buf = new StringBuilder();
        while ( isLETTER() ){
            buf.append(c);
            LETTER();
        }
        return new Token(NAME,buf.toString());
    }
    
    void LETTER(){
        if( isLETTER())
            consume();
        else throw new Error("expectiong LETTER; found " + c );
    }

    void WS() {
        while ( c == ' ' || c == '\t' || c == '\n' || c == '\r')
            advance();
    }
}
