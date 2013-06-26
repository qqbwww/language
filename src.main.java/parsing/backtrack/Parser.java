package parsing.backtrack;

import java.util.ArrayList;
import java.util.List;


/******************************
 *   date: 2013-6-20 下午04:06:48
 * author: qb.qian
 *****************************************/
public class Parser {
    
    Lexer input;
    int p = 0;
    List<Integer> markers;
    List<Token> lookahead;
   
    public Parser(Lexer input){
        this.input = input;
        markers = new ArrayList<Integer>();
        lookahead = new ArrayList<Token>();
        sync(1);
    }
    
    
    public Token LT(int i){
        sync(i);
        return lookahead.get(p+i-1);
    }
    
    public int LA(int i) {
        return LT(i).type;
    }
    
    public void match(int x) throws MismatchedTokenException {
        if ( LA(1) == x )
            consume();
        else
            throw new MismatchedTokenException("expecting " + input.getTokenName(x) + " found " + LT(1));
    }
    
    /**
     * 确保当前位置p之前有i个词法单元
     * @param i
     */
    public void sync(int i){
       if( p+i-1 > (lookahead.size() - 1)){
           int n = (p+i-1) - (lookahead.size() - 1);
           fill(n);
       }
    }
    
    public void fill(int n){
        for ( int i = 1; i <= n; i++){
            lookahead.add(input.nextToken());
        }
    }
    
    public void consume(){
        p++;
        //非推断状态，而且到达向前看缓冲区的末尾
        if( p == lookahead.size() && !isSpeculating() ){
            //到了末尾，就该重新从0开始填入新的词法单元
            p = 0 ;
            lookahead.clear();  //大小清零，但不回收内存
        }
        sync(1);
    }
    
    public int mark(){
        markers.add(p);
        return p;
    }
    
    public void release(){
        int marker = markers.get(markers.size() - 1);
        markers.remove(markers.size() - 1);
        seek(marker);
    }
    
    public void seek(int index){
        p = index;
    }
    
    public boolean isSpeculating(){
        return markers.size() > 0 ;
    }

}
