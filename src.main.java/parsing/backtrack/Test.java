package parsing.backtrack;

/******************************
 *   date: 2013-6-20 下午05:12:41
 * author: qb.qian
 *****************************************/
public class Test {
    
    public static void main(String[] args) throws RecognitionException{
        BacktrackLexer lexer = new BacktrackLexer(args[0]);
        BacktrackParser parser = new BacktrackParser(lexer);
        parser.stat();
    }
}
