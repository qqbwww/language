package parsing.lexer;

/******************************
 *   date: 2013-6-17 下午01:04:06
 * author: qb.qian
 *****************************************/
public class Token {
    
    public int type;
    public String text;
    public Token(int type,String text){
        this.type = type;
        this.text = text;
    }
    
    public String toString(){
        String tname = ListLexer.tokenNames[type];
        return "<'" + text + "''," + tname +">";
    }
}
