package parsing.multi;


/******************************
 *   date: 2013-6-20 下午02:13:58
 * author: qb.qian
 *****************************************/
public class Test {
    
    
    public static void main(String[] args) {
        LookaheadLexer lexer = new LookaheadLexer(args[0]);
        LookaheadParser parser = new LookaheadParser(lexer, 2);
        parser.list();  
    }
}
