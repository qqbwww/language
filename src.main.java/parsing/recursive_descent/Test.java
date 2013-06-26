package parsing.recursive_descent;

import parsing.lexer.ListLexer;

/******************************
 *   date: 2013-6-20 下午01:23:08
 * author: qb.qian
 *****************************************/
public class Test {

    public static void main(String[] args) {
        ListLexer lexer = new ListLexer(args[0]);
        ListParser parser = new ListParser(lexer);
        parser.list();
    }
}
