package parsing.lexer;

/******************************
 *   嵌套列表文法构建词法解析器
 * author: qb.qian
 *****************************************/
public class Test {
    
    
    public static void main(String[] args) {
        ListLexer lexer = new ListLexer(args[0]);
        Token t= lexer.nextToken();
        while ( t.type != Lexer.EOF_TYPE){
            System.out.println(t);
            t = lexer.nextToken();
        }
        
        /**
         * 
         */
        System.out.println(t);   //EOF
    }
}
