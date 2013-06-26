package parsing.multi;

import parsing.lexer.Token;

/******************************
 *   date: 2013-6-20 下午01:36:51
 * author: qb.qian
 *****************************************/
public class Parser {
    Lexer input;
    Token[] lookahead;
    Integer k;       //数组长度
    Integer p = 0;   //当前所在数组位置
    
    
    public Parser(Lexer input,int k){
        this.input = input;
        this.k = k;
        lookahead = new Token[k];
        for (int i=1;i<=k;i++)
            consume();
    }
    
    public void consume(){
        lookahead[p] = input.nextToken();
        p = (p+1) % k;
    }
    
    /**
     * 环式取值,返回第i个向前看词法单元
     * @param i
     * @return
     */
    public Token LT(int i){
        return lookahead[(p+i-1) % k];
    }
    /**
     * 返回第i个向前看词法单元的类型
     * @param i
     * @return
     */
    public int LA(int i){
        return LT(i).type ;
    }
    
    public void match(int x){
        if ( LA(1) == x )
            consume();
        else
            throw new Error("expecting " + input.getTokenName(x) + "; found " + LT(1));
    }
}
