package parsing.memorize;

/******************************
 *   date: 2013-6-24 上午10:25:13
 * author: qb.qian
 *****************************************/
public class Token {

    int type;
    String text;
    
    public Token(int type,String text){
        this.type = type;
        this.text = text;
    }
    
    public String toString(){
        String tname = BacktrackLexer.tokenNames[type];
        return "<" + text +":" + tname + ">";
    }
}
