package parsing.memorize;

/******************************
 *   date: 2013-6-24 下午01:20:50
 * author: qb.qian
 *****************************************/
public class BacktrackLexer extends Lexer{
    public static final int NAME = 2;
    public static final int COMMA  = 3;
    public static final int LBRACK = 4;
    public static final int RBRACK = 5;
    public static final int EQUALS = 6;
    public static String[] tokenNames = {
      "n/a","<EOF>","NAME",";","[","]","="      
    };

    /**
     * @param input
     */
    public BacktrackLexer(String input) {
        super(input);
    }

    @Override
    void WS() {
        while( c == ' ' || c == '\n' || c == '\t' || c == '\r' )
            advance();
    }

    @Override
    public String getTokenName(int x) {
        return BacktrackLexer.tokenNames[x];
    }

    @Override
    public Token nextToken() {
        while ( c != EOF){
            switch (c){
                case ' ': case '\t': case '\r': case '\n' : WS();continue;
                case ',': consume(); return new Token(COMMA,",");
                case '[': consume(); return new Token(LBRACK,"[");
                case ']': consume(); return new Token(RBRACK,"]");
                case '=': consume(); return new Token(EQUALS,"=");
                default:
                    if (isLETTER())
                        return name();
                    else
                        throw new Error("invalid character" + c);
            }
        }
        return new Token(EOF,"<EOF>");
    }
    
    Token name(){
        StringBuilder sb = new StringBuilder();
        while ( isLETTER()){
            sb.append(c);
            LETTER();
        }
        return new Token(NAME,sb.toString());
    }
    
    void LETTER(){
        if(isLETTER ())
            consume();
        else
            throw new Error("expected LETTER,found " + c);
    }
    
    boolean isLETTER(){
        return ( c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z');
    }

}
