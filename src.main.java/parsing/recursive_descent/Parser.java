package parsing.recursive_descent;

import parsing.lexer.Lexer;
import parsing.lexer.Token;

/******************************
 *   date: 2013-6-20 下午01:13:03
 * author: qb.qian
 *****************************************/
public abstract class Parser {
    Lexer input;
    Token lookahead;
    
    public Parser(Lexer input){
        this.input = input;
        this.lookahead = input.nextToken();
    }
    
    /** 如果向前看词法类型能匹配x,那么就忽略并返回;否则报错 */
    public void match(int x){
        if(lookahead.type == x )
            consume();
        else
            throw new Error("expecting " + input.getTokenName(x) + "; found " + lookahead);
        
    }
    
    public void consume(){
        lookahead = input.nextToken();
    }
}
