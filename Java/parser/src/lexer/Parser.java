package lexer;

/**
    解析类父类
 */
public class Parser {
    //输入的词法单元
    Lexer input;
    //环形缓冲区
    Token[] lookahead;
    int k;
    int p = 0;

    public Parser(Lexer input,int k){
        this.input = input;
        this.k = k;
        lookahead = new Token[k];  //开辟向前看缓冲区
        for(int i=0;i<k;i++){       //用K个向前看符号初始化缓冲区
            consume();
        }
    }

    public void consume(){
        lookahead[p] = input.nextToken(); //在下一个位置上放入词法单元
        p = (p+1) % k;                      //自增下标
    }

    public Token LT(int i){
        return lookahead[(p+i-1) % k]; //环式取值
    }
    public int LA(int i){
        return LT(i).type;
    }

    public void  match(int x){
        if (LA(1) == x)
            consume();
        else throw new Error("exception " + input.getTokenName(x) + "; found " + LT(1));
    }





/**    LL(1)
    //当前的向前看符号
    //Token lookahead;
//    public void match(int x){
//        if(lookahead.type == x)
//            consume();
//        else
//            throw new Error("exception " + input.getTokenName(x) + ";found" + lookahead);
//    }
//
//    public void consume(){
//        lookahead = input.nextToken();
//    }
 **/
}
