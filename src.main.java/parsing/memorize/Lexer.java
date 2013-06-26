package parsing.memorize;

/******************************
 *   date: 2013-6-24 上午10:36:18
 * author: qb.qian
 *****************************************/
public abstract class Lexer {
    
    public static final int EOF = (char)-1;
    public static final int EOF_TYPE = 1;
    
    String input;
    int i;
    char c;
    
    public Lexer(String input){
        this.input = input;
        c = input.charAt(i);
    }
    
    public void consume(){
        advance();
    }
    
    public void advance(){
        i++;
        if( i >= input.length() )
            c = EOF;
        else
            c = input.charAt(i);
    }
    
    public void match(char x){
        if( c == x )
            consume();
        else
            throw new Error("expected " + x +";found " + c);
    }
    
    
    abstract void WS();
    public abstract Token nextToken();
    public abstract String getTokenName(int x);
    
    
    
}
