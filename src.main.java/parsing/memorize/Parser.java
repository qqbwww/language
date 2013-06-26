package parsing.memorize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/******************************
 *   date: 2013-6-24 上午09:56:36
 * author: qb.qian
 *****************************************/
public abstract class Parser {
    
    public static final int FAILED = -1;    //表示上一次解析失败
    Lexer input;
    List<Integer> marks;
    List<Token> lookahead;
    int p = 0;
    
    public Parser(Lexer input){
        this.input = input;
        this.marks = new ArrayList<Integer>();
        this.lookahead = new ArrayList<Token>();
        sync(1);
    }
    
    public void consume(){
        p++;
        if( p == lookahead.size() && !isSpeculating()){
            p = 0;
            lookahead.clear();
            clearMemo();
        }
        sync(1);   
    }
    
    public void sync(int i){
        if( p+i-1 > ( lookahead.size() - 1)){
            int n = (p+i-1) - (lookahead.size()-1);
            fill(n);
        }
    }
    
    public void fill(int n){
        for(int i = 0;i < n ;i++){
            lookahead.add(input.nextToken());
        }
    }
    
    
    public Token LT(int i){
        sync(i);
        return lookahead.get(p+i-1);
    }
    
    public int LA(int i){
        return LT(i).type;
    }
    
    public void match(int x) throws MismatchedTokenException{
        if( LA(1) == x)
            consume();
        else 
            throw new MismatchedTokenException("expecting "+
                    input.getTokenName(x)+" found "+LT(1));
    }
    
    public abstract void clearMemo();
    
    public int mark(){
        marks.add(p);
        return p;
    }
    
    public void release(){
        int mark = marks.get(marks.size() - 1);
        marks.remove(marks.size() - 1);
        seek(mark);
    }
    
    public void seek(int index){
        p = index;
    }
    
    boolean isSpeculating(){
        return marks.size() > 0;
    }
    
    /**
     * 在当前输入位置解析过这个规则么，
     * 如果查不到相关的记录，那么没有解析过
     * 如果返回值是FAILED，那么上次解析失败，
     * 如果返回值是0或1，这是词法单元缓冲区的下标，表示解析成功
     * 该方法有副作用：
     * 如果不用重新解析，该方法会把缓冲区的下标向前移动，以避免重新解析
     * @param memorization
     * @return
     * @throws PreviousParseFailedException 
     */
    public boolean alreadyParsedRule(Map<Integer,Integer> memorization) throws PreviousParseFailedException{
        Integer memoI = memorization.get(index());
        if( memoI == null )
            return false;
        int memo = memoI.intValue();
        System.out.println("parse list before at index " + index() +
                "; skip ahead to token index " + memo + ":" +
                lookahead.get(memo).text);
        if( memo == -1)
            throw new PreviousParseFailedException();
        //否则跳过去，就好像解析过一样
        seek(memo);
        return true;
    }
    
    /**
     * 回溯时，记忆解析的中间结果，
     * 如果解析失败，要记录下来
     * 如果解析成功，则下次对同一规则进行解析时跳过，要记录该跳到哪里
     * @param memorization
     */
    public void memorize(Map<Integer,Integer> memorization,int startTokenIndex,boolean failure){
        int stopTokenIndex = failure==true?FAILED:index();
        memorization.put(startTokenIndex, stopTokenIndex);
    }
    
    /**
     * 返回当前输入流的位置
     * @return
     */
    public int index(){
        return p;
    }

}
