package lexer;

/**
 * Created with IntelliJ IDEA.
 * User: qqbwww
 * Date: 12-11-6
 * Time: 上午12:11
 * To change this template use File | Settings | File Templates.
 */
public class LookaheadLexer extends Lexer {
    //标识符  数组索引
    public static int NAME = 2;
    //逗号   数组索引
    public static int COMMA = 3;
    //左括号 数组索引
    public static int LBRACK = 4;
    //右括号  数组索引
    public static int RBRACK = 5;
    //词法单元名数组
    public static String[] tokenNames = {"n/a","EOF","NAME","COMMA","LBRACK","RBTACK"};
    /** NAME:('a' ..'z'|'A'..'Z')+ //NAME由一个或多个字母组成 */

    //获得词法单元名
    public String getTokenName(int x){
        return tokenNames[x];
    }
    public LookaheadLexer(String input){
        super(input);
    }
    //判断字母是不是
    boolean isLETTER(){
        return c>='a' && c<='z' || c>='A' && c<='Z';
    }

    public Token nextToken(){
        while( c!= EOF ){
            switch (c){
                case ' ': case '\t': case '\n': case '\r': WS();continue;
                case ',':consume();return new Token(COMMA,",");
                case '[':consume();return new Token(LBRACK,"[");
                case ']':consume();return new Token(RBRACK,"]");
                default:
                    if(isLETTER()) return NAME();
                    throw new Error("invalid character: "+c);
            }
        }
        return new Token(EOF_TYPE,"EOF");
    }

    Token NAME(){
        StringBuilder buf = new StringBuilder();
        do{
            buf.append(c);consume();
        }while(isLETTER());
        return new Token(NAME,buffer.toString());
    }

    /**
     * 跳过空格
     */
    void WS(){
        while( c==' ' || c=='\t' || c=='\n' || c=='\r') consume();
    }
}
