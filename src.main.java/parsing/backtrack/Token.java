package parsing.backtrack;

/******************************
 *   date: 2013-6-20 下午04:25:05
 * author: qb.qian
 *****************************************/
public class Token {
    public int type;
    public String text;
    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }
    public String toString(){
        String tname = BacktrackLexer.tokenNames[type];
        return "<'" + text + "'," + tname +  ">";
    }
}
