package parsing.recursive_descent;

import parsing.lexer.Lexer;
import parsing.lexer.ListLexer;

/******************************
 *   date: 2013-6-20 下午01:06:10
 * author: qb.qian
 *****************************************/
public class ListParser extends Parser{

    public ListParser(Lexer input){
        super(input);
    }
    
    /** list: '[' element ']' ;//匹配方括号 */
    public void list(){
        match(ListLexer.LBRACK);
        elements();
        match(ListLexer.RBRACK);
    }
    
    /** elements : element (',' element) *; */
    void elements(){
        element();
        while ( lookahead.type == ListLexer.COMMA){
            match(ListLexer.COMMA);
            element();
        }
    }
    
    /** element:name|list; //一个element要么是NAME,要么是嵌套的列表 */
    void element(){
        if ( lookahead.type == ListLexer.NAME )
            match(ListLexer.NAME);
        else if(lookahead.type == ListLexer.LBRACK)
            list();
        else
            throw new Error("expecting name or list; found " + lookahead );
    }
}
